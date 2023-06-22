package com.example.foundit;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Home extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        HBox root = new HBox();
        root.setSpacing(20);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

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

        root.getChildren().addAll(myNotificationButton, reportLostItemButton, submitFoundItemButton, dashboardButton);

        Scene scene = new Scene(root, 1000, 700);
        primaryStage.setTitle("Home");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button createButton(String text) {
        Button button = new Button(text);
        button.setPrefSize(200, 200);
        return button;
    }

    private void showNotification() {
        // Replace with your implementation to show the "Report Lost Item" display
        System.out.println("Showing My myNotification");
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
