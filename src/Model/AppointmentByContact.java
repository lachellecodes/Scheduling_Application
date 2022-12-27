package Model;

import javafx.scene.control.TableColumn;

import java.time.LocalDateTime;

public class AppointmentByContact {

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




    public AppointmentByContact(int apptContactID, int appointmentID, String title, String type, String description, LocalDateTime startDateTime, LocalDateTime endDateTime, int apptCustomerID) {
    }
}
