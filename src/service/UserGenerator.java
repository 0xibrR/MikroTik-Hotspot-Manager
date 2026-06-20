package service;

import model.User;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class UserGenerator {

    private static final String DIGITS =
            "0123456789";

    private static final String LETTERS_DIGITS =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private static final SecureRandom random =
            new SecureRandom();

    public List<User> generateUsers(

            int count,

            int usernameMode,

            int passwordMode,

            int usernameLength,

            int passwordLength,

            int sequenceStart,

            String prefix,

            String profile,

            String server,

            String uptime
    ) {

        List<User> users =
                new ArrayList<>();

        for (int i = 1; i <= count; i++) {

            String username;

            switch (usernameMode) {

                case 1:
                    username =
                            String.format(
                                    "user%03d",
                                    i);
                    break;

                case 2:
                    username =
                            randomString(
                                    DIGITS,
                                    usernameLength);
                    break;

                case 3:
                    username =
                            profile +
                                    String.format(
                                            "%03d",
                                            i);
                    break;

                default:
                    username =
                            randomString(
                                    LETTERS_DIGITS,
                                    usernameLength);
            }

            String password;

            switch (passwordMode) {

                case 1:
                    password =
                            username;
                    break;

                case 2:
                    password =
                            String.valueOf(
                                    sequenceStart
                                            + i - 1);
                    break;

                case 3:
                    password =
                            prefix +
                                    username;
                    break;

                default:
                    password =
                            randomString(
                                    DIGITS,
                                    passwordLength);
            }

            users.add(
                    new User(
                            username,
                            password,
                            profile,
                            server,
                            uptime
                    ));
        }

        return users;
    }

    private String randomString(
            String chars,
            int length) {

        StringBuilder sb =
                new StringBuilder();

        for (int i = 0; i < length; i++) {

            sb.append(
                    chars.charAt(
                            random.nextInt(
                                    chars.length()
                            )
                    )
            );
        }

        return sb.toString();
    }
}