package com.openclassrooms.mareu.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.openclassrooms.mareu.model.MeetingTime.MeetingTimeGenerator.generateMeetingTimes;

/**
 * Place class representing the place where meetings take place
 */
public class Place {

    /**
     * identifier of the place
     */
    private long id;

    /**
     * name of the place
     */
    private String name;

    /**
     * list of the meeting times of the place
     */
    private List<MeetingTime> meetingTimes;

    public String getName() {
        return name;
    }

    public List<MeetingTime> getMeetingTimes() {
        return meetingTimes;
    }

    public Place(long id, String name, List<MeetingTime> meetingTimes) {
        this.id = id;
        this.name = name;
        this.meetingTimes = meetingTimes;
    }

    /**
     * Class to generate places where the meetings take place
     */
    static class PlaceGenerator {

        static List<Place> generatePlaces() {
            return new ArrayList<>(PLACES_LIST);
        }

        static List<Place> PLACES_LIST = Arrays.asList(
                new Place(1L, "Peach", generateMeetingTimes()),
                new Place(2L, "Mario", generateMeetingTimes()),
                new Place(3L, "Luigi", generateMeetingTimes()),
                new Place(4L, "Toad", generateMeetingTimes()),
                new Place(5L, "Yoshi", generateMeetingTimes()),
                new Place(6L, "Daisy", generateMeetingTimes())
        );
    }
}
