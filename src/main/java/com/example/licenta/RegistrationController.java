package com.example.licenta;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistrationController {

    @FXML
    private TextField regUsernameTextField;

    @FXML
    private PasswordField regPasswordField;

    @FXML
    private Label registrationMessage;

    @FXML
    public void registrationButtonOnAction() {
        String username = regUsernameTextField.getText();
        String password = regPasswordField.getText();

        try {
            if (createUser(username, password)) {
                registrationMessage.setText("Registration successful!");

                // Create a pause of 3 seconds
                PauseTransition delay = new PauseTransition(Duration.seconds(3));

                // Set the action to perform after the pause
                delay.setOnFinished(event -> navigateToLogin());

                // Start the pause
                delay.play();

            } else {
                registrationMessage.setText("Username taken, please choose another.");
            }
        } catch (SQLException e) {
            registrationMessage.setText("An error occurred!");
            e.printStackTrace();
        }
    }

    public boolean createUser(String username, String password) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Connect to the database
            connection = DatabaseHandler.getConnection();

            // Check if user already exists
            String query = "SELECT * FROM users WHERE username = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // User already exists
                return false;
            } else {
                // Add new user
                query = "INSERT INTO users (username, password) VALUES (?, ?)";
                statement = connection.prepareStatement(query);
                statement.setString(1, username);
                statement.setString(2, password);
                int rowsAffected = statement.executeUpdate();

                // If rows are affected, the user was successfully created
                return rowsAffected > 0;
            }
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void navigateToLogin() {
        try {
            // Load the login interface (assuming it's called login.fxml)
            // Make sure to adjust the path to your login.fxml file
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));

            // Create a new scene with the login interface
            Scene scene = new Scene(root);

            // Get the current stage
            Stage stage = (Stage) registrationMessage.getScene().getWindow();

            // Set the new scene to the current stage
            stage.setScene(scene);

            // Display the new scene
            stage.show();

        } catch (Exception e) {
            // Handle the exception (e.g. print the error to the console)
            e.printStackTrace();
        }
    }
}
