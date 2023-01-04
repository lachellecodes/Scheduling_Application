package Controller;

import DAO.AppointmentDAO;
import Model.Appointments;
import Model.MonthTypeReport;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Month;
import java.util.ResourceBundle;

/** A controller class that provides logic for the Reports By Month screen, shows a list of appointments count by month.*/

public class ReportsByMonthController implements Initializable {



        @FXML
        private TableView<MonthTypeReport> appointmentsByMonthTableView;

        @FXML
        private TableColumn<MonthTypeReport, String> apptTypeMonth;

        @FXML
        private TableColumn<MonthTypeReport, String> monthName;

        @FXML
        private TableColumn<MonthTypeReport, Integer> totalNumAppts;

        Month month;
        String type;
        Integer count;
        Appointments appointment;

        /** Takes user back to the reports dashboard if button is clicked.
         * @param event
         * */

        @FXML
        void backToReportsApptByMonth(ActionEvent event) throws IOException{


                Parent parent = FXMLLoader.load(getClass().getResource("/View/ReportsDashboard.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();

        }

        /** Takes user back to the main dashboard screen if button is clicked.
         * @param event
         * */

        @FXML
        void homeButtonApptsByMonth(ActionEvent event) throws IOException {

                Parent parent = FXMLLoader.load(getClass().getResource("/View/Dashboard.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();


        }


        /** Initializes this screen with the appointment type and and count in the table view.
         * Calls the @viewMonthType report method to set the items in the table view.
         * Table columns are set by lambda to determine the objects in the fields.
         * @param url
         * @param resourceBundle
         * */


        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

                try {
                        appointmentsByMonthTableView.setItems(AppointmentDAO.appointmentsByType());
                } catch (SQLException throwables) {
                        throwables.printStackTrace();
                }


                monthName.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getMonth()));
                apptTypeMonth.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getType()));
                totalNumAppts.setCellValueFactory(cellData ->new SimpleObjectProperty<>(cellData.getValue().getCount()));
                //monthName.setCellValueFactory(new PropertyValueFactory<>("month"));
                //apptTypeMonth.setCellValueFactory(new PropertyValueFactory<>("type"));
                //totalNumAppts.setCellValueFactory(new PropertyValueFactory<>("count"));

        }
}


