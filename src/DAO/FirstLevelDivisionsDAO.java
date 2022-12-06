package DAO;

import Model.Countries;
import Model.FirstLevelDivisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FirstLevelDivisionsDAO {

    /** A method to create a list of division IDs.
     * Creates an observable list , uses a prepared statement to execute a query that retrieves all division IDs from the database.
     * Creates new division object and adds it to the observable list.
     *
     * @return the observable list of division IDs */

    public static ObservableList<FirstLevelDivisions> getAllDivisions () throws SQLException {

        ObservableList<FirstLevelDivisions> allDivisions = FXCollections.observableArrayList();

        String query = "SELECT Division_ID, Division, Country_ID FROM first_level_divisions";
        PreparedStatement ps = DBConnection.connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int divisionID = rs.getInt("Division_ID");
            String divisionName = rs.getString("Division");
            int firstLevelDivisionsCountryID = rs.getInt("Country_ID");
            FirstLevelDivisions divisions = new FirstLevelDivisions (divisionID, divisionName, firstLevelDivisionsCountryID);
            allDivisions.add(divisions);
        }
        return allDivisions;

    }
}
