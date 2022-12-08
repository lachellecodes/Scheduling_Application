package Model;

import java.time.LocalDateTime;

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
}
