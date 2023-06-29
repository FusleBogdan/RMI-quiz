package com.example.licenta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler {

    private static final String DB_URL = "jdbc:sqlite:pwlic.db";

    static {
        try {
            createTableIfNotExists();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new org.sqlite.JDBC());
        return DriverManager.getConnection(DB_URL);
    }

    private static void createTableIfNotExists() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            String createUsersTableSQL = "CREATE TABLE IF NOT EXISTS users ("
                    + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + " username TEXT NOT NULL,"
                    + " password TEXT NOT NULL,"
                    + " photo TEXT"
                    + ");";
            statement.execute(createUsersTableSQL);

            String createLeaderboardTableSQL = "CREATE TABLE IF NOT EXISTS leaderboard ("
                    + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + " username TEXT NOT NULL,"
                    + " topic TEXT NOT NULL,"
                    + " correct_answers INTEGER NOT NULL"
                    + ");";
            statement.execute(createLeaderboardTableSQL);
        }
    }
    public static void insertIntoLeaderboard(String username, String topic, int correctAnswers) {
        String insertQuery = "INSERT INTO leaderboard (username, topic, correct_answers) VALUES (?, ?, ?)";

        Connection connection = null; // Declare the connection variable outside the try block

        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setString(1, username);
            statement.setString(2, topic);
            statement.setInt(3, correctAnswers);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<LeaderboardEntry> getLeaderboardData() throws SQLException {
        List<LeaderboardEntry> data = new ArrayList<>();

        String query = "SELECT username, topic, correct_answers FROM leaderboard";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String topic = resultSet.getString("topic");
                int correctAnswers = resultSet.getInt("correct_answers");

                data.add(new LeaderboardEntry(username, topic, correctAnswers));
            }
        }

        return data;
    }






    public static void saveProfilePhoto(String username, String photoPath) {
        String updateQuery = "UPDATE users SET photo = ? WHERE username = ?";

        Connection connection = null; // Declare the connection variable outside the try block

        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setString(1, photoPath);
            statement.setString(2, username);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
