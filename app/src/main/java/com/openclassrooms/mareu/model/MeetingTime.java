package com.openclassrooms.mareu.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Timer;

/**
 * MeetingTime class representing the MeetingTime of Places
 */
public class MeetingTime {

    /**
     * identifier of the meeting time
     */
    private long id ;

    /**
     * start time of the meeting
     */
    private Date startTime;

    /**
     * end time of the meeting
     */
    private Date endTime;

    /**
     * check if the meeting time in the place is reserved or not
     */
    private boolean reserved ;

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public boolean getReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public MeetingTime(long id, Date startTime, Date endTime, boolean reserved) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.reserved = reserved;
    }

    /**
     * Class to generate  meetingTimes for each places
     */
    static class MeetingTimeGenerator {

        static List<MeetingTime> generateMeetingTimes() {
            return new ArrayList<>(MEETING_TIMES);
        }

        static List<MeetingTime> MEETING_TIMES = Arrays.asList(
                new MeetingTime(1L, new GregorianCalendar( 1900, 0, 0, 0, 0).getTime(),
                        new GregorianCalendar(1900,0,0,1,0).getTime(), false),
                new MeetingTime(1L, new GregorianCalendar( 1900, 0, 0, 1, 0).getTime(),
                        new GregorianCalendar(1900,0,0,2,0).getTime(), false),
                new MeetingTime(1L, new GregorianCalendar( 1900, 0, 0, 2, 0).getTime(),
                        new GregorianCalendar(1900,0,0,3,0).getTime(), false),
                new MeetingTime(1L, new GregorianCalendar( 1900, 0, 0, 3, 0).getTime(),
                        new GregorianCalendar(1900,0,0,4,0).getTime(), false),
                new MeetingTime(1L, new GregorianCalendar( 1900, 0, 0, 4, 0).getTime(),
                        new GregorianCalendar(1900,0,0,5,0).getTime(), false),
                new MeetingTime(1L, new GregorianCalendar( 1900, 0, 0, 5, 0).getTime(),
                        new GregorianCalendar(1900,0,0,6,0).getTime(), false),
                new MeetingTime(1L, new GregorianCalendar( 1900, 0, 0, 6, 0).getTime(),
                        new GregorianCalendar(1900,0,0,7,0).getTime(), false),
                new MeetingTime(1L, new GregorianCalendar( 1900, 0, 0, 7, 0).getTime(),
                        new GregorianCalendar(1900,0,0,8,0).getTime(), false),
                new MeetingTime(1L, new GregorianCalendar( 1900, 0, 0, 8, 0).getTime(),
                        new GregorianCalendar(1900,0,0,9,0).getTime(), false),
                new MeetingTime(1L, new GregorianCalendar( 1900, 0, 0, 9, 0).getTime(),
                        new GregorianCalendar(1900,0,0,10,0).getTime(), false),
                new MeetingTime(1L, new GregorianCalendar( 1900, 0, 0, 10, 0).getTime(),
                        new GregorianCalendar(1900,0,0,11,0).getTime(), false),
                new MeetingTime(1L, new GregorianCalendar( 1900, 0, 0, 11, 0).getTime(),
                        new GregorianCalendar(1900,0,0,12,0).getTime(), false),
                new MeetingTime(1L, new GregorianCalendar( 1900, 0, 0, 12, 0).getTime(),
                        new GregorianCalendar(1900,0,0,13,0).getTime(), false),
                new MeetingTime(1L, new GregorianCalendar( 1900, 0, 0, 13, 0).getTime(),
                        new GregorianCalendar(1900,0,0,14,0).getTime(), false),
                new MeetingTime(1L, new GregorianCalendar( 1900, 0, 0, 14, 0).getTime(),
                        new GregorianCalendar(1900,0,0,15,0).getTime(), false),
                new MeetingTime(1L, new GregorianCalendar( 1900, 0, 0, 15, 0).getTime(),
                        new GregorianCalendar(1900,0,0,16,0).getTime(), false),
                new MeetingTime(1L, new GregorianCalendar( 1900, 0, 0, 16, 0).getTime(),
                        new GregorianCalendar(1900,0,0,17,0).getTime(), false),
                new MeetingTime(1L, new GregorianCalendar( 1900, 0, 0, 17, 0).getTime(),
                        new GregorianCalendar(1900,0,0,18,0).getTime(), false),
                new MeetingTime(1L, new GregorianCalendar( 1900, 0, 0, 18, 0).getTime(),
                        new GregorianCalendar(1900,0,0,19,0).getTime(), false),
                new MeetingTime(1L, new GregorianCalendar( 1900, 0, 0, 19, 0).getTime(),
                        new GregorianCalendar(1900,0,0,20,0).getTime(), false),
                new MeetingTime(1L, new GregorianCalendar( 1900, 0, 0, 20, 0).getTime(),
                        new GregorianCalendar(1900,0,0,21,0).getTime(), false),
                new MeetingTime(1L, new GregorianCalendar( 1900, 0, 0, 21, 0).getTime(),
                        new GregorianCalendar(1900,0,0,22,0).getTime(), false),
                new MeetingTime(1L, new GregorianCalendar( 1900, 0, 0, 22, 0).getTime(),
                        new GregorianCalendar(1900,0,0,23,0).getTime(), false),
                new MeetingTime(1L, new GregorianCalendar( 1900, 0, 0, 23, 0).getTime(),
                        new GregorianCalendar(1900,0,0,24,0).getTime(), false)
        );
    }
}
