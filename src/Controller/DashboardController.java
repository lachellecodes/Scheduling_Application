package Controller;



import DAO.DBConnection;
import Model.Appointments;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;


public class DashboardController implements Initializable {

    @FXML
    private TableColumn<Appointments, Integer> apptContact;

    @FXML
    private TableColumn<Appointments, Integer> apptCustomerID;

    @FXML
    private TableColumn<Appointments, String> apptDescription;

    @FXML
    private TableColumn<Appointments, LocalDateTime> apptEndDate;

    @FXML
    private TableColumn<?, ?> apptEndTime;

    @FXML
    private TableColumn<?, ?> apptID;

    @FXML
    private TableColumn<?, ?> apptLocation;

    @FXML
    private TableColumn<?, ?> apptStartDate;

    @FXML
    private TableColumn<?, ?> apptStartTime;

    @FXML
    private TableView<?> apptTableView;

    @FXML
    private TableColumn<?, ?> apptTitle;

    @FXML
    private TableColumn<?, ?> apptType;

    @FXML
    private TableColumn<?, ?> apptUserID;




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
        Parent parent = FXMLLoader.load(getClass().getResource("/View/UpdateAppointment.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



    }
}
