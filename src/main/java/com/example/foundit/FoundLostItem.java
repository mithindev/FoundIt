package com.example.foundit;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
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

        //icon
        Image icon = new Image("file:C:/Users/nmary/OneDrive/Desktop/UN ORGANISED/ILLUSTRATIONS/logo.jpg");
        primaryStage.getIcons().add(icon);

        // Main layout
        BorderPane mainLayout = new BorderPane();
        mainLayout.setStyle("-fx-background-color: #f1f1f1;");

        // Header
        Label headerLabel = new Label("Found Item Report");
        headerLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        BorderPane.setMargin(headerLabel, new Insets(10));

        // Footer
        Label footerLabel = new Label("© 2023 FoundIt App. All rights reserved.");
        footerLabel.setStyle("-fx-font-size: 12px;");
        BorderPane.setMargin(footerLabel, new Insets(10));

        // Form
        GridPane formGrid = new GridPane();
        formGrid.setAlignment(Pos.CENTER);
        formGrid.setHgap(10);
        formGrid.setVgap(10);
        formGrid.setPadding(new Insets(25));

        // Item Description
        Label descriptionLabel = new Label("Item Name:");
        TextField descriptionField = new TextField();
        descriptionField.setPromptText("Enter item name");
        formGrid.add(descriptionLabel, 0, 0);
        formGrid.add(descriptionField, 1, 0);

        // Item Category
        Label categoryLabel = new Label("Item Category:");
        ComboBox<String> categoryComboBox = new ComboBox<>();
        categoryComboBox.getItems().addAll("Electronics", "Accessories", "Books", "Clothing");
        formGrid.add(categoryLabel, 0, 1);
        formGrid.add(categoryComboBox, 1, 1);

        // Date and Time of Finding
        Label dateTimeLabel = new Label("Date and Time of Finding:");
        DatePicker datePicker = new DatePicker();
        TextField timeField = new TextField();
        timeField.setPromptText("HH:mm");
        HBox dateTimeBox = new HBox(10);
        dateTimeBox.getChildren().addAll(datePicker, timeField);
        formGrid.add(dateTimeLabel, 0, 2);
        formGrid.add(dateTimeBox, 1, 2);

        // Location of Finding
        Label locationLabel = new Label("Location of Finding:");
        TextField locationField = new TextField();
        locationField.setPromptText("Enter location");
        formGrid.add(locationLabel, 0, 3);
        formGrid.add(locationField, 1, 3);

        // Additional Details
        Label additionalDetailsLabel = new Label("Additional Details:");
        TextArea additionalDetailsArea = new TextArea();
        additionalDetailsArea.setPromptText("Enter additional details");
        additionalDetailsArea.setPrefRowCount(3);
        formGrid.add(additionalDetailsLabel, 0, 4);
        formGrid.add(additionalDetailsArea, 1, 4);

        // Submit Button
        Button submitButton = new Button("Submit");
        submitButton.setStyle("-fx-font-weight: bold; -fx-text-fill: white; -fx-background-color: #4CAF50;");
        formGrid.add(submitButton, 1, 5);
        GridPane.setHalignment(submitButton, HPos.RIGHT);

        // Submit Button Action
        submitButton.setOnAction(e -> {
            // Read the entered data
            String description = descriptionField.getText();
            String category = categoryComboBox.getValue();
            String date = datePicker.getValue().toString();
            String time = timeField.getText();
            String location = locationField.getText();
            String additionalDetails = additionalDetailsArea.getText();

            // Save the entered details to the found items file
            try (FileWriter writer = new FileWriter(FOUND_ITEMS_FILE, true)) {
                writer.write(description + "," + category + "," + date + "," + time + "," + location + "," +
                        additionalDetails);
                writer.write(System.lineSeparator());
                writer.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            System.out.println("Details saved successfully!");

            // Check for a match in the lost items file
            boolean matchFound = false;
            String foundUserId = null; // Variable to store the userId if a match is found
            try (BufferedReader reader = new BufferedReader(new FileReader(LOST_ITEMS_FILE))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] itemDetails = line.split(",");
                    if (itemDetails[0].equalsIgnoreCase(description) && itemDetails[1].equalsIgnoreCase(category)) {
                        matchFound = true;
                        foundUserId = itemDetails[itemDetails.length - 1]; // Assuming the userId is at the last index in the "lost_item.txt" file
                        break;
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            if (matchFound) {
                if (foundUserId != null) {
                    // Write a notification to the notification file with the found userId
                    try (FileWriter writer = new FileWriter(NOTIFICATION_FILE, true)) {
                        writer.write(foundUserId + ": A match has been found for your lost item!");
                        writer.write(System.lineSeparator());
                        writer.flush();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    System.out.println("Match found notification written to file!");
                } else {
                    System.out.println("No userId found for the matched item!");
                }
            } else {
                System.out.println("No match found!");
            }

            // Close the page
            primaryStage.close();
        });

        // Set the header, form, and footer in the main layout
        mainLayout.setTop(headerLabel);
        mainLayout.setCenter(formGrid);
        mainLayout.setBottom(footerLabel);
        BorderPane.setAlignment(headerLabel, Pos.CENTER);
        BorderPane.setAlignment(footerLabel, Pos.CENTER);
        BorderPane.setMargin(formGrid, new Insets(10));

        Scene scene = new Scene(mainLayout, 800, 570);

        // Apply CSS styles to the scene
        String css = """
                -fx-font-size: 14px;
                -fx-control-inner-background: #ffffff;
                -fx-prompt-text-fill: #808080;
                """;
        scene.getRoot().setStyle(css);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
