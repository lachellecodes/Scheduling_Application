package DAO;

import Model.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDateTime;

public class AppointmentDAO {

    public static ObservableList<Appointments> getAllAppointments() throws SQLException {

        ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();

        String query = "SELECT Appointment_ID, Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID FROM appointments";

        PreparedStatement ps = DBConnection.connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int appointmentID = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            LocalDateTime startDateTime = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime endDateTime = rs.getTimestamp("End").toLocalDateTime();
            int apptCustomerID = rs.getInt("Customer_ID");
            int apptContactID = rs.getInt("Contact_ID");
            Appointments appointment = new Appointments(appointmentID, title, description, location, type, startDateTime, endDateTime, apptCustomerID, apptContactID, apptCustomerID);
            allAppointments.add(appointment);}

            return allAppointments;


    }
}
