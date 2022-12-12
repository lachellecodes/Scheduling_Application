package Controller;

import DAO.ContactsDAO;
import DAO.CustomerDAO;
import DAO.UserDaoImpl;
import Model.Appointments;
import Model.Contacts;
import Model.Customers;
import Model.Users;
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
        void saveNewAppointment(ActionEvent event) {

                int appointmentID= 0;
                String title = newApptTitle.getText();
                String description = newApptDescription.getText();
                String location = newApptLocation.getText();
                int apptContactId = newApptContact.getSelectionModel().getSelectedItem().getContactID();
                String type = newApptType.getText();
                //TODO how to initialize these times to show?
                LocalDateTime startDate = getStartDateTime();
                LocalDateTime startTime = getStartDateTime();
                LocalDateTime endDate= getEndDateTime();
                LocalDateTime endTime= getEndDateTime();
                int apptCustomerID= newApptCustomerID.getSelectionModel().getSelectedItem().getCustomerId();
                int apptUserID= newApptUserID.getSelectionModel().getSelectedItem().getUserID();
                //TODO this constructor is not right
                Appointments appointment = new Appointments(appointmentID, title, description, location, type, startDateTime, endDateTime, )

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


                } catch (SQLException throwables) {
                        throwables.printStackTrace();
                }

        }
}



