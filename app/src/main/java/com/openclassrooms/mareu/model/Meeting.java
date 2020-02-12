package com.openclassrooms.mareu.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.openclassrooms.mareu.model.MeetingTime.MeetingTimeGenerator.MEETING_TIMES;
import static com.openclassrooms.mareu.model.Place.PlaceGenerator.PLACES_LIST;
import static com.openclassrooms.mareu.model.Participant.ParticipantGenerator.PARTICIPANTS_LIST;

/**
 * Meeting class representing the Meeting object
 */
public class Meeting {

    /**
     * identifier of meeting
     */
    private long id;

    /**
     * subject of meeting
     */
    private String subject;

    /**
     * slot of the meeting
     */
    private MeetingTime slot;

    /**
     * list of participants of the meeting
     */
    private List<Participant> participants;

    /**
     * place of the meeting
     */
    private Place place;

    public String getSubject() {
        return subject;
    }

    public MeetingTime getSlot() {
        return slot;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public Place getPlace() {
        return place;
    }

    public Meeting(long id, String subject, MeetingTime slot, List<Participant> participants, Place place) {
        this.id = id;
        this.subject = subject;
        this.slot = slot;
        this.participants = participants ;
        this.place = place;
    }


    /**
     * Class to generate random meetings
     */
    public static class MeetingGenerator {

        public static List<Meeting> generateMeetings() {
            return new ArrayList<>(MEETINGS_LIST);
        }

        static List<Meeting> MEETINGS_LIST = Arrays.asList(
                new Meeting(1L, "A", MEETING_TIMES.get(11),
                        new ArrayList<Participant>(PARTICIPANTS_LIST),
                        PLACES_LIST.get(0)),
                new Meeting(2L, "B", MEETING_TIMES.get(1),
                        new ArrayList<Participant>(PARTICIPANTS_LIST),
                        PLACES_LIST.get(3)),
                new Meeting(3L, "C", MEETING_TIMES.get(10),
                        new ArrayList<Participant>(PARTICIPANTS_LIST),
                        PLACES_LIST.get(4))
        );
    }
}
