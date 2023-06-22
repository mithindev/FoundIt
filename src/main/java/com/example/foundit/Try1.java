package com.example.foundit;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Try1 extends Application {

    private static final String FILE_NAME = "lost_item.txt";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Report Lost Item");

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

        // Date and Time of Loss
        Label dateTimeLabel = new Label("Date and Time of Loss:");
        DatePicker datePicker = new DatePicker();
        TextField timeField = new TextField();
        timeField.setPromptText("HH:mm");
        HBox dateTimeBox = new HBox(10);
        dateTimeBox.getChildren().addAll(datePicker, timeField);
        grid.add(dateTimeLabel, 0, 2);
        grid.add(dateTimeBox, 1, 2);

        // Location of Loss
        Label locationLabel = new Label("Location of Loss:");
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
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().add(submitButton);
        grid.add(buttonBox, 1, 9);
        submitButton.setOnAction(e -> {
            String description = descriptionField.getText();
            String category = categoryComboBox.getValue();
            String date = datePicker.getValue().format(DateTimeFormatter.ISO_DATE);
            String time = timeField.getText();
            String location = locationField.getText();
            String fullName = fullNameField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();
            String additionalDetails = additionalDetailsArea.getText();

            String entry = description + ";" + category + ";" + date + ";" + time + ";" +
                    location + ";" + fullName + ";" + phone + ";" + email + ";" + additionalDetails;

            writeToFile(entry);
            clearFields();
            // You can add additional actions here, such as displaying a confirmation message.
        });

        // Apply CSS styles to the form elements
        descriptionLabel.setStyle("-fx-font-weight: bold;");
        categoryLabel.setStyle("-fx-font-weight: bold;");
        dateTimeLabel.setStyle("-fx-font-weight: bold;");
        locationLabel.setStyle("-fx-font-weight: bold;");
        contactLabel.setStyle("-fx-font-weight: bold;");
        additionalDetailsLabel.setStyle("-fx-font-weight: bold;");

        submitButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");

        Scene scene = new Scene(grid, 700, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void writeToFile(String entry) {
        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            writer.write(entry + "\n");
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception as per your application's error handling mechanism
        }
    }

    private void clearFields() {
        // Clear all the input fields after submission
        // You can customize this method to reset individual fields as needed
    }
}
