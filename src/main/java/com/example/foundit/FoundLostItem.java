//package com.example.foundit;
//
//// The most important Class.
//
//import javafx.application.Application;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.layout.*;
//import javafx.stage.Stage;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.List;
//
//public class FoundLostItem extends Application {
//
//    private static final String FOUND_ITEMS_FILE = "found_item.txt"; //Found items
//    private static final String LOST_ITEMS_FILE = "lost_item.txt"; // Lost items
//
//    private List<ItemEntry> foundItems;
//    private List<ItemEntry> lostItems;
//    private Notification notification;
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//    @Override
//    public void start(Stage primaryStage) {
//        primaryStage.setTitle("Report Found Item");
//
//        foundItems = readItemsFromFile(FOUND_ITEMS_FILE);
//        lostItems = readItemsFromFile(LOST_ITEMS_FILE);
//
//        GridPane grid = new GridPane();
//        grid.setAlignment(Pos.CENTER);
//        grid.setHgap(10);
//        grid.setVgap(10);
//        grid.setPadding(new Insets(25));
//
//        // Apply CSS styles to the grid
//        grid.setStyle("-fx-background-color: #f1f1f1;");
//
//        // Item Description
//        Label descriptionLabel = new Label("Item Description:");
//        TextField descriptionField = new TextField();
//        grid.add(descriptionLabel, 0, 0);
//        grid.add(descriptionField, 1, 0);
//
//        // Item Category
//        Label categoryLabel = new Label("Item Category:");
//        ComboBox<String> categoryComboBox = new ComboBox<>();
//        categoryComboBox.getItems().addAll("Electronics", "Accessories", "Books", "Clothing");
//        grid.add(categoryLabel, 0, 1);
//        grid.add(categoryComboBox, 1, 1);
//
//        // Date and Time of Finding
//        Label dateTimeLabel = new Label("Date and Time of Finding:");
//        DatePicker datePicker = new DatePicker();
//        TextField timeField = new TextField();
//        timeField.setPromptText("HH:mm");
//        HBox dateTimeBox = new HBox(10);
//        dateTimeBox.getChildren().addAll(datePicker, timeField);
//        grid.add(dateTimeLabel, 0, 2);
//        grid.add(dateTimeBox, 1, 2);
//
//        // Location of Finding
//        Label locationLabel = new Label("Location of Finding:");
//        TextField locationField = new TextField();
//        grid.add(locationLabel, 0, 3);
//        grid.add(locationField, 1, 3);
//
//        // Contact Information
//        Label contactLabel = new Label("Contact Information:");
//        TextField fullNameField = new TextField();
//        fullNameField.setPromptText("Full Name");
//        TextField phoneField = new TextField();
//        phoneField.setPromptText("Phone Number");
//        TextField emailField = new TextField();
//        emailField.setPromptText("Email Address");
//        grid.add(contactLabel, 0, 4);
//        grid.add(fullNameField, 1, 4);
//        grid.add(phoneField, 1, 5);
//        grid.add(emailField, 1, 6);
//
//        // Additional Details
//        Label additionalDetailsLabel = new Label("Additional Details:");
//        TextArea additionalDetailsArea = new TextArea();
//        additionalDetailsArea.setPrefRowCount(3);
//        grid.add(additionalDetailsLabel, 0, 7);
//        grid.add(additionalDetailsArea, 1, 7);
//
//        // Submit Button
//        Button submitButton = new Button("Submit");
//        HBox buttonBox = new HBox(10);
//        buttonBox.setAlignment(Pos.CENTER);
//        buttonBox.getChildren().add(submitButton);
//        grid.add(buttonBox, 1, 9);
//        submitButton.setOnAction(e -> {
//            String description = descriptionField.getText();
//            String category = categoryComboBox.getValue();
//            String date = datePicker.getValue().format(DateTimeFormatter.ISO_DATE);
//            String time = timeField.getText();
//            String location = locationField.getText();
//            String fullName = fullNameField.getText();
//            String phone = phoneField.getText();
//            String email = emailField.getText();
//            String additionalDetails = additionalDetailsArea.getText();
//
//            ItemEntry foundItem = new ItemEntry(description, category, date, time, location, fullName, phone, email, additionalDetails);
//            foundItems.add(foundItem);
//
//            // Compare with lost items
//            for (ItemEntry lostItem : lostItems) {
//                if (foundItem.matches(lostItem)) {
//                    // Found a match, display notification
//                    showNotification(foundItem, lostItem);
//                }
//            }
//
//            writeItemsToFile(foundItems, FOUND_ITEMS_FILE);
//            clearFields();
//        });
//
//        // Apply CSS styles to the form elements
//        descriptionLabel.setStyle("-fx-font-weight: bold;");
//        categoryLabel.setStyle("-fx-font-weight: bold;");
//        dateTimeLabel.setStyle("-fx-font-weight: bold;");
//        locationLabel.setStyle("-fx-font-weight: bold;");
//        contactLabel.setStyle("-fx-font-weight: bold;");
//        additionalDetailsLabel.setStyle("-fx-font-weight: bold;");
//
//        submitButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
//
//        Scene scene = new Scene(grid, 700, 500);
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//    private void writeItemsToFile(List<ItemEntry> items, String fileName) {
//        try (FileWriter writer = new FileWriter(fileName, true)) {
//            for (ItemEntry item : items) {
//                writer.write(item.toEntryString() + "\n");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            // Handle the exception as per your application's error handling mechanism
//        }
//    }
//
//    private List<ItemEntry> readItemsFromFile(String fileName) {
//        List<ItemEntry> items = new ArrayList<>();
//        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                ItemEntry item = ItemEntry.fromString(line);
//                if (item != null) {
//                    items.add(item);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            // Handle the exception as per your application's error handling mechanism
//        }
//        return items;
//    }
//
//    private void clearFields() {
//        // Clear all the input fields after submission
//        // You can customize this method to reset individual fields as needed
//    }
//
//    private void showNotification(ItemEntry foundItem, ItemEntry lostItem) {
//        if (notification == null) {
//            notification = new Notification();
//        }
//        String message = "A lost item has been found!\n\n" +
//                "Found Item:\n" + foundItem.toString() + "\n\n" +
//                "Lost Item:\n" + lostItem.toString();
//        notification.display("Found Item Notification", message, Notification.NotificationType.INFO, () -> {
//            // Action performed when the button is clicked
//            System.out.println("Notification action performed!");
//        });
//    }
//
//
//    private static class ItemEntry {
//        private String description;
//        private String category;
//        private String date;
//        private String time;
//        private String location;
//        private String fullName;
//        private String phone;
//        private String email;
//        private String additionalDetails;
//
//        public ItemEntry(String description, String category, String date, String time, String location, String fullName,
//                         String phone, String email, String additionalDetails) {
//            this.description = description;
//            this.category = category;
//            this.date = date;
//            this.time = time;
//            this.location = location;
//            this.fullName = fullName;
//            this.phone = phone;
//            this.email = email;
//            this.additionalDetails = additionalDetails;
//        }
//
//        public String getDescription() {
//            return description;
//        }
//
//        public String getCategory() {
//            return category;
//        }
//
//        public String getDate() {
//            return date;
//        }
//
//        public String getTime() {
//            return time;
//        }
//
//        public String getLocation() {
//            return location;
//        }
//
//        public String getFullName() {
//            return fullName;
//        }
//
//        public String getPhone() {
//            return phone;
//        }
//
//        public String getEmail() {
//            return email;
//        }
//
//        public String getAdditionalDetails() {
//            return additionalDetails;
//        }
//
//        public boolean matches(ItemEntry other) {
//            return this.category.equalsIgnoreCase(other.category) &&
//                    this.date.equals(other.date) &&
//                    this.location.equalsIgnoreCase(other.location);
//        }
//
//        public String toEntryString() {
//            return description + ";" + category + ";" + date + ";" + time + ";" +
//                    location + ";" + fullName + ";" + phone + ";" + email + ";" + additionalDetails;
//        }
//
//        public static ItemEntry fromString(String entryString) {
//            String[] parts = entryString.split(";");
//            if (parts.length == 9) {
//                String description = parts[0];
//                String category = parts[1];
//                String date = parts[2];
//                String time = parts[3];
//                String location = parts[4];
//                String fullName = parts[5];
//                String phone = parts[6];
//                String email = parts[7];
//                String additionalDetails = parts[8];
//                return new ItemEntry(description, category, date, time, location, fullName, phone, email, additionalDetails);
//            }
//            return null;
//        }
//
//        @Override
//        public String toString() {
//            return "Description: " + description + "\n" +
//                    "Category: " + category + "\n" +
//                    "Date: " + date + "\n" +
//                    "Time: " + time + "\n" +
//                    "Location: " + location + "\n" +
//                    "Full Name: " + fullName + "\n" +
//                    "Phone: " + phone + "\n" +
//                    "Email: " + email + "\n" +
//                    "Additional Details: " + additionalDetails;
//        }
//    }
//}
