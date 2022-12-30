package Controller;

import DAO.AppointmentDAO;
import Model.Customers;
import DAO.CustomerDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

/** This class is a controller for the customer dashboard screen that provides logic for displaying, adding, deleting and updating
 * the customer tableview.*/

public class CustomerInfoController implements Initializable {


    @FXML
    private  TableView<Customers> customerTableView;

    @FXML
    private TableColumn<Customers, String> customerTableAddress;

    @FXML
    private TableColumn <Customers,String> customerTableCountry;


    @FXML
    private TableColumn<Customers, Integer> customerTableDivisionId;

    @FXML
    private TableColumn<Customers, Integer> customerTableId;


    @FXML
    private TableColumn<Customers, String> customerTableName;

    @FXML
    private TableColumn<Customers, String> customerTablePhone;

    @FXML
    private TableColumn<Customers, String> customerTablePostalCode;

    Stage stage;
    Parent scene;



    /** Loads the Add New Customer screen when New button is clicked.
     * @param event
     * */
    @FXML
        void addCustomerButton(ActionEvent event) throws IOException {
            Parent parent = FXMLLoader.load(getClass().getResource("/View/AddCustomer.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        }

        /** Loads the home screen of the application when the home button is clicked.
         * @param event
         * */

        @FXML
        void customerHomeButton(ActionEvent event) throws IOException {
            Parent parent = FXMLLoader.load(getClass().getResource("/View/Dashboard.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();


        }
        /** Deletes the selected customer and all appointments associated with this customer.
         * User must confirm delete.
         * Refreshes the customer table view after the customer is deleted.
         * @param event
         *
         * */

        @FXML
        void deleteCustomerButton(ActionEvent event) throws SQLException {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete this customer and all appointments associated with this customer?");
            Optional<ButtonType> confirm = alert.showAndWait();
            if (confirm.isPresent() && confirm.get() == ButtonType.OK) {
                int customerToDelete = customerTableView.getSelectionModel().getSelectedItem().getCustomerId();

                AppointmentDAO.deleteAppointment(customerToDelete);
                CustomerDAO.deleteCustomer(customerToDelete);

                customerTableView.refresh();

            }
        }

        /** Gets the selected customer from the customers tableview and loads that customer into the update customers screen.
         * @param event */
        @FXML
        void updateCustomerButton(ActionEvent event) throws IOException {

           Customers selectedCustomer = customerTableView.getSelectionModel().getSelectedItem();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/View/UpdateCustomer.fxml"));
            Parent root = loader.load();

            UpdateCustomerController modifyCustomer = loader.getController();
            modifyCustomer.setUpdatedCustomerValues(selectedCustomer);

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();


        }



    /** Initializes the table view for this screen with all of the customer info.
     * @param resourceBundle
     * @param url
     * Calls the getAllCustomers method to get customer info.
     * The table columns are set to the appropriate values.*/

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            customerTableView.setItems(CustomerDAO.getAllCustomers());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        customerTableId.setCellValueFactory( new PropertyValueFactory<>("customerId"));
        customerTableName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerTableAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        customerTablePostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        customerTablePhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        customerTableDivisionId.setCellValueFactory(new PropertyValueFactory<>("divisionId"));
        customerTableCountry.setCellValueFactory(new PropertyValueFactory<>("country"));



    }




}


