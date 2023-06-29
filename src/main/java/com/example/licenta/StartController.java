package com.example.licenta;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.stage.Window;

import java.io.IOException;

public class StartController {

    @FXML
    private Button signupbutton;

    @FXML
    private Button loginbutton;

    @FXML
    public void handleSignUpButtonAction() throws IOException {
        // Get the current window
        Window currentWindow = signupbutton.getScene().getWindow();

        // Load the registration interface
        Parent root = FXMLLoader.load(getClass().getResource("Registration.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        // Close the current window
        currentWindow.hide();
    }

    @FXML
    public void handleLogInButtonAction() throws IOException {
        // Get the current window
        Window currentWindow = loginbutton.getScene().getWindow();

        // Load the login interface
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        // Close the current window
        currentWindow.hide();
    }
}
