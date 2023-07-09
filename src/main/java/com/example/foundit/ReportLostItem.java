package com.example.foundit;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ReportLostItem extends Application {

    private String userId;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private static final String LOST_ITEMS_FILE = "lost_item.txt"; // File path for lost items
    private static final String FOUND_ITEMS_FILE = "found_item.txt"; // File path for found items
    private static final String NOTIFICATION_FILE = "notification.txt"; // File path for notifications

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Report Lost Item");

        // Set application icon
        Image icon = new Image("file:C:/Users/nmary/OneDrive/Desktop/UN ORGANISED/ILLUSTRATIONS/logo.jpg");
        primaryStage.getIcons().add(icon);

        // Header
        Label headerLabel = new Label("Report Lost Item");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        // Footer
        Label footerLabel = new Label("© 2023 FoundIt. All rights reserved.");
        footerLabel.setStyle("-fx-font-size: 12px;");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25));

        // Apply CSS styles to the grid
        grid.setStyle("-fx-background-color: #f1f1f1;");

        // Item Description
        Label descriptionLabel = new Label("Item Name:");
        TextField descriptionField = new TextField();
        grid.add(descriptionLabel, 0, 0);
        grid.add(descriptionField, 1, 0);

        // Item Category
        Label categoryLabel = new Label("Item Category:");
        ComboBox<String> categoryComboBox = new ComboBox<>();
        categoryComboBox.getItems().addAll("Electronics", "Accessories", "Books", "Clothing");
        grid.add(categoryLabel, 0, 1);
        grid.add(categoryComboBox, 1, 1);

        // Date and Time of Losing
        Label dateTimeLabel = new Label("Date and Time:");
        DatePicker datePicker = new DatePicker();
        TextField timeField = new TextField();
        timeField.setPromptText("HH:mm");
        HBox dateTimeBox = new HBox(10);
        dateTimeBox.getChildren().addAll(datePicker, timeField);
        grid.add(dateTimeLabel, 0, 2);
        grid.add(dateTimeBox, 1, 2);

        // Location of Losing
        Label locationLabel = new Label("Location of Losing:");
        TextField locationField = new TextField();
        grid.add(locationLabel, 0, 3);
        grid.add(locationField, 1, 3);

        // Contact Information
        Label contactLabel = new Label("Contact Information:");
        TextField fullNameField = new TextField();
        fullNameField.setPromptText("Full Name");
        TextField phoneField = new TextField();
        phoneField.setPromptText("Phone Number");
        TextField emailField = new TextField();
        emailField.setPromptText("Email Address");
        grid.add(contactLabel, 0, 4);
        grid.add(fullNameField, 1, 4);
        grid.add(phoneField, 1, 5);
        grid.add(emailField, 1, 6);

        // Additional Details
        Label additionalDetailsLabel = new Label("Additional Details:");
        TextArea additionalDetailsArea = new TextArea();
        additionalDetailsArea.setPrefRowCount(3);
        grid.add(additionalDetailsLabel, 0, 7);
        grid.add(additionalDetailsArea, 1, 7);

        // Submit Button
        Button submitButton = new Button("Submit");
        submitButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        submitButton.setPrefWidth(120);
        grid.add(submitButton, 1, 8);

        // Footer
        VBox footerBox = new VBox(10);
        footerBox.setAlignment(Pos.CENTER);
        footerBox.getChildren().add(footerLabel);

        // Main layout
        VBox mainLayout = new VBox(20);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setStyle("-fx-background-color: #e9e9e9;");
        mainLayout.getChildren().addAll(headerLabel, grid, footerBox);

        // Submit Button Action
        submitButton.setOnAction(e -> {
            // Read the entered data
            String description = descriptionField.getText();
            String category = categoryComboBox.getValue();
            String date = datePicker.getValue().toString();
            String time = timeField.getText();
            String location = locationField.getText();
            String fullName = fullNameField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();
            String additionalDetails = additionalDetailsArea.getText();

            // Validate phone number
            if (!phone.matches("\\d{10}")) {
                showAlert("Invalid phone number");
                return;
            }

            // Validate email
            if (!isValidEmail(email)) {
                showAlert("Invalid email address");
                return;
            }

            // Save the entered details to the lost items file
            try (FileWriter writer = new FileWriter(LOST_ITEMS_FILE, true)) {
                writer.write(description + "," + category + "," + date + "," + time + "," + location + ","
                        + fullName + "," + phone + "," + email + "," + additionalDetails + "," + userId);
                writer.write(System.lineSeparator());
                writer.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            System.out.println("Details saved successfully!");

            // Check for a match in the found items file
            boolean matchFound = false;
            try (BufferedReader reader = new BufferedReader(new FileReader(FOUND_ITEMS_FILE))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] itemDetails = line.split(",");
                    if (itemDetails[0].equalsIgnoreCase(description) && itemDetails[1].equalsIgnoreCase(category)) {
                        matchFound = true;
                        break;
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            if (matchFound) {
                // Write a notification to the notification file
                try (FileWriter writer = new FileWriter(NOTIFICATION_FILE, true)) {
                    writer.write(userId + ": A match has been found for your lost item!");
                    writer.write(System.lineSeparator());
                    writer.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                System.out.println("Match found notification written to file!");
            } else {
                System.out.println("No match found!");
            }

            // Close the page
            primaryStage.close();
        });

        Scene scene = new Scene(mainLayout, 780, 550);

        // Apply CSS styles to the scene
        String css = """
                -fx-font-size: 14px;
                -fx-background-color: #e9e9e9;
                """;
        scene.getRoot().setStyle(css);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex);
    }
}
