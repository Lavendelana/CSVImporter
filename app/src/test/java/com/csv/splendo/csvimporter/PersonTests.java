package com.csv.splendo.csvimporter;

import com.csv.splendo.models.Person;

import org.junit.Test;

import java.util.Date;

import static junit.framework.Assert.assertEquals;

public class PersonTests {

    final String firstName = "John";
    final String lastName = "Doe";
    final int issuesCount = 10;
    final Date dateOfBirth = new Date();

    @Test
    public void testFullName() {
        Person person = new Person(firstName, lastName, issuesCount, dateOfBirth);
        assertEquals("John Doe", person.getFullName());
        assertEquals(String.valueOf(issuesCount), person.getIssuesCount());
        assertEquals(dateOfBirth, person.getDateOfBirth());
    }
}
