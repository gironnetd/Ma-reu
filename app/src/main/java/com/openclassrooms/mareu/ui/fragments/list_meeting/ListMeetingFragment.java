package com.openclassrooms.mareu.ui.fragments.list_meeting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.mareu.R;
import com.openclassrooms.mareu.api.ApiService;
import com.openclassrooms.mareu.di.Injection;
import com.openclassrooms.mareu.model.Meeting;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListMeetingFragment extends Fragment {

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_meeting, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        mApiService = Injection.getMeetingApiService();
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
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