package com.csv.splendo.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.csv.splendo.csvimporter.R;
import com.csv.splendo.models.Person;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class CsvListAdapter extends ArrayAdapter<Person> {

    private final Context context;
    private final List<Person> persons;

    public CsvListAdapter(@NonNull Context context, List<Person> values) {
        super(context, -1, values);

        this.context = context;
        this.persons = values;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Person currentPerson = persons.get(position);

        CsvAdapterViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_main, parent, false);

            viewHolder = new CsvAdapterViewHolder((TextView)convertView.findViewById(R.id.fullNameTextView), (TextView)convertView.findViewById(R.id.issuesCountValueTextView), (TextView)convertView.findViewById(R.id.dateOfBirthValueTextView));

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (CsvAdapterViewHolder) convertView.getTag();
        }

        viewHolder.setFullName(currentPerson.getFullName());
        viewHolder.setIssuesCount(currentPerson.getIssuesCount());
        viewHolder.setDateOfBirth(currentPerson.getDateOfBirth());

        return convertView;
    }
}
