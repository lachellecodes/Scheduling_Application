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

/** A controller class that provides logic for the update customer screen, allows user to update an existing customer's information
 * or cancel the action. */

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


        private ObservableList<Countries> countryList = FXCollections.observableArrayList();

        private ObservableList<FirstLevelDivisions> divisionList = FXCollections.observableArrayList();

        /** Takes user back to the customer dashboard table view if the cancel button is clicked.
         * @param event
         * */

        @FXML
        void cancelUpdateCustomerButton(ActionEvent event) throws IOException {

                Parent parent = FXMLLoader.load(getClass().getResource("/View/CustomerInfo.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();

        }

        /** Populates the country combo box with countries based on the first level divisions.
         * @param event
         * */

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

        /** Gets the selected customer to update and loads their information into the update customer screen.
         * @param selectedCustomer*/

        public  void setUpdatedCustomerValues(Customers selectedCustomer){


                try {
                        Countries country = CountryDAO.getCountryByDivision(selectedCustomer.getDivisionId());
                        FirstLevelDivisions divisions = FirstLevelDivisionsDAO.getDivision(selectedCustomer.getDivisionId());
                        updateCustomerId.setText(String.valueOf(selectedCustomer.getCustomerId()));
                        updateCustomerName.setText(String.valueOf(selectedCustomer.getCustomerName()));
                        updateCustomerPhone.setText(String.valueOf(selectedCustomer.getPhone()));
                        updateCustomerStreetAddress.setText(String.valueOf(selectedCustomer.getAddress()));
                        updateCustomerPostalCode.setText(String.valueOf(selectedCustomer.getPostalCode()));
                        updateCustomerCountry.setItems(CountryDAO.getAllCountries());
                        updateCustomerCountry.setValue(country);
                        updateCustomerDivisionID.setItems(FirstLevelDivisionsDAO.getDivisionsByCountry(country.getCountryId()));
                        updateCustomerDivisionID.setValue(divisions);

                } catch (SQLException throwables) {
                        throwables.printStackTrace();
                }
        }

        /** Gets text from the Update Customer form and updates the selected customer object.
         * Checks if fields are blank.
         * If all fields are filled in, calls the updateCustomer method to add the customer to the database.
         * If added successfully, loads the Customer Info screen which shows all tableview list of all customers.
         * @param event
         * */

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

        /** Initializes this update customer screen with the items in the country and first division drop down combo box list.
         * @param url
         * @param resourceBundle */

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





