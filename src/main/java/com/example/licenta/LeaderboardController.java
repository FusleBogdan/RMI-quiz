package com.example.licenta;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

public class LeaderboardController {
    @FXML
    private TableView<LeaderboardEntry> leaderboardTable;
    @FXML
    private TableColumn<LeaderboardEntry, String> usernameColumn;
    @FXML
    private TableColumn<LeaderboardEntry, String> topicColumn;
    @FXML
    private TableColumn<LeaderboardEntry, Integer> correctAnswersColumn;
    private ObservableList<LeaderboardEntry> leaderboardEntries;

    public void initialize() {
        leaderboardEntries = FXCollections.observableArrayList();
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        topicColumn.setCellValueFactory(new PropertyValueFactory<>("topic"));
        correctAnswersColumn.setCellValueFactory(new PropertyValueFactory<>("correctAnswers"));

        try {
            loadLeaderboardData();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to load leaderboard data.");
        }

        leaderboardTable.setItems(leaderboardEntries);
    }

    private void loadLeaderboardData() throws SQLException {
        List<LeaderboardEntry> data = DatabaseHandler.getLeaderboardData();
        if (data.isEmpty()) {
            System.out.println("No data fetched from database.");
        } else {
            leaderboardEntries.addAll(data);
        }
    }
}
