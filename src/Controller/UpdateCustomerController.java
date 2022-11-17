package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class UpdateCustomerController {




        @FXML
        private TextField updateCustomerCity;

        @FXML
        private ChoiceBox<?> updateCustomerCountry;

        @FXML
        private ChoiceBox<?> updateCustomerDivisonId;

        @FXML
        private TextField updateCustomerId;

        @FXML
        private TextField updateCustomerName;

        @FXML
        private TextField updateCustomerPhone;

        @FXML
        private TextField updateCustomerPostalCode;

        @FXML
        private TextField updateCustomerStateProvince;

        @FXML
        private TextField updateCustomerStreetAddress;

        @FXML
        private AnchorPane updateName;

        @FXML
        void cancelUpdateCustomerButton(ActionEvent event) throws IOException {

            Parent parent = FXMLLoader.load(getClass().getResource("/View/CustomerInfo.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        }

        @FXML
        void updateCustomerSaveButton(ActionEvent event) {

        }

    }


