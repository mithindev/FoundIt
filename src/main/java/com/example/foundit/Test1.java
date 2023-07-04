package com.example.foundit;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Test1 extends Application {
    private static final String LOST_ITEMS_FILE = "lost_item.txt";
    private static final String FOUND_ITEMS_FILE = "found_item.txt";

    private ComboBox<String> categoryComboBox;
    private TableView<Item> tableView;
    private ObservableList<Item> items;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create the header
        HBox header = createHeader();

        // Create the refresh button
        Button refreshButton = new Button("Refresh");
        refreshButton.setOnAction(e -> refreshTable());

        // Create the center pane
        VBox centerPane = createCenterPane(refreshButton);

        // Create the footer
        HBox footer = createFooter();

        // Create the root pane
        BorderPane root = new BorderPane();
        root.setTop(header);
        root.setCenter(centerPane);
        root.setBottom(footer);

        // Create the scene with the root pane
        Scene scene = new Scene(root, 800, 600);

        // Set the stage title and scene, then show the stage
        primaryStage.setTitle("Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Initialize the table view
        initializeTableView();
    }

    private HBox createHeader() {
        HBox header = new HBox();
        header.setPadding(new Insets(10));
        header.setAlignment(Pos.CENTER);
        Label titleLabel = new Label("Dashboard");
        titleLabel.setTextFill(javafx.scene.paint.Color.WHITE);
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        header.getChildren().add(titleLabel);
        return header;
    }

    // Replace the createCenterPane() method with the following code:
    private VBox createCenterPane(Button refreshButton) {
        VBox centerPane = new VBox(10);
        centerPane.setPadding(new Insets(20));

        // Create the controls box
        HBox controlsBox = new HBox(10);
        controlsBox.setAlignment(Pos.CENTER_LEFT);
        categoryComboBox = new ComboBox<>();
        categoryComboBox.getItems().addAll("All", "Lost", "Found");
        categoryComboBox.setValue("All");
        controlsBox.getChildren().addAll(categoryComboBox, refreshButton);

        // Create the table view
        tableView = new TableView<>();
        items = FXCollections.observableArrayList();
        tableView.setItems(items);

        TableColumn<Item, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));

        TableColumn<Item, String> categoryColumn = new TableColumn<>("Category");
        categoryColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategory()));

        TableColumn<Item, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDate()));

        TableColumn<Item, String> timeColumn = new TableColumn<>("Time");
        timeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTime()));

        TableColumn<Item, String> detailsColumn = new TableColumn<>("Details");
        detailsColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDetails()));

        tableView.getColumns().addAll(nameColumn, categoryColumn, dateColumn, timeColumn, detailsColumn);

        centerPane.getChildren().addAll(controlsBox, tableView);
        VBox.setVgrow(tableView, javafx.scene.layout.Priority.ALWAYS);

        return centerPane;
    }


    private HBox createFooter() {
        HBox footer = new HBox();
        footer.setPadding(new Insets(10));
        footer.setAlignment(Pos.CENTER);
        Label footerLabel = new Label("Footer");
        footerLabel.setTextFill(javafx.scene.paint.Color.WHITE);
        footer.getChildren().add(footerLabel);
        return footer;
    }

    private void initializeTableView() {
        tableView = new TableView<>();
        items = FXCollections.observableArrayList();
        tableView.setItems(items);

        TableColumn<Item, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));

        TableColumn<Item, String> categoryColumn = new TableColumn<>("Category");
        categoryColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategory()));

        TableColumn<Item, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDate()));

        TableColumn<Item, String> timeColumn = new TableColumn<>("Time");
        timeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTime()));

        TableColumn<Item, String> detailsColumn = new TableColumn<>("Details");
        detailsColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDetails()));

        tableView.getColumns().addAll(nameColumn, categoryColumn, dateColumn, timeColumn, detailsColumn);

        refreshTable();
    }

    private void refreshTable() {
        String selectedCategory = categoryComboBox.getValue();
        items.clear();

        try {
            BufferedReader reader;
            if (selectedCategory.equals("All")) {
                reader = new BufferedReader(new FileReader(LOST_ITEMS_FILE));
                loadItemsFromReader(reader);
                reader = new BufferedReader(new FileReader(FOUND_ITEMS_FILE));
                loadItemsFromReader(reader);
            } else if (selectedCategory.equals("Lost")) {
                reader = new BufferedReader(new FileReader(LOST_ITEMS_FILE));
                loadItemsFromReader(reader);
            } else if (selectedCategory.equals("Found")) {
                reader = new BufferedReader(new FileReader(FOUND_ITEMS_FILE));
                loadItemsFromReader(reader);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadItemsFromReader(BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length >= 9) {
                String name = parts[0].trim();
                String category = parts[1].trim();
                String date = parts[2].trim();
                String time = parts[3].trim();
                String details = parts[4].trim();
                Item item = new Item(name, category, date, time, details);
                items.add(item);
            }
        }
    }

    private static class Item {
        private final String name;
        private final String category;
        private final String date;
        private final String time;
        private final String details;

        public Item(String name, String category, String date, String time, String details) {
            this.name = name;
            this.category = category;
            this.date = date;
            this.time = time;
            this.details = details;
        }

        public String getName() {
            return name;
        }

        public String getCategory() {
            return category;
        }

        public String getDate() {
            return date;
        }

        public String getTime() {
            return time;
        }

        public String getDetails() {
            return details;
        }
    }
}
