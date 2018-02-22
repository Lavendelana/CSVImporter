package com.csv.splendo.csvimporter;

import android.os.AsyncTask;

import com.csv.splendo.csvparser.CSVCallback;
import com.csv.splendo.csvparser.CSVTaskParameters;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.ref.WeakReference;
import java.util.List;

public class CSVTask<T> extends AsyncTask<CSVTaskParameters<T>, Integer, List<T>> {

    private final WeakReference<CSVCallback> callbackReference;

    public CSVTask(CSVCallback callback) {
        this.callbackReference = new WeakReference<>(callback);
    }

    @Override
    protected List<T> doInBackground(CSVTaskParameters<T>... parameters) {
        if (parameters.length <= 0) {
            return null;
        }
        CSVTaskParameters<T> handleParameters = parameters[0];
        InputStream inputStream = handleParameters.inputStream;

        List<CSVRecord> resultsList;

        try {
            Reader reader = new InputStreamReader(inputStream, "UTF-8");
            CSVParser parser = new CSVParser(reader, CSVFormat.EXCEL.withHeader());
            resultsList = parser.getRecords();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        return handleParameters.parser.parseCsv(resultsList);
    }

    @Override
    protected void onPostExecute(List<T> results) {
        if (this.callbackReference.get() != null && results != null) {
            this.callbackReference.get().csvParsingFinished(results);
        }
    }
}
