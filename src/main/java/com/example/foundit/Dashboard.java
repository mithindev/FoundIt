package com.example.foundit;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Dashboard extends Application {
    private static final String LOST_ITEMS_FILE = "lost_items.txt";
    private static final String FOUND_ITEMS_FILE = "found_items.txt";
    private TableView<Item> table;
    private ObservableList<Item> lostItems;
    private ObservableList<Item> foundItems;

    @Override
    public void init() {
        loadItemsFromFiles();
    }

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 800, 600);

        // Header
        Label titleLabel = new Label("Lost and Found Dashboard");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        VBox headerBox = new VBox(titleLabel);
        headerBox.setPadding(new Insets(20));
        headerBox.setStyle("-fx-background-color: #f4f4f4;");
        root.setTop(headerBox);

        // Left Sidebar
        VBox sidebarBox = new VBox();
        sidebarBox.setPadding(new Insets(20));
        sidebarBox.setSpacing(10);

        Button lostItemsButton = new Button("Lost Items");
        lostItemsButton.setOnAction(event -> {
            table.setItems(lostItems);
        });

        Button foundItemsButton = new Button("Found Items");
        foundItemsButton.setOnAction(event -> {
            table.setItems(foundItems);
        });

        Button addNewItemButton = new Button("Add New Item");
        addNewItemButton.setOnAction(event -> {
            showAddItemDialog();
        });

        sidebarBox.getChildren().addAll(lostItemsButton, foundItemsButton, addNewItemButton);
        root.setLeft(sidebarBox);

        // Center Content
        table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn<Item, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Item, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        TableColumn<Item, LocalDate> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        TableColumn<Item, String> locationColumn = new TableColumn<>("Location");
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        TableColumn<Item, String> contactColumn = new TableColumn<>("Contact");
        contactColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));

        table.getColumns().addAll(nameColumn, descriptionColumn, dateColumn, locationColumn, contactColumn);

        VBox contentBox = new VBox();
        contentBox.setPadding(new Insets(20));
        contentBox.setSpacing(10);
        contentBox.getChildren().add(table);
        root.setCenter(contentBox);

        table.setItems(lostItems);

        stage.setTitle("Dashboard");
        stage.setScene(scene);
        stage.show();

        // Save items to files on application exit
        stage.setOnCloseRequest(event -> {
            saveItemsToFile(lostItems, LOST_ITEMS_FILE);
            saveItemsToFile(foundItems, FOUND_ITEMS_FILE);
        });
    }

    private void loadItemsFromFiles() {
        lostItems = Item.loadItemsFromFile(LOST_ITEMS_FILE);
        foundItems = Item.loadItemsFromFile(FOUND_ITEMS_FILE);
    }

    private void saveItemsToFile(ObservableList<Item> items, String filename) {
        Item.saveItemsToFile(items, filename);
    }

    private void showAddItemDialog() {
        Dialog<Item> dialog = new Dialog<>();
        dialog.setTitle("Add New Item");
        dialog.setHeaderText("Enter item details");

        ButtonType addButton = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        TextField nameField = new TextField();
        nameField.setPromptText("Item name");

        TextArea descriptionArea = new TextArea();
        descriptionArea.setPromptText("Item description");

        DatePicker datePicker = new DatePicker();
        datePicker.setPromptText("Date");

        TextField locationField = new TextField();
        locationField.setPromptText("Location");

        TextField contactField = new TextField();
        contactField.setPromptText("Contact");

        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Description:"), 0, 1);
        grid.add(descriptionArea, 1, 1);
        grid.add(new Label("Date:"), 0, 2);
        grid.add(datePicker, 1, 2);
        grid.add(new Label("Location:"), 0, 3);
        grid.add(locationField, 1, 3);
        grid.add(new Label("Contact:"), 0, 4);
        grid.add(contactField, 1, 4);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButton) {
                String name = nameField.getText();
                String description = descriptionArea.getText();
                LocalDate date = datePicker.getValue();
                String location = locationField.getText();
                String contact = contactField.getText();

                return new Item(name, description, date, location, contact);
            }
            return null;
        });

        dialog.showAndWait().ifPresent(item -> {
            lostItems.add(item);
            table.setItems(lostItems);
            saveItemsToFile(lostItems, LOST_ITEMS_FILE);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
