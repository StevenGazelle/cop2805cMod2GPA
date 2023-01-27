//Appointment.java
//Steven Gsell
//1/26/2023
//Program that displays appointment information
package edu.fscj.cop2805c.appointment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

enum REMINDER {NONE, TEXT, EMAIL};

public class Appointment {
    //fields
    String title, description;
    Contact contact;
    ZonedDateTime appointmentTime, reminderTime;

    //constructors
    public Appointment(String title,
                       String description,
                       Contact contact,
                       ZonedDateTime appointmentTime){
        this.title = title;
        this.description = description;
        this.contact = contact;
        this.appointmentTime = appointmentTime;
        this.reminderTime = appointmentTime.minusHours(12);
    }

    //methods
    @Override
    public String toString(){
        String retString = "Your current appointment:\n";
        retString += ("Title: " + title + "\n" +
                "Description: " + description + "\n" +
                "Contact: " + contact + "\n" +
                "Appt Date/Time: " + appointmentTime + "\n" +
                "Reminder: " + reminderTime).indent(10);

        return retString;
    }
    //main
    public static void main(String[] args){
        Contact apptContact = new Contact(new StringBuilder("Gsell, Steven"),
                "StevenGsell@email.com", "904-555-5555",
                ZoneId.systemDefault(), REMINDER.TEXT);

        Appointment appt = new Appointment("CAT scan",
                "Meow meow meow", apptContact,
                ZonedDateTime.now().plusWeeks(2));

        System.out.println(appt);
    }
}

class Contact {
    //fields
    private StringBuilder name;
    private String email, phoneNumber;
    private ZoneId timeZone;
    private REMINDER reminderPref;

    //constructors
    public Contact(StringBuilder name, String email, String phoneNumber,
                   ZoneId timeZone, REMINDER reminderPref){
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.timeZone = timeZone;
        this.reminderPref = reminderPref;
    }

    //methods
    @Override
    public String toString() {
        String retString = "";
        retString += (email + ",(" + name + ")," +
                    phoneNumber + "," + reminderPref.name() +
                    "," + timeZone);

        return retString;
    }
}
