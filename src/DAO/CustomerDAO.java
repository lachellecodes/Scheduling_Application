package DAO;

import Model.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class CustomerDAO {

    private static ObservableList<Customers> allCustomers = FXCollections.observableArrayList();

    public static ObservableList<Customers> getAllCustomers() throws SQLException {

        String query = "SELECT * FROM client_schedule.customers; ";
        PreparedStatement ps = DBConnection.connection.prepareStatement(query);
        ResultSet rs= ps.executeQuery();
        while (rs.next()){
            //TODO where is the customer ID?
            int customerId = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");
            String address = rs.getString("Address");
            String postalCode = rs.getString("Postal_Code");
            String  phone = rs.getString("Phone");
            //TODO why does the time not show up?
            LocalDateTime createDate = rs.getTimestamp("Create_Date").toLocalDateTime();
            String createdBy = rs.getString("Created_By");
            LocalDateTime updatedDate= rs.getTimestamp("Last_Update").toLocalDateTime();
            String updatedBy = rs.getString("Last_Updated_By");
            //TODO why does the division not show up?
            int divsionId = rs.getInt("Division_ID");
            Customers customer = new Customers(customerId, customerName, address, postalCode, phone, createDate, createdBy, updatedDate,updatedBy, divsionId );
            allCustomers.add(customer);

        }
        return allCustomers;
    }
}
