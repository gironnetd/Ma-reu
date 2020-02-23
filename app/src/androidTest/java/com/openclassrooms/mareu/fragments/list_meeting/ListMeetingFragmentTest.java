package com.openclassrooms.mareu.fragments.list_meeting;


import android.view.View;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.openclassrooms.mareu.R;
import com.openclassrooms.mareu.api.ApiService;
import com.openclassrooms.mareu.api.MeetingApiService;
import com.openclassrooms.mareu.di.Injection;
import com.openclassrooms.mareu.model.Meeting;
import com.openclassrooms.mareu.ui.activity.MeetingActivity;
import com.openclassrooms.mareu.ui.fragments.add_meeting.AddMeetingFragment;
import com.openclassrooms.mareu.ui.fragments.list_meeting.ListMeetingFragment;
import com.openclassrooms.mareu.utils.ClickViewAction;
import com.openclassrooms.mareu.utils.DeleteViewAction;
import com.openclassrooms.mareu.utils.RecyclerViewItemCountAssertion;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static com.openclassrooms.mareu.utils.RecyclerViewItemCountAssertion.*;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import static com.openclassrooms.mareu.model.Meeting.MeetingGenerator.*;

@RunWith(AndroidJUnit4.class)
public class ListMeetingFragmentTest {

    MeetingActivity mActivity;
    ApiService meetingApiService;

    @Rule
    public ActivityTestRule<MeetingActivity> mActivityRule = new ActivityTestRule(MeetingActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
        meetingApiService = Injection.getMeetingApiService();
    }

    @After
    public void tearDown() {
        meetingApiService.deleteAllMeetings();
    }

    @Test
    public void display_No_Meetings_On_Launch() {
        // verify that list of meeting is empty
        onView(withId(R.id.list_meetings)).check(withItemCount(0));
    }

    @Test
    public void display_Message_No_Meetings_On_Launch() {
        onView(withText(R.string.no_meetings_created)).check(matches(isDisplayed()));
    }

    @Test
    public void display_Meetings_When_Meetings_Have_Been_Adding() {

        // verify that list of meeting is empty
        onView(withId(R.id.list_meetings)).check(withItemCount(0));

        // click on add floating action button if two panes screen
        clickOn_FloatingActionButton_If_Needed();

        addMeetings(3);

        // verify that the list of meeting have now an item
        onView(withId(R.id.list_meetings)).check(withItemCount(3));
    }

    @Test
    public void verify_Delete_Meeting_With_Success() {
        // Given : We remove the element at position 1

        // click on add floating action button if two panes screen
        clickOn_FloatingActionButton_If_Needed();

        final int ITEMS_COUNT = 3;
        addMeetings(ITEMS_COUNT);

        onView(ViewMatchers.withId(R.id.list_meetings)).check(withItemCount(ITEMS_COUNT));

        // When perform a click on a delete icon
        onView(ViewMatchers.withId(R.id.list_meetings))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));

        // Then : the number of element is 11
        onView(ViewMatchers.withId(R.id.list_meetings)).check(withItemCount(ITEMS_COUNT - 1));
    }

    @Test
    public void filter_Meetings_by_Date() {

        // click on add floating action button if two panes screen
        clickOn_FloatingActionButton_If_Needed();

        addMeetings(6);

        List<Meeting> meetings = meetingApiService.getMeetings();

        onView(withId(R.id.filter)).perform(click());
        onView(withText(R.string.filter_by_date)).perform(click());

        ListMeetingFragment listMeetingFragment =
                (ListMeetingFragment) mActivity.getSupportFragmentManager()
                        .getFragments().get(0);

        Collections.sort(meetings, new Meeting.MeetingDateComparator());

        assertEquals(meetings, listMeetingFragment.getMeetings());
    }

    @Test
    public void filter_Meetings_by_Place() {

        // click on add floating action button if two panes screen
        clickOn_FloatingActionButton_If_Needed();

        addMeetings(6);

        List<Meeting> meetings = meetingApiService.getMeetings();

        onView(withId(R.id.filter)).perform(click());
        onView(withText(R.string.filter_by_place)).perform(click());

        ListMeetingFragment listMeetingFragment =
                (ListMeetingFragment) mActivity.getSupportFragmentManager()
                        .getFragments().get(0);

        Collections.sort(meetings, new Meeting.MeetingPlaceComparator());

        assertEquals(meetings, listMeetingFragment.getMeetings());
    }

    private void clickOn_FloatingActionButton_If_Needed() {
        // check if screen have two panes
        if(check_If_Is_TwoPanes_Screen()) {
            // then click on floating action button
            clickOn_AddMeeting_FoatingActionButton();
        }
    }

    private boolean check_If_Is_TwoPanes_Screen() {
        return mActivity.fabAddMeeting.getVisibility() == View.VISIBLE;
    }

    private void clickOn_AddMeeting_FoatingActionButton() {
        onView(ViewMatchers.withId(R.id.fab_add_meeting)).perform(ViewActions.click());
    }

    private void addMeetings(int number) {

        // add  meetings
        for(int index = 0; index < number; index++) {
            onView(withId(R.id.text_input_edit_text_meeting_subject))
                    .perform(typeText("Meeting " + (index + 1)), pressImeActionButton());

            onView(withId(R.id.spinner_places)).perform(click());
            if(index % 3 == 0) {
                onView(withText("Mario")).perform(click());
                onView(withId(R.id.meeting_times_list))
                        .perform(RecyclerViewActions.actionOnItemAtPosition(index + 2, new ClickViewAction()));
            } else if(index % 2 == 0) {
                onView(withText("Luigi")).perform(click());
                onView(withId(R.id.meeting_times_list))
                        .perform(RecyclerViewActions.actionOnItemAtPosition(index + 1, new ClickViewAction()));
            } else {
                onView(withText("Peach")).perform(click());
                onView(withId(R.id.meeting_times_list))
                        .perform(RecyclerViewActions.actionOnItemAtPosition(index, new ClickViewAction()));
            }

            onView(withId(R.id.image_view_meeting_times)).perform(click());

            onView(ViewMatchers.withId(R.id.collaborators_list))
                    .perform(RecyclerViewActions.actionOnItemAtPosition(1, new ClickViewAction()));

            onView(ViewMatchers.withId(R.id.collaborators_list))
                    .perform(RecyclerViewActions.actionOnItemAtPosition(3, new ClickViewAction()));

            // click on add meeting button
            onView(withId(R.id.add_meeting)).perform(click());

            // check if screen have two panes
            if(index != number - 1 && check_If_Is_TwoPanes_Screen()) {
                // then click on floating action button
                clickOn_FloatingActionButton_If_Needed();
            }
        }
    }
}
