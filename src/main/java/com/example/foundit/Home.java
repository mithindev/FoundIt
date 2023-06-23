package com.example.foundit;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Home extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #f4f4f4;");

        HBox buttonContainer = new HBox();
        buttonContainer.setSpacing(20);
        buttonContainer.setAlignment(Pos.CENTER);

        Button myNotificationButton = createButton("My Notifications");
        myNotificationButton.setOnAction(event -> {
            showNotification();
        });

        Button reportLostItemButton = createButton("Report Lost Item");
        reportLostItemButton.setOnAction(event -> {
            showReportLostItem();
        });

        Button submitFoundItemButton = createButton("Submit Found Item");
        submitFoundItemButton.setOnAction(event -> {
            showSubmitFoundItem();
        });

        Button dashboardButton = createButton("Dashboard");
        dashboardButton.setOnAction(event -> {
            showDashboard();
        });

        buttonContainer.getChildren().addAll(
                myNotificationButton, reportLostItemButton, submitFoundItemButton, dashboardButton
        );

        root.setCenter(buttonContainer);

        Scene scene = new Scene(root, 1000, 700);
        primaryStage.setTitle("Home");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button createButton(String text) {
        Button button = new Button(text);
        button.setPrefSize(200, 200);
        button.setStyle(
                "-fx-background-color: #ffffff;" +
                        "-fx-font-size: 16px;" +
                        "-fx-font-weight: bold;"
        );
        button.setTextFill(Color.BLACK);
        button.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        // Hovering effect
        button.setOnMouseEntered(event -> {
            button.setStyle("-fx-background-color: #e0e0e0;");
        });

        button.setOnMouseExited(event -> {
            button.setStyle("-fx-background-color: #ffffff;");
        });

        // Clicking effect
        button.setOnMouseClicked(event -> {
            button.setStyle("-fx-background-color: #c0c0c0;");
        });

        return button;
    }

    private void showNotification() {
        // Replace with your implementation to show the "My Notifications" display
        System.out.println("Showing My Notifications");
    }

    private void showReportLostItem() {
        // Replace with your implementation to show the "Report Lost Item" display
        System.out.println("Showing Report Lost Item display");
    }

    private void showSubmitFoundItem() {
        // Replace with your implementation to show the "Submit Found Item" display
        System.out.println("Showing Submit Found Item display");
    }

    private void showDashboard() {
        // Replace with your implementation to show the "Dashboard" display
        System.out.println("Showing Dashboard display");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
