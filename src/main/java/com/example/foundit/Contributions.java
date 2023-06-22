package com.example.foundit;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Contributions extends Application {

    @Override
    public void start(Stage primaryStage) {
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(20);
        root.setVgap(20);
        root.setPadding(new Insets(20));
        Scene scene = new Scene(root, 800, 600);

        // Create and add flip cards to the grid pane
        createFlipCard(root, "Card 1", "C:\\Users\\nmary\\OneDrive\\Desktop\\UN ORGANISED\\ILLUSTRATIONS\\1.jpeg", 0, 0);
        createFlipCard(root, "Card 2", "C:\\Users\\nmary\\OneDrive\\Desktop\\UN ORGANISED\\ILLUSTRATIONS\\1.jpeg", 1, 0);
        createFlipCard(root, "Card 3", "C:\\Users\\nmary\\OneDrive\\Desktop\\UN ORGANISED\\ILLUSTRATIONS\\1.jpeg", 0, 1);
        createFlipCard(root, "Card 4", "C:\\Users\\nmary\\OneDrive\\Desktop\\UN ORGANISED\\ILLUSTRATIONS\\1.jpeg", 1, 1);

        primaryStage.setTitle("Contributions");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void createFlipCard(GridPane parent, String title, String imagePath, int colIndex, int rowIndex) {
        Image image = new Image(imagePath);
        StackPane flipCard = createFlipCard(title, image);
        parent.add(flipCard, colIndex, rowIndex);
    }

    private StackPane createFlipCard(String title, Image image) {
        StackPane flipCard = new StackPane();
        flipCard.setPrefSize(image.getWidth(), image.getHeight());

        // Creating the front side of the flip card
        StackPane front = createFlipCardSide(title, image, Color.CORAL, Color.WHITE);
        // Creating the back side of the flip card
        StackPane back = createFlipCardSide("BACK", image, Color.WHITE, Color.CORAL);

        // Initially showing the front side of the flip card
        flipCard.getChildren().add(front);

        // Flipping the card on hover
        flipCard.setOnMouseEntered(event -> {
            flipCard.getChildren().setAll(back);
        });

        // Flipping the card back on mouse exit
        flipCard.setOnMouseExited(event -> {
            flipCard.getChildren().setAll(front);
        });

        return flipCard;
    }

    private StackPane createFlipCardSide(String title, Image image, Color bgColor, Color textColor) {
        Rectangle cardBg = new Rectangle(image.getWidth(), image.getHeight(), bgColor);
        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Arial", 18));
        titleLabel.setTextFill(textColor);
        titleLabel.setTextAlignment(TextAlignment.CENTER);

        StackPane side = new StackPane();
        side.getChildren().addAll(cardBg, titleLabel, new ImageView(image));
        StackPane.setAlignment(titleLabel, Pos.TOP_CENTER);
        StackPane.setAlignment(new ImageView(image), Pos.CENTER);

        return side;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
