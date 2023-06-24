package com.example.foundit;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FoundLostItem extends Application {

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
        primaryStage.setTitle("Report Found Item");

        // Header
        Label headerLabel = new Label("Found Item Report");
        headerLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        VBox headerBox = new VBox(headerLabel);
        headerBox.setAlignment(Pos.CENTER);
        headerBox.setPadding(new Insets(10));
        headerBox.setStyle("-fx-background-color: #f1f1f1;");

        // Footer
        Label footerLabel = new Label("© 2023 FoundIt App. All rights reserved.");
        footerLabel.setStyle("-fx-font-size: 12px;");

        VBox footerBox = new VBox(footerLabel);
        footerBox.setAlignment(Pos.CENTER);
        footerBox.setPadding(new Insets(10));
        footerBox.setStyle("-fx-background-color: #f1f1f1;");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25));

        // Apply CSS styles to the grid
        grid.setStyle("-fx-background-color: #f1f1f1;");

        // Item Description
        Label descriptionLabel = new Label("Item Description:");
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
        Label dateTimeLabel = new Label("Date and Time of Losing:");
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
        grid.add(submitButton, 1, 8);

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

            // Save the entered details to the lost items file
            try (FileWriter writer = new FileWriter(FOUND_ITEMS_FILE, true)) {
                writer.write(description + "," + category + "," + date + "," + time + "," + location + "," +
                        fullName + "," + phone + "," + email + "," + additionalDetails);
                writer.write(System.lineSeparator());
                writer.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            System.out.println("Details saved successfully!");

            // Check for a match in the found items file
            boolean matchFound = false;
            try (BufferedReader reader = new BufferedReader(new FileReader(LOST_ITEMS_FILE))) {
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
        });

        // Main layout
        BorderPane mainLayout = new BorderPane();
        mainLayout.setTop(headerBox);
        mainLayout.setCenter(grid);
        mainLayout.setBottom(footerBox);
        mainLayout.setAlignment(headerBox, Pos.CENTER);
        mainLayout.setAlignment(footerBox, Pos.CENTER);
        mainLayout.setPadding(new Insets(10));
        mainLayout.setStyle("-fx-background-color: #e9e9e9;");

        Scene scene = new Scene(mainLayout, 800, 570);

        // Apply CSS styles to the scene
        String css = "-fx-font-size: 14px;";
        scene.getRoot().setStyle(css);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
