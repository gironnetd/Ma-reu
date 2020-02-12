package com.openclassrooms.mareu.ui.meeting_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.openclassrooms.mareu.R;
import com.openclassrooms.mareu.api.ApiService;
import com.openclassrooms.mareu.di.Injection;
import com.openclassrooms.mareu.model.Meeting;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Main activity of the application that display the list of meetings,
 * permit to add one and also remove.
 */
public class ListMeetingActivity extends AppCompatActivity {

    /**
     * RecyclerView meetings
     */
    @BindView(R.id.list_meetings)
    RecyclerView meetingsList;

    /**
     * RecyclerViewAdapter for the meetings RecyclerView
     */
    ListMeetingAdapter meetingListAdapter;

    /**
     * TextView displaying that there are no meetings to register
     */
    @BindView(R.id.tv_no_meetings)
    TextView noMeetingsTextView;

    /**
     * ApiService to perform tasks
     */
    private ApiService mApiService;

    /**
     * list of meetings
     */
    private List<Meeting> meetings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_meeting);
        ButterKnife.bind(this);
        mApiService = Injection.getMeetingApiService();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadMeetings();
    }

    /**
     * load meetings from ApiService to populate meetingsList
     */
    private void loadMeetings() {
        meetings = mApiService.getMeetings();
        meetingListAdapter = new ListMeetingAdapter(meetings);
        meetingsList.setAdapter(meetingListAdapter);

        displayMeetingsList();
    }

    /**
     * check if meetingsList have to be displayed
     */
    private void displayMeetingsList() {
        if(!meetings.isEmpty()) {
            noMeetingsTextView.setVisibility(View.GONE);
            meetingsList.setVisibility(View.VISIBLE);
        } else {
            noMeetingsTextView.setVisibility(View.VISIBLE);
            meetingsList.setVisibility(View.GONE);
        }
    }
}
