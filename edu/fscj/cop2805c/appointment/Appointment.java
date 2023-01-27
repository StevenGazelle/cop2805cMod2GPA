//Appointment.java
//Steven Gsell
//1/26/2023
//Program that displays appointment information
package edu.fscj.cop2805c.appointment;

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
                       String description){
        this.title = title;
        this.description = description;
    }

    //methods
    public void setContact(Contact contact) {
        this.contact = contact;
    }
    public void setAppointmentTime(ZonedDateTime appointmentTime){
        this.appointmentTime = appointmentTime;
    }
    public void setReminderTime(ZonedDateTime reminderTime){
        this.reminderTime = reminderTime;
    }
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
        //create contact
        Contact apptContact = new Contact(new StringBuilder("Gsell, Steven"),
                "StevenGsell@email.com", "904-555-5555",
                ZoneId.systemDefault(), REMINDER.TEXT);

        //create appointment
        Appointment appt = new Appointment("CAT scan", "Meow meow meow");

        //schedule appointment through contact
        apptContact.scheduleAppointment(appt);

        //display appointment
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
    //schedules given appointment 2 weeks from current date and time
    public void scheduleAppointment(Appointment appt) {
        ZonedDateTime apptDateTime = ZonedDateTime.now().plusWeeks(2);

        appt.setContact(this);
        appt.setAppointmentTime(apptDateTime);
        appt.setReminderTime(apptDateTime.minusHours(12));
    }

    //formats contact info for string output
    @Override
    public String toString() {
        String retString = "";
        retString += (email + ",(" + name + ")," +
                    phoneNumber + "," + reminderPref.name() +
                    "," + timeZone);

        return retString;
    }
}
