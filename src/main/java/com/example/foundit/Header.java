package com.example.foundit;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Header extends Application {

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 800, 600);

        // Create the menu bar
        MenuBar menuBar = new MenuBar();

        // Create the sections in the menu
        Menu aboutMenu = new Menu("About");
        Menu contactMenu = new Menu("Contact");
        Menu teamMenu = new Menu("Team Members");
        Menu contributionsMenu = new Menu("Contributions");

        // Create menu items for each section
        MenuItem aboutItem = new MenuItem("About");
        MenuItem contactItem = new MenuItem("Contact");
        MenuItem teamItem = new MenuItem("Team Members");
        MenuItem contributionsItem = new MenuItem("Contributions");

        // Event handlers for menu item clicks
        aboutItem.setOnAction(event -> redirectToAbout());
        contactItem.setOnAction(event -> redirectToContact());
        teamItem.setOnAction(event -> redirectToTeamMembers());
        contributionsItem.setOnAction(event -> redirectToContributions());

        // Add menu items to the respective menus
        aboutMenu.getItems().add(aboutItem);
        contactMenu.getItems().add(contactItem);
        teamMenu.getItems().add(teamItem);
        contributionsMenu.getItems().add(contributionsItem);

        // Add menus to the menu bar
        menuBar.getMenus().addAll(aboutMenu, contactMenu, teamMenu, contributionsMenu);

        // Set the menu bar as the top section of the layout
        root.setTop(menuBar);

        // Placeholder content
        VBox content = new VBox();
        content.setPadding(new Insets(10));
        content.getChildren().add(new Label("Content goes here"));
        root.setCenter(content);

        stage.setTitle("Header Example");
        stage.setScene(scene);
        stage.show();
    }

    // Method to handle redirect to About class
    private void redirectToAbout() {
        System.out.println("Redirecting to About");
        // Add your logic to open the About class or perform the desired action
    }

    // Method to handle redirect to Contact class
    private void redirectToContact() {
        System.out.println("Redirecting to Contact");
        // Add your logic to open the Contact class or perform the desired action
    }

    // Method to handle redirect to Team Members class
    private void redirectToTeamMembers() {
        System.out.println("Redirecting to Team Members");
        // Add your logic to open the Team Members class or perform the desired action
    }

    // Method to handle redirect to Contributions class
    private void redirectToContributions() {
        System.out.println("Redirecting to Contributions");
        // Add your logic to open the Contributions class or perform the desired action
    }

    public static void main(String[] args) {
        launch(args);
    }
}
