package com.csv.splendo.models;

import java.util.Date;

public class Person {
    private String firstName;
    private String lastName;
    private int issuesCount;
    private Date dateOfBirth;

    public Person(String firstName, String lastName, int issuesCount, Date dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.issuesCount = issuesCount;
        this.dateOfBirth = dateOfBirth;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public int getIssuesCount() {
        return issuesCount;
    }
}
