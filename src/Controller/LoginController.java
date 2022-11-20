package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
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
        void loginButton(ActionEvent event) {

        }

    Locale currentLocale = Locale.getDefault();

    ResourceBundle rb = ResourceBundle.getBundle("Resources/Nat_en.properties\n" +
            "Resources/Nat_fr.properties", currentLocale);

    private void setLanguage () {

            userIdLabel.setText(rb.getString("userIdLabel"));
            passwordLabel.setText(rb.getString("passwordLabel"));
            zoneIdLabel.setText(rb.getString("zoneIdLabel"));

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        setLanguage();

    }
}


