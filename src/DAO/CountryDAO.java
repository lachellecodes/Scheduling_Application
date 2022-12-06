package DAO;

import Model.Countries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryDAO {


    /** A method to create a list of countries.
     * Declares an observable list of all countries.
     * Uses a prepared statement to get a list of all countries from the database.
     * Creates a new country object.
     * @return an observable list of countries*/

    public static ObservableList<Countries> getAllCountries () throws SQLException {

        ObservableList<Countries> allCountries = FXCollections.observableArrayList();

        String query = "SELECT Country_ID, Country FROM countries";
        PreparedStatement ps = DBConnection.connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int countryId = rs.getInt("Country_ID");
            String countryName = rs.getString("Country");
            Countries country = new Countries (countryId, countryName);
            allCountries.add(country);
        }
        return allCountries;

    }
}
