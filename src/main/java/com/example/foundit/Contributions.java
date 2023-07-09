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
        // Icon
        Image icon = new Image("file:///C:/Users/nmary/OneDrive/Desktop/UN%20ORGANISED/ILLUSTRATIONS/logo.jpg");
        primaryStage.getIcons().add(icon);

        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(20);
        root.setVgap(20);
        root.setPadding(new Insets(20));
        Scene scene = new Scene(root, 800, 700);

        // Mithin
        String str1 = "CONTRIBUTIONS:\n Login.java\nContact.java\nContribution.java";

        // Vikash
        String str2 = "CONTRIBUTIONS:\n FoundLostItem.java\nNotification.java";

        // Sucharan
        String str3 = "CONTRIBUTIONS:\n ReportLostItem.java\nDashboard.java\nItem.java";

        // Cherish
        String str4 = "CONTRIBUTIONS:\n Home.java\nHeader.java\nHeaderLogin.java\nFooter.java";

        // Create and add flip cards to the grid pane
        createFlipCard(root, str1, "C:\\Users\\nmary\\OneDrive\\Desktop\\FoundIt\\ASSETS\\avatar\\1.jpg", 0, 0);
        createFlipCard(root, str2, "C:\\Users\\nmary\\OneDrive\\Desktop\\FoundIt\\ASSETS\\avatar\\2.jpg", 1, 0);
        createFlipCard(root, str3, "C:\\Users\\nmary\\OneDrive\\Desktop\\FoundIt\\ASSETS\\avatar\\3.jpg", 0, 1);
        createFlipCard(root, str4, "C:\\Users\\nmary\\OneDrive\\Desktop\\FoundIt\\ASSETS\\avatar\\4.jpg", 1, 1);

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

        // Set the maximum width and height for the image to fit within the flip card
        double maxWidth = 400;
        double maxHeight = 300;

        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(maxWidth);
        imageView.setFitHeight(maxHeight);

        flipCard.setPrefSize(maxWidth, maxHeight);

        // Creating the front side of the flip card
        StackPane front = createFlipCardSide(title, imageView, Color.BLACK, Color.WHITE);
        // Creating the back side of the flip card
        StackPane back = createFlipCardSide("BACK", imageView, Color.BLACK, Color.WHITE);

        // Initially showing the back side of the flip card
        flipCard.getChildren().add(back);

        // Flipping the card on hover
        flipCard.setOnMouseEntered(event -> {
            flipCard.getChildren().setAll(front);
        });

        // Flipping the card back on mouse exit
        flipCard.setOnMouseExited(event -> {
            flipCard.getChildren().setAll(back);
        });

        return flipCard;
    }

    private StackPane createFlipCardSide(String title, ImageView imageView, Color bgColor, Color textColor) {
        Rectangle cardBg = new Rectangle(imageView.getFitWidth(), imageView.getFitHeight(), bgColor);
        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Arial", 18));
        titleLabel.setTextFill(textColor);
        titleLabel.setTextAlignment(TextAlignment.CENTER);

        StackPane side = new StackPane();
        side.getChildren().addAll(cardBg, titleLabel, imageView);
        StackPane.setAlignment(titleLabel, Pos.TOP_CENTER);
        StackPane.setAlignment(imageView, Pos.CENTER);

        return side;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
