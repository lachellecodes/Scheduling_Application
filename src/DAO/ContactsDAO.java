package DAO;

import Model.Contacts;
import Model.Countries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** A DAO class for interacting with data on the contacts table in the database. */

public class ContactsDAO {

    /** A method to return a list of all contacts.
     * @throws SQLException*/

    public static ObservableList<Contacts> getAllContacts () throws SQLException {

        ObservableList<Contacts> allContacts = FXCollections.observableArrayList();

        String query = "SELECT Contact_ID, Contact_Name, Email FROM contacts";
        PreparedStatement ps = DBConnection.connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int contactID = rs.getInt("Contact_ID");
            String contactName = rs.getString("Contact_Name");
            String contactEmail = rs.getString("Email");
            Contacts contact = new Contacts (contactID, contactName, contactEmail);
            allContacts.add(contact);
        }
        return allContacts;

    }
}

