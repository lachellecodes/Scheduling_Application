package Controller;

import DAO.CountryDAO;
import Model.Countries;
import Model.FirstLevelDivisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import static DAO.CountryDAO.getCountries;

public class NewCustomerController {


        @FXML
        private TextField addNewCustomerAddress;

        @FXML
        private TextField addNewCustomerCity;

        @FXML
        private ComboBox<Countries> addNewCustomerCountry;

        @FXML
        private ComboBox<FirstLevelDivisions> addNewCustomerDivisionId;

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

        private ObservableList <Countries> countryList = FXCollections.observableArrayList();

        @FXML
        void cancelAddNewCustomer(ActionEvent event) throws IOException {

            Parent parent = FXMLLoader.load(getClass().getResource("/View/CustomerInfo.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        }

        @FXML
        void countryComboBoxAction(ActionEvent event) throws SQLException {

                countryList = CountryDAO.getCountries();
                addNewCustomerCountry.setItems(countryList);


            Countries selectedCountry = addNewCustomerCountry.getSelectionModel().getSelectedItem();

        }

        @FXML
        void saveAddNewCustomer(ActionEvent event) {


        }

    }

