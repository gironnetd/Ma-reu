package com.openclassrooms.mareu.api;

import com.openclassrooms.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.List;

/**
 * Api that manage the meetings
 */
public class MeetingApiService implements ApiService {

    /**
     * list of meetings
     */
    List<Meeting> meetings = new ArrayList<>(); // Meeting.MeetingGenerator.generateMeetings(); //

    /**
     * @return all meetings
     */
    @Override
    public List<Meeting> getMeetings() {
        return meetings;
    }

    /**
     * @param meeting to create
     * @return true if the meeting is added
     */
    @Override
    public boolean createMeeting(Meeting meeting) {
        return meetings.add(meeting);
    }

    /**
     * @param meetings to create
     * @return true if the meetings have been added
     */
    @Override
    public boolean createMeetings(List<Meeting> meetings) {
        return meetings.addAll(meetings);
    }

    /**
     * @param meeting to delete
     * @return true if the meeting have been deleted
     */
    @Override
    public boolean deleteMeeting(Meeting meeting) {
        return meetings.remove(meeting);
    }

    /**
     * @param meetings to delete
     * @return true if the meetings have been deleted
     */
    @Override
    public boolean deleteMeetings(List<Meeting> meetings) {
        return this.meetings.removeAll(meetings);
    }

    /**
     * @return true if all the meetings have been deleted
     */
    @Override
    public boolean deleteAllMeetings() {
        meetings.clear();
        return true;
    }
}
