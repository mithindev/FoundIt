package com.example.foundit;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class Test2 extends Application {
    public static final int MAX_NOTIFICATIONS = 4;
    private static final Duration NOTIFICATION_DURATION = Duration.seconds(5);

    private static List<NotificationPane> notifications;
    private static VBox notificationContainer;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        notifications = new ArrayList<>();

        // Create the notification container
        notificationContainer = new VBox(10);
        notificationContainer.setAlignment(Pos.TOP_RIGHT);
        notificationContainer.setPadding(new Insets(20));
        notificationContainer.setStyle("-fx-background-color: #f8f8f8;");

        // Create a sample notification
        createNotification("Success", "We Found a match!", NotificationType.SUCCESS, () -> {
            // Action performed when the button is clicked
            System.out.println("Notification action performed!");
        });

        // Create the left-side image view
        Image image = new Image("C:\\Users\\nmary\\OneDrive\\Desktop\\UN ORGANISED\\ILLUSTRATIONS\\1.jpeg");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(400); // Adjust the width as desired
        imageView.setFitHeight(300); // Adjust the height as desired

        // Create the right-side pane for the image view and notifications
        StackPane rightPane = new StackPane(imageView, notificationContainer);

        // Create the split pane and set its orientation and division ratio
        SplitPane splitPane = new SplitPane();
        splitPane.setOrientation(javafx.geometry.Orientation.HORIZONTAL);
        splitPane.setDividerPositions(0.5);

        // Add the image view and right pane to the split pane
        splitPane.getItems().addAll(imageView, rightPane);

        // Create the scene with the split pane as its root
        Scene scene = new Scene(splitPane, 800, 600);

        // Set the stage title and scene, then show the stage
        primaryStage.setTitle("Notification");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void createNotification(String title, String message, NotificationType type, Runnable action) {
        if (notifications.size() >= MAX_NOTIFICATIONS) {
            // Remove the oldest notification if the maximum limit is reached
            NotificationPane oldestNotification = notifications.remove(0);
            notificationContainer.getChildren().remove(oldestNotification);
        }

        // Create the notification
        NotificationPane notification = new NotificationPane(title, message, type, action);
        notifications.add(notification);
        notificationContainer.getChildren().add(notification);

        // Apply fade-in animation
        FadeTransition fadeInTransition = new FadeTransition(Duration.millis(500), notification);
        fadeInTransition.setFromValue(0);
        fadeInTransition.setToValue(1);
        fadeInTransition.play();

        // Schedule notification dismissal
//        FadeTransition fadeOutTransition = new FadeTransition(NOTIFICATION_DURATION, notification);
//        fadeOutTransition.setFromValue(1);
//        fadeOutTransition.setToValue(0);
//        fadeOutTransition.setOnFinished(event -> {
//            notifications.remove(notification);
//            notificationContainer.getChildren().remove(notification);
//        });
//        fadeOutTransition.playFromStart();
    }

    static class NotificationPane extends VBox {
        private static final int NOTIFICATION_WIDTH = 300;
        private static final int NOTIFICATION_HEIGHT = 80;
        private static final int ICON_SIZE = 24;

        public NotificationPane(String title, String message, NotificationType type, Runnable action) {
            setPrefSize(NOTIFICATION_WIDTH, NOTIFICATION_HEIGHT);
            setMaxSize(NOTIFICATION_WIDTH, NOTIFICATION_HEIGHT);
            setStyle("-fx-background-color: #ffffff; -fx-border-color: #c0c0c0; -fx-border-width: 1px;");

            Label titleLabel = new Label(title);
            titleLabel.setStyle("-fx-font-weight: bold;");
            Label messageLabel = new Label(message);

            HBox content = new HBox(10);
            content.setAlignment(Pos.CENTER_LEFT);
            content.setPadding(new Insets(10));
            content.getChildren().addAll(createIcon(type), titleLabel, messageLabel, createActionButton(action));

            getChildren().add(content);
        }

        private ImageView createIcon(NotificationType type) {
            ImageView imageView = new ImageView();
            imageView.setFitWidth(ICON_SIZE);
            imageView.setFitHeight(ICON_SIZE);

            Image image1 = new Image("C:\\Users\\nmary\\OneDrive\\Desktop\\UN ORGANISED\\ILLUSTRATIONS\\1.jpeg");

            switch (type) {
                case SUCCESS:
                    imageView.setImage(image1);
                    break;
                case ERROR:
                    imageView.setImage(image1);
                    break;
                case WARNING:
                    imageView.setImage(image1);
                    break;
                case INFO:
                    imageView.setImage(image1);
                    break;
            }

            return imageView;
        }

        private Button createActionButton(Runnable action) {
            Button actionButton = new Button("Dismiss");
            actionButton.setStyle("-fx-background-color: #a3113e; -fx-text-fill: white;");
            actionButton.setOnAction(event -> {
                action.run();
                closeNotification();
            });

            return actionButton;
        }

        private void closeNotification() {
            FadeTransition fadeOutTransition = new FadeTransition(Duration.millis(500), this);
            fadeOutTransition.setFromValue(1);
            fadeOutTransition.setToValue(0);
            fadeOutTransition.setOnFinished(event -> {
                Pane parentContainer = (Pane) getParent();
                parentContainer.getChildren().remove(this);
            });
            fadeOutTransition.play();
        }
    }

    enum NotificationType {
        SUCCESS,
        ERROR,
        WARNING,
        INFO
    }

    public static void display(String title, String message, NotificationType type, Runnable action) {
        createNotification(title, message, type, action);
    }
}
