package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ReportDashboardController {


        @FXML
        void reportsApptsByContact(ActionEvent event) throws IOException {
            Parent parent = FXMLLoader.load(getClass().getResource("/View/ReportsByContact.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();


        }

        @FXML
        void reportsApptsByTypeMonth(ActionEvent event) throws IOException {
            Parent parent = FXMLLoader.load(getClass().getResource("/View/ReportsByMonthType.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        }

        @FXML
        void reportsApptsByUser(ActionEvent event) throws IOException {
            Parent parent = FXMLLoader.load(getClass().getResource("/View/ReportsByCountry.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        }

        @FXML
        void reportsHomeButton(ActionEvent event) throws IOException {

            Parent parent = FXMLLoader.load(getClass().getResource("/View/Dashboard.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        }

    }

