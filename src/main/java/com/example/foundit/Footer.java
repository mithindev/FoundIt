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
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Footer extends Application {

    @Override
    public void start(Stage stage) {
        // Creating a BorderPane
        BorderPane root = new BorderPane();
        // Creating a scene to stage.
        Scene scene = new Scene(root, 800, 600);

        // Creating the footer
        HBox footer = createFooter();

        // Setting the footer at the bottom of the BorderPane
        root.setBottom(footer);

        stage.setTitle("Creative Footer Example");
        stage.setScene(scene);
        stage.show();
    }

    private HBox createFooter() {
        // Creating the HBox for the footer
        HBox footer = new HBox();
        footer.setStyle("-fx-background-color: #333333;");
        footer.setPadding(new Insets(10));
        footer.setAlignment(Pos.CENTER);

        // Creating the logo image
        Image logoImage = new Image("C:\\Users\\nmary\\OneDrive\\Desktop\\UN ORGANISED\\ILLUSTRATIONS\\1.jpeg");
        ImageView logoImageView = new ImageView(logoImage);
        logoImageView.setFitHeight(30);
        logoImageView.setPreserveRatio(true);

        // Creating the navigation buttons
        Button homeButton = createFooterButton("Home", "C:\\Users\\nmary\\OneDrive\\Desktop\\UN ORGANISED\\ILLUSTRATIONS\\1.jpeg");
        Button aboutButton = createFooterButton("About", "C:\\Users\\nmary\\OneDrive\\Desktop\\UN ORGANISED\\ILLUSTRATIONS\\1.jpeg");
        Button contactButton = createFooterButton("Contact", "C:\\Users\\nmary\\OneDrive\\Desktop\\UN ORGANISED\\ILLUSTRATIONS\\1.jpeg");

        // Creating the search field
        TextField searchField = new TextField();
        searchField.setPromptText("Search");
        searchField.setPrefWidth(200);

        // Creating the search button
        Button searchButton = new Button();
        Image searchIcon = new Image("C:\\Users\\nmary\\OneDrive\\Desktop\\UN ORGANISED\\ILLUSTRATIONS\\1.jpeg");
        ImageView searchIconView = new ImageView(searchIcon);
        searchIconView.setFitHeight(20);
        searchIconView.setPreserveRatio(true);
        searchButton.setGraphic(searchIconView);
        searchButton.setOnAction(event -> {
            String searchQuery = searchField.getText();
            // Perform search functionality with the query
            System.out.println("Search: " + searchQuery);
        });

        // Adding the logo, search field, search button, and navigation buttons to the footer
        footer.getChildren().addAll(logoImageView, searchField, searchButton, homeButton, aboutButton, contactButton);
        HBox.setMargin(searchField, new Insets(0, 10, 0, 10));

        return footer;
    }

    private Button createFooterButton(String text, String iconFileName) {
        Button button = new Button(text);
        button.setTextFill(Color.WHITE);
        Image icon = new Image(iconFileName);
        ImageView iconView = new ImageView(icon);
        iconView.setFitHeight(20);
        iconView.setPreserveRatio(true);
        button.setGraphic(iconView);
        button.setOnAction(event -> {
            // Perform functionality based on the button clicked
            System.out.println("Clicked: " + text);
        });
        return button;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
