package Resources;

import DAO.AppointmentDAO;
import Model.Appointments;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

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

        LocalDateTime businessWeekStart ;
    }
}

