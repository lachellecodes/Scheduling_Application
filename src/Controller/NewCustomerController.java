package Controller;

import DAO.CountryDAO;
import DAO.CustomerDAO;
import DAO.FirstLevelDivsionsDAO;
import Model.Countries;
import Model.Customers;
import Model.FirstLevelDivisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class NewCustomerController implements Initializable {


        @FXML
        private TextField addNewCustomerAddress;



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



        private ObservableList<Countries> countryList = FXCollections.observableArrayList();

        private ObservableList<FirstLevelDivisions> divisionList = FXCollections.observableArrayList();

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

                try {
                        Countries selectedCountry = addNewCustomerCountry.getSelectionModel().getSelectedItem();
                        divisionList = FirstLevelDivsionsDAO.getAllDivisions();
                        ObservableList<FirstLevelDivisions> divisionsByCountry = FXCollections.observableArrayList();

                        for(FirstLevelDivisions firstLevelDivsions : divisionList ){

                                if(firstLevelDivsions.getFirstLevelDivisionsCountryID() == selectedCountry.getCountryId()){
                                        divisionsByCountry.add(firstLevelDivsions);
                                }
                                addNewCustomerDivisionId.setItems(divisionsByCountry);

                        }
                } catch (SQLException throwables) {
                        throwables.printStackTrace();
                }


        }

        @FXML
        void saveAddNewCustomer(ActionEvent event) throws SQLException {

                try {
                        int customerId = 0;
                        String customerName = addNewCustomerName.getText();
                        String phone = addNewCustomerPhone.getText();
                        String address = addNewCustomerAddress.getText();
                        String postalCode = addNewCustomerPostalCode.getText();
                        String country = addNewCustomerCountry.getSelectionModel().getSelectedItem().toString();
                        int divisionId = addNewCustomerDivisionId.getSelectionModel().getSelectedItem().getDivisionID();

                        Customers newCustomer = new Customers(customerId, customerName, phone, address, postalCode, divisionId, country);

                        if (!customerName.isBlank() || !phone.isBlank() || !address.isBlank() || !postalCode.isBlank() || !divisionList.isEmpty() || !country.isBlank()) {
                                CustomerDAO.addNewCustomer(newCustomer);
                        }
                }

                  catch (NullPointerException e)     {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Error");
                                alert.setHeaderText("Please enter a value for each field.");
                                alert.show();

                        }
                 catch (SQLException throwables) {
                        throwables.printStackTrace();
                }


        }

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

                try {
                        countryList = CountryDAO.getAllCountries();
                        addNewCustomerCountry.setItems(countryList);
                } catch (SQLException throwables) {
                        throwables.printStackTrace();
                }



        }
}

