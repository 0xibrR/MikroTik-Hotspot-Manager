package service;

import model.User;

import java.io.FileWriter;
import java.util.List;

public class CsvExporter {

    public static void export(
            List<User> users,
            String filePath)
            throws Exception {

        FileWriter writer =
                new FileWriter(filePath);

        writer.write(
                "Username;Password;Profile;Server;Uptime\n");

        for (User user : users) {

            writer.write(
                    user.getUsername() + ";" +
                            user.getPassword() + ";" +
                            user.getProfile() + ";" +
                            user.getServer() + ";" +
                            user.getUptime() + "\n");
        }

        writer.close();
    }
}