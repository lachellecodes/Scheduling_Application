package Controller;

import Model.Appointments;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReportsByMonthController implements Initializable {




        @FXML
        private TableView<Appointments> appointmentsByMonthTableView;

        @FXML
        private TableColumn<Appointments, String> apptTypeMonth;

        @FXML
        private TableColumn<Appointments, String> monthName;

        @FXML
        private TableColumn<Appointments, Integer> totalNumAppts;

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

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

        }
}


