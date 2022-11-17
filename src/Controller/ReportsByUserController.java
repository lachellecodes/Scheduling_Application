package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class ReportsByUserController {


        @FXML
        private TableView<?> apptsByUsersTableView;

        @FXML
        private TableColumn<?, ?> users;

        @FXML
        private TableColumn<?, ?> usersApptCustomerId;

        @FXML
        private TableColumn<?, ?> usersApptDescription;

        @FXML
        private TableColumn<?, ?> usersApptEndDate;

        @FXML
        private TableColumn<?, ?> usersApptEndTime;

        @FXML
        private TableColumn<?, ?> usersApptId;

        @FXML
        private TableColumn<?, ?> usersApptStartDate;

        @FXML
        private TableColumn<?, ?> usersApptStartTime;

        @FXML
        private TableColumn<?, ?> usersApptTitle;

        @FXML
        private TableColumn<?, ?> usersApptType;

        @FXML
        void backToReportsButtonFromApptByUsers(ActionEvent event) throws IOException {

            Parent parent = FXMLLoader.load(getClass().getResource("/View/ReportsDashboard.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        }

        @FXML
        void homeButtonFromApptByUsers(ActionEvent event) throws IOException {

            Parent parent = FXMLLoader.load(getClass().getResource("/View/ReportsDashboard.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        }

    }


