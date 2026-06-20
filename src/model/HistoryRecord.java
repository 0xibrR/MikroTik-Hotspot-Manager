package model;

public class HistoryRecord {

    private String date;
    private int count;
    private String profile;
    private String folder;

    public HistoryRecord(
            String date,
            int count,
            String profile,
            String folder) {

        this.date = date;
        this.count = count;
        this.profile = profile;
        this.folder = folder;
    }

    public String getDate() {
        return date;
    }

    public int getCount() {
        return count;
    }

    public String getProfile() {
        return profile;
    }

    public String getFolder() {
        return folder;
    }
}