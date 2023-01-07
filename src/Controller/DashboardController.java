package Controller;



import DAO.AppointmentDAO;
import DAO.DBConnection;
import Model.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


/** A controller class that provider logic for the dashboard screen, the main screen for the application. */

public class DashboardController implements Initializable {

    @FXML
    private TableColumn<Appointments, Integer> apptContact;

    @FXML
    private TableColumn<Appointments, Integer> apptCustomerID;

    @FXML
    private TableColumn<Appointments, String> apptDescription;


    @FXML
    private TableColumn<Appointments, LocalDateTime> apptEnd;

    @FXML
    private TableColumn<Appointments, LocalDateTime> apptStart;

    @FXML
    private TableColumn<Appointments, Integer> apptID;

    @FXML
    private TableColumn<Appointments, String> apptLocation;


    @FXML
    private TableView<Appointments> apptTableView;

    @FXML
    private TableColumn<Appointments, String> apptTitle;

    @FXML
    private TableColumn<Appointments, String> apptType;

    @FXML
    private TableColumn<Appointments, Integer> apptUserID;

    @FXML
    private RadioButton monthlyViewRadioButton;

    @FXML
    private RadioButton viewAllRadioButton;

    @FXML
    private RadioButton weeklyViewRadioButton;


/** Loads the report dashboard once the user clicks on the reports button.
 *
 * @param event
 * */

    @FXML
    void ReportsButtonClicked(ActionEvent event) throws IOException {
       Parent parent = FXMLLoader.load(getClass().getResource("/View/ReportsDashboard.fxml"));
       Scene scene = new Scene(parent);
       Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
       stage.setScene(scene);
       stage.show();


    }

    /** Displays all appointments in the dashboard table view if the all radio button is selected.
     *
     * @param event
     *
     * */

    @FXML
    void viewAllSelected(ActionEvent event) throws SQLException {

        try {
            ObservableList<Appointments> appointmentsList = AppointmentDAO.getAllAppointments();

            if(viewAllRadioButton.isSelected()){
                apptTableView.setItems(appointmentsList);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    /** Sets the dashboard table view to all appointments within a week of the current date when user
     * selects the weekly view radio button.
     * @param event
     * */

    @FXML
    void weeklyViewSelected(ActionEvent event) throws SQLException {


        ObservableList<Appointments> appointmentsList = AppointmentDAO.getAllAppointments();
        ObservableList<Appointments> appointmentsByWeek = FXCollections.observableArrayList();

        if(weeklyViewRadioButton.isSelected()){

           LocalDateTime start =  LocalDateTime.now().minusWeeks(1);
            LocalDateTime end = LocalDateTime.now().plusWeeks(1);

            for(Appointments appointments : appointmentsList){
                if(appointments.getStartDateTime().isAfter(ChronoLocalDateTime.from(start)) && appointments.getStartDateTime().isBefore(ChronoLocalDateTime.from(end))){
                    appointmentsByWeek.add(appointments);}
                    apptTableView.setItems(appointmentsByWeek);




            }
        }

    }

    /** Sets the dashboard table view to all upcoming appointments within a month of the current date when
     * user selects the monthly view radio button.
     * @param event
     * */

    @FXML
    void monthlyViewSelected(ActionEvent event) throws SQLException {

        ObservableList<Appointments> appointmentsList = AppointmentDAO.getAllAppointments();
        ObservableList<Appointments> appointmentsByMonth = FXCollections.observableArrayList();

        LocalDateTime start = LocalDateTime.now().minusMonths(1);
        LocalDateTime end = LocalDateTime.now().plusMonths(1);

        if(monthlyViewRadioButton.isSelected()) {

            for(Appointments appointments: appointmentsList){
                if(appointments.getStartDateTime().isAfter(ChronoLocalDateTime.from(start)) && appointments.getEndDateTime().isBefore(ChronoLocalDateTime.from(end))){
                    appointmentsByMonth.add(appointments);
                    apptTableView.setItems(appointmentsByMonth);
                }
            }


        }

    }

    /** Takes user to the customer dashboard view the customers button is clicked.
     * @param event
     * */

    @FXML
    void customersButtonClicked(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/View/CustomerInfo.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    /** Deletes the appointment selected by the user in the main dashboard appointment table view.
     * User must confirm if they want to delete the appointment type before the appointment is deleted.
     * Refreshes the dashboard appointment table view with the rest of the appointments after the selected
     * appointment is deleted.
     * @param event
     *
     * */

    @FXML
    void deleteAppointment(ActionEvent event) throws SQLException {

        int appointmentID= apptTableView.getSelectionModel().getSelectedItem().getAppointmentID();
        String appointmentType = apptTableView.getSelectionModel().getSelectedItem().getType();

        Alert alert = new Alert(Alert.AlertType.WARNING, "Are you sure you want to delete Appointment ID : " +appointmentID+ " and Type :  " +appointmentType+ "  ?");
        Optional<ButtonType> confirm = alert.showAndWait();
        if (confirm.isPresent() && confirm.get() == ButtonType.OK) {
            int appointmentToDelete = apptTableView.getSelectionModel().getSelectedItem().getAppointmentID();
            AppointmentDAO.deleteSingleAppointment(appointmentToDelete);
            apptTableView.getItems().clear();
            apptTableView.setItems(AppointmentDAO.getAllAppointments());


        }
    }

    /** Closes the application when user selects the Exit button.
     * @param event
     * */

    @FXML
    void exitButton(ActionEvent event) {
        DBConnection.closeConnection();

        System.exit(0);

    }

    /** Loads the add new appointment screen when the New Appointment button is clicked.
     * @param event
     * */

    @FXML
    void newAppointmentButton(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/View/AddAppointment.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();


    }

    /** Loads the Update Appointment screen when the users selects an appointment and then clicks the
     * update appointment button.
     * @param event
     * */

    @FXML
    void updateAppointment(ActionEvent event) throws IOException {

        Appointments selectedAppointment = apptTableView.getSelectionModel().getSelectedItem();
        FXMLLoader loader= new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/UpdateAppointment.fxml"));
        Parent root = loader.load();
        UpdateAppointmentController modifyAppointment = loader.getController();
        modifyAppointment.setUpdatedAppointmentValues(selectedAppointment);

        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }


    /** Initializes the dashboard screen with the tableview set to a list of all the appointments. 
     * @param resourceBundle
     * @param url
     * */




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            apptTableView.setItems(AppointmentDAO.getAllAppointments());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        apptID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        apptTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        apptDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        apptLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        apptContact.setCellValueFactory(new PropertyValueFactory<>("apptContactID"));
        apptType.setCellValueFactory(new PropertyValueFactory<>("type"));
        apptStart.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        apptEnd.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
        apptCustomerID.setCellValueFactory(new PropertyValueFactory<>("apptCustomerID"));
        apptUserID.setCellValueFactory(new PropertyValueFactory<>("apptUserID"));

    }
}
