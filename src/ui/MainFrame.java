package ui;

import model.HistoryRecord;
import model.User;
import service.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;

public class MainFrame extends JFrame {

    private final UserGenerator userGenerator = new UserGenerator();
    private JComboBox<String> usernameModeBox;
    private JComboBox<String> passwordModeBox;
    private JComboBox<String> profileBox;
    private JComboBox<String> serverBox;
    private JComboBox<String> uptimeBox;
    private JTextField countField;
    private JTextField userLengthField;
    private JTextField passLengthField;
    private JTextField passStartField;
    private JTextField prefixField;
    private JButton btnPreview;
    private JButton btnGenerate;
    private JButton btnImport;
    private JButton btnOpenPdf;
    private JButton btnOpenFolder;
    private JButton btnReset;
    private JButton btnClearHistory;
    private JLabel statusLabel;
    private JLabel historyLabel;
    private JLabel usersLabel;

    public MainFrame() {

        setTitle("MikroTik Hotspot Manager");
        setSize(900, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initUI();
        loadSettings();
        updateStatistics();

        addWindowListener(
                new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        saveSettings();
                    }
                });
    }

    private void initUI() {

        usernameModeBox =
                new JComboBox<>(
                        new String[]{
                                "Random",
                                "user001",
                                "Numbers Only",
                                "Profile Based"
                        });

        passwordModeBox =
                new JComboBox<>(
                        new String[]{
                                "Random",
                                "Same Username",
                                "Sequential",
                                "Prefix + Username"
                        });

        profileBox =
                new JComboBox<>(
                        new String[]{
                                "1M",
                                "2M",
                                "3M",
                                "4M",
                                "5M",
                                "10M",
                                "20M",
                                "30M",
                                "40M",
                                "default"
                        });

        serverBox =
                new JComboBox<>(
                        new String[]{
                                "all"
                        });

        uptimeBox =
                new JComboBox<>(
                        new String[]{
                                "1h",
                                "8h",
                                "16h",
                                "24h"
                        });

        countField =
                new JTextField("20");

        userLengthField =
                new JTextField("6");

        passLengthField =
                new JTextField("6");

        passStartField =
                new JTextField("5001");

        prefixField =
                new JTextField("pass");

        btnPreview =
                new JButton("Preview Users");

        btnPreview.addActionListener(
                e -> previewUsers());

        btnGenerate =
                new JButton(
                        "Generate CSV + RSC + PDF");

        btnGenerate.addActionListener(
                e -> exportFiles());

        btnImport =
                new JButton("History");

        btnImport.addActionListener(
                e -> showHistory());

        btnOpenPdf =
                new JButton("Open PDF");

        btnOpenPdf.addActionListener(
                e -> openLastPdf());

        btnOpenFolder =
                new JButton("Open Folder");

        btnOpenFolder.addActionListener(
                e -> openFolder());

        btnReset =
                new JButton("Reset");

        btnReset.addActionListener(
                e -> resetFields());

        btnClearHistory =
                new JButton(
                        "Clear History");

        btnClearHistory.addActionListener(
                e -> clearHistory());

        statusLabel =
                new JLabel("Ready");

        historyLabel =
                new JLabel("History Records: 0");

        usersLabel =
                new JLabel("Generated Users: 0");

        setLayout(new BorderLayout());

        JPanel mainPanel =
                new JPanel();

        mainPanel.setLayout(
                new BoxLayout(
                        mainPanel,
                        BoxLayout.Y_AXIS));

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        15,
                        15,
                        15));

        JLabel title = getJLabel();

        mainPanel.add(title);

        mainPanel.add(
                Box.createVerticalStrut(
                        20));

        JPanel userPanel =
                new JPanel(
                        new GridLayout(
                                0,
                                2,
                                10,
                                10));

        userPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "User Settings"));

        userPanel.add(
                new JLabel(
                        "Username Mode"));

        userPanel.add(
                usernameModeBox);

        userPanel.add(
                new JLabel(
                        "Password Mode"));

        userPanel.add(
                passwordModeBox);

        userPanel.add(
                new JLabel(
                        "Username Length"));

        userPanel.add(
                userLengthField);

        userPanel.add(
                new JLabel(
                        "Password Length"));

        userPanel.add(
                passLengthField);

        userPanel.add(
                new JLabel(
                        "Sequential Start"));

        userPanel.add(
                passStartField);

        userPanel.add(
                new JLabel(
                        "Prefix"));

        userPanel.add(
                prefixField);

        mainPanel.add(userPanel);

        mainPanel.add(
                Box.createVerticalStrut(
                        15));

        JPanel networkPanel =
                new JPanel(
                        new GridLayout(
                                0,
                                2,
                                10,
                                10));

        networkPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Network Settings"));

        networkPanel.add(
                new JLabel(
                        "Profile"));

        networkPanel.add(
                profileBox);

        networkPanel.add(
                new JLabel(
                        "Server"));

        networkPanel.add(
                serverBox);

        networkPanel.add(
                new JLabel(
                        "Uptime"));

        networkPanel.add(
                uptimeBox);

        networkPanel.add(
                new JLabel(
                        "Users Count"));

        networkPanel.add(
                countField);

        mainPanel.add(networkPanel);

        mainPanel.add(
                Box.createVerticalStrut(
                        15));

        JPanel actionPanel =
                new JPanel(
                        new GridLayout(
                                3,
                                3,
                                10,
                                10));

        actionPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Actions"));

        actionPanel.add(btnPreview);
        actionPanel.add(btnGenerate);
        actionPanel.add(btnImport);

        actionPanel.add(btnOpenPdf);
        actionPanel.add(btnOpenFolder);
        actionPanel.add(btnReset);
        actionPanel.add(btnClearHistory);
        mainPanel.add(actionPanel);

        mainPanel.add(
                Box.createVerticalStrut(
                        15));

        JPanel statusPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT));

        statusPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Status"));

        statusPanel.add(statusLabel);
        statusPanel.add(new JLabel(" | "));
        statusPanel.add(historyLabel);
        statusPanel.add(new JLabel(" | "));
        statusPanel.add(usersLabel);

        mainPanel.add(statusPanel);

        add(mainPanel,
                BorderLayout.CENTER);
    }


    private List<User> generateUsers() {

        int count =
                Integer.parseInt(
                        countField.getText());

        int usernameMode =
                usernameModeBox
                        .getSelectedIndex();

        int passwordMode =
                passwordModeBox
                        .getSelectedIndex();

        int passwordLength =
                Integer.parseInt(
                        passLengthField
                                .getText());

        int usernameLength =
                Integer.parseInt(
                        userLengthField
                                .getText());

        int sequenceStart =
                Integer.parseInt(
                        passStartField
                                .getText());

        String prefix =
                prefixField.getText();

        String profile =
                profileBox
                        .getSelectedItem()
                        .toString();

        String server =
                serverBox
                        .getSelectedItem()
                        .toString();

        String uptime =
                uptimeBox
                        .getSelectedItem()
                        .toString();
        return userGenerator
                .generateUsers(
                        count,
                        usernameMode,
                        passwordMode,
                        usernameLength,
                        passwordLength,
                        sequenceStart,
                        prefix,
                        profile,
                        server,
                        uptime);
    }

    private JLabel getJLabel() {
        JLabel title =
                new JLabel(
                        "MikroTik Hotspot Manager");

        title.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR));

        title.setToolTipText(
                "About MikroTik Hotspot Manager");


        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        24));

        title.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        title.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseClicked(
                            MouseEvent e) {

                        showAboutDialog();
                    }
                });
        return title;
    }

    private void exportFiles() {

        try {

            if (!validateInputs()) {
                return;
            }

            List<User> users =
                    generateUsers();

            String folderName =
                    LocalDateTime.now()
                            .format(
                                    DateTimeFormatter.ofPattern(
                                            "yyyy-MM-dd_HH-mm-ss"));

            File folder =
                    new File(
                            "vouchers/" + folderName);

            folder.mkdirs();

            CsvExporter.export(
                    users,
                    folder.getPath()
                            + "/mikrotik_users.csv");

            RscExporter.export(
                    users,
                    folder.getPath()
                            + "/mikrotik_users.rsc");

            String pdfName =
                    profileBox
                            .getSelectedItem()
                            .toString()
                            + "_"
                            + users.size()
                            + "Users.pdf";

            PdfVoucherGenerator.export(
                    users,
                    folder.getPath()
                            + "/"
                            + pdfName);

            HistoryManager.save(
                    new HistoryRecord(
                            LocalDateTime.now()
                                    .toString(),
                            users.size(),
                            profileBox
                                    .getSelectedItem()
                                    .toString(),
                            folderName
                    ));

            updateStatistics();

            statusLabel.setText(
                    "CSV + RSC + PDF Generated");

        } catch (Exception ex) {

            ex.printStackTrace();

            statusLabel.setText(
                    ex.getMessage());
        }
    }

    private void previewUsers() {

        try {

            java.util.List<User> users =
                    generateUsers();

            String[] columns = {
                    "Username",
                    "Password",
                    "Profile",
                    "Uptime",
                    "Server"
            };

            Object[][] data =
                    new Object[users.size()][5];

            for (int i = 0; i < users.size(); i++) {

                User user = users.get(i);

                data[i][0] =
                        user.getUsername();

                data[i][1] =
                        user.getPassword();

                data[i][2] =
                        user.getProfile();

                data[i][3] =
                        user.getUptime();

                data[i][4] =
                        user.getServer();
            }

            JTable table =
                    new JTable(data, columns);

            JScrollPane scrollPane =
                    new JScrollPane(table);

            JDialog dialog =
                    new JDialog(
                            this,
                            "Preview Users",
                            true);

            dialog.add(scrollPane);

            dialog.setSize(700, 500);

            dialog.setLocationRelativeTo(this);

            dialog.setVisible(true);

        } catch (Exception ex) {

            ex.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage());
        }
    }

    private void resetFields() {

        countField.setText("20");
        userLengthField.setText("6");
        passLengthField.setText("6");

        passStartField.setText("5001");
        prefixField.setText("pass");

        usernameModeBox.setSelectedIndex(0);
        passwordModeBox.setSelectedIndex(0);
        profileBox.setSelectedIndex(0);
        serverBox.setSelectedIndex(0);
        uptimeBox.setSelectedIndex(0);

        statusLabel.setText("Ready");
    }

    private void clearHistory() {

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        "Delete all history records?",
                        "Confirm",
                        JOptionPane.YES_NO_OPTION);

        if (result !=
                JOptionPane.YES_OPTION) {

            return;
        }

        try {

            File history =
                    new File(
                            "history.csv");

            new FileWriter(
                    "history.csv",
                    false).close();

            updateStatistics();

            JOptionPane.showMessageDialog(
                    this,
                    "History cleared successfully");

        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }

    private void openLastPdf() {

        try {

            File vouchers =
                    new File("vouchers");

            File[] folders =
                    vouchers.listFiles(File::isDirectory);

            if (folders == null ||
                    folders.length == 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "No PDF Found");

                return;
            }

            File latest =
                    folders[0];

            for (File folder : folders) {

                if (folder.lastModified()
                        > latest.lastModified()) {

                    latest = folder;
                }
            }

            File[] pdfFiles =
                    latest.listFiles(
                            (dir, name) ->
                                    name.toLowerCase()
                                            .endsWith(".pdf"));

            if (pdfFiles == null ||
                    pdfFiles.length == 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "No PDF Found");

                return;
            }

            Desktop.getDesktop().open(
                    pdfFiles[0]);

        } catch (Exception ex) {

            ex.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage());
        }
    }

    private void showHistory() {

        try {

            List<HistoryRecord> records =
                    HistoryManager.load();

            String[] columns = {
                    "Date",
                    "Users",
                    "Profile",
                    "Folder"
            };

            Object[][] data =
                    new Object[
                            records.size()][4];

            for (int i = 0;
                 i < records.size();
                 i++) {

                HistoryRecord r =
                        records.get(i);

                data[i][0] =
                        r.getDate();

                data[i][1] =
                        r.getCount();

                data[i][2] =
                        r.getProfile();

                data[i][3] =
                        r.getFolder();
            }

            JTable table =
                    new JTable(
                            data,
                            columns);

            JScrollPane scroll =
                    new JScrollPane(
                            table);

            JDialog dialog =
                    new JDialog(
                            this,
                            "History",
                            true);

            dialog.add(scroll);

            dialog.setSize(
                    800,
                    500);

            dialog.setLocationRelativeTo(
                    this);

            dialog.setVisible(true);

        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }

    private void openFolder() {

        try {

            File vouchers =
                    new File("vouchers");

            if (!vouchers.exists()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Folder Not Found");

                return;
            }

            Desktop.getDesktop().open(
                    vouchers);

        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }

    private void updateStatistics() {

        try {

            List<HistoryRecord> records =
                    HistoryManager.load();

            int totalUsers = 0;

            for (HistoryRecord record : records) {

                totalUsers +=
                        record.getCount();
            }

            historyLabel.setText(
                    "History Records: "
                            + records.size());

            usersLabel.setText(
                    "Generated Users: "
                            + totalUsers);

        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }

    private boolean validateInputs() {

        try {

            int count =
                    Integer.parseInt(
                            countField.getText());

            int usernameLength =
                    Integer.parseInt(
                            userLengthField.getText());

            int passwordLength =
                    Integer.parseInt(
                            passLengthField.getText());

            int sequenceStart =
                    Integer.parseInt(
                            passStartField.getText());

            if (count <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Users Count must be greater than 0");

                return false;
            }

            if (usernameLength < 4) {

                JOptionPane.showMessageDialog(
                        this,
                        "Username Length must be at least 4");

                return false;
            }

            if (passwordLength < 4) {

                JOptionPane.showMessageDialog(
                        this,
                        "Password Length must be at least 4");

                return false;
            }

            if (sequenceStart < 1) {

                JOptionPane.showMessageDialog(
                        this,
                        "Sequential Start must be greater than 0");

                return false;
            }

            return true;

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter valid numeric values");

            return false;
        }
    }

    private void saveSettings() {

        try {

            Properties p =
                    new Properties();

            p.setProperty(
                    "usernameMode",
                    String.valueOf(
                            usernameModeBox.getSelectedIndex()));

            p.setProperty(
                    "passwordMode",
                    String.valueOf(
                            passwordModeBox.getSelectedIndex()));

            p.setProperty(
                    "profile",
                    String.valueOf(
                            profileBox.getSelectedIndex()));

            p.setProperty(
                    "uptime",
                    String.valueOf(
                            uptimeBox.getSelectedIndex()));

            p.setProperty(
                    "count",
                    countField.getText());

            p.setProperty(
                    "userLength",
                    userLengthField.getText());

            p.setProperty(
                    "passwordLength",
                    passLengthField.getText());

            p.setProperty(
                    "sequenceStart",
                    passStartField.getText());

            p.setProperty(
                    "prefix",
                    prefixField.getText());

            SettingsManager.save(p);

        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }

    private void loadSettings() {

        try {

            Properties p =
                    SettingsManager.load();

            usernameModeBox.setSelectedIndex(
                    Integer.parseInt(
                            p.getProperty(
                                    "usernameMode",
                                    "0")));

            passwordModeBox.setSelectedIndex(
                    Integer.parseInt(
                            p.getProperty(
                                    "passwordMode",
                                    "0")));

            profileBox.setSelectedIndex(
                    Integer.parseInt(
                            p.getProperty(
                                    "profile",
                                    "0")));

            uptimeBox.setSelectedIndex(
                    Integer.parseInt(
                            p.getProperty(
                                    "uptime",
                                    "0")));

            countField.setText(
                    p.getProperty(
                            "count",
                            "20"));

            userLengthField.setText(
                    p.getProperty(
                            "userLength",
                            "6"));

            passLengthField.setText(
                    p.getProperty(
                            "passwordLength",
                            "6"));

            passStartField.setText(
                    p.getProperty(
                            "sequenceStart",
                            "5001"));

            prefixField.setText(
                    p.getProperty(
                            "prefix",
                            "pass"));

        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }

    private void showAboutDialog() {

        JDialog dialog =
                new JDialog(
                        this,
                        "About MikroTik Hotspot Manager",
                        true);

        dialog.setSize(
                600,
                500);

        dialog.setLocationRelativeTo(
                this);

        dialog.setLayout(
                new BorderLayout(
                        10,
                        10));

        JTextArea area =
                new JTextArea(
                        """
     MikroTik Hotspot Manager
      Version 1.1
    
     Developed by
      Ibrahim Ramadan
    
     Features
      • User Generation
      • CSV Export
      • RSC Export
      • PDF Voucher Generation
      • QR Code Generation
      • QR Auto Login
      • History Tracking
      • Settings Persistence
    
     Technologies
      • Java
      • Swing
      • iTextPDF
      • ZXing
      • Git
    
     Copyright © 2026 Ibrahim Ramadan
     All Rights Reserved
    """);

        area.setEditable(false);

        area.setLineWrap(true);

        area.setWrapStyleWord(true);

        area.setFont(
                new Font(
                        "Segue UI",
                        Font.PLAIN,
                        15));

        area.setCaretPosition(0);

        JScrollPane scrollPane =
                new JScrollPane(area);

        JButton githubButton =
                new JButton(
                        "Open GitHub Repository");

        githubButton.addActionListener(
                e -> {

                    try {

                        Desktop.getDesktop()
                                .browse(
                                        new java.net.URI("https://github.com/0xibrR/MikroTik-Hotspot-Manager"));

                    } catch (Exception ex) {

                        ex.printStackTrace();
                    }
                });

        JButton closeButton =
                new JButton(
                        "Close");

        closeButton.addActionListener(
                e -> dialog.dispose());

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT));

        buttonPanel.add(
                githubButton);

        buttonPanel.add(
                closeButton);

        dialog.add(
                scrollPane,
                BorderLayout.CENTER);

        dialog.add(
                buttonPanel,
                BorderLayout.SOUTH);

        dialog.setVisible(
                true);
    }

}

