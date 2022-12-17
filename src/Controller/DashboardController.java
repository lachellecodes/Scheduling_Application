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




    @FXML
    void ReportsButtonClicked(ActionEvent event) throws IOException {
       Parent parent = FXMLLoader.load(getClass().getResource("/View/ReportsDashboard.fxml"));
       Scene scene = new Scene(parent);
       Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
       stage.setScene(scene);
       stage.show();


    }

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

    @FXML
    void monthlyViewSelected(ActionEvent event) throws SQLException {

        ObservableList<Appointments> appointmentsList = AppointmentDAO.getAllAppointments();
        ObservableList<Appointments> appointmentsByMonth = FXCollections.observableArrayList();

        if(monthlyViewRadioButton.isSelected()) {
            LocalDateTime start = LocalDateTime.now().minusMonths(1);
            LocalDateTime end = LocalDateTime.now().plusMonths(1);

            for(Appointments appointments: appointmentsList){
                if(appointments.getStartDateTime().isAfter(ChronoLocalDateTime.from(start)) && appointments.getEndDateTime().isBefore(ChronoLocalDateTime.from(end))){
                    appointmentsByMonth.add(appointments);
                    apptTableView.setItems(appointmentsByMonth);
                }
            }


        }

    }

    @FXML
    void customersButtonClicked(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/View/CustomerInfo.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void deleteAppointment(ActionEvent event) throws SQLException {

        int appointmentID= apptTableView.getSelectionModel().getSelectedItem().getAppointmentID();
        String appointmentType = apptTableView.getSelectionModel().getSelectedItem().getType();

        Alert alert = new Alert(Alert.AlertType.WARNING, "Are you sure you want to delete Appointment ID : " +appointmentID+ " and Type :  " +appointmentType+ "  ?");
        Optional<ButtonType> confirm = alert.showAndWait();
        if (confirm.isPresent() && confirm.get() == ButtonType.OK) {
            int appointmentToDelete = apptTableView.getSelectionModel().getSelectedItem().getAppointmentID();
            AppointmentDAO.deleteSingleAppointment(appointmentToDelete);
            AppointmentDAO.getAllAppointments();
            apptTableView.refresh();

        }
    }

    @FXML
    void exitButton(ActionEvent event) {
        DBConnection.closeConnection();

        System.exit(0);

    }

    @FXML
    void newAppointmentButton(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/View/AddAppointment.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();


    }

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
