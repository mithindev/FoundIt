package com.example.foundit;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SignUp extends Application {
    static String name, password, nextClass = "";
    public static ArrayList<String> list = new ArrayList<>();

    public static String UserName = "", Password = "";
    Text verificationMessage = new Text();

    public static void FileToList() throws IOException {
        File file = new File("user_details.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            list.add(scanner.nextLine());
        }
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.getIcons().add(new Image("C:\\Users\\nmary\\OneDrive\\Desktop\\UN ORGANISED\\ILLUSTRATIONS\\1.jpeg"));

        BorderPane root = new BorderPane();

        SplitPane middle = new SplitPane();
        middle.setDividerPosition(1, 1);

        Image image = new Image("C:\\Users\\nmary\\OneDrive\\Desktop\\UN ORGANISED\\ILLUSTRATIONS\\1.jpeg");

        ImageView photo = new ImageView(image);
        photo.setFitWidth(400);
        photo.setFitHeight(400);

        StackPane left = new StackPane(photo);

        VBox right = new VBox(10);
        right.setAlignment(Pos.CENTER);
        right.setPadding(new Insets(10));

        Text title = new Text("Sign In");
        title.setFill(Color.BLACK);

        TextField nameField = new TextField();
        nameField.setPromptText("Username/email/collegeID");
        nameField.setMaxWidth(230);
        nameField.textProperty().addListener((observable, oldValue, newValue) -> {
            name = newValue.trim();
            UserName = newValue.trim();
        });

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setMaxWidth(230);
        passwordField.textProperty().addListener((observable, oldValue, newValue) -> {
            password = newValue.trim();
            Password = newValue.trim();
        });

        Button signInButton = new Button("Sign In");
        signInButton.setOnAction(event -> {
            String[] temp = { name, password };
            boolean fieldsFilled = true;
            for (String str : temp) {
                if (str == null || str.isEmpty()) {
                    System.out.println(str);
                    fieldsFilled = false;
                    break;
                }
            }
            boolean credentialsMatch = false;
            if (fieldsFilled) {
                for (String str : list) {
                    String[] value = str.split(",");
                    if (value[0].equals(name) || value[1].equals(name) || value[3].equals(name)) {
                        if (value[4].equals(password)) {
                            credentialsMatch = true;
                            break;
                        }
                    }
                }
                if (credentialsMatch) {
                    verificationMessage.setText("Successfully Logged-In");
                    verificationMessage.setFill(Color.GREEN);

                    PauseTransition pause = new PauseTransition(Duration.seconds(2));
                    pause.setOnFinished(e -> {
                        Stage currentStage = (Stage) signInButton.getScene().getWindow();
                        currentStage.hide();
                        // TODO: Add logic for the next steps after successful login
                    });
                    pause.play();
                } else {
                    verificationMessage.setText("Credentials Do Not Match.");
                    verificationMessage.setFill(Color.RED);
                }
            } else {
                verificationMessage.setText("Please fill in all the details in the text fields.");
                verificationMessage.setFill(Color.RED);
            }
        });

        Button forgotPasswordButton = new Button("Forgot Password");
        forgotPasswordButton.setOnAction(event -> {
            Stage currentStage = (Stage) forgotPasswordButton.getScene().getWindow();
            currentStage.hide();
            // TODO: Add the logic for the forgot password functionality or page
        });

        VBox buttonsBox = new VBox(10, signInButton, forgotPasswordButton);
        buttonsBox.setAlignment(Pos.CENTER);

        VBox fieldsBox = new VBox(10, nameField, passwordField, buttonsBox);
        fieldsBox.setAlignment(Pos.CENTER);
        right.getChildren().addAll(title, fieldsBox, verificationMessage);

        middle.getItems().addAll(left, right);

        root.setCenter(middle);

        Scene scene = new Scene(root, 800, 600);

        primaryStage.setTitle("FoundIt");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) throws IOException {
        FileToList();
        launch(args);
        System.out.println(UserName);
        System.out.println(Password);
    }
}
