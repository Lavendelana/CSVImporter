package com.csv.splendo.csvparser;

import java.io.InputStream;

public class CSVTaskParameters<T> {
    public CSVResultParser<T> parser;
    public InputStream inputStream;

    public CSVTaskParameters(CSVResultParser<T> parser, InputStream inputStream) {
        this.parser = parser;
        this.inputStream = inputStream;
    }
}
