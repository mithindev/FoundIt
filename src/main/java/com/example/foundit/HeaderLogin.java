package com.example.foundit;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import com.example.foundit.Contact;

public class HeaderLogin extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 800, 600);

        HBox header = createHeader();
        root.setTop(header);

        primaryStage.setTitle("FoundIt: We Search for You!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static HBox createHeader() {
        HBox header = new HBox();
        header.setStyle("-fx-background-color: #303030;");
        header.setPadding(new Insets(10));
        header.setAlignment(Pos.CENTER);

        HBox logoBox = new HBox(5);
        logoBox.setAlignment(Pos.CENTER_LEFT);

        ImageView logoImage = new ImageView(new Image("C:\\Users\\nmary\\OneDrive\\Desktop\\UN ORGANISED\\ILLUSTRATIONS\\1.jpeg"));
        logoImage.setFitHeight(40);
        logoImage.setPreserveRatio(true);
        logoBox.getChildren().add(logoImage);

        header.getChildren().add(logoBox);

        HBox titleBox = new HBox();
        titleBox.setAlignment(Pos.CENTER);

        Text title = new Text("Amrita FoundIt");
        title.setFont(Font.font("Arial", 24));
        title.setFill(Color.WHITE);
        titleBox.getChildren().add(title);

        header.getChildren().add(titleBox);
        HBox.setHgrow(titleBox, Priority.ALWAYS);

        HBox searchBox = new HBox(10);
        searchBox.setAlignment(Pos.CENTER_RIGHT);

        Button contributionsButton = createHeaderButton("Contributions");
        searchBox.getChildren().add(contributionsButton);

        Button contactButton = createHeaderButton("Contact");
        searchBox.getChildren().add(contactButton);

        header.getChildren().add(searchBox);

        return header;
    }

    public static Button createHeaderButton(String buttonText) {
        Button button = new Button(buttonText);
        button.setStyle("-fx-background-color: #8b0000; -fx-text-fill: white;");
        button.setOnAction(event -> {
            if (buttonText.equals("Contributions")) {
                Contributions contributions = new Contributions();
                Stage stage = new Stage();
                contributions.start(stage);
            } else if (buttonText.equals("Contact")) {
                Contact contact = new Contact();
                Stage stage = new Stage();
                contact.start(stage);
            } else {
                System.out.println("Performing action for button: " + buttonText);
            }
        });
        return button;
    }





    public static void main(String[] args) {
        launch(args);
    }
}
