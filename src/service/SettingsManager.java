package service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class SettingsManager {

    private static final String FILE =
            "settings.properties";

    public static void save(
            Properties properties)
            throws Exception {

        FileOutputStream out =
                new FileOutputStream(FILE);

        properties.store(
                out,
                "MikroTik Hotspot Manager");

        out.close();
    }

    public static Properties load()
            throws Exception {

        Properties properties =
                new Properties();

        try {

            FileInputStream in =
                    new FileInputStream(FILE);

            properties.load(in);

            in.close();

        } catch (Exception ignored) {
        }

        return properties;
    }
}