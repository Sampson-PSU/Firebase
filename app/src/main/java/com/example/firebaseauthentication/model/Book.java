package com.example.firebaseauthentication.model;

// Import all necessary libraries.
import java.io.Serializable;

public class Book implements Serializable {

    // Declare private fields for book details.
    private String title;
    private String author;
    private String publisher;
    private String publication;
    private String isbn;

    // Default constructor
    public Book() {
        // Empty constructor required for Firebase Realtime Database serialization.
    }

    // Constructor with book details.
    public Book(String title, String author, String publisher, String publication, String isbn) {

        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publication = publication;
        this.isbn = isbn;
    }

    // Getters and Setters.
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
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

    public String getPublication() {
        return publication;
    }
    public void setPublication(String publication) {
        this.publication = publication;
    }

    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}