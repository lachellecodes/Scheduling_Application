package Resources;

import DAO.AppointmentDAO;
import Model.Appointments;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.SQLException;
import java.time.*;
import java.time.format.DateTimeFormatter;

/** A class that provides logic to validate whether an appointment is outside of business hours or overlaps another. */

public class ValidateAppointment {

    //private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'hh:mm:ss");

    /** Checks for overlapping appointments when creating a new appointment.
     * @param newAppointment
     * @return  a true or false boolean that the appointment overlaps.
     * Goes through the list of all existing appointments and checks the appointment's proposed start and end time
     * against all current appointments in the database before saving the new appointment.*/

    public static boolean overlappingAppointmentCheck(Appointments newAppointment) throws SQLException {

        ObservableList<Appointments> appointmentList = AppointmentDAO.getAllAppointments();

        boolean overlap = false;

        //Appointments proposedAppointment = new Appointments();


        for (Appointments appointments : appointmentList) {

            if(appointments.getAppointmentID() == newAppointment.getAppointmentID()){
                continue;
            }


            int newApptCustomerID = newAppointment.getApptCustomerID();
            LocalDateTime proposedStart = newAppointment.getStartDateTime();
            LocalDateTime proposedEnd = newAppointment.getEndDateTime();

            int apptCustomerID = appointments.getApptCustomerID();
            LocalDateTime start = appointments.getStartDateTime();
            LocalDateTime end = appointments.getEndDateTime();

            //proposedAppointment = appointments;

           if(appointments.getApptCustomerID() != newAppointment.getApptCustomerID())
           {
               continue;
           }
           /* if (appointments.getAppointmentID() == newAppointment.getAppointmentID()) {
                continue;
            }*/


            /*int newApptCustomerID = newAppointment.getApptCustomerID();
            LocalDateTime proposedStart = newAppointment.getStartDateTime();
            LocalDateTime proposedEnd = newAppointment.getEndDateTime();

            int apptCustomerID = appointments.getApptCustomerID();
            LocalDateTime start = appointments.getStartDateTime();
            LocalDateTime end = appointments.getEndDateTime();*/

            //proposedAppointment = newAppointment;
            //proposed start == start
            //proposed end == end
            //proposed start > start && proposed start is < end
            //proposed end > start && proposed end < end
            //proposed start < start && proposed end > end
            /*if ((newApptCustomerID != apptCustomerID)){
                continue;
            }*/

            //new appointment begins at the same time as another appointment
            if ((newApptCustomerID == apptCustomerID) && proposedStart.isEqual(start)) {
                overlap = true;
                break;
            }

            // new appointment ends at the same time as another appointment
            else if ((newApptCustomerID == apptCustomerID) && proposedEnd.isEqual(end)) {
                overlap = true;
                break;
            }

            // new appointment starts before but ends after another appointment
             else if ((newApptCustomerID == apptCustomerID) && proposedStart.isBefore(start) && proposedEnd.isAfter(end)) {
                overlap = true;
                break;


                // new appointment starts and ends during another appointment
            } else if ((newApptCustomerID == apptCustomerID) && proposedEnd.isAfter(start) && proposedEnd.isBefore(end)) {
                overlap = true;
                break;

                //
            } else if ((newApptCustomerID == apptCustomerID) && proposedStart.isAfter(start) && proposedStart.isBefore(end)) {
                overlap = true;
                break;

            }



        }
        return overlap;
    }

    /** A method to check if an appointment is outside of business hours.
     * @param newAppointment
     * @return Boolean true or false if the appointment overlaps
     * Checks to see if the appointment the user is trying to save falls outside of business hours
     * before saving the appointment to the database.*/
    public static Boolean checkBusinessHours(Appointments newAppointment) throws SQLException {

        //ObservableList<Appointments> appointmentList = AppointmentDAO.getAllAppointments();

        boolean outsideBusinessHours = false;

        Appointments proposedAppointment = new Appointments();

        /*for (Appointments appointments : appointmentList) {

            if (appointments.getAppointmentID() == newAppointment.getAppointmentID()) {
                continue;
            }*/

            proposedAppointment = newAppointment;

            LocalDateTime apptStart = proposedAppointment.getStartDateTime();
            LocalDate apptStartDate = proposedAppointment.getStartDateTime().toLocalDate();
            ZonedDateTime zonedStartDateTime = apptStart.atZone(ZoneId.systemDefault());
            ZonedDateTime zonedEstStart = zonedStartDateTime.withZoneSameInstant(ZoneId.of("America/New_York"));

            LocalTime businessStart = LocalTime.of(8, 00);
            LocalDate businessDateStart = apptStartDate;
            LocalDateTime businessStartTime = LocalDateTime.of(businessDateStart, businessStart);
            ZonedDateTime zonedBusinessStart = businessStartTime.atZone(ZoneId.systemDefault());
            ZonedDateTime zonedEstBusinesStart = zonedBusinessStart.withZoneSameInstant(ZoneId.of("America/New_York"));


            LocalTime businessEnd = LocalTime.of(22, 00);
            //LocalDate businessDateEnd = LocalDate.now();
            LocalDateTime businessEndTime = LocalDateTime.of(apptStartDate, businessEnd);
            ZonedDateTime zonedBusinessEnd = businessEndTime.atZone(ZoneId.systemDefault());
            ZonedDateTime zonedEstBusinessEnd = zonedBusinessEnd.withZoneSameInstant(ZoneId.of("America/New_York"));

            LocalDateTime apptEnd = proposedAppointment.getEndDateTime();
            ZonedDateTime zonedEndDateTime = apptEnd.atZone(ZoneId.systemDefault());
            ZonedDateTime zonedEstEnd = zonedEndDateTime.withZoneSameInstant(ZoneId.of("America/New_York"));


            if (zonedEstStart.isBefore(zonedEstBusinesStart) || zonedEstEnd.isAfter(zonedEstBusinessEnd)) {
                outsideBusinessHours = true;


            }

        return outsideBusinessHours;
    }
}

