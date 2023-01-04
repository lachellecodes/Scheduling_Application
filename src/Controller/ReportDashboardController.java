package Controller;

import DAO.AppointmentDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

/** A controller class that providers logic for the report dashboard, allows user to click on one of 3 reports to view report.*/

public class ReportDashboardController {

    /** Takes user to the Report By Contact screen when button is selected.
     * @param event */


        @FXML
        void reportsApptsByContact(ActionEvent event) throws IOException {
            Parent parent = FXMLLoader.load(getClass().getResource("/View/ReportsByContact.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();


        }

        /** Takes user to the Report By Month and Type screen once button is clicked.
         * @param event */

        @FXML
        void reportsApptsByTypeMonth(ActionEvent event) throws IOException, SQLException {
            AppointmentDAO.appointmentsByType();
            Parent parent = FXMLLoader.load(getClass().getResource("/View/ReportsByMonthType.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        }

        /** Takes user to the Report by Country once button is clicked.
         * @param event */

        @FXML
        void reportsApptsByUser(ActionEvent event) throws IOException {
            Parent parent = FXMLLoader.load(getClass().getResource("/View/ReportsByCountry.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        }

        /** Takes user back to the main dashboard if button is clicked.
         * @param event */

        @FXML
        void reportsHomeButton(ActionEvent event) throws IOException {

            Parent parent = FXMLLoader.load(getClass().getResource("/View/Dashboard.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        }

    }

