package Controller;

import DAO.DBConnection;
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
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;

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


        private  TimeZone userTimeZone = TimeZone.getDefault();


        public void loadDashboard (ActionEvent event) throws IOException {
            Parent parent = FXMLLoader.load(getClass().getResource("/View/Dashboard.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }

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

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(rb.getString("alertTitle"));
                    alert.setHeaderText(rb.getString("errorMessage2"));
                    alert.show();
                }

            }
        }

    Locale currentLocale = Locale.getDefault();

    ResourceBundle rb = ResourceBundle.getBundle("Resources/Nat", currentLocale);

    public TimeZone getUserTimeZone(){
        return userTimeZone;
    }

    private void setLanguage () {

            userIdLabel.setText(rb.getString("userIdLabel"));
            passwordLabel.setText(rb.getString("passwordLabel"));
            zoneIdLabel.setText(rb.getString("zoneIdLabel"));
            loginTitleLabel.setText(rb.getString("loginTitleLabel"));
            loginButton.setText(rb.getString("loginButton"));


    }





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        setLanguage();
        getUserTimeZone();
        zoneText.setText(userTimeZone.getID());



    }
}


