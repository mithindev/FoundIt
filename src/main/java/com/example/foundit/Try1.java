package com.example.foundit;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Try1 extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));

        // Item Name
        Label itemNameLabel = new Label("Item Name:");
        TextField itemNameField = new TextField();

        // Description
        Label descriptionLabel = new Label("Description:");
        TextArea descriptionArea = new TextArea();

        // Date/Time
        Label dateTimeLabel = new Label("Date/Time:");
        DatePicker datePicker = new DatePicker();
        TextField timeField = new TextField();

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
            String dateTime = datePicker.getValue().toString() + " " + timeField.getText();
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

        // Apply CSS styling
        root.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        root.getStyleClass().add("root");

        // Add form elements to the root container
        root.getChildren().addAll(
                createFieldLayout(itemNameLabel, itemNameField),
                createFieldLayout(descriptionLabel, descriptionArea),
                createFieldLayout(dateTimeLabel, createDateTimeLayout(datePicker)),
                createFieldLayout(locationLabel, locationField),
                createFieldLayout(contactLabel, contactField),
                createFieldLayout(imageLabel, createImageLayout(chooseImageButton)),
                submitButton
        );

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Report Lost Item");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createFieldLayout(Label label, Control control) {
        VBox fieldLayout = new VBox(5);
        fieldLayout.getChildren().addAll(label, control);
        return fieldLayout;
    }

    private HBox createDateTimeLayout(DatePicker datePicker) {
        HBox dateTimeLayout = new HBox(5);
        dateTimeLayout.getChildren().addAll(datePicker);
        return dateTimeLayout;
    }

    private HBox createImageLayout(Button chooseImageButton) {
        HBox imageLayout = new HBox(5);
        imageLayout.getChildren().addAll(chooseImageButton);
        return imageLayout;
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
