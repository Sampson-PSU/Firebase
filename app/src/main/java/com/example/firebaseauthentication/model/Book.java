package com.example.firebaseauthentication.model;

import java.io.Serializable;

public class Book implements Serializable {

    private String title;
    private String author;
    private String publisher;
    private String publication_date;
    private String isbn;

    public Book() {
    }

    public Book(String title, String author, String publisher, String publication_date, String isbn) {

        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publication_date = publication_date;
        this.isbn = isbn;
    }

    //Getters and Setters
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

    public String getPublication_date() {
        return publication_date;
    }

    public void setPublication_date(String publication_date) {
        this.publication_date = publication_date;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}