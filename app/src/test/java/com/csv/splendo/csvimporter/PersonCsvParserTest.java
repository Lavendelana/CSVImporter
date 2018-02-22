package com.csv.splendo.csvimporter;

import com.csv.splendo.csvparser.PersonCsvParser;
import com.csv.splendo.models.Person;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersonCsvParserTest {

    List<CSVRecord> stubRecords = new ArrayList<>();

    @Before
    public void setup() {
        String inputString = "\"First name\",\"Sur name\",\"Issue count\",\"Date of birth\""
                + System.lineSeparator() + "\"row11\",\"row12\",\"20\",\"1978-01-02T00:00:00\""
                + System.lineSeparator() + "\"row21\",\"row22\",\"15\",\"2001-04-20T00:00:00\"";
        try {
            CSVParser parser = CSVParser.parse(inputString, CSVFormat.EXCEL.withHeader());
            stubRecords = parser.getRecords();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPersonParsing() {
        assertNotNull(stubRecords);
        PersonCsvParser parser = new PersonCsvParser();
        List<Person> results = parser.parseCsv(stubRecords);
        assertEquals(results.size(), 2);
    }
}
