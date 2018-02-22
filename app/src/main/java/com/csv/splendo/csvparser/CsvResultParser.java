package com.csv.splendo.csvparser;

import org.apache.commons.csv.CSVRecord;

import java.util.List;

public interface CsvResultParser<T> {
    List<T> parseCsv(List<CSVRecord> records);
}
