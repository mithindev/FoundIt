package com.example.foundit;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Notification extends Application {
    private String userIdFinal;

    public void setUserId(String userId) {
        this.userIdFinal = userId;
    }

    private static final String NOTIFICATION_FILE = "notification.txt";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Load and display notifications for a sample user ID
        String userId = userIdFinal;
        String notifications = getNotificationsForUser(userId);
        if (notifications.isEmpty()) {
            showNoNotificationsAlert();
        } else {
            showNotification("Notification", notifications);
        }

        // Create the left-side image view
        Image image = new Image("C:\\Users\\nmary\\OneDrive\\Desktop\\UN ORGANISED\\ILLUSTRATIONS\\1.jpeg");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(400); // Adjust the width as desired
        imageView.setFitHeight(300); // Adjust the height as desired

        // Create the right-side pane for the notifications
        VBox notificationPane = new VBox(10);
        notificationPane.setPadding(new Insets(20));
        notificationPane.setAlignment(Pos.TOP_LEFT);
        notificationPane.setStyle("-fx-background-color: #f8f8f8;");

        // Create a label for the notifications
        Label notificationLabel = new Label(notifications);
        notificationLabel.setWrapText(true);
        notificationPane.getChildren().add(notificationLabel);

        // Create the split pane and set its orientation and division ratio
        SplitPane splitPane = new SplitPane();
        splitPane.setOrientation(javafx.geometry.Orientation.HORIZONTAL);
        splitPane.setDividerPositions(0.5);

        // Add the image view and right pane to the split pane
        splitPane.getItems().addAll(imageView, notificationPane);

        // Create the scene with the split pane as its root
        Scene scene = new Scene(splitPane, 800, 600);

        // Set the stage title and scene, then show the stage
        primaryStage.setTitle("Notification");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private String getNotificationsForUser(String userId) {
        StringBuilder notificationBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(NOTIFICATION_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length >= 2 && parts[0].trim().equals(userId)) {
                    String notification = parts[1].trim();
                    notificationBuilder.append(notification).append("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return notificationBuilder.toString().trim();
    }

    private void showNoNotificationsAlert() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Notifications");
        alert.setHeaderText(null);
        alert.setContentText("No notifications found for the current user.");
        alert.showAndWait();
    }

    private void showNotification(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
