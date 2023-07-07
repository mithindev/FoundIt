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
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Test2 extends Application {
    private String userIdFinal;
    private static final String NOTIFICATION_FILE = "notification.txt";

    public void setUserId(String userId) {
        this.userIdFinal = userId;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Set application icon
        Image icon = new Image("file:C:/Users/nmary/OneDrive/Desktop/UN ORGANISED/ILLUSTRATIONS/logo.jpg");
        primaryStage.getIcons().add(icon);

        // Create the header
        HBox header = createHeader();

        // Create the footer
        HBox footer = createFooter();

        // Load and display notifications for a sample user ID
        String userId = userIdFinal;
        List<String> notifications = getNotificationsForUser(userId);
        if (notifications.isEmpty()) {
            showNoNotificationsAlert();
        } else {
            showNotification("Notification", String.join("\n", notifications));
        }

        // Create the left-side image view
        ImageView imageView = createImageView();

        // Create the right-side pane for the notifications
        VBox notificationPane = createNotificationPane(userId, notifications);

        // Create the split pane and set its orientation and division ratio
        SplitPane splitPane = createSplitPane(imageView, notificationPane);

        // Create the root pane
        BorderPane root = new BorderPane();
        root.setTop(header);
        root.setCenter(splitPane);
        root.setBottom(footer);

        // Create the scene with the root pane
        Scene scene = new Scene(root, 800, 600);

        // Apply embedded styles
        scene.getRoot().setStyle("-fx-font-family: Arial;");

        // Set the stage title and scene, then show the stage
        primaryStage.setTitle("Notification");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private HBox createHeader() {
        HBox header = new HBox();
        header.setPadding(new Insets(10));
        header.setAlignment(Pos.CENTER);
        Label titleLabel = new Label("Notifications");
        titleLabel.setTextFill(javafx.scene.paint.Color.WHITE);
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        header.getChildren().add(titleLabel);
        header.setStyle("-fx-background-color: #333333;");
        return header;
    }

    private HBox createFooter() {
        HBox footer = new HBox();
        footer.setPadding(new Insets(10));
        footer.setAlignment(Pos.CENTER);
        Label footerLabel = new Label("© 2023 FoundIt");
        footerLabel.setTextFill(javafx.scene.paint.Color.WHITE);
        footerLabel.setStyle("-fx-font-size: 14px;");
        footer.getChildren().add(footerLabel);
        footer.setStyle("-fx-background-color: #333333;");
        return footer;
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

    private ImageView createImageView() {
        ImageView imageView = null;
        Image image = new Image("C:\\Users\\nmary\\OneDrive\\Desktop\\UN ORGANISED\\ILLUSTRATIONS\\1.jpeg");
        imageView = new ImageView(image);
        imageView.setFitWidth(400); // Adjust the width as desired
        imageView.setFitHeight(300); // Adjust the height as desired
        return imageView;
    }

    private VBox createNotificationPane(String userId, List<String> notifications) {
        VBox notificationPane = new VBox(10);
        notificationPane.setPadding(new Insets(20));
        notificationPane.setAlignment(Pos.TOP_LEFT);
        notificationPane.setStyle("-fx-background-color: #f8f8f8; -fx-border-color: #dddddd; -fx-border-width: 1px;");
        notificationPane.setMaxWidth(Double.MAX_VALUE); // Expand to fill available width

        // Create an icon image
        Image iconImage = new Image("file:C:/path/to/icon.png");
        ImageView iconImageView = new ImageView(iconImage);
        iconImageView.setFitWidth(20);
        iconImageView.setFitHeight(20);

        // Create a label for each notification
        for (String notification : notifications) {
            HBox notificationBox = new HBox(10);
            Label notificationLabel = new Label(notification);
            Button dismissButton = new Button("Dismiss");
            dismissButton.setOnAction(e -> {
                notificationPane.getChildren().remove(notificationBox);
                removeNotificationFromUser(userId, notification);
            });
            notificationBox.getChildren().addAll(iconImageView, notificationLabel, dismissButton);
            notificationBox.setAlignment(Pos.CENTER_LEFT);
            notificationBox.setSpacing(10);
            notificationBox.setPadding(new Insets(5));
            notificationBox.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
            notificationPane.getChildren().add(notificationBox);
        }

        return notificationPane;
    }

    private SplitPane createSplitPane(ImageView imageView, VBox notificationPane) {
        SplitPane splitPane = new SplitPane();
        splitPane.setOrientation(javafx.geometry.Orientation.HORIZONTAL);
        splitPane.setDividerPositions(0.5);
        splitPane.getItems().addAll(imageView, notificationPane);
        return splitPane;
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
}
