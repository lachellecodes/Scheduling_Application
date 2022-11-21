package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.PreparedStatement;
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

        public void validateUser ()  {

            String verifyLogin = "SELECT count(1) FROM users WHERE User_Name = '" + userIDtext.getText()+"' AND Password  = '"+passwordText.getText() +"'";


        }

        @FXML
        void loginButton(ActionEvent event) {

            if(userIDtext.getText().isBlank()== false && passwordText.getText().isBlank()==false)
            {
                Alert alert = new Alert (Alert.AlertType.ERROR, "Please enter a username and password.");
                alert.showAndWait();
            }

            else

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


