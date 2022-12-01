package Controller;

import Model.Customers;
import DAO.CustomerDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class CustomerInfoController implements Initializable {


    @FXML
    private TableView<Customers> customerTableView;

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


    @FXML
        void addCustomerButton(ActionEvent event) throws IOException {
            Parent parent = FXMLLoader.load(getClass().getResource("/View/AddCustomer.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        }

        @FXML
        void customerHomeButton(ActionEvent event) throws IOException {
            Parent parent = FXMLLoader.load(getClass().getResource("/View/Dashboard.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();


        }

        @FXML
        void deleteCustomerButton(ActionEvent event) {

        }

        @FXML
        void updateCustomerButton(ActionEvent event) throws IOException {
            Parent parent = FXMLLoader.load(getClass().getResource("/View/UpdateCustomer.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();


        }



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
        customerTableDivisionId.setCellValueFactory(new PropertyValueFactory<>("divsionId"));
        customerTableCountry.setCellValueFactory(new PropertyValueFactory<>("country"));



    }




}


