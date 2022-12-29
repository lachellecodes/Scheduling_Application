package Resources;

import DAO.AppointmentDAO;
import Model.Appointments;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.SQLException;
import java.time.*;

public class ValidateAppointment {

    public static boolean overlappingAppointmentCheck(Appointments newAppointment) throws SQLException {

        ObservableList<Appointments> appointmentList = AppointmentDAO.getAllAppointments();

        boolean overlap = false;

        Appointments proposedAppointment = new Appointments();

        for (Appointments appointments : appointmentList) {

            if (appointments.getAppointmentID() == newAppointment.getAppointmentID()) {
                continue;
            }


            int newApptCustomerID = newAppointment.getApptCustomerID();
            LocalDateTime proposedStart = newAppointment.getStartDateTime();
            LocalDateTime proposedEnd = newAppointment.getEndDateTime();

            int apptCustomerID = appointments.getApptCustomerID();
            LocalDateTime start = appointments.getStartDateTime();
            LocalDateTime end = appointments.getEndDateTime();

            proposedAppointment = appointments;


            //new appointment is the same time as another appointment
            if ((newApptCustomerID == apptCustomerID) && proposedStart.isEqual(start) && proposedEnd.isEqual(end)) {
                overlap = true;
                break;
            }


            else if((newApptCustomerID == apptCustomerID) && proposedStart.isBefore(start) && proposedEnd.isAfter(start)){
                overlap=true;
                break;
            }


            else if ((newApptCustomerID == apptCustomerID) && proposedStart.isBefore(start) && proposedEnd.isEqual(end)){
                overlap = true;
                break;
            }



            else if ((newApptCustomerID == apptCustomerID) && proposedStart.isEqual(start) && proposedEnd.isAfter(start)){
                overlap=true;
                break;
            }

            else if ((newApptCustomerID == apptCustomerID) && proposedStart.isAfter(start) && proposedEnd.isEqual(end)){
                overlap=true;
                break;
            }


        }
        return overlap;
    }

    public static void checkBusinessHours (Appointments newAppointment){

        LocalTime businessStart = LocalTime.parse("8:00");
        LocalDate businessDateStart = LocalDate.now();
        LocalDateTime businessStartTime = LocalDateTime.of(businessDateStart, businessStart);
        ZonedDateTime zonedBusinessStart = businessStartTime.atZone(ZoneId.systemDefault());
        ZonedDateTime zonedEstBusinesStart = zonedBusinessStart.withZoneSameInstant(ZoneId.of("America/New_York"));

        LocalDateTime apptStart = newAppointment.getStartDateTime();
        //LocalDate apptDate = newAppointment.getStartDateTime().toLocalDate();
        ZonedDateTime zonedStartDateTime = apptStart.atZone(ZoneId.systemDefault());
        ZonedDateTime zonedEstStart = zonedStartDateTime.withZoneSameInstant(ZoneId.of("America/New_York"));

        LocalTime businessEnd = LocalTime.parse("22:00");
        LocalDate businessDateEnd = LocalDate.now();
        LocalDateTime businessEndTime = LocalDateTime.of(businessDateEnd, businessEnd);
        ZonedDateTime zonedBusinessEnd = businessEndTime.atZone(ZoneId.systemDefault());
        ZonedDateTime zonedEstBusinessEnd = zonedBusinessEnd.withZoneSameInstant(ZoneId.of("America/New_York"));

        LocalDateTime apptEnd = newAppointment.getEndDateTime();
        ZonedDateTime zonedEndDateTime = apptEnd.atZone(ZoneId.systemDefault());
        ZonedDateTime zonedEstEnd = zonedEndDateTime.withZoneSameInstant(ZoneId.of("America/New_York"));

        Boolean outsideBusinessHours = false;

        if(zonedEstStart.isBefore(zonedEstBusinesStart) || zonedEstEnd.isAfter(zonedEstBusinessEnd)){
            outsideBusinessHours = true;

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(" The appointment is outside of scheduled business hours. Business hours are from 8:00 AM to 22:00 PM EST.");
            alert.show();
        }
    }
}

