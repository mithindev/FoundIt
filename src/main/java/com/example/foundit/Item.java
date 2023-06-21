package com.example.foundit;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Item {
    private String name;
    private String description;
    private LocalDate date;
    private String location;
    private String contact;

    public Item(String name, String description, LocalDate date, String location, String contact) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.location = location;
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public String getContact() {
        return contact;
    }

    public static ObservableList<Item> loadItemsFromFile(String filename) {
        List<Item> items = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 5) {
                    String name = parts[0].trim();
                    String description = parts[1].trim();
                    LocalDate date = LocalDate.parse(parts[2].trim());
                    String location = parts[3].trim();
                    String contact = parts[4].trim();
                    Item item = new Item(name, description, date, location, contact);
                    items.add(item);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return FXCollections.observableArrayList(items);
    }

    public static void saveItemsToFile(ObservableList<Item> items, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Item item : items) {
                writer.write(item.getName() + ";");
                writer.write(item.getDescription() + ";");
                writer.write(item.getDate().toString() + ";");
                writer.write(item.getLocation() + ";");
                writer.write(item.getContact());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
