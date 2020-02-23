package com.openclassrooms.mareu;

import com.openclassrooms.mareu.api.MeetingApiService;
import com.openclassrooms.mareu.di.Injection;
import com.openclassrooms.mareu.model.Meeting;
import com.openclassrooms.mareu.model.Place;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Collections;
import java.util.List;

import static com.openclassrooms.mareu.model.Collaborator.CollaboratorGenerator.generateCollaborators;
import static com.openclassrooms.mareu.model.Meeting.MeetingGenerator.generateMeetings;
import static com.openclassrooms.mareu.model.Place.PlaceGenerator.generatePlaces;
import static com.openclassrooms.mareu.model.Meeting.MeetingGenerator;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Meeting service
 */
@RunWith(JUnit4.class)
public class MeetingServiceTest {

    private MeetingApiService service;

    @Before
    public void setup() {
        service = Injection.getNewInstanceApiService();
        service.createMeetings(generateMeetings());
    }

    @Test
    public void get_Meetings_With_Success() {
        List<Meeting> meetings = service.getMeetings();
        List<Meeting> expectedMeetings = MeetingGenerator.MEETINGS_LIST;
        assertThat(meetings, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedMeetings.toArray()));
    }

    @Test
    public void delete_Meeting_With_Success() {
        Meeting meetingToDelete = service.getMeetings().get(0);
        service.deleteMeeting(meetingToDelete);
        assertFalse(service.getMeetings().contains(meetingToDelete));
    }

    @Test
    public void delete_Meetings_With_Success() {
        List<Meeting> meetingsToDelete = service.getMeetings();
        service.deleteMeetings(meetingsToDelete.subList(0, 3));
        assertFalse(service.getMeetings().contains(meetingsToDelete));
    }

    @Test
    public void delete_AllMeetings_With_Success() {
        service.deleteAllMeetings();
        assertTrue(service.getMeetings().isEmpty());
    }

    @Test
    public void add_Meeting_With_Success() {
        int MeetingsNumber = service.getMeetings().size();
        Place place = generatePlaces().get(0);
        Meeting newMeeting = new Meeting(MeetingsNumber + 1,
                "Add a Meeting",
                place.getMeetingTimes().get(2) ,
                generateCollaborators(),
                place);
        service.createMeeting(newMeeting);
        assertTrue(service.getMeetings().size() == MeetingsNumber + 1);
    }

    @Test
    public void add_Meetings_With_Success() {
        service.deleteAllMeetings();
        List<Meeting> meetingsToAdd = generateMeetings();
        service.createMeetings(meetingsToAdd);
        assertTrue(service.getMeetings().containsAll(meetingsToAdd));
    }

    @Test
    public void filter_Meetings_by_Date_With_Success() {
        List<Meeting> meetings = generateMeetings();

        service.filterMeetingsByDate();

        Collections.sort(meetings, new Meeting.MeetingDateComparator());
        assertEquals(meetings, service.getMeetings());
    }

    @Test
    public void filter_Meetings_by_Place_With_Success() {
        List<Meeting> meetings = generateMeetings();

        service.filterMeetingsByPlace();

        Collections.sort(meetings, new Meeting.MeetingPlaceComparator());
        assertEquals(meetings, service.getMeetings());
    }
}
