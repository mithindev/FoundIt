package com.example.foundit;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Contact extends Application {

    public static void main(String[] args) {
        launch(args);
    }

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

        // Member 1
        VBox member1Box = createMemberProfile("MITHIN DEV", "Login&signup", "mithindev1@example.com", "+1 123-456-7890", "Lorem ipsum.", "https://www.linkedin.com/in/mithindev/", "https://twitter.com/MithinDev", "C:\\Users\\nmary\\OneDrive\\Desktop\\FoundIt\\ASSETS\\avatar\\1.jpg");
        root.add(member1Box, 0, 0);

        // Member 2
        VBox member2Box = createMemberProfile("VIKASH", "Finding Item", "vikash@example.com", "+1 987-654-3210", "Lorem ipsum", "https://linkedin.com/janesmith", "https://github.com/janesmith", "C:\\Users\\nmary\\OneDrive\\Desktop\\FoundIt\\ASSETS\\avatar\\2.jpg");
        root.add(member2Box, 1, 0);

        // Member 3
        VBox member3Box = createMemberProfile("SUCHARAN", "Reporting Item", "sucharan@example.com", "+1 555-123-4567", "Lorem ipsum ", "https://linkedin.com/markjohnson", "https://github.com/janesmith", "C:\\Users\\nmary\\OneDrive\\Desktop\\FoundIt\\ASSETS\\avatar\\3.jpg");
        root.add(member3Box, 0, 1);

        // Member 4
        VBox member4Box = createMemberProfile("CHARISH", "Landing Pages", "cherish@example.com", "+1 777-999-0000", "Lorem ipsum .", "https://linkedin.com/markjohnson", "https://github.com/janesmith", "C:\\Users\\nmary\\OneDrive\\Desktop\\FoundIt\\ASSETS\\avatar\\4.jpg");
        root.add(member4Box, 1, 1);

        primaryStage.setTitle("Contact");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createMemberProfile(String name, String role, String email, String phoneNumber, String bio, String linkedInUrl, String twitterUrl, String profilePicturePath) {
        VBox profileBox = new VBox(10);
        profileBox.setAlignment(Pos.CENTER);
        profileBox.setPadding(new Insets(10));

        ImageView profileImage = new ImageView(new Image(profilePicturePath));
        profileImage.setFitWidth(150);
        profileImage.setFitHeight(150);

        Text nameLabel = new Text(name);
        nameLabel.setFont(Font.font("Arial", 18));

        Text roleLabel = new Text(role);
        roleLabel.setFont(Font.font("Arial", 14));

        Text emailLabel = new Text(email);
        emailLabel.setFont(Font.font("Arial", 14));

        Text phoneLabel = new Text(phoneNumber);
        phoneLabel.setFont(Font.font("Arial", 14));

        Text bioLabel = new Text(bio);
        bioLabel.setFont(Font.font("Arial", 14));

        HBox socialMediaBox = new HBox(10);
        socialMediaBox.setAlignment(Pos.CENTER);

        if (!linkedInUrl.isEmpty()) {
            Hyperlink linkedInLink = new Hyperlink("LinkedIn");
            linkedInLink.setOnAction(e -> getHostServices().showDocument(linkedInUrl));
            socialMediaBox.getChildren().add(linkedInLink);
        }

        if (!twitterUrl.isEmpty()) {
            Hyperlink twitterLink = new Hyperlink("Twitter");
            twitterLink.setOnAction(e -> getHostServices().showDocument(twitterUrl));
            socialMediaBox.getChildren().add(twitterLink);
        }

        profileBox.getChildren().addAll(profileImage, nameLabel, roleLabel, emailLabel, phoneLabel, bioLabel, socialMediaBox);
        return profileBox;
    }
}
