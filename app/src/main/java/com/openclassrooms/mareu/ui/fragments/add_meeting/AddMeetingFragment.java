package com.openclassrooms.mareu.ui.fragments.add_meeting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.openclassrooms.mareu.R;
import com.openclassrooms.mareu.api.ApiService;
import com.openclassrooms.mareu.di.Injection;
import com.openclassrooms.mareu.model.Meeting;
import com.openclassrooms.mareu.model.Place;
import com.openclassrooms.mareu.ui.activity.MeetingActivity;
import com.openclassrooms.mareu.ui.fragments.add_meeting.meetingtimes.MeetingTimesAdapter;
import com.openclassrooms.mareu.ui.fragments.add_meeting.participants.CollaboratorsAdapter;
import com.openclassrooms.mareu.ui.fragments.add_meeting.places.PlacesSpinnerAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.openclassrooms.mareu.model.Collaborator.CollaboratorGenerator.generateCollaborators;
import static com.openclassrooms.mareu.model.Place.PlaceGenerator.generatePlaces;

public class AddMeetingFragment extends Fragment {

    @BindView(R.id.image_view_subject_meeting)
    ImageView subjectImageView;

    @BindView(R.id.text_input_layout_subject_meeting)
    TextInputLayout subjectTextInputLayout;

    @BindView(R.id.text_input_edit_text_meeting_subject)
    TextInputEditText subjectEditText;

    @BindView(R.id.image_view_place_meeting)
    ImageView placesImageView;

    @BindView(R.id.spinner_places)
    Spinner spinnerPlaces;

    @BindView(R.id.label_meeting_time)
    TextView labelMeetingTimes;

    @BindView(R.id.image_view_meeting_times)
    ImageView meetingTimesImageView;

    @BindView(R.id.meeting_times_list)
    RecyclerView meetingTimesList;

    @BindView(R.id.image_view_meeting_participants)
    ImageView participantsImageView;

    @BindView(R.id.collaborators_list)
    RecyclerView collaboratorsList;

    /**
     * adapter for the list of meeting times
     */
    private MeetingTimesAdapter meetingTimesAdapter;

    /**
     * adapter for the list of participants of meeting
     */
    private CollaboratorsAdapter collaboratorsAdapter;

    /**
     * place selected for the meeting
     */
    private Place selectedPlace;

    /**
     * ApiService to perform tasks
     */
    private ApiService mApiService;

    /**
     * list of places that can be choosed for the meeting
     */
    final List<Place> places = generatePlaces();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_meeting, container, false);
        ButterKnife.bind(this,view);
        ((MeetingActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MeetingActivity) getActivity()).getSupportActionBar().setTitle(R.string.add_meeting_title);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        mApiService = Injection.getMeetingApiService();
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MeetingActivity) getActivity()).fabAddMeeting.setVisibility(View.GONE);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_add_meeting, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_meeting :
                Snackbar snackbar = Snackbar.make(((MeetingActivity) getActivity()).coordinatorLayout,getString(R.string.meeting_added),Snackbar.LENGTH_SHORT);

                if(subjectEditText.getText().length() == 0) {
                    snackbar.setText(getString(R.string.no_subject_meeting_registered));
                    snackbar.show();
                    break;
                }

                if(selectedPlace == null) {
                    snackbar.setText(getString(R.string.no_place_meeting_selected));
                    snackbar.show();
                    break;
                }

                if(meetingTimesAdapter.getSelectedMeetingTime() == null) {
                    snackbar.setText(getString(R.string.no_meeting_time_selected));
                    snackbar.show();
                    break;
                }

                if(collaboratorsAdapter.getParticipants().isEmpty()) {
                    snackbar.setText(getString(R.string.no_participants_selected));
                    snackbar.show();
                    break;
                }

                selectedPlace.getMeetingTimes().get(meetingTimesAdapter.getLastSelectedPosition()).setReserved(true);
                mApiService.createMeeting(new Meeting(mApiService.getMeetings().size(),
                        subjectEditText.getText().toString(),
                        meetingTimesAdapter.getSelectedMeetingTime(),
                        collaboratorsAdapter.getParticipants(),selectedPlace));
                snackbar.show();

                getActivity().onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        configureImageViews();
        configureSpinnerPlaces();
        configureCollaboratorsList();
    }

    /**
     * configure the icons of fragment
     */
    private void configureImageViews() {
        subjectImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(subjectTextInputLayout.getVisibility() == View.GONE) {
                    subjectTextInputLayout.setVisibility(View.VISIBLE);
                    subjectEditText.requestFocus();
                } else {
                    subjectTextInputLayout.setVisibility(View.GONE);
                }
            }
        });

        placesImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(spinnerPlaces.getVisibility() == View.GONE) {
                    spinnerPlaces.setVisibility(View.VISIBLE);
                } else {
                    spinnerPlaces.setVisibility(View.GONE);
                }
            }
        });

        meetingTimesImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(meetingTimesList.getVisibility() == View.GONE) {
                    meetingTimesList.setVisibility(View.VISIBLE);
                } else {
                    meetingTimesList.setVisibility(View.GONE);
                }
            }
        });

        participantsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(collaboratorsList.getVisibility() == View.GONE) {
                    collaboratorsList.setVisibility(View.VISIBLE);
                } else {
                    collaboratorsList.setVisibility(View.GONE);
                }
            }
        });
    }

    /**
     * configure the spinner of the places for meeting
     */
    private void configureSpinnerPlaces() {
        PlacesSpinnerAdapter dataAdapter = new PlacesSpinnerAdapter(getActivity(), places);
        spinnerPlaces.setAdapter(dataAdapter);
        spinnerPlaces.setPrompt(getString(R.string.spinner_default_text));

        spinnerPlaces.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position != 0) {
                    selectedPlace = places.get(position - 1);
                    labelMeetingTimes.setVisibility(View.VISIBLE);
                    meetingTimesImageView.setVisibility(View.VISIBLE);
                    meetingTimesList.setVisibility(View.VISIBLE);

                    if(meetingTimesAdapter == null) {
                        meetingTimesAdapter = new MeetingTimesAdapter(selectedPlace);
                        meetingTimesList.setAdapter(meetingTimesAdapter);
                    } else {
                      meetingTimesAdapter.setSelectedPlace(selectedPlace);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }

    /**
     * configure the list of the collaborators that can participate to the meeting
     */
    private void configureCollaboratorsList() {
        collaboratorsAdapter = new CollaboratorsAdapter(generateCollaborators());
        collaboratorsList.setAdapter(collaboratorsAdapter);
    }
}
