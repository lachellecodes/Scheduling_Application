package DAO;

import Model.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** A DAO class for interacting with the user table in the database. */

public class UserDaoImpl {

    /** A method to get a list of all users in the database.
     * @return an observable list of all users in the database.
     * @throws SQLException
     **/

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
    /** A method to return a speccific user by ID numner.
     * @param currentUserName  takes in the user name string
     * @return a user
     * @throws SQLException*/


    public static Users getUserById (String currentUserName) throws SQLException {

        Users currentUser= new Users();

        ObservableList<Users> usersById = getAllUsers();

        String query = "SELECT * from users WHERE User_Name =? ";
        PreparedStatement ps =DBConnection.connection.prepareStatement(query);

        ps.setString(1, currentUserName);
        ps.execute();
        ResultSet rs = ps.getResultSet();
        if (rs.next()){


                currentUser.setUserID(rs.getInt("User_ID"));
               currentUser.setUserName( rs.getString("User_Name"));
               currentUser.setPasswordText(rs.getString("Password"));


            }

        return currentUser ;
    }
}
