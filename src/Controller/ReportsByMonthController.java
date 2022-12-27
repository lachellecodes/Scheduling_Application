package Controller;

import DAO.AppointmentDAO;
import Model.Appointments;
import Model.MonthTypeReport;
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

public class ReportsByMonthController implements Initializable {



        @FXML
        private TableView<MonthTypeReport> appointmentsByMonthTableView;

        @FXML
        private TableColumn<MonthTypeReport, Month> apptTypeMonth;

        @FXML
        private TableColumn<MonthTypeReport, String> monthName;

        @FXML
        private TableColumn<MonthTypeReport, Integer> totalNumAppts;

        Month month;
        String type;
        Integer count;
        Appointments appointment;


        @FXML
        void backToReportsApptByMonth(ActionEvent event) throws IOException{


                Parent parent = FXMLLoader.load(getClass().getResource("/View/ReportsDashboard.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();

        }

        @FXML
        void homeButtonApptsByMonth(ActionEvent event) throws IOException {

                Parent parent = FXMLLoader.load(getClass().getResource("/View/Dashboard.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();


        }

        public void viewMonthTypeReport() throws SQLException {

                ObservableList<Appointments> appointmentList = AppointmentDAO.getAllAppointments();
                ObservableList<Month> months = FXCollections.observableArrayList();
                ObservableList<String> types = FXCollections.observableArrayList();
                ObservableList<MonthTypeReport> monthTypeReports = FXCollections.observableArrayList();

                appointmentList.forEach(appointments -> {
                        Month month = appointments.getStartDateTime().getMonth();
                        if (!months.contains(month)) {
                                months.add(month);
                        }

                });

                appointmentList.forEach(appointments -> {
                        String type = appointments.getType();
                        if (!types.contains(type)) {
                                types.add(type);
                                count = 0;

                        }
                });

                for (int i = 0; i < months.size(); i++) {
                        month = months.get(i);


                        for (int j = 0; j < types.size(); j++) {
                                type = types.get(j);


                                /*for (int k = 0; k < appointmentList.size(); k++) {
                                        appointment = appointmentList.get(k);*/

                                for (Appointments appointment : appointmentList) {
                                        if (appointment.getStartDateTime().getMonth() == month && appointment.getType() == type) {
                                                count++;
                                                //todo why does this keep counting and adding one for each appt? i have tried break ?


                                                if (count > 0) {
                                                        MonthTypeReport monthType = new MonthTypeReport(month, type, count);
                                                        monthTypeReports.add(monthType);


                                                }
                                        }
                                }

                        }


                }
                appointmentsByMonthTableView.setItems(monthTypeReports);
        }


        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

                try {
                        viewMonthTypeReport();
                } catch (SQLException throwables) {
                        throwables.printStackTrace();
                }


                monthName.setCellValueFactory(new PropertyValueFactory<>("month"));
                apptTypeMonth.setCellValueFactory(new PropertyValueFactory<>("type"));
                totalNumAppts.setCellValueFactory(new PropertyValueFactory<>("count"));

        }
}


