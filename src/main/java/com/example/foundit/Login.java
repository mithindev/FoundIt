package com.example.foundit;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

public class Login extends Application {
    private static final String USER_FILE = "users.txt";
    private Map<String, String> userCredentials;

    @Override
    public void init() {
        userCredentials = readUserCredentials();
    }

    @Override
    public void start(Stage stage) throws MalformedURLException {
        Image icon = new Image(getFileUrl("C:\\Users\\nmary\\OneDrive\\Desktop\\UN ORGANISED\\ILLUSTRATIONS\\logo.jpg"));
        stage.getIcons().add(icon);

        // Creating a BorderPane
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #f8f8f8;");

        // Creating a scene to stage.
        Scene scene = new Scene(root);

        // Create the header
        HBox header = HeaderLogin.createHeader();

        // Create the footer
        HBox footer = Footer.createFooter();

        // Splitting the Page into two equal parts.
        SplitPane middle = new SplitPane();
        middle.setDividerPosition(1, 1);

        // Left Pane with Image
        Image image = new Image("C:\\Users\\nmary\\OneDrive\\Desktop\\UN ORGANISED\\ILLUSTRATIONS\\1.jpeg");

        ImageView leftImageView = new ImageView(image);
        leftImageView.setPreserveRatio(true);
        leftImageView.setFitHeight(400);

        StackPane left = new StackPane(leftImageView);
        left.setPadding(new Insets(10));

        VBox right = new VBox(10);
        right.setAlignment(Pos.CENTER);
        right.setPadding(new Insets(10));

        Text title = new Text("Sign In");
        title.setFont(Font.font("Arial", 24));
        title.setFill(Color.BLACK);

        // Right Pane with Login Form
        GridPane rightPane = new GridPane();
        rightPane.setAlignment(Pos.CENTER);
        rightPane.setHgap(10);
        rightPane.setVgap(10);
        rightPane.setPadding(new Insets(50));

        Label amritaLogText = new Label("Amrita ID:");
        amritaLogText.setStyle("-fx-font-weight: bold;");
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-background-color: #a3113e; -fx-text-fill: white;");
        loginButton.setOnAction(event -> {
            // Perform login logic here
            String username = usernameField.getText();
            String password = passwordField.getText();
            if (authenticateUser(username, password)) {
                try {
                    openHomePage(stage, username);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                showErrorDialog("Invalid username or password");
            }
        });

        Button signupButton = new Button("Signup");
        signupButton.setStyle("-fx-background-color: #a3113e; -fx-text-fill: white;");
        signupButton.setOnAction(event -> {
            openSignupPage(stage);
        });

        rightPane.add(amritaLogText, 0, 0, 2, 1);
        rightPane.add(usernameLabel, 0, 1);
        rightPane.add(usernameField, 1, 1);
        rightPane.add(passwordLabel, 0, 2);
        rightPane.add(passwordField, 1, 2);
        rightPane.add(loginButton, 1, 3);
        rightPane.add(signupButton, 1, 4);

        VBox loginContainer = new VBox(20, title, rightPane);
        loginContainer.setAlignment(Pos.CENTER);

        // Set the header, middle section, and footer in the border pane
        root.setTop(header);
        root.setLeft(left);
        root.setCenter(loginContainer);
        root.setBottom(footer);

        stage.setTitle("Amrita Integrated Management System");
        stage.setWidth(1080);
        stage.setHeight(720);
        stage.setScene(scene);
        stage.show();
    }

    private String getFileUrl(String filePath) {
        try {
            return new File(filePath).toURI().toURL().toExternalForm();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> readUserCredentials() {
        Map<String, String> credentials = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String username = parts[0].trim();
                    String password = parts[1].trim();
                    credentials.put(username, password);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return credentials;
    }

    private boolean authenticateUser(String username, String password) {
        return userCredentials.containsKey(username) && userCredentials.get(username).equals(password);
    }

    private void openHomePage(Stage stage, String userId) throws MalformedURLException {
        Home home = new Home();
        home.setUserId(userId); // Set the userId in the Home class
        home.start(stage);
    }

    private void openSignupPage(Stage stage) {
        Stage signupStage = new Stage();
        GridPane signupPane = new GridPane();
        signupPane.setAlignment(Pos.CENTER);
        signupPane.setHgap(10);
        signupPane.setVgap(10);
        signupPane.setPadding(new Insets(50));

        Label signupLabel = new Label("Signup");
        signupLabel.setStyle("-fx-font-weight: bold;");
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        Label reenterPasswordLabel = new Label("Re-enter Password:");
        PasswordField reenterPasswordField = new PasswordField();

        Button signupButton = new Button("Sign up");
        signupButton.setStyle("-fx-background-color: #a3113e; -fx-text-fill: white;");
        signupButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String reenterPassword = reenterPasswordField.getText();

            if (!password.equals(reenterPassword)) {
                showErrorDialog("Passwords do not match");
                return;
            }

            if (userCredentials.containsKey(username)) {
                showErrorDialog("Username already exists");
                return;
            }

            userCredentials.put(username, password);
            saveUserCredentials();

            signupStage.close();
            showSuccessDialog("Signup successful! Please login.");
        });

        signupPane.add(signupLabel, 0, 0, 2, 1);
        signupPane.add(usernameLabel, 0, 1);
        signupPane.add(usernameField, 1, 1);
        signupPane.add(passwordLabel, 0, 2);
        signupPane.add(passwordField, 1, 2);
        signupPane.add(reenterPasswordLabel, 0, 3);
        signupPane.add(reenterPasswordField, 1, 3);
        signupPane.add(signupButton, 1, 4);

        Scene signupScene = new Scene(signupPane);
        signupStage.setTitle("Signup");
        signupStage.setWidth(400);
        signupStage.setHeight(300);
        signupStage.setScene(signupScene);
        signupStage.show();
    }

    private void showErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showSuccessDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void saveUserCredentials() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE))) {
            for (Map.Entry<String, String> entry : userCredentials.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
