package com.csv.splendo.csvparser;

import com.csv.splendo.models.Person;

import org.apache.commons.csv.CSVRecord;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PersonCsvParser implements CsvResultParser<Person> {

    static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    static final String FIRST_NAME_TAG = "First name";
    static final String SUR_NAME_TAG = "Sur name";
    static final String ISSUES_COUNT_TAG = "Issue count";
    static final String DATE_OF_BIRTH_TAG = "Date of birth";

    public List<Person> parseCsv(List<CSVRecord> records) {
        List<Person> results = new ArrayList<>();

        for (CSVRecord record: records) {
            String firstName = record.get(FIRST_NAME_TAG);
            String lastName = record.get(SUR_NAME_TAG);
            int issueCount = Integer.parseInt(record.get(ISSUES_COUNT_TAG));
            DateFormat format = new SimpleDateFormat(DATE_FORMAT);
            Date dateOfBirth;
            try {
                dateOfBirth = format.parse(record.get(DATE_OF_BIRTH_TAG));
            } catch (ParseException exception) {
                dateOfBirth = null;
            }
            Person person = new Person(firstName, lastName, issueCount, dateOfBirth);
            results.add(person);
        }

        return results;
    }
}
