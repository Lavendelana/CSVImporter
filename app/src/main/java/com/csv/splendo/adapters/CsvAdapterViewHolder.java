package com.csv.splendo.adapters;

import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

class CsvAdapterViewHolder {
    static final String DATE_FORMAT = "dd-MM-yyyy";

    private TextView fullNameTextView;
    private TextView issuesCountTextView;
    private TextView dateOfBirthTextView;

    public CsvAdapterViewHolder(TextView fullNameTextView, TextView issuesCountTextView, TextView dateOfBirthTextView) {
        super();

        this.fullNameTextView = fullNameTextView;
        this.issuesCountTextView = issuesCountTextView;
        this.dateOfBirthTextView = dateOfBirthTextView;
    }

    public void setFullName(String fullName) {
        fullNameTextView.setText(fullName);
    }

    public void setIssuesCount(int issuesCount) {
        issuesCountTextView.setText(String.valueOf(issuesCount));
    }

    public void setDateOfBirth(Date dateOfBirth) {
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        dateOfBirthTextView.setText(dateFormat.format(dateOfBirth));
    }
}
