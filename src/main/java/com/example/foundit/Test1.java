package com.example.foundit;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Test1 extends Application {

    private static final String FOUND_ITEMS_FILE = "found_item.txt"; // File path for found items
    private static final String LOST_ITEMS_FILE = "lost_item.txt"; // File path for lost items

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Dashboard");

        // Main layout
        BorderPane mainLayout = new BorderPane();
        mainLayout.setStyle("-fx-background-color: #f1f1f1;");

        // Header
        Label headerLabel = new Label("FoundIt - Dashboard");
        headerLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        headerLabel.setTextFill(Color.WHITE);
        headerLabel.setPadding(new Insets(20));
        headerLabel.setBackground(new Background(new BackgroundFill(Color.DARKBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        // Found Items
        VBox foundItemsBox = createItemBox("Found Items", getItemsFromFile(FOUND_ITEMS_FILE));

        // Lost Items
        VBox lostItemsBox = createItemBox("Lost Items", getItemsFromFile(LOST_ITEMS_FILE));

        // ScrollPane to contain the item boxes
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(new HBox(20, foundItemsBox, lostItemsBox));
        scrollPane.setStyle("-fx-background-color: transparent;");

        // Set the header and scroll pane in the main layout
        mainLayout.setTop(headerLabel);
        mainLayout.setCenter(scrollPane);
        BorderPane.setAlignment(headerLabel, Pos.CENTER);

        Scene scene = new Scene(mainLayout, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createItemBox(String title, List<String> items) {
        VBox itemBox = new VBox();
        itemBox.setPadding(new Insets(20));
        itemBox.setStyle("-fx-background-color: #f8f8f8;");

        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        itemBox.getChildren().add(titleLabel);

        if (items.isEmpty()) {
            Label noItemsLabel = new Label("No items found.");
            itemBox.getChildren().add(noItemsLabel);
        } else {
            for (String item : items) {
                Label itemLabel = new Label(item);
                itemBox.getChildren().add(itemLabel);
            }
        }

        return itemBox;
    }

    private List<String> getItemsFromFile(String filePath) {
        // Code to read items from the file and return a list of items
        // For demonstration purposes, returning dummy items

        List<String> items = new ArrayList<>();
        items.add("Item 1");
        items.add("Item 2");
        items.add("Item 3");

        return items;
    }
}
