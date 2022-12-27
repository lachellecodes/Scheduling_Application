package Controller;

import DAO.*;
import Model.Appointments;
import Model.Countries;
import Model.FirstLevelDivisions;
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
import java.util.ResourceBundle;

public class ReportsByCountryController implements Initializable {


    @FXML
    private TableView<Appointments> appointmentsByCountryTableView;

    @FXML
    private TableColumn<?, ?> apptId;

    @FXML
    private TableColumn<?, ?> contact;

    @FXML
    private ComboBox<Countries> countryComboBox;

    @FXML
    private TableColumn<?, ?> customerId;

    @FXML
    private TableColumn<?, ?> description;

    @FXML
    private TableColumn<?, ?> end;

    @FXML
    private TableColumn<?, ?> start;

    @FXML
    private TableColumn<?, ?> title;

    @FXML
    private TableColumn<?, ?> type;

    private ObservableList<Countries> countryList = FXCollections.observableArrayList();
    private ObservableList<Appointments> appointments = FXCollections.observableArrayList();
    private ObservableList<FirstLevelDivisions> firstLevelDivisions= FXCollections.observableArrayList();


    @FXML
    void backToReports(ActionEvent event) throws IOException {

        Parent parent = FXMLLoader.load(getClass().getResource("/View/ReportsDashboard.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void countryComboBox(ActionEvent event) throws SQLException {

        int selectedCountry = countryComboBox.getSelectionModel().getSelectedItem().getCountryId();


        appointmentsByCountryTableView.setItems(AppointmentDAO.appointmentsByCountry(selectedCountry));



    }

    @FXML
    void homeButton(ActionEvent event) throws IOException {

        Parent parent = FXMLLoader.load(getClass().getResource("/View/Dashboard.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            countryList = CountryDAO.getAllCountries();
            countryComboBox.setItems(countryList);
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