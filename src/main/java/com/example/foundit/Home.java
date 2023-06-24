package com.example.foundit;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.net.MalformedURLException;

public class Home extends Application {
    private Stage primaryStage;
    private String userId;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public void start(Stage primaryStage) throws MalformedURLException {
        this.primaryStage = primaryStage;

        // Set application icon
        Image icon = new Image("file:C:/Users/nmary/OneDrive/Desktop/UN ORGANISED/ILLUSTRATIONS/logo.jpg");
        primaryStage.getIcons().add(icon);

        // Header
        HBox header = Header.createHeader();
        header.setPadding(new Insets(10));
        header.setStyle("-fx-background-color: #333333;");

        // Footer
        HBox footer = Footer.createFooter();
        footer.setPadding(new Insets(10));
        footer.setStyle("-fx-background-color: #333333;");

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #f4f4f4;");

        HBox buttonContainer = new HBox();
        buttonContainer.setSpacing(20);
        buttonContainer.setAlignment(Pos.CENTER);

        Button myNotificationButton = createButton("My Notifications");
        myNotificationButton.setOnAction(event -> showNotification());

        Button reportLostItemButton = createButton("Report Lost Item");
        reportLostItemButton.setOnAction(event -> showReportLostItem());

        Button submitFoundItemButton = createButton("Submit Found Item");
        submitFoundItemButton.setOnAction(event -> showSubmitFoundItem());

        Button dashboardButton = createButton("Dashboard");
        dashboardButton.setOnAction(event -> showDashboard());

        buttonContainer.getChildren().addAll(
                myNotificationButton, reportLostItemButton, submitFoundItemButton, dashboardButton
        );

        root.setCenter(buttonContainer);
        root.setBottom(footer);
        root.setTop(header);

        Scene scene = new Scene(root, 900, 700);
        primaryStage.setTitle("Home");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button createButton(String text) {
        Button button = new Button(text);
        button.setPrefSize(200, 200);
        button.setStyle(
                "-fx-background-color: linear-gradient(#4CAF50, #2E7D32);" +
                        "-fx-font-size: 16px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #FFFFFF;"
        );
        button.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        // Hovering effect
        button.setOnMouseEntered(event -> button.setStyle(
                "-fx-background-color: linear-gradient(#81C784, #43A047);"
        ));
        button.setOnMouseExited(event -> button.setStyle(
                "-fx-background-color: linear-gradient(#4CAF50, #2E7D32);"
        ));

        // Clicking effect
        button.setOnMousePressed(event -> button.setStyle(
                "-fx-background-color: linear-gradient(#388E3C, #1B5E20);"
        ));
        button.setOnMouseReleased(event -> button.setStyle(
                "-fx-background-color: linear-gradient(#81C784, #43A047);"
        ));

        return button;
    }

    private void showNotification() {
        Notification notificationPage = new Notification();
        notificationPage.setUserId(userId);
        notificationPage.start(new Stage());
    }

    private void showReportLostItem() {
        ReportLostItem reportLostItem = new ReportLostItem();
        reportLostItem.setUserId(userId);
        reportLostItem.start(new Stage());
        System.out.println("Showing Report Lost Item display");
    }

    private void showSubmitFoundItem() {
        FoundLostItem foundLostItem = new FoundLostItem();
        foundLostItem.setUserId(userId);
        foundLostItem.start(new Stage());
        System.out.println("Showing Submit Found Item display");
    }

    private void showDashboard() {
        System.out.println("Showing Dashboard display");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
