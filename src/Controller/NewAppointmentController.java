package Controller;

import DAO.AppointmentDAO;
import DAO.ContactsDAO;
import DAO.CustomerDAO;
import DAO.UserDaoImpl;
import Model.Appointments;
import Model.Contacts;
import Model.Customers;
import Model.Users;
import Resources.ValidateAppointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class NewAppointmentController implements Initializable {


        public ComboBox<Users> newApptUserID;

        @FXML
        private TextField newAppointmentId;

        @FXML
        private ComboBox<Contacts> newApptContact;

        @FXML
        private ComboBox<Customers> newApptCustomerID;

        @FXML
        private TextField newApptDescription;

        @FXML
        private DatePicker newApptEndDate;

        @FXML
        private ComboBox<LocalTime> newApptEndTime;

        @FXML
        private DatePicker newApptStartDate;

        @FXML
        private ComboBox<LocalTime> newApptStartTime;

        @FXML
        private TextField newApptTitle;

        @FXML
        private TextField newApptType;



        @FXML
        private TextField newApptLocation;

        private ObservableList<Contacts> contactList = FXCollections.observableArrayList();
        private ObservableList<Customers> customerList = FXCollections.observableArrayList();
        private ObservableList<Users> usersList= FXCollections.observableArrayList();




        @FXML
        void cancelNewAppointment(ActionEvent event) throws IOException {

            Parent parent = FXMLLoader.load(getClass().getResource("/View/Dashboard.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        }

        @FXML
        void newApptContactComboBox(ActionEvent event) throws SQLException {



        }

        @FXML
        void selectApptStartTime(ActionEvent event) {



        }

        private LocalDateTime getStartDateTime() {

                LocalDate startDate = newApptStartDate.getValue();
                LocalTime startTime= newApptStartTime.getValue();
                LocalDateTime startDateTime = LocalDateTime.of(startDate, startTime);

                return startDateTime;
        }

        private LocalDateTime getEndDateTime(){
                LocalDate endDate = newApptEndDate.getValue();
                LocalTime endTime = newApptEndTime.getValue();
                LocalDateTime endDateTime = LocalDateTime.of(endDate, endTime);

                return endDateTime;
        }

        @FXML
        void saveNewAppointment(ActionEvent event) throws IOException {

                try {
                        int appointmentID = 0;
                        String title = newApptTitle.getText();
                        String description = newApptDescription.getText();
                        String location = newApptLocation.getText();
                        int apptContactID = newApptContact.getSelectionModel().getSelectedItem().getContactID();
                        String type = newApptType.getText();
                        LocalDateTime startDateTime = getStartDateTime();
                        LocalDateTime endDateTime = getEndDateTime();
                        int apptUserID = newApptUserID.getSelectionModel().getSelectedItem().getUserID();
                        int apptCustomerID = newApptCustomerID.getSelectionModel().getSelectedItem().getCustomerId();
                        Appointments newAppointment = new Appointments(appointmentID, title, description, location, type, startDateTime, endDateTime, apptCustomerID, apptUserID, apptContactID);

                        if (!title.isBlank() || !description.isBlank() || !location.isBlank() || apptContactID < 1 || !type.isBlank() || !startDateTime.isEqual(null)
                                || !endDateTime.isEqual(null) || apptCustomerID < 1 || apptUserID < 1) {

                                //todo getting an error that says 8:00 text could not be parsed
                                ValidateAppointment.checkBusinessHours(newAppointment);

                                if (ValidateAppointment.overlappingAppointmentCheck(newAppointment)) {
                                        Alert alert = new Alert(Alert.AlertType.ERROR);
                                        alert.setTitle("Error");
                                        alert.setHeaderText("This appointment overlaps with another existing appointment for this customer.");
                                        alert.showAndWait();
                                } else if (!ValidateAppointment.overlappingAppointmentCheck(newAppointment)) {
                                        AppointmentDAO.addNewAppointment(newAppointment);


                                        Parent parent = FXMLLoader.load(getClass().getResource("/View/Dashboard.fxml"));
                                        Scene scene = new Scene(parent);
                                        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                                        stage.setScene(scene);
                                        stage.show();
                                }
                        }
                }

                        catch(NullPointerException e){
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Error");
                                alert.setHeaderText("Please enter a value for each field.");
                                alert.show();
                        }
                         catch(SQLException throwables){
                                throwables.printStackTrace();
                        }



        }

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

                try {
                        contactList = ContactsDAO.getAllContacts();
                        newApptContact.setItems(contactList);

                        customerList = CustomerDAO.getAllCustomers();
                        newApptCustomerID.setItems(customerList);

                        usersList = UserDaoImpl.getAllUsers();
                        newApptUserID.setItems(usersList);

                        LocalTime start = LocalTime.of(6, 0);
                        LocalTime end = LocalTime.of(22, 0);
                        while (start.isBefore(end)) {
                                newApptStartTime.getItems().add(start);
                                newApptEndTime.getItems().add(start);
                                start = start.plusMinutes(15);
                        }
                }



                 catch (SQLException throwables) {
                        throwables.printStackTrace();
                }

        }
}



