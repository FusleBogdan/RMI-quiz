package com.example.licenta;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class QuizController {
    private String username;
    private String topic;
    @FXML
    public Label question;

    @FXML
    public Button opt1, opt2, opt3, opt4;

    static int counter = 0;
    static int correct = 0;
    static int wrong = 0;

    @FXML
    private void initialize() {
        counter = 0; // Reset counter here
        loadQuestions();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }


    private void loadQuestions() {


        if (counter == 0) { //Question 1
            question.setText("1. Cine a regizat filmul Inception?");
            opt1.setText("Christopher Nolan");
            opt2.setText("Steven Spielberg");
            opt3.setText("Martin Scorsese");
            opt4.setText("Quentin Tarantino");
        }
        if (counter == 1) { // Question 2
            question.setText("Care este primul film din seria Star Wars?");
            opt1.setText("Star Wars: Episode IV - A New Hope");
            opt2.setText("Star Wars: Episode I - The Phantom Menace");
            opt3.setText("Star Wars: Episode VI - Return of the Jedi");
            opt4.setText("Star Wars: Episode VII - The Force Awakens");
        }
        if (counter == 2) { // Question 3
            question.setText("Cine a interpretat rolul lui Tony Stark / Iron Man în MCU?");
            opt1.setText("Robert Downey Jr.");
            opt2.setText("Chris Evans");
            opt3.setText("Chris Hemsworth");
            opt4.setText("Mark Ruffalo");
        }
        if (counter == 3) { // Question 4
            question.setText("Care este filmul cu cele mai mari încasări din toate timpurile?");
            opt1.setText("Avengers: Endgame");
            opt2.setText("Avatar");
            opt3.setText("Titanic");
            opt4.setText("Star Wars: Episode VII - The Force Awakens");
        }
        if (counter == 4) { // Question 5
            question.setText("Cine a jucat rolul lui Harry Potter în seria de filme Harry Potter?");
            opt1.setText("Daniel Radcliffe");
            opt2.setText("Rupert Grint");
            opt3.setText("Emma Watson");
            opt4.setText("Tom Felton");
        }
        if (counter == 5) { // Question 6
            question.setText("Cine a regizat trilogia originală a filmelor The Lord of the Rings?");
            opt1.setText("Peter Jackson");
            opt2.setText("Steven Spielberg");
            opt3.setText("Christopher Nolan");
            opt4.setText("James Cameron");
        }
        if (counter == 6) { // Question 7
            question.setText("Care este primul film din seria Jurassic Park?");
            opt1.setText("Jurassic Park");
            opt2.setText("The Lost World: Jurassic Park");
            opt3.setText("Jurassic Park III");
            opt4.setText("Jurassic World");
        }
        if (counter == 7) { // Question
            question.setText("Cine a regizat filmul Pulp Fiction?");
            opt1.setText("Quentin Tarantino");
            opt2.setText("Martin Scorsese");
            opt3.setText("Stanley Kubrick");
            opt4.setText("Steven Spielberg");
        }
        if (counter == 8) { // Question 9
            question.setText("Care este primul film din seria The Hunger Games?");
            opt1.setText("The Hunger Games");
            opt2.setText("Catching Fire");
            opt3.setText("Mockingjay - Part 1");
            opt4.setText("Mockingjay - Part 2");
        }
        if (counter == 9) { // Question 10
            question.setText("Cine a interpretat rolul lui Joker în filmul The Dark Knight?");
            opt1.setText("Heath Ledger");
            opt2.setText("Joaquin Phoenix");
            opt3.setText("Jack Nicholson");
            opt4.setText("Jared Leto");




    }

    }


    @FXML
    public void opt1clicked(ActionEvent event) {
        checkAnswer(opt1.getText().toString());
        if (checkAnswer(opt1.getText().toString())) {
            correct = correct + 1;
        } else {
            wrong = wrong + 1;
        }
        if (counter == 9) {
            try {
                System.out.println(correct);
                Stage thisstage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                thisstage.close();
                FXMLLoader quiz = new FXMLLoader(getClass().getResource("MeniuPrincipal.fxml"));
                Scene quizscene = new Scene(quiz.load());
                quizscene.setFill(Color.TRANSPARENT);
                Stage quizstage = new Stage();
                quizstage.setScene(quizscene);
                quizstage.setTitle("Dashboard"); // Add this line
                quizstage.initStyle(StageStyle.TRANSPARENT);
                quizstage.show();
                DatabaseHandler.insertIntoLeaderboard(username, topic, correct);


            } catch(IOException e) {
                e.printStackTrace();
            }
        } else {
            counter++;
            loadQuestions();
        }

    }

    boolean checkAnswer(String answer) {

        if (counter == 0) {
            if (answer.equals("Christopher Nolan")) {
                return true;
            } else {
                return false;
            }
        }
        if (counter == 1) {
            if (answer.equals("Star Wars: Episode IV - A New Hope")) {
                return true;
            } else {
                return false;
            }
        }
        if (counter == 2) {
            if (answer.equals("Robert Downey Jr.")) {
                return true;
            } else {
                return false;
            }
        }
        if (counter == 3) {
            if (answer.equals("Avengers: Endgame")) {
                return true;
            } else {
                return false;
            }
        }
        if (counter == 4) {
            if (answer.equals("Daniel Radcliffe")) {
                return true;
            } else {
                return false;
            }
        }
        if (counter == 5) {
            if (answer.equals("Peter Jackson")) {
                return true;
            } else {
                return false;
            }
        }
        if (counter == 6) {
            if (answer.equals("Jurassic Park")) {
                return true;
            } else {
                return false;
            }
        }
        if (counter == 7) {
            if (answer.equals("Quentin Tarantin")) {
                return true;
            } else {
                return false;
            }
        }
        if (counter == 8) {
            if (answer.equals("The Hunger Games")) {
                return true;
            } else {
                return false;
            }
        }
        if (counter == 9) {
            if (answer.equals("Heath Ledger")) {
                return true;
            } else {
                return false;
            }
        }
        return false;


    }

    @FXML
    public void opt2clicked(ActionEvent event) {
        checkAnswer(opt2.getText().toString());
        if (checkAnswer(opt2.getText().toString())) {
            correct = correct + 1;
        } else {
            wrong = wrong + 1;
        }
        if (counter == 9) {
            try {
                System.out.println(correct);
                Stage thisstage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                thisstage.close();
                FXMLLoader quiz = new FXMLLoader(getClass().getResource("MeniuPrincipal.fxml"));
                Scene quizscene = new Scene(quiz.load());
                quizscene.setFill(Color.TRANSPARENT);
                Stage quizstage = new Stage();
                quizstage.setScene(quizscene);
                quizstage.setTitle("Dashboard"); // Add this line
                quizstage.initStyle(StageStyle.TRANSPARENT);
                quizstage.show();

            } catch(IOException e) {
                e.printStackTrace();
            }
        } else {
            counter++;
            loadQuestions();
        }
    }

    @FXML
    public void opt3clicked(ActionEvent event) {
        checkAnswer(opt3.getText().toString());
        if (checkAnswer(opt3.getText().toString())) {
            correct = correct + 1;
        } else {
            wrong = wrong + 1;
        }
        if (counter == 9) {
            try {
                System.out.println(correct);
                Stage thisstage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                thisstage.close();
                FXMLLoader quiz = new FXMLLoader(getClass().getResource("MeniuPrincipal.fxml"));
                Scene quizscene = new Scene(quiz.load());
                quizscene.setFill(Color.TRANSPARENT);
                Stage quizstage = new Stage();
                quizstage.setScene(quizscene);
                quizstage.setTitle("Dashboard"); // Add this line
                quizstage.initStyle(StageStyle.TRANSPARENT);
                quizstage.show();

            } catch(IOException e) {
                e.printStackTrace();
            }
        } else {
            counter++;
            loadQuestions();
        }
    }

    @FXML
    public void opt4clicked(ActionEvent event) {
        checkAnswer(opt4.getText().toString());
        if (checkAnswer(opt4.getText().toString())) {
            correct = correct + 1;
        } else {
            wrong = wrong + 1;
        }
        if (counter == 9) {
            try {
                System.out.println(correct);
                Stage thisstage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                thisstage.close();
                FXMLLoader quiz = new FXMLLoader(getClass().getResource("MeniuPrincipal.fxml"));
                Scene quizscene = new Scene(quiz.load());
                quizscene.setFill(Color.TRANSPARENT);
                Stage quizstage = new Stage();
                quizstage.setScene(quizscene);
                quizstage.setTitle("Dashboard"); // Add this line
                quizstage.initStyle(StageStyle.TRANSPARENT);
                quizstage.show();

            } catch(IOException e) {
                e.printStackTrace();
            }
        } else {
            counter++;
            loadQuestions();
        }
    }

}


