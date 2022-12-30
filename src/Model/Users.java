package Model;

/** A class to model the users table in the database. */

public class Users {

    int userID;
    String userName;
    String passwordText;

    /** Creates a new user object.
     * @param userID
     * @param userName
     * @param passwordText
     * */

    public Users(int userID, String userName, String passwordText) {
        this.userID = userID;
        this.userName = userName;
        this.passwordText= passwordText;

    }

    /** A user constructor with no parameters*/

    public Users() {

    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswordText() {
        return passwordText;
    }

    public void setPasswordText(String passwordText) {
        this.passwordText = passwordText;
    }

    /** Displays the user object as a string of user ID and user name in the combo box. */
    @Override
    public String toString(){
      return " ["+ userID +" ]" +  userName;

    }

}
