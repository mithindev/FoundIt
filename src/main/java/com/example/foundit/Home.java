package com.example.foundit;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Home extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        VBox root = new VBox();
        root.setSpacing(20);
        root.setPadding(new Insets(20));

        Button reportLostItemButton = new Button("Report Lost Item");
        reportLostItemButton.setOnAction(event -> {
            showReportLostItem();
        });

        Button submitFoundItemButton = new Button("Submit Found Item");
        submitFoundItemButton.setOnAction(event -> {
            showSubmitFoundItem();
        });

        Button dashboardButton = new Button("Dashboard");
        dashboardButton.setOnAction(event -> {
            showDashboard();
        });

        root.getChildren().addAll(reportLostItemButton, submitFoundItemButton, dashboardButton);

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Home");
        primaryStage.setScene(scene);
        primaryStage.show();
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
