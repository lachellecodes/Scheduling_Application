package Model;

import java.time.LocalDateTime;

/** A class to model the data on the appointments table in the database. */

public class Appointments {

    int appointmentID;
    String title;
    String description;
    String location;
    String type;
    LocalDateTime startDateTime;
    LocalDateTime endDateTime;
    int apptCustomerID;
    int apptUserID;
    int apptContactID;

    /**Creates a new appointment object.
     * @param appointmentID
     * @param title
     * @param description
     * @param location
     * @param type
     * @param startDateTime
     * @param endDateTime
     * @param apptCustomerID
     * @param apptUserID
     * @param apptContactID
     * */

    public Appointments(int appointmentID, String title, String description, String location, String type, LocalDateTime startDateTime, LocalDateTime endDateTime, int apptCustomerID, int apptUserID, int apptContactID) {
        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.apptCustomerID = apptCustomerID;
        this.apptUserID = apptUserID;
        this.apptContactID = apptContactID;
    }

    /** No argument constructor for an appointment object. */
    public Appointments() {

    }


    public int getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public int getApptCustomerID() {
        return apptCustomerID;
    }

    public void setApptCustomerID(int apptCustomerID) {
        this.apptCustomerID = apptCustomerID;
    }

    public int getApptUserID() {
        return apptUserID;
    }

    public void setApptUserID(int apptUserID) {
        this.apptUserID = apptUserID;
    }

    public int getApptContactID() {
        return apptContactID;
    }

    public void setApptContactID(int apptContactID) {
        this.apptContactID = apptContactID;
    }
    /** Turns the start time of the appointment into a string.
     * */
    @Override
    public String toString (){
        return String.valueOf(startDateTime);
    }
}
