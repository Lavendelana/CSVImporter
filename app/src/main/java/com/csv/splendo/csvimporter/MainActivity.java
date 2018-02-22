package com.csv.splendo.csvimporter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.csv.splendo.adapters.CSVListAdapter;
import com.csv.splendo.csvparser.CSVCallback;
import com.csv.splendo.csvparser.CSVTaskParameters;
import com.csv.splendo.csvparser.PersonCSVParser;
import com.csv.splendo.models.Person;

import java.io.InputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CSVCallback<Person> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startCsvHandling();
    }

    private void startCsvHandling() {
        InputStream input = getResources().openRawResource(getResources().getIdentifier("issues", "raw", getPackageName()));
        PersonCSVParser parser = new PersonCSVParser();

        CSVTask<Person> task = new CSVTask<>(this);
        task.execute(new CSVTaskParameters<Person>(parser, input));
    }

    public void csvParsingFinished(List<Person> results) {
        ProgressBar progressBar = findViewById(R.id.csvProgressBar);
        progressBar.setVisibility(View.GONE);

        TextView loadingTextView = findViewById(R.id.loadingTextView);
        loadingTextView.setVisibility(View.GONE);

        CSVListAdapter adapter = new CSVListAdapter(this, results);

        ListView mainListView = findViewById(R.id.mainListView);
        mainListView.setVisibility(View.VISIBLE);
        mainListView.setAdapter(adapter);
    }
}
