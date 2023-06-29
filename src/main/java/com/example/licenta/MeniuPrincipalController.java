package com.example.licenta;

import com.example.licenta.DatabaseHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.cert.PolicyNode;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class MeniuPrincipalController{

    @FXML
    private Circle circle;

    @FXML
    private Label usernameLabel;

    private Stage stage;

    @FXML
    private Button logoutbutton;
    @FXML
    private Button profilbutton;
    @FXML
    private Button playButton;

    @FXML
    private Button dashboardbutton;
    @FXML
    private Button leaderboardbutton;
    @FXML
    private BorderPane borderPane;
    @FXML
    private AnchorPane rightAnchorPane;

    private LeaderboardController LeaderboardController;


    @FXML
    private ComboBox<String> categoryComboBox;
    @FXML
    private Label categorie;

    @FXML
    public void initialize() {

        categorie = new Label();
        if (categoryComboBox != null) {
            categoryComboBox.getItems().addAll(
                    "Istoria lumii",
                    "Geografie mondială",
                    "Filme și seriale celebre",
                    "Simboluri și steaguri naționale",
                    "Matematică",
                    "Calcul",
                    "Fizică",
                    "Chimie",
                    "Astronomie",
                    "Informatică și programare",
                    "Baze de date",
                    "Sisteme de operare",
                    "Jocuri video și istoria lor",
                    "Istoria internetului"
            );

            categoryComboBox.setOnAction(event -> {
                String selectedCategory = categoryComboBox.getValue();
                if (selectedCategory != null) {
                    // Handle the selected category
                    categorie.setText(selectedCategory);
                }
            });
        }
    }

    @FXML
    private void handleLogout() {
        // Code to perform logout operations
        // For example, clearing user session, resetting values, etc.

        // Load the Login.fxml file
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root = loader.load();

            // Get the current stage
            Stage currentStage = (Stage) categoryComboBox.getScene().getWindow();

            // Create a new stage for the Login screen
            Stage loginStage = new Stage();
            loginStage.setScene(new Scene(root));
            loginStage.setTitle("Login");

            // Close the current stage (Meniu Principal)
            currentStage.close();

            // Show the Login screen
            loginStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void handleDashboardButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MeniuPrincipal.fxml"));
        ((Node)event.getSource()).getScene().setRoot(root);
    }


    @FXML
    private void handleLeaderboardButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Leaderboard.fxml"));
        AnchorPane dashboardPane = loader.load();
        rightAnchorPane.getChildren().setAll(dashboardPane);
    }


    @FXML
    private void handleProfileButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Profile.fxml"));
        AnchorPane profilePane = loader.load();
        rightAnchorPane.getChildren().setAll(profilePane);
    }

    @FXML
    private void handlePlayButton(ActionEvent event) throws IOException {
        String selectedCategory = categoryComboBox.getValue();
        if (selectedCategory != null) {
            System.out.println("Selected category: " + selectedCategory); // Debug message
            categorie.setText(selectedCategory);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CategoryQuiz.fxml"));
            AnchorPane categoryQuizPane = loader.load();

            // Get the controller instance from the loader
            QuizController quizController = loader.getController();

            // Set the logged-in username and selected topic
            quizController.setUsername(loginController.loggedInUsername);
            quizController.setTopic(categorie.getText());

            rightAnchorPane.getChildren().setAll(categoryQuizPane);
        }
    }



    public void setSelectedCategory(String selectedCategory) {
        categorie.setText(selectedCategory);
    }



    @FXML
    private void handleCategoryChange(ActionEvent event) {
        String selectedCategory = categoryComboBox.getValue();
        if (selectedCategory != null) {
            categorie.setText(selectedCategory);
        }
    }





    private void loadContent(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            AnchorPane content = loader.load();
            borderPane.setCenter(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void changeProfilePhoto() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Profile Photo");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            try {
                Image selectedImage = new Image(selectedFile.toURI().toString());
                circle.setFill(new ImagePattern(selectedImage));

                String username = usernameLabel.getText(); // Assuming the username is displayed in the usernameLabel

                if (!username.isEmpty()) {
                    String photoPath = selectedFile.getAbsolutePath();
                    DatabaseHandler.saveProfilePhoto(username, photoPath);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void setDefaultImagePattern() {
        try {
            Image defaultImage = new Image(getClass().getResourceAsStream("/img/pfp.png"));
            circle.setFill(new ImagePattern(defaultImage));
        } catch (NullPointerException e) {
            System.err.println("Failed to load default image: " + e.getMessage());
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }


}
