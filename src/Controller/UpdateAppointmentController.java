package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class UpdateAppointmentController {


        @FXML
        private ComboBox<?> updateAppointmentContact;

        @FXML
        private ChoiceBox<?> updateAppointmentCustomerId;

        @FXML
        private TextField updateAppointmentDescription;

        @FXML
        private DatePicker updateAppointmentEndDate;

        @FXML
        private ChoiceBox<?> updateAppointmentEndTime;

        @FXML
        private TextField updateAppointmentId;

        @FXML
        private TextField updateAppointmentLocation;

        @FXML
        private DatePicker updateAppointmentStartDate;

        @FXML
        private ChoiceBox<?> updateAppointmentStartTime;

        @FXML
        private TextField updateAppointmentTitle;

        @FXML
        private ComboBox<?> updateAppointmentType;

        @FXML
        private ChoiceBox<?> updateAppointmentUserId;

        @FXML
        void cancelUpdateAppointmentButton(ActionEvent event) throws IOException {

            Parent parent = FXMLLoader.load(getClass().getResource("/View/Dashboard.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();


        }

        @FXML
        void saveUpdatedAppointmentButton(ActionEvent event) {

        }

    }


