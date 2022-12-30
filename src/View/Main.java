package View;

import DAO.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.util.Locale;
import java.util.ResourceBundle;

/** The main entry point for the application. */

public class Main extends Application {


/** Displays the login screen for the application
 * @throws Exception*/
    @Override
    public void start(Stage primaryStage) throws Exception{


        Parent root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
        primaryStage.setTitle("Timely Team Scheduling Application");
        primaryStage.setScene(new Scene(root, 520, 400));
        primaryStage.show();
    }

/** Opens the database connection when the application is started and closes the database connection
 * when the application is closed. */

    public static void main(String[] args) {

        DBConnection.openConnection();
        launch(args);


       DBConnection.closeConnection();


    }
}
