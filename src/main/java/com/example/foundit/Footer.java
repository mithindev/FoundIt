package com.example.foundit;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.net.MalformedURLException;
import java.net.URL;

public class Footer extends Application {

    @Override
    public void start(Stage primaryStage) throws MalformedURLException {
        // Create a footer HBox
        HBox footer = new HBox();
        footer.setAlignment(javafx.geometry.Pos.CENTER);
        footer.setSpacing(10);

        // Add some labels to the footer
        Text copyrightLabel = new Text("Copyright © 2023 Company Name");
        Text versionLabel = new Text("Version 1.0");
        Text designedByLabel = new Text("Designed by Bard");
        Text poweredByLabel = new Text("Powered by Python");
        footer.getChildren().addAll(copyrightLabel, versionLabel, designedByLabel, poweredByLabel);

        // Add some padding to the footer
        footer.setPadding(new javafx.geometry.Insets(10));

        // Add a separator line to the footer
        Line separator = new Line();
        separator.setStyle("-fx-stroke: black; -fx-stroke-width: 1px");
        footer.getChildren().add(separator);

        // Add a link to the footer
        Text linkLabel = new Text("Visit our website");
        URL link = new URL("https://www.companyname.com");
        TextFlow linkFlow = new TextFlow(linkLabel, new Hyperlink(link.toString()));
        footer.getChildren().add(linkFlow);

        // Add some CSS styles to the footer
        footer.setStyle("-fx-background-color: #333333; -fx-font-family: sans-serif");
        copyrightLabel.setStyle("-fx-font-size: 12px; -fx-font-weight: bold");
        versionLabel.setStyle("-fx-font-size: 10px");
        designedByLabel.setStyle("-fx-font-size: 10px");
        poweredByLabel.setStyle("-fx-font-size: 10px");
        linkFlow.setStyle("-fx-font-size: 10px; -fx-color: #ffffff");

        // Add some icons to the footer
        Image icon1 = new Image("C:\\Users\\nmary\\OneDrive\\Desktop\\UN ORGANISED\\ILLUSTRATIONS\\1.jpeg");
        Image icon2 = new Image("C:\\Users\\nmary\\OneDrive\\Desktop\\UN ORGANISED\\ILLUSTRATIONS\\1.jpeg");
        Image icon3 = new Image("C:\\Users\\nmary\\OneDrive\\Desktop\\UN ORGANISED\\ILLUSTRATIONS\\1.jpeg");
        ImageView icon1View = new ImageView(icon1);
        icon1View.setFitWidth(20);
        icon1View.setFitHeight(20);
        ImageView icon2View = new ImageView(icon2);
        icon2View.setFitWidth(20);
        icon2View.setFitHeight(20);
        ImageView icon3View = new ImageView(icon3);
        icon3View.setFitWidth(20);
        icon3View.setFitHeight(20);
        footer.getChildren().addAll(icon1View, icon2View, icon3View);

        // Create a BorderPane and add the footer to it
        BorderPane root = new BorderPane();
        root.setBottom(footer);

        // Create a scene and show it
        Scene scene = new Scene(root, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}