package com.example.firebaseauthentication.model;

import java.io.Serializable;

public class Book implements Serializable {

    private String title;
    private String author;
    private String publisher;
    private String publication;
    private String isbn;
    private String image;

    public Book() {
    }

    public Book(String title, String author, String publisher, String publication, String isbn) {

        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publication = publication;
        this.isbn = isbn;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}