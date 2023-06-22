package com.example.foundit;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class About extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Creating a ScrollPane as the root layout
        ScrollPane root = new ScrollPane();
        root.setFitToWidth(true);

        // Creating a VBox to hold the content
        VBox content = new VBox();
        content.setSpacing(20);
        content.setPadding(new Insets(20));

        // Create scene
        Scene scene = new Scene(root, 800, 600);

        // Add project logo
        ImageView logoImage = new ImageView(new Image("C:\\Users\\nmary\\OneDrive\\Desktop\\UN ORGANISED\\ILLUSTRATIONS\\1.jpeg"));
        logoImage.setFitWidth(100);
        logoImage.setPreserveRatio(true);

        // Add project name
        Text projectName = new Text("Lost & Found App");
        projectName.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        // Add project description
        Text projectDescription = new Text("This is a powerful application that helps you find lost items quickly and efficiently.");
        projectDescription.setFont(Font.font("Arial", 16));

        // Add project screenshots
        ImageView screenshot1 = new ImageView(new Image("C:\\Users\\nmary\\OneDrive\\Desktop\\UN ORGANISED\\ILLUSTRATIONS\\1.jpeg"));
        screenshot1.setFitWidth(400);
        screenshot1.setPreserveRatio(true);

        ImageView screenshot2 = new ImageView(new Image("C:\\Users\\nmary\\OneDrive\\Desktop\\UN ORGANISED\\ILLUSTRATIONS\\1.jpeg"));
        screenshot2.setFitWidth(400);
        screenshot2.setPreserveRatio(true);

        HBox screenshots = new HBox(20, screenshot1, screenshot2);
        screenshots.setAlignment(Pos.CENTER);

        // Add team member information
        Text teamHeading = new Text("Meet the Team");
        teamHeading.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        VBox teamMembers = new VBox(10);
        teamMembers.setAlignment(Pos.CENTER);

        // Team member 1
        ImageView member1Photo = new ImageView(new Image("C:\\Users\\nmary\\OneDrive\\Desktop\\UN ORGANISED\\ILLUSTRATIONS\\1.jpeg"));
        member1Photo.setFitWidth(80);
        member1Photo.setPreserveRatio(true);

        Text member1Name = new Text("John Doe");
        member1Name.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        Text member1Role = new Text("UI/UX Designer");
        member1Role.setFont(Font.font("Arial", 14));

        VBox member1 = new VBox(member1Photo, member1Name, member1Role);
        member1.setAlignment(Pos.CENTER);
        member1.setSpacing(5);

        // Team member 2
        ImageView member2Photo = new ImageView(new Image("C:\\Users\\nmary\\OneDrive\\Desktop\\UN ORGANISED\\ILLUSTRATIONS\\1.jpeg"));
        member2Photo.setFitWidth(80);
        member2Photo.setPreserveRatio(true);

        Text member2Name = new Text("Jane Smith");
        member2Name.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        Text member2Role = new Text("Software Developer");
        member2Role.setFont(Font.font("Arial", 14));

        VBox member2 = new VBox(member2Photo, member2Name, member2Role);
        member2.setAlignment(Pos.CENTER);
        member2.setSpacing(5);

        teamMembers.getChildren().addAll(member1, member2);

        // Add showcase project features
        Text featuresHeading = new Text("Key Features");
        featuresHeading.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        VBox featuresList = new VBox(10);
        featuresList.setAlignment(Pos.CENTER_LEFT);

        // Feature 1
        ImageView feature1Icon = new ImageView(new Image("C:\\Users\\nmary\\OneDrive\\Desktop\\UN ORGANISED\\ILLUSTRATIONS\\1.jpeg"));
        feature1Icon.setFitWidth(20);
        feature1Icon.setPreserveRatio(true);

        Text feature1Title = new Text("Item Search");
        feature1Title.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        Text feature1Description = new Text("Effortlessly search for lost items using various filters and search criteria.");
        feature1Description.setFont(Font.font("Arial", 14));

        VBox feature1 = new VBox(feature1Icon, feature1Title, feature1Description);
        feature1.setAlignment(Pos.CENTER_LEFT);
        feature1.setSpacing(5);

        // Feature 2
        ImageView feature2Icon = new ImageView(new Image("C:\\Users\\nmary\\OneDrive\\Desktop\\UN ORGANISED\\ILLUSTRATIONS\\1.jpeg"));
        feature2Icon.setFitWidth(20);
        feature2Icon.setPreserveRatio(true);

        Text feature2Title = new Text("Item Submission");
        feature2Title.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        Text feature2Description = new Text("Easily submit details and images of found items to help others find their lost belongings.");
        feature2Description.setFont(Font.font("Arial", 14));

        VBox feature2 = new VBox(feature2Icon, feature2Title, feature2Description);
        feature2.setAlignment(Pos.CENTER_LEFT);
        feature2.setSpacing(5);

        featuresList.getChildren().addAll(feature1, feature2);

        // Add testimonials or user reviews
        Text testimonialsHeading = new Text("Testimonials");
        testimonialsHeading.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        VBox testimonialsList = new VBox(10);
        testimonialsList.setAlignment(Pos.CENTER);

        // Testimonial 1
        Text testimonial1 = new Text("The Lost & Found App helped me find my lost wallet within minutes! Highly recommended!");
        testimonial1.setFont(Font.font("Arial", 16));

        Text author1Name = new Text("John Smith");
        author1Name.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        VBox testimonial1Box = new VBox(testimonial1, author1Name);
        testimonial1Box.setAlignment(Pos.CENTER);
        testimonial1Box.setSpacing(5);

        // Testimonial 2
        Text testimonial2 = new Text("I love how intuitive and user-friendly this app is. It made finding my lost keys a breeze!");
        testimonial2.setFont(Font.font("Arial", 16));

        Text author2Name = new Text("Emily Johnson");
        author2Name.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        VBox testimonial2Box = new VBox(testimonial2, author2Name);
        testimonial2Box.setAlignment(Pos.CENTER);
        testimonial2Box.setSpacing(5);

        testimonialsList.getChildren().addAll(testimonial1Box, testimonial2Box);

        // Add social media links
        HBox socialMediaLinks = new HBox(10);

        Hyperlink facebookLink = new Hyperlink("Facebook");
        Hyperlink twitterLink = new Hyperlink("Twitter");
        Hyperlink linkedinLink = new Hyperlink("LinkedIn");

        socialMediaLinks.getChildren().addAll(facebookLink, twitterLink, linkedinLink);

        // Add contact information
        HBox contactInfo = new HBox(10);

        Text emailLabel = new Text("Email:");
        emailLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        Text email = new Text("contact@example.com");
        email.setFont(Font.font("Arial", 14));

        Text phoneLabel = new Text("Phone:");
        phoneLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        Text phone = new Text("123-456-7890");
        phone.setFont(Font.font("Arial", 14));

        contactInfo.getChildren().addAll(emailLabel, email, phoneLabel, phone);

        // Add animations or interactive elements
        // TODO: Add animations or interactive elements

        // Customize colors and fonts
        root.setStyle("-fx-background-color: #F5F5F5;");
        projectName.setFill(Color.DARKBLUE);
        projectDescription.setFill(Color.DARKGRAY);
        teamHeading.setFill(Color.DARKBLUE);
        featuresHeading.setFill(Color.DARKBLUE);
        testimonialsHeading.setFill(Color.DARKBLUE);

        // Add all components to the root layout
        root.getChildren().addAll(logoImage, projectName, projectDescription, screenshots, teamHeading,
                teamMembers, featuresHeading, featuresList, testimonialsHeading, testimonialsList,
                socialMediaLinks, contactInfo);

        // Set the scene and show the stage
        primaryStage.setTitle("About Lost & Found App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
