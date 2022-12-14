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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/** A controller class that provides logic for the new customer screen to update a customer info or cancel the action. */

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

        /**Takes the user back to the Customer Info screen with a tableview of all customers if the Cancel button is clicked.
         * @param event
         * */
        @FXML
        void cancelAddNewCustomer(ActionEvent event) throws IOException {

            Parent parent = FXMLLoader.load(getClass().getResource("/View/CustomerInfo.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        }

        /**Sets items for the combo box drop down of countries and then the division combo box by country. Lambda expression filters the entire first level division list by country ID against the country ID
         * from the country list. Returns true if object belongs in the filtered list. 
         * Returns false if the object does not belong in the filtered list. By using a lambda expression, the amount
         * of code needed to filter a list of objects is reduced. This particular example uses the lambda expression to
         * filter out a list of divisions and return those that match a given country ID. This saves time and resources by
         * avoiding the need to loop through an entire loop.
         * @param event
         */

        @FXML
        public void countryComboBoxAction(ActionEvent event) throws SQLException {

                try {
                        Countries selectedCountry = addNewCustomerCountry.getSelectionModel().getSelectedItem();
                        divisionList = FirstLevelDivisionsDAO.getAllDivisions();
                        ObservableList<FirstLevelDivisions> divisionsByCountry = divisionList.filtered( d->{
                                if (d.getFirstLevelDivisionsCountryID()==selectedCountry.getCountryId())
                                        return true;
                                return false;
                        });
                        /*ObservableList<FirstLevelDivisions> divisionsByCountry = FXCollections.observableArrayList();
                        //TODO lambda can be used here instead of for loop review email
                        for(FirstLevelDivisions firstLevelDivsions : divisionList ){

                                if(firstLevelDivisions.getFirstLevelDivisionsCountryID() == selectedCountry.getCountryId()){
                                        divisionsByCountry.add(firstLevelDivsions);
                                }*/
                                addNewCustomerDivisionId.setItems(divisionsByCountry);


                } catch (SQLException throwables) {
                        throwables.printStackTrace();
                }


        }

        /**Gets text from the New Customer form and creates a new customers object.
         * Checks if fields are blank.
         * If all fields are filled in, calls the addNewCustomer method to add the customer to the database.
         * If added successfully, loads the Customer Info screen which shows all tableview list of all customers.
         * @param event
         * */

        @FXML
        void saveAddNewCustomer(ActionEvent event) throws SQLException, IOException {

                try {
                        int customerId = 0;
                        String customerName = addNewCustomerName.getText();
                        String phone = addNewCustomerPhone.getText();
                        String address = addNewCustomerAddress.getText();
                        String postalCode = addNewCustomerPostalCode.getText();
                        String country = addNewCustomerCountry.getSelectionModel().getSelectedItem().toString();
                        int divisionId = addNewCustomerDivisionId.getSelectionModel().getSelectedItem().getDivisionID();

                        Customers newCustomer = new Customers(customerId, customerName, address, postalCode, phone, country, divisionId);

                        if (!customerName.isBlank() || !phone.isBlank() || !address.isBlank() || !postalCode.isBlank() || !divisionList.isEmpty() || !country.isBlank()) {

                                CustomerDAO.addNewCustomer(newCustomer);

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

        /**Initializes this form with a combo box that drops down with a list of countries.
         * @param url
         * @param resourceBundle
         * */

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

