package Utilities;

import Controller.LoginController;
import DAO.UserDaoImpl;
import Model.Users;


import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class UserActivity {

    static Users currentUser;

    static ZoneId utcZone = ZoneId.of("UTC");

    static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'hh:mm:ss");

    public static void updateUserLog (Boolean successful) throws IOException {

        LocalDateTime localDateTime= LocalDateTime.now();
        ZonedDateTime zonedDateTime = localDateTime.atZone(utcZone);
        String dateTime = dateTimeFormatter.format(zonedDateTime);

        FileWriter fileWriter = new FileWriter("login_activity", true);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        currentUser = LoginController.getCurrentUser();
        String userName = currentUser.getUserName();


        if(successful){
            printWriter.println(userName + " logged in successfully at" + dateTime );
            printWriter.close();
        }
        if (!successful){
            printWriter.println(userName + " was denied access at" + dateTime);
            printWriter.close();
        }


    }
}
