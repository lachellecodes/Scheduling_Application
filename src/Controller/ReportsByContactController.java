package Controller;

import DAO.AppointmentDAO;
import DAO.ContactsDAO;
import Model.AppointmentByContact;
import Model.Appointments;
import Model.Contacts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class ReportsByContactController implements Initializable {


        @FXML
        private TableView<AppointmentByContact> appointmentsByContactTableView;

        @FXML
        void backToReportsApptsByContact(ActionEvent event) throws IOException {
            Parent parent = FXMLLoader.load(getClass().getResource("/View/ReportsDashboard.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        }

        @FXML
        void homeButtonApptsByContact(ActionEvent event) throws IOException{

            Parent parent = FXMLLoader.load(getClass().getResource("/View/Dashboard.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        }



    @FXML
    private ComboBox <Contacts> contactsComboBox;

    @FXML
    private TableColumn<AppointmentByContact, Integer> apptId;

    @FXML
    private TableColumn<AppointmentByContact, Integer> contact;

    @FXML
    private TableColumn<?, Integer> customerId;

    @FXML
    private TableColumn<AppointmentByContact, String> description;

    @FXML
    private TableColumn<AppointmentByContact, LocalDate> start;

    @FXML
    private TableColumn<AppointmentByContact, LocalTime> end;


    @FXML
    private TableColumn<AppointmentByContact, String > title;

    @FXML
    private TableColumn<AppointmentByContact, String > type;

    public ObservableList <Contacts> contactList = FXCollections.observableArrayList();
    public ObservableList <Appointments> appointments = FXCollections.observableArrayList();
    public ObservableList<AppointmentByContact> contactScheduledAppointments = FXCollections.observableArrayList();





    @FXML
    void contactScheduleComboBox(ActionEvent event) throws SQLException {

       int  selectedContactId = contactsComboBox.getSelectionModel().getSelectedItem().getContactID();
       appointments= AppointmentDAO.getAllAppointments();

        for (Appointments contactAppointment : appointments){
            if ( contactAppointment.getApptContactID() == selectedContactId){

                int apptContactID = contactAppointment.getApptContactID();
                int appointmentID = contactAppointment.getAppointmentID();
                String title = contactAppointment.getTitle();
                String type = contactAppointment.getType();
                String description = contactAppointment.getDescription();
                LocalDateTime startDateTime = contactAppointment.getStartDateTime();
                LocalDateTime endDateTime = contactAppointment.getEndDateTime();
                int apptCustomerID = contactAppointment.getApptCustomerID();


                AppointmentByContact newAppointment = new AppointmentByContact(apptContactID, appointmentID, title, type, description, startDateTime, endDateTime, apptCustomerID);
                contactScheduledAppointments.add(newAppointment);
                appointmentsByContactTableView.setItems(contactScheduledAppointments);




            }
        }


    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            contactList = ContactsDAO.getAllContacts();
            contactsComboBox.setItems(contactList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        contact.setCellValueFactory(new PropertyValueFactory<>("apptContactID"));
        apptId.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        title. setCellValueFactory(new PropertyValueFactory<>("title"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        start.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        end.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
        customerId.setCellValueFactory(new PropertyValueFactory<>("apptCustomerID"));




    }
}


