package Model;

public class Users {

    int userID;
    String userName;
    String passwordText;

    public Users(int userID, String userName, String passwordText) {
        this.userID = userID;
        this.userName = userName;
        this.passwordText= passwordText;

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

    @Override
    public String toString(){
      return " ["+ userID +" ]" +  userName;

    }
}
