package com.csv.splendo.csvimporter;

import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.csv.splendo.adapters.CSVListAdapter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.*;

@MediumTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void useAppContext() throws Exception {
        MainActivity activity = rule.getActivity();

        View progressBar = activity.findViewById(R.id.csvProgressBar);
        assertNotNull(progressBar);
        assertThat(progressBar, instanceOf(ProgressBar.class));
        assertEquals(progressBar.getVisibility(), View.GONE);

        View loadingText = activity.findViewById(R.id.loadingTextView);
        assertNotNull(loadingText);
        assertThat(loadingText, instanceOf(TextView.class));
        assertEquals(loadingText.getVisibility(), View.GONE);

        View listView = activity.findViewById(R.id.mainListView);
        assertNotNull(listView);
        assertThat(listView, instanceOf(ListView.class));

        ListView listViewObject = (ListView) listView;
        ListAdapter adapter = listViewObject.getAdapter();
        assertThat(adapter, instanceOf(CSVListAdapter.class));
        assertThat(adapter.getCount(), greaterThan(0));
    }
}
