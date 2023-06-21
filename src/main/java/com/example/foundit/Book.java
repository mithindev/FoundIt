package com.example.foundit;

import java.time.LocalDate;

public class Book extends Item {
    private String author;
    private String publisher;
    private int quantity;

    public Book(String name, String description, LocalDate date, String location, String contact,
                String author, String publisher, int quantity) {
        super(name, description, date, location, contact);
        this.author = author;
        this.publisher = publisher;
        this.quantity = quantity;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", date=" + getDate() +
                ", location='" + getLocation() + '\'' +
                ", contact='" + getContact() + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
