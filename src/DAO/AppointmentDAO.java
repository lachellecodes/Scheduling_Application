package DAO;

import Model.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDateTime;

/** A DAO class for performing operations on the appointment table in the database. */

public class AppointmentDAO {

    /** This method returns an observable list of all the appointments in the database.
     *
     * @return a list of all appointments
     * @throws SQLException
     * */

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
            int apptUserID = rs.getInt("User_ID");
            int apptContactID = rs.getInt("Contact_ID");
            Appointments appointment = new Appointments(appointmentID, title, description, location, type, startDateTime, endDateTime, apptCustomerID, apptUserID, apptContactID);
            allAppointments.add(appointment);}

            return allAppointments;


    }

    /** Adds a new appointment to the appointment table.
     * @param appointments
     * @throws SQLException
     * */

    public static void addNewAppointment(Appointments appointments) throws SQLException {

        try {
            String newApptQuery = "INSERT INTO appointments ( Title, Description, Location, Type, Start, End, Create_Date, "+" " +
                    "Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) VALUES ( ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

            PreparedStatement ps =DBConnection.connection.prepareStatement(newApptQuery);


            ps.setString(1, appointments.getTitle());
            ps.setString(2, appointments.getDescription());
            ps.setString(3,appointments.getLocation());
            ps.setString(4, appointments.getType());
            ps.setTimestamp(5, Timestamp.valueOf(appointments.getStartDateTime()));
            ps.setTimestamp(6, Timestamp.valueOf(appointments.getEndDateTime()));
            ps.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(8, "admin");
            ps.setTimestamp(9, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(10, "admin");
            ps.setInt(11, appointments.getApptCustomerID());
            ps.setInt(12, appointments.getApptUserID());
            ps.setInt(13, appointments.getApptContactID());
            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /** Updates an existing appointment in the database.
     * @param appointments
     * @throws SQLException
     * */


    public static void updateAppointment(Appointments appointments) throws SQLException {

        try {
            String updateQuery = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?,"+"" +
                    "Create_Date =?, Created_By =?,Last_Update =?, Last_Updated_By =?,Customer_ID =?, User_ID =?, Contact_ID =? WHERE Appointment_ID=? ";

            PreparedStatement ps = DBConnection.connection.prepareStatement(updateQuery);

            ps.setString(1, appointments.getTitle());
            ps.setString(2, appointments.getDescription());
            ps.setString(3, appointments.getLocation());
            ps.setString(4, appointments.getType());
            ps.setTimestamp(5, Timestamp.valueOf(appointments.getStartDateTime()));
            ps.setTimestamp(6, Timestamp.valueOf(appointments.getEndDateTime()));
            ps.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(8, "admin");
            ps.setTimestamp(9, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(10, "admin");
            ps.setInt(11, appointments.getApptCustomerID());
            ps.setInt(12, appointments.getApptUserID());
            ps.setInt(13, appointments.getApptContactID());
            ps.setInt(14, appointments.getAppointmentID());
            ps.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    /** Deletes all appointments associated with a specific customer.
     * @param customerToDelete takes the in the customer ID of the appointment.
     * @throws SQLException from querying the database for appointments by customer ID.
     * */

    public static void deleteAppointment (int customerToDelete) throws SQLException {

        getAllAppointments();

        String deleteQuery = "DELETE from appointments WHERE Customer_ID =?";
        PreparedStatement ps = DBConnection.connection.prepareStatement(deleteQuery);
        ps.setInt(1, customerToDelete);
        ps.execute();

    }

    /** Deletes a single appointment from the main table view by appointment ID.
     * @param appointmentToDelete  takes in the appointment ID number.
     * */

    public static void deleteSingleAppointment (int appointmentToDelete) throws SQLException {

        getAllAppointments();

        String deleteQuery = "DELETE from appointments WHERE Appointment_ID =?";
        PreparedStatement ps = DBConnection.connection.prepareStatement(deleteQuery);
        ps.setInt(1, appointmentToDelete);
        ps.execute();
    }

    /** Returns a list of appointments filetered by country ID
     * @param selectedCountry takes in the country ID from the combo box
     * @throws SQLException from the query on 4 tables in the database.
     * */
    public static ObservableList<Appointments> appointmentsByCountry(int selectedCountry) throws SQLException {

        getAllAppointments();

        ObservableList<Appointments> appointmentsByCountry = FXCollections.observableArrayList();

        String query = "SELECT * from appointments join customers on appointments.Customer_ID = customers.Customer_ID join first_level_divisions on customers.Division_ID = first_level_divisions.Division_ID join countries on countries.Country_ID = first_level_divisions.Country_ID WHERE countries.Country_ID = ?;";

        PreparedStatement ps = DBConnection.connection.prepareStatement(query);
        ps.setInt(1, selectedCountry );

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
            int apptUserID = rs.getInt("User_ID");
            int apptContactID = rs.getInt("Contact_ID");
            Appointments appointment = new Appointments(appointmentID, title, description, location, type, startDateTime, endDateTime, apptCustomerID, apptUserID, apptContactID);
            appointmentsByCountry.add(appointment);}

        return appointmentsByCountry;



    }

}


