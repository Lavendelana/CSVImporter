package com.csv.splendo.csvparser;

import android.os.AsyncTask;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.ref.WeakReference;
import java.util.List;

public class CsvTask<T> extends AsyncTask<CsvTaskParameters<T>, Integer, List<T>> {

    private final WeakReference<CsvCallback> callbackReference;

    public CsvTask(CsvCallback callback) {
        this.callbackReference = new WeakReference<>(callback);
    }

    @Override
    protected List<T> doInBackground(CsvTaskParameters<T>... parameters) {
        if (parameters.length <= 0) {
            return null;
        }
        CsvTaskParameters<T> handleParameters = parameters[0];
        InputStream inputStream = handleParameters.getResourceInputStream();
        if (inputStream == null) {
            return null;
        }

        List<CSVRecord> resultsList;

        try {
            Reader reader = new InputStreamReader(inputStream, "UTF-8");
            CSVParser parser = new CSVParser(reader, CSVFormat.EXCEL.withHeader());
            resultsList = parser.getRecords();
        } catch (IOException e) {
            return null;
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                return null;
            }
        }

        return handleParameters.getParser().parseCsv(resultsList);
    }

    @Override
    protected void onPostExecute(List<T> results) {
        CsvCallback callback = this.callbackReference.get();
        if (callback != null && results != null) {
            callback.onCsvParsingFinished(results);
        } else {
            callback.onCsvParsingFailed();
        }
    }
}
