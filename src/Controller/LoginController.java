package Controller;

import DAO.AppointmentDAO;
import DAO.DBConnection;
import DAO.UserDaoImpl;
import Model.Appointments;
import Model.Users;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;

/** This class is a controller for the log in screen and provides all logic for sign in actions, error messages and local time zone. */

public class LoginController implements Initializable {


        @FXML
        private Label passwordLabel;

        @FXML
        private Label userIdLabel;

        @FXML
        private Label zoneIdLabel;

        @FXML
        private Label loginTitleLabel;

        @FXML
        private PasswordField passwordText;

        @FXML
        private TextField userIDtext;

        @FXML
        private TextField zoneText;

        @FXML
        private Button loginButton;

        private ObservableList<Appointments> allAppointments= AppointmentDAO.getAllAppointments();

        /** Gets the default time zone for the user's locale.*/
        private  TimeZone userTimeZone = TimeZone.getDefault();

        Users currentUser;

    public LoginController() throws SQLException {
    }

    /** Loads the next screen (Dashboard) after log in if user is successfully verified. */
        public void loadDashboard (ActionEvent event) throws IOException {
            Parent parent = FXMLLoader.load(getClass().getResource("/View/Dashboard.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
        /** A method to verify if user enters the correct information. Gets text entered into the username and password field on
         * the log in form. Uses a SQL prepared statement to match these strings against database. If there is a match, returns
         * a true Boolean variable. */
        public Boolean validateUser () throws SQLException {
            
            Boolean userVerified = false;

            String user = userIDtext.getText();
            String password = passwordText.getText();


            String verifyLogin = "SELECT * FROM users WHERE User_Name = '" + user + "' AND Password  = '"+password + "'";

            try{

             PreparedStatement ps= DBConnection.connection.prepareStatement(verifyLogin);
             ResultSet rs = ps.executeQuery();

             while(rs.next()){

                 String userName = rs.getString("User_Name");
                 String userPassword = rs.getString("Password");

                 userVerified = true;}

            }
            catch (Exception e){
                e.printStackTrace();
            }
            return userVerified;

        }
        /** Method that handles the login in button action. Displays an alert message if the user ID and password fields are blank.
         * Calls the validateUser method to validate credentials. If user is validated calls the loadDashboard method to go
         * to the next screen. If user is not validated, displays an error message to enter a valid user ID and password.*/
        @FXML
        void loginButton(ActionEvent event) throws SQLException, IOException {

            if (userIDtext.getText().isBlank() == true || passwordText.getText().isBlank() == true) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(rb.getString("alertTitle"));
                alert.setHeaderText(rb.getString("errorMessage1"));
                alert.show();
            } else {
                if (validateUser()) {
                    loadDashboard(event);

                    for(Appointments appointments: allAppointments){

                        String userName= userIDtext.getText();
                        currentUser = UserDaoImpl.getUserById(userName);
                        int currentUserID = currentUser.getUserID();
                        LocalDateTime start = appointments.getStartDateTime();
                        LocalDateTime now = LocalDateTime.now();
                       // ZoneId zoneId= ZoneId.systemDefault();
                       // ZonedDateTime zonedDateTime= ZonedDateTime.of(start, zoneId);

                        //ZoneId  utcZone = ZoneId.of("UTC");
                        //ZonedDateTime  utcZoneDT = ZonedDateTime.ofInstant(zonedDateTime.toInstant(), utcZone);
                        boolean appointmentOverlap = false;


                        if (currentUserID == appointments.getApptUserID() && start.isBefore(now.plusMinutes(15))){

                            appointmentOverlap= true;}

                        if (appointmentOverlap){


                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Warning");
                            alert.setHeaderText(" You have an appointment starting within the next 15 minutes.");
                            alert.show();

                        }

                        else {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setHeaderText("You have no upcoming appointments within the next 15 minutes. ");
                            alert.show();
                            break;
                        }

                    }

                }
                    else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(rb.getString("alertTitle"));
                    alert.setHeaderText(rb.getString("errorMessage2"));
                    alert.show();
                }

            }
        }
    /** Gets the users current locale. */
    Locale currentLocale = Locale.getDefault();

    /** Specifies location of resource bundle for supported languages.*/
    ResourceBundle rb = ResourceBundle.getBundle("Resources/Nat", currentLocale);


    /** A getter for the users current Time Zone. */
    public TimeZone getUserTimeZone(){
        return userTimeZone;
    }

    /** A method to get the language on the login in screen to users locale. */
    private void setLanguage () {

            userIdLabel.setText(rb.getString("userIdLabel"));
            passwordLabel.setText(rb.getString("passwordLabel"));
            zoneIdLabel.setText(rb.getString("zoneIdLabel"));
            loginTitleLabel.setText(rb.getString("loginTitleLabel"));
            loginButton.setText(rb.getString("loginButton"));


    }

    /*public boolean appointmentReminderNotification ( Appointments appointments) throws SQLException {

       ObservableList <Appointments> allAppointments = AppointmentDAO.getAllAppointments();

        LocalDateTime currentTime = LocalDateTime.now();
        Boolean appointmentNotify = false;
    }*/



    /** Upon initialization of the login screen calls the setLanguage method to set Text to default locale language.
     * Calls method to get users Time Zone and sets the text in the Time Zone box to that. */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        setLanguage();
        getUserTimeZone();
        zoneText.setText(userTimeZone.getID());



    }
}


