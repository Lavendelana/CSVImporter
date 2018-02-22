package com.csv.splendo.csvparser;

import com.csv.splendo.models.Person;

import org.apache.commons.csv.CSVRecord;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PersonCSVParser implements CSVResultParser<Person> {

    public List<Person> parseCsv(List<CSVRecord> records) {
        List<Person> results = new ArrayList<>();

        for (CSVRecord record: records) {
            String firstName = record.get("First name");
            String lastName = record.get("Sur name");
            int issueCount = Integer.parseInt(record.get("Issue count"));
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date dateOfBirth;
            try {
                dateOfBirth = format.parse(record.get("Date of birth"));
            } catch (ParseException exception) {
                dateOfBirth = null;
            }
            Person person = new Person(firstName, lastName, issueCount, dateOfBirth);
            results.add(person);
        }

        return results;
    }
}
