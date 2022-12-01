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


    public static ObservableList<Customers> getAllCustomers() throws SQLException {

        ObservableList<Customers> allCustomers = FXCollections.observableArrayList();

        String query = "SELECT * FROM customers, first_level_divisions, countries \n" +
                        "WHERE customers.Division_ID = first_level_divisions.Division_ID \n" +
                        "AND countries.Country_ID = first_level_divisions.Country_ID";
        PreparedStatement ps = DBConnection.connection.prepareStatement(query);
        ResultSet rs= ps.executeQuery();
        while (rs.next()){
            int customerId = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");
            String address = rs.getString("Address");
            String postalCode = rs.getString("Postal_Code");
            String  phone = rs.getString("Phone");
            String country = rs.getString("Country");
            int divsionId = rs.getInt("Division_ID");
            Customers customer = new Customers(customerId, customerName, address, postalCode, phone,  divsionId, country);
            allCustomers.add(customer);

        }
        return allCustomers;
    }
}
