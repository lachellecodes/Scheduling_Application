package DAO;

import Model.Countries;
import Model.FirstLevelDivisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FirstLevelDivsionsDAO  {



    public static ObservableList<FirstLevelDivisions> getAllDivisions () throws SQLException {

        ObservableList<FirstLevelDivisions> allDivisions = FXCollections.observableArrayList();

        String query = "SELECT Division_ID, Division, Country_ID FROM first_level_divisions";
        PreparedStatement ps = DBConnection.connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int divisionID = rs.getInt("Division_ID");
            String divsionName = rs.getString("Division");
            int firstLevelDivsionsCountryID = rs.getInt("Country_ID");
            FirstLevelDivisions divisions = new FirstLevelDivisions (divisionID, divsionName, firstLevelDivsionsCountryID);
            allDivisions.add(divisions);
        }
        return allDivisions;

    }
}
