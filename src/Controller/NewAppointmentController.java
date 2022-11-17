package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class NewAppointmentController {


        @FXML
        private TextField newAppointmentId;

        @FXML
        private ComboBox<?> newApptContact;

        @FXML
        private ChoiceBox<?> newApptCustomerID;

        @FXML
        private TextField newApptDescription;

        @FXML
        private DatePicker newApptEndDate;

        @FXML
        private ChoiceBox<?> newApptEndTime;

        @FXML
        private DatePicker newApptStartDate;

        @FXML
        private ChoiceBox<?> newApptStartTime;

        @FXML
        private TextField newApptTitle;

        @FXML
        private ChoiceBox<?> newApptType;

        @FXML
        private ChoiceBox<?> newApptUserId;

        @FXML
        void cancelNewAppointment(ActionEvent event) throws IOException {

            Parent parent = FXMLLoader.load(getClass().getResource("/View/Dashboard.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        }

        @FXML
        void saveNewAppointment(ActionEvent event) {

        }

    }



