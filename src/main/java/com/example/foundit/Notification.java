package com.example.foundit;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
        List<String> notifications = getNotificationsForUser(userId);
        if (notifications.isEmpty()) {
            showNoNotificationsAlert();
        } else {
            showNotification("Notification", String.join("\n", notifications));
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
        notificationPane.setStyle("-fx-background-color: #f8f8f8; -fx-border-color: #dddddd; -fx-border-width: 1px;");
        notificationPane.setMaxWidth(Double.MAX_VALUE); // Expand to fill available width

        // Create a label for each notification
        for (String notification : notifications) {
            HBox notificationBox = new HBox(10);
            Label notificationLabel = new Label(notification);
            Button dismissButton = new Button("Dismiss");
            dismissButton.setOnAction(e -> {
                notificationPane.getChildren().remove(notificationBox);
                removeNotificationFromUser(userId, notification);
            });
            notificationBox.getChildren().addAll(notificationLabel, dismissButton);
            notificationPane.getChildren().add(notificationBox);
        }

        // Create the split pane and set its orientation and division ratio
        SplitPane splitPane = new SplitPane();
        splitPane.setOrientation(javafx.geometry.Orientation.HORIZONTAL);
        splitPane.setDividerPositions(0.5);

        // Add the image view and right pane to the split pane
        splitPane.getItems().addAll(imageView, notificationPane);

        // Create the scene with the split pane as its root
        Scene scene = new Scene(splitPane, 800, 600);

        // Apply embedded styles
        scene.getRoot().setStyle("-fx-font-family: Arial;");

        // Set the stage title and scene, then show the stage
        primaryStage.setTitle("Notification");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private List<String> getNotificationsForUser(String userId) {
        List<String> notifications = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(NOTIFICATION_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length >= 2 && parts[0].trim().equals(userId)) {
                    String notification = parts[1].trim();
                    notifications.add(notification);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return notifications;
    }

    private void removeNotificationFromUser(String userId, String notification) {
        try {
            File inputFile = new File(NOTIFICATION_FILE);
            File tempFile = new File(NOTIFICATION_FILE + ".tmp");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String lineToRemove = userId + ": " + notification;
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                String trimmedLine = currentLine.trim();
                if (!trimmedLine.equals(lineToRemove)) {
                    writer.write(currentLine + System.lineSeparator());
                }
            }

            writer.close();
            reader.close();

            if (inputFile.delete()) {
                if (!tempFile.renameTo(inputFile)) {
                    throw new IOException("Could not rename the temporary file to the original file");
                }
            } else {
                throw new IOException("Could not delete the original file");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showNoNotificationsAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Notifications");
        alert.setHeaderText(null);
        alert.setContentText("No notifications found for the current user.");
        alert.showAndWait();
    }

    private void showNotification(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
