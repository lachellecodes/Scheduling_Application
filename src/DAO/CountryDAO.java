package DAO;

import Model.Countries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryDAO {


    public static ObservableList <Countries> getCountries () throws SQLException {

        ObservableList<Countries> allCountries = FXCollections.observableArrayList();

        String query = "SELECT * FROM client_schedule.countries";
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
