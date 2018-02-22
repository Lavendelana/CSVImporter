package com.csv.splendo.csvimporter;

import com.csv.splendo.models.Person;

import org.junit.Test;

import java.util.Date;

import static junit.framework.Assert.assertEquals;

public class PersonTests {

    @Test
    public void testFullName() {
        Person person = new Person("John", "Doe", 10, new Date());
        assertEquals("John Doe", person.getFullName());
    }
}
