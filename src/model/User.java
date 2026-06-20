package model;

public class User {

    private final String username;
    private final String password;
    private final String profile;
    private final String server;
    private final String uptime;

    public User(
            String username,
            String password,
            String profile,
            String server,
            String uptime) {

        this.username = username;
        this.password = password;
        this.profile = profile;
        this.server = server;
        this.uptime = uptime;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getProfile() {
        return profile;
    }

    public String getServer() {
        return server;
    }

    public String getUptime() {
        return uptime;
    }
}