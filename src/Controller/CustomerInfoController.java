package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerInfoController {

        @FXML
        private TableView<?> customerTableView;

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

}

