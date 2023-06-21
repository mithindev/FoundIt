package com.example.foundit;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DashboardDemo extends Application {
    private static final String ITEM_FILE = "items.txt";
    private ObservableList<ItemDemo> items;
    private TableView<ItemDemo> table;

    @Override
    public void init() {
        loadItemsFromFile();
    }

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 800, 600);

        // Header
        Label titleLabel = new Label("Book Shop Management System");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        VBox headerBox = new VBox(titleLabel);
        headerBox.setPadding(new Insets(20));
        headerBox.setStyle("-fx-background-color: #f4f4f4;");
        root.setTop(headerBox);

        // Left Sidebar
        VBox sidebarBox = new VBox();
        sidebarBox.setPadding(new Insets(20));
        sidebarBox.setSpacing(10);

        Button viewItemsButton = new Button("View Items");
        viewItemsButton.setOnAction(event -> {
            table.setItems(items);
        });

        Button addItemButton = new Button("Add Item");
        addItemButton.setOnAction(event -> {
            showAddItemDialog();
        });

        sidebarBox.getChildren().addAll(viewItemsButton, addItemButton);
        root.setLeft(sidebarBox);

        // Center Content
        table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn<ItemDemo, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        TableColumn<ItemDemo, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        TableColumn<ItemDemo, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        TableColumn<ItemDemo, LocalDate> publishDateColumn = new TableColumn<>("Publish Date");
        publishDateColumn.setCellValueFactory(new PropertyValueFactory<>("publishDate"));

        table.getColumns().addAll(titleColumn, authorColumn, priceColumn, publishDateColumn);

        VBox contentBox = new VBox();
        contentBox.setPadding(new Insets(20));
        contentBox.setSpacing(10);
        contentBox.getChildren().add(table);
        root.setCenter(contentBox);

        // Sample Data
        items = FXCollections.observableArrayList();
        table.setItems(items);

        stage.setTitle("Dashboard");
        stage.setScene(scene);
        stage.show();
    }

    private void loadItemsFromFile() {
        items = loadItemsFromFile(ITEM_FILE);
    }

    private ObservableList<ItemDemo> loadItemsFromFile(String filename) {
        ObservableList<ItemDemo> loadedItems = FXCollections.observableArrayList();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 4) {
                    String title = parts[0].trim();
                    String author = parts[1].trim();
                    double price = Double.parseDouble(parts[2].trim());
                    LocalDate publishDate = LocalDate.parse(parts[3].trim());
                    ItemDemo item = new ItemDemo(title, author, price, publishDate);
                    loadedItems.add(item);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading items from file: " + e.getMessage());
        }
        return loadedItems;
    }

    private void saveItemsToFile() {
        saveItemsToFile(items, ITEM_FILE);
    }

    private void saveItemsToFile(ObservableList<ItemDemo> items, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (ItemDemo item : items) {
                String line = item.getTitle() + ";" + item.getAuthor() + ";" + item.getPrice() + ";" +
                        item.getPublishDate().toString();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving items to file: " + e.getMessage());
        }
    }

    private void showAddItemDialog() {
        Dialog<ItemDemo> dialog = new Dialog<>();
        dialog.setTitle("Add Item");

        // Set the button types
        ButtonType addButton = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

        // Create the item form layout
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField titleField = new TextField();
        titleField.setPromptText("Title");
        TextField authorField = new TextField();
        authorField.setPromptText("Author");
        TextField priceField = new TextField();
        priceField.setPromptText("Price");
        DatePicker publishDateField = new DatePicker();
        publishDateField.setPromptText("Publish Date");

        grid.add(new Label("Title:"), 0, 0);
        grid.add(titleField, 1, 0);
        grid.add(new Label("Author:"), 0, 1);
        grid.add(authorField, 1, 1);
        grid.add(new Label("Price:"), 0, 2);
        grid.add(priceField, 1, 2);
        grid.add(new Label("Publish Date:"), 0, 3);
        grid.add(publishDateField, 1, 3);

        dialog.getDialogPane().setContent(grid);

        // Request focus on the title field by default
        Platform.runLater(titleField::requestFocus);

        // Convert the result to an item when the add button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButton) {
                String title = titleField.getText();
                String author = authorField.getText();
                double price = Double.parseDouble(priceField.getText());
                LocalDate publishDate = publishDateField.getValue();

                ItemDemo newItem = new ItemDemo(title, author, price, publishDate);
                items.add(newItem);
                saveItemsToFile();
                return newItem;
            }
            return null;
        });

        dialog.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
