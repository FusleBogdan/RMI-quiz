package com.example.licenta;

public class LeaderboardEntry {
    private String username;
    private String topic;
    private int correctAnswers;

    public LeaderboardEntry(String username, String topic, int correctAnswers) {
        this.username = username;
        this.topic = topic;
        this.correctAnswers = correctAnswers;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getTopic() {
        return topic;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    // Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }
}
