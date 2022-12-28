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

    Users currentUser;

    ZoneId utcZone = ZoneId.of("UTC");

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM-DD-YY at HH:mm:ss");

    public void updateUserLog () throws IOException {

        LocalDateTime localDateTime= LocalDateTime.now();
        ZonedDateTime zonedDateTime = localDateTime.atZone(utcZone);
        String dateTime = dateTimeFormatter.format(zonedDateTime);

        FileWriter fileWriter = new FileWriter("login_activity", true);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        currentUser = LoginController.getCurrentUser();
        String userName = currentUser.getUserName();


    }
}
