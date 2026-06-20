package service;

import model.HistoryRecord;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class HistoryManager {

    private static final String FILE =
            "history.csv";

    public static void save(
            HistoryRecord record)
            throws Exception {

        FileWriter writer =
                new FileWriter(
                        FILE,
                        true);

        writer.write(
                record.getDate() + ";" +
                        record.getCount() + ";" +
                        record.getProfile() + ";" +
                        record.getFolder() +
                        "\n");

        writer.close();
    }

    public static List<HistoryRecord> load()
            throws Exception {

        List<HistoryRecord> records =
                new ArrayList<>();

        File file =
                new File(FILE);

        if (!file.exists()) {
            return records;
        }

        BufferedReader reader =
                new BufferedReader(
                        new FileReader(file));

        String line;

        while ((line =
                reader.readLine()) != null) {

            String[] parts =
                    line.split(";");

            if (parts.length == 4) {

                records.add(
                        new HistoryRecord(
                                parts[0],
                                Integer.parseInt(
                                        parts[1]),
                                parts[2],
                                parts[3]
                        ));
            }
        }

        reader.close();

        return records;
    }
}