package com.csv.splendo.csvparser;

import java.util.List;

public interface CSVCallback<T> {
    void csvParsingFinished(List<T> results);
}
