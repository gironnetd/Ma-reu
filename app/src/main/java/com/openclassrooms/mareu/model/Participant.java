package com.openclassrooms.mareu.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Participant class representing the Participant who take part of meetings
 */
public class Participant {

    /**
     * identifier of the participant
     */
    private long id;

    /**
     * first name of the participant
     * @return
     */
    private String firstName;

    /**
     * last name of the participant
     */
    private String lastName;

    /**
     * email of the participant
     */
    private String email;


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Participant(long id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    /**
     * Class to generate list of employees of the society that can participate to meetings
     */
    static class ParticipantGenerator {

        static List<Participant> generateParticipants() {
            return new ArrayList<>(PARTICIPANTS_LIST);
        }

        static List<Participant> PARTICIPANTS_LIST = Arrays.asList(
                new Participant(1L,"Francis","Dupont", "francis@lamzone.com"),
                new Participant(2L, "Alexandra", "Dupont","alexandra@lamzone.com"),
                new Participant(3L, "Alain", "Dupont","alain@lamzone.com"),
                new Participant(4L, "Steeve", "Dupont","steeve@lamzone.com"),
                new Participant(5L, "Jean", "Le Québel","jean@lamzone.com"),
                new Participant(6L, "Frank", "Dupont","frank@lamzone.com"),
                new Participant(7L, "Stéphane", "Dupont","stéphane@lamzone.com"),
                new Participant(8L, "Sylvain", "Dupont","sylvain@lamzone.com"),
                new Participant(9L, "Jérôme", "Dupont","jérôme@lamzone.com"),
                new Participant(10L, "Cynthia", "Dupont","cynthia@lamzone.com"),
                new Participant(11L, "Pierre", "Dupont","pierre@lamzone.com"),
                new Participant(12L, "Cécile", "Dupont","cécile@lamzone.com")
        );
    }
}
