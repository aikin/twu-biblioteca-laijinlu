package com.twu.biblioteca.domain.model;


import java.text.SimpleDateFormat;
import java.util.Date;

public class Book implements Comparable<Book> {

    private String id;
    private String title;
    private String author;
    private Date publishedYear;

    public Book(String id, String title, String author, Date publishedYear) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publishedYear = publishedYear;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    @Override
    public int compareTo(Book book) {
        return this.getId().compareTo(book.getId());
    }

    public static String generateColumnHeader() {
        return "ID         Title         Author     Published Year";
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return id + ",   " + title + ",  " + author + ",  " +  formatter.format(publishedYear) + "    ";
    }
}
