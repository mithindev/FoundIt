package com.example.foundit;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Notification extends Application {
    private static final String NOTIFICATION_FILE = "notification.txt";
    private static final int NOTIFICATION_WIDTH = 300;
    private static final int NOTIFICATION_HEIGHT = 100;

    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Notification");

        Label titleLabel = new Label("Notification");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        TextArea contentArea = new TextArea();
        contentArea.setEditable(false);
        contentArea.setWrapText(true);
        contentArea.setPrefWidth(NOTIFICATION_WIDTH);
        contentArea.setPrefHeight(NOTIFICATION_HEIGHT);

        Button dismissButton = new Button("Dismiss");
        dismissButton.setStyle("-fx-background-color: #a3113e; -fx-text-fill: white;");
        dismissButton.setOnAction(event -> primaryStage.hide());

        HBox buttonContainer = new HBox(10, dismissButton);
        buttonContainer.setAlignment(Pos.CENTER);

        StackPane notificationPane = new StackPane(contentArea);
        notificationPane.setPadding(new Insets(10));
        notificationPane.setStyle("-fx-background-color: #ffffff; -fx-border-color: #c0c0c0; -fx-border-width: 1px;");
        notificationPane.setMaxSize(NOTIFICATION_WIDTH, NOTIFICATION_HEIGHT);
        notificationPane.setPrefSize(NOTIFICATION_WIDTH, NOTIFICATION_HEIGHT);
        notificationPane.setOpacity(0);

        BorderPane root = new BorderPane();
        root.setTop(titleLabel);
        root.setCenter(notificationPane);
        root.setBottom(buttonContainer);
        root.setStyle("-fx-padding: 10px;");

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        // Check for notifications when the application starts
        Platform.runLater(this::checkNotifications);

        // Check for notifications periodically
        Duration notificationCheckInterval = Duration.seconds(10);
        FadeTransition fadeInTransition = createFadeInTransition(notificationPane);
        fadeInTransition.setOnFinished(event -> {
            PauseTransition pauseTransition = new PauseTransition(notificationCheckInterval);
            pauseTransition.setOnFinished(e -> {
                checkNotifications();
                if (contentArea.getText().isEmpty()) {
                    FadeTransition fadeOutTransition = createFadeOutTransition(notificationPane);
                    fadeOutTransition.setOnFinished(evt -> primaryStage.hide());
                    fadeOutTransition.play();
                } else {
                    fadeInTransition.play();
                }
            });
            pauseTransition.play();
        });

        primaryStage.setOnShowing(event -> fadeInTransition.play());

        primaryStage.show();
    }

    private FadeTransition createFadeInTransition(StackPane notificationPane) {
        FadeTransition fadeInTransition = new FadeTransition(Duration.millis(500), notificationPane);
        fadeInTransition.setFromValue(0);
        fadeInTransition.setToValue(1);
        return fadeInTransition;
    }

    private FadeTransition createFadeOutTransition(StackPane notificationPane) {
        FadeTransition fadeOutTransition = new FadeTransition(Duration.millis(500), notificationPane);
        fadeOutTransition.setFromValue(1);
        fadeOutTransition.setToValue(0);
        return fadeOutTransition;
    }

    private void checkNotifications() {
        try (BufferedReader reader = new BufferedReader(new FileReader(NOTIFICATION_FILE))) {
            StringBuilder contentBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line);
                contentBuilder.append(System.lineSeparator());
            }
            String content = contentBuilder.toString().trim();
            TextArea contentArea = (TextArea) ((BorderPane) primaryStage.getScene().getRoot()).getCenter();
            contentArea.setText(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
