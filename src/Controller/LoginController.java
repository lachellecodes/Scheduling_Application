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
import java.util.Locale;
import java.util.ResourceBundle;

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
        private Text messageText;

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
            System.out.println(verifyLogin);
            try{

             PreparedStatement ps= DBConnection.connection.prepareStatement(verifyLogin);
             ResultSet rs = ps.executeQuery();

             while(rs.next()){
                 System.out.println("Wow");
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
                messageText.setText("Please enter a username and password.");
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a username and password.");
                alert.show();
            } else {
                if (validateUser()) {
                    loadDashboard(event);

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, " Invalid username or password. Please try again.");
                    alert.show();
                }

            }
        }

    Locale currentLocale = Locale.getDefault();

    ResourceBundle rb = ResourceBundle.getBundle("Resources/Nat", currentLocale);

    private void setLanguage () {

            userIdLabel.setText(rb.getString("userIdLabel"));
            passwordLabel.setText(rb.getString("passwordLabel"));
            zoneIdLabel.setText(rb.getString("zoneIdLabel"));
            loginTitleLabel.setText(rb.getString("loginTitleLabel"));


    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        setLanguage();

    }
}


