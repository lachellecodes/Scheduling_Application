package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class NewCustomerController {


        @FXML
        private TextField addNewCustomerAddress;

        @FXML
        private TextField addNewCustomerCity;

        @FXML
        private ChoiceBox<?> addNewCustomerCountry;

        @FXML
        private ChoiceBox<?> addNewCustomerDivisionId;

        @FXML
        private TextField addNewCustomerId;

        @FXML
        private TextField addNewCustomerName;

        @FXML
        private TextField addNewCustomerPhone;

        @FXML
        private TextField addNewCustomerPostalCode;

        @FXML
        private TextField addNewCustomerStateProvince;

        @FXML
        void cancelAddNewCustomer(ActionEvent event) throws IOException {

            Parent parent = FXMLLoader.load(getClass().getResource("/View/CustomerInfo.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        }

        @FXML
        void saveAddNewCustomer(ActionEvent event) {


        }

    }

