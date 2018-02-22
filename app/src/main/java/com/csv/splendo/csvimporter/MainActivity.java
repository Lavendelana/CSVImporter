package com.csv.splendo.csvimporter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.csv.splendo.adapters.CsvListAdapter;
import com.csv.splendo.csvparser.CsvCallback;
import com.csv.splendo.csvparser.CsvTask;
import com.csv.splendo.csvparser.CsvTaskParameters;
import com.csv.splendo.csvparser.PersonCsvParser;
import com.csv.splendo.models.Person;

import java.util.List;

public class MainActivity extends AppCompatActivity implements CsvCallback<Person> {

    CsvTask<Person> task;
    ProgressBar progressBar;
    TextView loadingTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startCsvHandling();

        this.progressBar = findViewById(R.id.csvProgressBar);
        this.loadingTextView = findViewById(R.id.loadingTextView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        task.cancel(true);
    }

    private void startCsvHandling() {
        PersonCsvParser parser = new PersonCsvParser();

        this.task = new CsvTask<>(this);
        this.task.execute(new CsvTaskParameters<>(parser, R.raw.issues, getResources()));
    }

    public void onCsvParsingFinished(List<Person> results) {
        this.progressBar.setVisibility(View.GONE);
        this.loadingTextView.setVisibility(View.GONE);

        CsvListAdapter adapter = new CsvListAdapter(this, results);

        ListView mainListView = findViewById(R.id.mainListView);
        mainListView.setVisibility(View.VISIBLE);
        mainListView.setAdapter(adapter);
    }

    @Override
    public void onCsvParsingFailed() {
        this.progressBar.setVisibility(View.GONE);

        this.loadingTextView.setText(getText(R.string.csv_error_text));
    }
}
