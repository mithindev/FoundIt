package com.example.foundit;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ReportLostItem extends Application {

    @Override
    public void start(Stage primaryStage) {
        GridPane root = new GridPane();
        root.setPadding(new Insets(20));
        root.setHgap(10);
        root.setVgap(10);

        // Item Name
        Label itemNameLabel = new Label("Item Name:");
        TextField itemNameField = new TextField();

        // Description
        Label descriptionLabel = new Label("Description:");
        TextArea descriptionArea = new TextArea();

        // Date/Time
        Label dateTimeLabel = new Label("Date/Time:");
        DatePicker datePicker = new DatePicker();

        // Location
        Label locationLabel = new Label("Location:");
        TextField locationField = new TextField();

        // Contact Information
        Label contactLabel = new Label("Contact Information:");
        TextField contactField = new TextField();

        // Image
        Label imageLabel = new Label("Image:");
        Button chooseImageButton = new Button("Choose Image");
        ImageView selectedImageView = new ImageView();
        selectedImageView.setFitWidth(200);
        selectedImageView.setFitHeight(200);

        chooseImageButton.setOnAction(event -> {
            // Handle image selection logic
            // Replace with your implementation
            System.out.println("Choosing image...");
        });

        // Submit Button
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(event -> {
            // Retrieve form data and handle submission
            String itemName = itemNameField.getText();
            String description = descriptionArea.getText();
            String dateTime = datePicker.getValue().toString() + " ";
            String location = locationField.getText();
            String contactInfo = contactField.getText();
            // Handle image selection and submission logic

            // Perform data validation and submission
            if (validateFormData(itemName, description, dateTime, location, contactInfo)) {
                submitFormData(itemName, description, dateTime, location, contactInfo);
                clearForm();
                showSuccessDialog("Item report submitted successfully!");
            } else {
                showErrorDialog("Please fill in all required fields.");
            }
        });

        // Add form elements to the grid pane
        root.add(itemNameLabel, 0, 0);
        root.add(itemNameField, 1, 0);
        root.add(descriptionLabel, 0, 1);
        root.add(descriptionArea, 1, 1);
        root.add(dateTimeLabel, 0, 2);
        root.add(datePicker, 1, 2);
        root.add(locationLabel, 0, 3);
        root.add(locationField, 1, 3);
        root.add(contactLabel, 0, 4);
        root.add(contactField, 1, 4);
        root.add(imageLabel, 0, 5);
        root.add(chooseImageButton, 1, 5);
        root.add(selectedImageView, 1, 6);
        root.add(submitButton, 0, 7);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Report Lost Item");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private boolean validateFormData(String itemName, String description, String dateTime, String location, String contactInfo) {
        // Perform validation logic
        return !itemName.isEmpty() && !description.isEmpty() && !dateTime.isEmpty()
                && !location.isEmpty() && !contactInfo.isEmpty();
    }

    private void submitFormData(String itemName, String description, String dateTime, String location, String contactInfo) {
        // Replace with your implementation to handle form submission
        System.out.println("Submitting form data:");
        System.out.println("Item Name: " + itemName);
        System.out.println("Description: " + description);
        System.out.println("Date/Time: " + dateTime);
        System.out.println("Location: " + location);
        System.out.println("Contact Information: " + contactInfo);
    }

    private void clearForm() {
        // Clear form fields
        // Replace with your implementation
        System.out.println("Clearing form fields");
    }

    private void showSuccessDialog(String message) {
        // Show success dialog
        // Replace with your implementation
        System.out.println("Success: " + message);
    }

    private void showErrorDialog(String message) {
        // Show error dialog
        // Replace with your implementation
        System.out.println("Error: " + message);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
