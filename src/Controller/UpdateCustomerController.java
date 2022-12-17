package Controller;

import DAO.CountryDAO;
import DAO.CustomerDAO;
import DAO.FirstLevelDivisionsDAO;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UpdateCustomerController implements Initializable {

        @FXML
        private ComboBox<Countries> updateCustomerCountry;

        @FXML
        private ComboBox<FirstLevelDivisions> updateCustomerDivisionID;

        @FXML
        private TextField updateCustomerId;

        @FXML
        private TextField updateCustomerName;

        @FXML
        private TextField updateCustomerPhone;

        @FXML
        private TextField updateCustomerPostalCode;

        @FXML
        private TextField updateCustomerStreetAddress;

        @FXML
        private AnchorPane updateName;

        private ObservableList<Countries> countryList = FXCollections.observableArrayList();

        private ObservableList<FirstLevelDivisions> divisionList = FXCollections.observableArrayList();




        @FXML
        void cancelUpdateCustomerButton(ActionEvent event) throws IOException {

                Parent parent = FXMLLoader.load(getClass().getResource("/View/CustomerInfo.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();

        }

        @FXML
        void onUpdateCountryComboAction(ActionEvent event) {

                try {
                        Countries selectedCountry = updateCustomerCountry.getSelectionModel().getSelectedItem();
                        divisionList = FirstLevelDivisionsDAO.getAllDivisions();
                        ObservableList<FirstLevelDivisions> divisionsByCountry = FXCollections.observableArrayList();

                        for(FirstLevelDivisions firstLevelDivsions : divisionList ){

                                if(firstLevelDivsions.getFirstLevelDivisionsCountryID() == selectedCountry.getCountryId()){
                                        divisionsByCountry.add(firstLevelDivsions);
                                }
                                updateCustomerDivisionID.setItems(divisionsByCountry);

                        }
                } catch (SQLException throwables) {
                        throwables.printStackTrace();
                }

        }

        public  void setUpdatedCustomerValues(Customers selectedCustomer){



                updateCustomerId.setText(String.valueOf(selectedCustomer.getCustomerId()));
                updateCustomerName.setText(String.valueOf(selectedCustomer.getCustomerName()));
                updateCustomerPhone.setText(String.valueOf(selectedCustomer.getPhone()));
                updateCustomerStreetAddress.setText(String.valueOf(selectedCustomer.getAddress()));
                updateCustomerPostalCode.setText(String.valueOf(selectedCustomer.getPostalCode()));


        }

        @FXML
        void updateCustomerSaveButton(ActionEvent event) throws IOException {

                try {
                        int customerId = Integer.parseInt(updateCustomerId.getText());
                        String customerName = updateCustomerName.getText();
                        String phone = updateCustomerPhone.getText();
                        String address = updateCustomerStreetAddress.getText();
                        String postalCode = updateCustomerPostalCode.getText();
                        String country = updateCustomerCountry.getSelectionModel().getSelectedItem().toString();
                        int divisionId = updateCustomerDivisionID.getSelectionModel().getSelectedItem().getDivisionID();

                        Customers updatedCustomer = new Customers(customerId, customerName, address, postalCode, phone, country, divisionId);
                        updatedCustomer.setCustomerId(customerId);

                        if (!customerName.isBlank() || !phone.isBlank() || !address.isBlank() || !postalCode.isBlank() || !divisionList.isEmpty() || !country.isBlank()) {

                                CustomerDAO.updateCustomer(updatedCustomer);

                                Parent parent = FXMLLoader.load(getClass().getResource("/View/CustomerInfo.fxml"));
                                Scene scene = new Scene(parent);
                                Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
                                stage.setScene(scene);
                                stage.show();
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
                        updateCustomerCountry.setItems(countryList);
                } catch (SQLException throwables) {
                        throwables.printStackTrace();
                }

        }
}





