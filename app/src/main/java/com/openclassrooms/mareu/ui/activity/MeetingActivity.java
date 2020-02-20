package com.openclassrooms.mareu.ui.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.openclassrooms.mareu.R;
import com.openclassrooms.mareu.di.Injection;
import com.openclassrooms.mareu.ui.fragments.add_meeting.AddMeetingFragment;
import com.openclassrooms.mareu.ui.fragments.list_meeting.ListMeetingFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Main activity of the application that display the list of meetings,
 * permit to add one and also remove.
 */
public class MeetingActivity extends AppCompatActivity {

    /**
     *
     */
    @BindView(R.id.coordinator_layout)
    public CoordinatorLayout coordinatorLayout;

    /**
     *
     */
    @BindView(R.id.tool_bar)
    Toolbar toolBar;

    /**
     * floating action button to show Add Meeting fragment
     */
    @BindView(R.id.fab_add_meeting)
    public FloatingActionButton fabAddMeeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting);
        ButterKnife.bind(this);

        configureToolBar();
        configureFloatingActionButton();
        showListMeetings();
    }

    private void configureToolBar() {
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(R.string.app_name);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void configureFloatingActionButton() {
        fabAddMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddMeeting();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_meeting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                showListMeetings();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        final Fragment fragmentInFrame = getSupportFragmentManager().findFragmentById(R.id.placeholder_fragment);

        if (fragmentInFrame instanceof ListMeetingFragment){
            super.onBackPressed();
        } else if (fragmentInFrame instanceof AddMeetingFragment) {
            showListMeetings();
        }
    }

    private void showListMeetings() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle(R.string.app_name);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.placeholder_fragment, new ListMeetingFragment())
                .commit();
        fabAddMeeting.setVisibility(View.VISIBLE);
    }

    private void showAddMeeting() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.placeholder_fragment, new AddMeetingFragment())
                .commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Injection.getMeetingApiService().deleteAllMeetings();
    }
}
