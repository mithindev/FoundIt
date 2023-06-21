package com.example.foundit;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderId;
    private LocalDate orderDate;
    private Customer customer;
    private List<Book> books;

    public Order(int orderId, LocalDate orderDate, Customer customer) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customer = customer;
        this.books = new ArrayList<>();
    }

    public int getOrderId() {
        return orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderDate=" + orderDate +
                ", customer=" + customer +
                ", books=" + books +
                '}';
    }
}
