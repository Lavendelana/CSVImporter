package com.csv.splendo.csvparser;

import java.util.List;

public interface CsvCallback<T> {
    void onCsvParsingFinished(List<T> results);
    void onCsvParsingFailed();
}
