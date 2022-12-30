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

/** A controller class that provides logic for the update appointment controller, allows user to udpate a selected appointment or cancel
 * the action. */

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

        /** A method that combines the appointment local start date and local start time together from the add appointment form
         * text fields into a LocalTimeDate object for saving into the database.
         * @return LocalDateTime */

        private LocalDateTime getStartDateTime() {

                LocalDate startDate = updateApptStartDate.getValue();
                LocalTime startTime= updateApptStartTime.getValue();
                LocalDateTime startDateTime = LocalDateTime.of(startDate, startTime);

                return startDateTime;
        }

        /** A method that combines the appointment local end date and local end time together from the add appointment form
         * text fields into a LocalTimeDate object for saving into the database.
         * @return LocalDateTime */

        private LocalDateTime getEndDateTime(){
                LocalDate endDate = updateApptEndDate.getValue();
                LocalTime endTime = updateApptEndTime.getValue();
                LocalDateTime endDateTime = LocalDateTime.of(endDate, endTime);

                return endDateTime;
        }

        /** Takes user back to the dashboard if they decide not to update the selected appointment and the
         * cancelled button is clicked.
         * @param event
         * */

        @FXML
        void cancelUpdateAppt(ActionEvent event) throws IOException {

                Parent parent = FXMLLoader.load(getClass().getResource("/View/Dashboard.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();


        }

        /** Updates an existing appointment in the database. Checks that the user has entered a value into each field on the new
         * appointment form. If no fields are blank the checkBusinessHours function checks if the proposed appointment is
         * outside of business hours. If it is outside of business hours an alert is shown to the user to alert them that
         * the appointment is outside of EST business hours. If the appointment is within business hours the
         * overlappingAppointmentCheck makes sure that the appointment does not conflict with another existing appointment.
         * If both checks return a false Boolean value, the appointment can be saved into the database. Once the
         * appointment is saved successfully, the user is taken back to the main dashboard.
         * @param event
         * */

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

                                if(ValidateAppointment.checkBusinessHours(newAppointment)){
                                        Alert alert = new Alert(Alert.AlertType.WARNING);
                                        alert.setTitle("Warning");
                                        alert.setHeaderText(" The appointment is outside of scheduled business hours. Business hours are from 8:00 AM to 22:00 PM EST.");
                                        alert.showAndWait();
                                }

                                if(ValidateAppointment.overlappingAppointmentCheck(newAppointment)){
                                        Alert alert = new Alert(Alert.AlertType.ERROR);
                                        alert.setTitle("Error");
                                        alert.setHeaderText("This appointment overlaps with another existing appointment for this customer.");
                                        alert.showAndWait();
                                }

                                else if (!ValidateAppointment.overlappingAppointmentCheck(newAppointment) && !ValidateAppointment.checkBusinessHours(newAppointment))  {

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

        /** Gets the values for the selected appointment and loads them onto the Update Appointment screen when the screen
         * is loaded.
         * @param selectedAppointment */

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
                //todo bring info over from combo boxes?

        }

        /** Initializes the update appointment screen combo boxes with a list of contacts, customers and users. Sets the combo
         * boxes time with the hours in 15 minute increments.
         * @param resourceBundle
         * @param url
         *  */

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {


                try {
                        contactList = ContactsDAO.getAllContacts();
                        updateApptContact.setItems(contactList);

                        customerList = CustomerDAO.getAllCustomers();
                        updateApptCustomerID.setItems(customerList);

                        usersList = UserDaoImpl.getAllUsers();
                        updateApptUserID.setItems(usersList);

                        LocalTime start = LocalTime.of(00, 0);
                        LocalTime end = LocalTime.of(24, 0);
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



