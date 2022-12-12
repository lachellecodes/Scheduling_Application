package DAO;

import Model.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl {

    public static ObservableList<Users> getAllUsers() throws SQLException {

        ObservableList<Users> allUsers = FXCollections.observableArrayList();

        String query = "SELECT User_ID, User_Name, Password FROM users";
        PreparedStatement ps = DBConnection.connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            int userID = rs.getInt("User_ID");
            String userName = rs.getString("User_Name");
            String passwordText = rs.getString("Password");
            Users user = new Users(userID, userName, passwordText);
            allUsers.add(user);
        }

            return allUsers;



    }
}
