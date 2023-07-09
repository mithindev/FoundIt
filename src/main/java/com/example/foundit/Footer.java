package com.example.foundit;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.net.MalformedURLException;

public class Footer extends Application {

    public HBox footer;

    @Override
    public void start(Stage primaryStage) throws MalformedURLException {
        footer = createFooter();

        BorderPane root = new BorderPane();
        root.setBottom(footer);

        Scene scene = new Scene(root, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static HBox createFooter() throws MalformedURLException {
        HBox footer = new HBox();
        footer.setAlignment(Pos.CENTER);
        footer.setSpacing(20);

        Text copyrightLabel = new Text("© 2023 FoundIt");
        Text versionLabel = new Text("Version 1.0");
        Text designedByLabel = new Text("Designed by @mithindev");

        Line separator = new Line();
        separator.setStyle("-fx-stroke: #ffffff; -fx-stroke-width: 1px");

        Text linkLabel = new Text("GROUP - 12");
        Hyperlink hyperlink = new Hyperlink();
        hyperlink.setGraphic(linkLabel);
        TextFlow linkFlow = new TextFlow(hyperlink);

        Image icon1 = new Image("C:\\Users\\nmary\\OneDrive\\Desktop\\FoundIt\\ASSETS\\avatar\\1.jpg");
        Image icon2 = new Image("C:\\Users\\nmary\\OneDrive\\Desktop\\FoundIt\\ASSETS\\avatar\\2.jpg");
        Image icon3 = new Image("C:\\Users\\nmary\\OneDrive\\Desktop\\FoundIt\\ASSETS\\avatar\\3.jpg");
        Image icon4 = new Image("C:\\Users\\nmary\\OneDrive\\Desktop\\FoundIt\\ASSETS\\avatar\\4.jpg");
        ImageView icon1View = new ImageView(icon1);
        icon1View.setFitWidth(20);
        icon1View.setFitHeight(20);
        ImageView icon2View = new ImageView(icon2);
        icon2View.setFitWidth(20);
        icon2View.setFitHeight(20);
        ImageView icon3View = new ImageView(icon3);
        icon3View.setFitWidth(20);
        icon3View.setFitHeight(20);
        ImageView icon4View = new ImageView(icon4);
        icon4View.setFitWidth(20);
        icon4View.setFitHeight(20);

        footer.getChildren().addAll(icon1View, icon2View, icon3View, icon4View, separator, linkFlow, versionLabel, designedByLabel, copyrightLabel);

        footer.setStyle("-fx-background-color: #333333;");
        footer.setPadding(new javafx.geometry.Insets(10, 20, 10, 20));
        separator.setStroke(Color.WHITE);
        linkLabel.setFont(Font.font("Arial", 10));
        hyperlink.setFont(Font.font("Arial", 10));
        hyperlink.setTextFill(Color.WHITE);
        versionLabel.setFont(Font.font("Arial", 10));
        designedByLabel.setFont(Font.font("Arial", 10));
        copyrightLabel.setFont(Font.font("Arial", 10));
        icon1View.setStyle("-fx-background-color: white");
        icon2View.setStyle("-fx-background-color: white");
        icon3View.setStyle("-fx-background-color: white");
        icon1View.setPreserveRatio(true);
        icon2View.setPreserveRatio(true);
        icon3View.setPreserveRatio(true);

        return footer;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
