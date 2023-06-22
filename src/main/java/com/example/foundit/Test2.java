package com.example.foundit;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Test2 extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Creating a BorderPane as the root layout
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 800, 600);

        // Creating the header section
        HBox header = createHeader();
        root.setTop(header);

        primaryStage.setTitle("Lost & Found App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private HBox createHeader() {
        HBox header = new HBox();
        header.setStyle("-fx-background-color: #303030;");
        header.setPadding(new Insets(10));
        header.setAlignment(Pos.CENTER);

        // Creating a HBox for logo and title
        HBox logoBox = new HBox(5);
        logoBox.setAlignment(Pos.CENTER_LEFT);

        // Adding the logo to the header
        ImageView logoImage = new ImageView(new Image("C:\\Users\\nmary\\OneDrive\\Desktop\\UN ORGANISED\\ILLUSTRATIONS\\1.jpeg"));
        logoImage.setFitHeight(40);
        logoImage.setPreserveRatio(true);
        logoBox.getChildren().add(logoImage);

        // Adding the logo and title HBox to the header
        header.getChildren().add(logoBox);

        // Creating a HBox for title and remaining sections
        HBox titleBox = new HBox();
        titleBox.setAlignment(Pos.CENTER_LEFT);
        titleBox.setSpacing(10);

        // Adding the title to the header
        Text title = new Text("Lost & Found App");
        title.setFont(Font.font("Arial", 18));
        title.setFill(Color.WHITE);
        titleBox.getChildren().add(title);

        // Adding the title box to the header
        header.getChildren().add(titleBox);
        HBox.setHgrow(titleBox, Priority.ALWAYS);

        // Creating a HBox for search field and buttons
        HBox searchBox = new HBox(5);
        searchBox.setAlignment(Pos.CENTER_RIGHT);

        // Adding the search field to the header
        TextField searchField = new TextField();
        searchField.setPromptText("Search...");
        searchBox.getChildren().add(searchField);

        // Adding the search button to the header
        Button searchButton = new Button("Search");
        searchButton.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        searchButton.setOnAction(event -> {
            String searchText = searchField.getText();
            // Perform search logic with the provided search text
            System.out.println("Performing search for: " + searchText);
        });
        searchBox.getChildren().add(searchButton);

        // Adding the contributions section to the header
        Button contributionsButton = createHeaderButton("Contributions", "Contributions.class");
        searchBox.getChildren().add(contributionsButton);

        // Adding the contact section to the header
        Button contactButton = createHeaderButton("Contact", "Contact.class");
        searchBox.getChildren().add(contactButton);

        // Adding the search box HBox to the header
        header.getChildren().add(searchBox);

        return header;
    }

    private Button createHeaderButton(String buttonText, String targetClass) {
        Button button = new Button(buttonText);
        button.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        button.setOnAction(event -> {
            // Handle button click event
            // Open the target class or perform related actions
            System.out.println("Opening " + targetClass + "...");
        });
        return button;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
