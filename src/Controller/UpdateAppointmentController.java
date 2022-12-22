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

public class UpdateAppointmentController implements Initializable {


        @FXML
        private TextField updateAppointmentId;

        @FXML
        private ComboBox<Contacts> updateApptContact;

        @FXML
        private ComboBox<Customers> updateApptCustomerID;

        @FXML
        private TextField updateApptDescription;

        @FXML
        private DatePicker updateApptEndDate;

        @FXML
        private ComboBox<LocalTime> updateApptEndTime;

        @FXML
        private DatePicker updateApptStartDate;

        @FXML
        private ComboBox<LocalTime> updateApptStartTime;

        @FXML
        private TextField updateApptTitle;

        @FXML
        private TextField updateApptType;

        @FXML
        private ComboBox<Users> updateApptUserID;

        @FXML
        private TextField updatedApptLocation;

        private ObservableList<Contacts> contactList = FXCollections.observableArrayList();
        private ObservableList<Customers> customerList = FXCollections.observableArrayList();
        private ObservableList<Users> usersList= FXCollections.observableArrayList();

        private LocalDateTime getStartDateTime() {

                LocalDate startDate = updateApptStartDate.getValue();
                LocalTime startTime= updateApptStartTime.getValue();
                LocalDateTime startDateTime = LocalDateTime.of(startDate, startTime);

                return startDateTime;
        }

        private LocalDateTime getEndDateTime(){
                LocalDate endDate = updateApptEndDate.getValue();
                LocalTime endTime = updateApptEndTime.getValue();
                LocalDateTime endDateTime = LocalDateTime.of(endDate, endTime);

                return endDateTime;
        }

        @FXML
        void cancelUpdateAppt(ActionEvent event) throws IOException {

                Parent parent = FXMLLoader.load(getClass().getResource("/View/Dashboard.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();


        }

        @FXML
        void saveUpdatedAppointment(ActionEvent event) throws IOException {

                try {
                        int appointmentID = Integer.parseInt(updateAppointmentId.getText());
                        String title = updateApptTitle.getText();
                        String description = updateApptDescription.getText();
                        String location = updatedApptLocation.getText();
                        int apptContactID = updateApptContact.getSelectionModel().getSelectedItem().getContactID();
                        String type = updateApptType.getText();
                        LocalDateTime startDateTime = getStartDateTime();
                        LocalDateTime endDateTime = getEndDateTime();
                        int apptCustomerID= updateApptCustomerID.getSelectionModel().getSelectedItem().getCustomerId();
                        int apptUserID= updateApptUserID.getSelectionModel().getSelectedItem().getUserID();
                        Appointments newAppointment = new Appointments(appointmentID, title, description, location, type,startDateTime, endDateTime, apptCustomerID, apptUserID, apptContactID);

                        newAppointment.setAppointmentID(appointmentID);

                        if(appointmentID <1 || title.isBlank() || !description.isBlank() || !location.isBlank() || apptContactID <1 || type.isBlank() || !startDateTime.isEqual(null) || !endDateTime.isEqual(null) ||
                                apptCustomerID <1 || apptUserID <1){

                                if(ValidateAppointment.overlappingAppointmentCheck(newAppointment)){
                                        Alert alert = new Alert(Alert.AlertType.ERROR);
                                        alert.setTitle("Error");
                                        alert.setHeaderText("This appointment overlaps with another existing appointment for this customer.");
                                        alert.showAndWait();
                                }

                                else if (!ValidateAppointment.overlappingAppointmentCheck(newAppointment)) {

                                        AppointmentDAO.updateAppointment(newAppointment);

                                        Parent parent = FXMLLoader.load(getClass().getResource("/View/Dashboard.fxml"));
                                        Scene scene = new Scene(parent);
                                        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                                        stage.setScene(scene);
                                        stage.show();
                                }

                        }
                } catch (NullPointerException e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Please enter a value for each field.");
                        alert.show();
                }

                catch (SQLException throwables){
                        throwables.printStackTrace();
                }


        }

        @FXML
        void updateApptContactComboBox(ActionEvent event) {

        }

        @FXML
        void updateApptStartTime(ActionEvent event) {


        }

        public void setUpdatedAppointmentValues (Appointments selectedAppointment){
                updateAppointmentId.setText(String.valueOf(selectedAppointment.getAppointmentID()));
                updateApptTitle.setText(String.valueOf(selectedAppointment.getTitle()));
                updateApptDescription.setText(String.valueOf(selectedAppointment.getDescription()));
                updatedApptLocation.setText(String.valueOf(selectedAppointment.getLocation()));
                updateApptContact.getSelectionModel().select(selectedAppointment.getApptContactID());
                updateApptType.setText(String.valueOf(selectedAppointment.getType()));
                updateApptStartTime.setValue(selectedAppointment.getStartDateTime().toLocalTime());
                updateApptStartDate.setValue(selectedAppointment.getStartDateTime().toLocalDate());
                updateApptEndDate.setValue(selectedAppointment.getEndDateTime().toLocalDate());
                updateApptEndTime.setValue(selectedAppointment.getEndDateTime().toLocalTime());
                updateApptCustomerID.getSelectionModel().select(selectedAppointment.getApptCustomerID());
                updateApptUserID.getSelectionModel().select(selectedAppointment.getApptUserID());

        }



        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {


                try {
                        contactList = ContactsDAO.getAllContacts();
                        updateApptContact.setItems(contactList);

                        customerList = CustomerDAO.getAllCustomers();
                        updateApptCustomerID.setItems(customerList);

                        usersList = UserDaoImpl.getAllUsers();
                        updateApptUserID.setItems(usersList);

                        LocalTime start = LocalTime.of(8, 0);
                        LocalTime end = LocalTime.of(22, 0);
                        while (start.isBefore(end)) {
                                updateApptStartTime.getItems().add(start);
                                updateApptEndTime.getItems().add(start);
                                start = start.plusMinutes(15);
                        }
                }



                catch (SQLException throwables) {
                        throwables.printStackTrace();
                }

        }

        }



