package com.twu.biblioteca.domain.model;


import java.util.Date;

public class Book {

    private int id;
    private String title;
    private String author;
    private Date publishedYear;

    public Book(int id, String title, String author, Date publishedYear) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publishedYear = publishedYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Date getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(Date publishedYear) {
        this.publishedYear = publishedYear;
    }
}
