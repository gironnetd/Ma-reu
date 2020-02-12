package com.openclassrooms.mareu.api;

import com.openclassrooms.mareu.model.Meeting;

import java.util.List;

/**
 * Api interface that perform features on list of meetings
 */
public interface ApiService {

    /**
     *
     * @return all meetings
     */
    List<Meeting> getMeetings();

    /**
     *
     * @param meeting to create
     * @return true if the meeting is added
     */
    boolean createMeeting(Meeting meeting);

    /**
     *
     * @param meetings to create
     * @return true if the meetings have been added
     */
    boolean createMeetings(List<Meeting> meetings);

    /**
     *
     * @param meeting to delete
     * @return true if the meeting have been deleted
     */
    boolean deleteMeeting(Meeting meeting);
}
