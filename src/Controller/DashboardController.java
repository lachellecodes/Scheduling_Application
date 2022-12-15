package Controller;



import DAO.AppointmentDAO;
import DAO.DBConnection;
import Model.Appointments;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
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
    void ReportsButtonClicked(ActionEvent event) throws IOException {
       Parent parent = FXMLLoader.load(getClass().getResource("/View/ReportsDashboard.fxml"));
       Scene scene = new Scene(parent);
       Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
       stage.setScene(scene);
       stage.show();


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
    void deleteAppointment(ActionEvent event) {

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

        //Parent parent = FXMLLoader.load(getClass().getResource("/View/UpdateAppointment.fxml"));
        //Scene scene = new Scene(parent);
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
