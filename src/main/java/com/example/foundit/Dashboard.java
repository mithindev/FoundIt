package com.example.foundit;

import com.example.foundit.Item;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

public class Dashboard extends Application {
    private static final String LOST_ITEMS_FILE = "lost_item.txt";
    private static final String FOUND_ITEMS_FILE = "found_item.txt";
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
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titleLabel.setTextFill(Color.GREEN);

        HBox headerBox = new HBox(titleLabel);
        headerBox.setAlignment(Pos.CENTER);
        headerBox.setPadding(new Insets(20));
        headerBox.setStyle("-fx-background-color: #f4f4f4;");
        root.setTop(headerBox);

        // Footer
        Label footerLabel = new Label("© 2023 FoundIt. All rights reserved.");
        footerLabel.setFont(Font.font("Arial", 14));
        footerLabel.setAlignment(Pos.CENTER);
        footerLabel.setPadding(new Insets(10));
        footerLabel.setStyle("-fx-background-color: #f4f4f4;");

        HBox footerBox = new HBox(footerLabel);
        footerBox.setAlignment(Pos.CENTER);
        footerBox.setStyle("-fx-background-color: #f4f4f4;");
        root.setBottom(footerBox);

        // Left Sidebar
        VBox sidebarBox = new VBox();
        sidebarBox.setPadding(new Insets(20));
        sidebarBox.setSpacing(10);

        Button lostItemsButton = new Button("Lost Items");
        lostItemsButton.setFont(Font.font("Arial", 16));
        lostItemsButton.setStyle("-fx-background-color: #8BC34A; -fx-text-fill: white;");
        lostItemsButton.setOnAction(event -> table.setItems(lostItems));

        Button foundItemsButton = new Button("Found Items");
        foundItemsButton.setFont(Font.font("Arial", 16));
        foundItemsButton.setStyle("-fx-background-color: #8BC34A; -fx-text-fill: white;");
        foundItemsButton.setOnAction(event -> table.setItems(foundItems));

        sidebarBox.getChildren().addAll(lostItemsButton, foundItemsButton);
        root.setLeft(sidebarBox);

        // Center Content
        table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setStyle("-fx-font-size: 14px;");

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
    }

    private void loadItemsFromFiles() {
        lostItems = loadItemsFromFile(LOST_ITEMS_FILE);
        foundItems = loadItemsFromFile(FOUND_ITEMS_FILE);
    }

    private ObservableList<Item> loadItemsFromFile(String filename) {
        ObservableList<Item> items = FXCollections.observableArrayList();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String name = data[0].trim();
                String description = data[1].trim();
                LocalDate date = LocalDate.parse(data[2].trim());
                String location = data[3].trim();
                String contact = data[4].trim();
                items.add(new Item(name, description, date, location, contact));
            }
        } catch (IOException e) {
            System.err.println("Error reading items from file: " + e.getMessage());
        }
        return items;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
