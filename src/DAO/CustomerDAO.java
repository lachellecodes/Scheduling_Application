package DAO;

import Model.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDateTime;

public class CustomerDAO {

    /** A method to get a list of all the customers.
     * Declares an observable list of all customers.
     * Uses a prepared statement to get a list of all customers currently in the database.
     * Creates a new customer object and adds it to the list.
     * @return  an observable list of all customers.
     * */


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
            int divisionId = rs.getInt("Division_ID");
            Customers customer = new Customers( customerId, customerName, address, postalCode, phone,country, divisionId );
            allCustomers.add(customer);

        }
        return allCustomers;
    }

    /** A method to add a new customer to the database.
     * Uses a prepared statement to do a insert into the database with SQL insert statement.
     * */

    public static void addNewCustomer(Customers customers) throws SQLException {

        try {
            String query = "INSERT INTO customers ( Customer_Name, Address, Postal_Code, Phone, Create_Date,"+" " +
                    " Created_By, Last_Update, Last_Updated_By, Division_ID) VALUES ( ?, ?, ?, ?, ? , ? , ?, ?, ?) ";

            PreparedStatement ps = DBConnection.connection.prepareStatement(query);

            ps.setString(1, customers.getCustomerName());
            ps.setString(2, customers.getAddress());
            ps.setString(3, customers.getPostalCode());
            ps.setString(4, customers.getPhone());
            ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(6, "admin");
            ps.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(8,"admin");
            ps.setInt(9, customers.getDivisionId());
            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void updateCustomer(Customers customers) throws SQLException {

        try {
            String updateQuery = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ? , "+
                    " Create_Date = ?, Created_By = ?, Last_Update = ?,Last_Updated_By = ?, Division_ID = ? WHERE Customer_ID = ?";

            PreparedStatement ps = DBConnection.connection.prepareStatement(updateQuery);

            ps.setString(1, customers.getCustomerName());
            ps.setString(2, customers.getAddress());
            ps.setString(3, customers.getPostalCode());
            ps.setString(4, customers.getPhone());
            ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(6, "admin");
            ps.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(8, "admin");
            ps.setInt(9,customers.getDivisionId());
            ps.setInt(10, customers.getCustomerId());
            ps.execute();



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
