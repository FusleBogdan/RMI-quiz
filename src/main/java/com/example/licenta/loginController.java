package com.example.licenta;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class loginController {

    @FXML
    private Button loginButton;

    @FXML
    private Label loginMessage;

    public static String loggedInUsername;


    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    public void loginButtonOnAction() throws SQLException, IOException, InterruptedException {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        if (isLoginValid(username, password)) {
            loginMessage.setText("Login successful!");
            openNewStage(username);
            closeCurrentStage();
        } else {
            loginMessage.setText("Login failed!");
        }
    }

    private boolean isLoginValid(String username, String password) throws SQLException {
        try (Connection connection = DatabaseHandler.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?")) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            // Check if a user exists with the given username and password
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void openNewStage(String username) throws IOException, InterruptedException {
        loggedInUsername = username; // set logged in user
        startRMIServer();

        // Delay for 2 seconds
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MeniuPrincipal.fxml"));
        Parent root = loader.load();

        // Get the controller instance from the loader
        MeniuPrincipalController meniuPrincipalController = loader.getController();

        // Set the logged-in username

        // Create a new stage
        Stage newStage = new Stage();
        Scene scene = new Scene(root); // Create the scene using the root node
        newStage.setScene(scene);
        newStage.show();

        // Close the current stage
        closeCurrentStage();
    }



    private void startRMIServer() throws RemoteException, InterruptedException {
        System.out.println("RMI server is starting");
        new RMIServer();
        Thread.sleep(1000);
        System.out.println("RMI server is running");
    }


    private void closeCurrentStage() {
        // Get the current stage
        Stage currentStage = (Stage) loginButton.getScene().getWindow();
        // Close the current stage
        currentStage.close();
    }
}
