package com.twu.biblioteca.domain.repo;


import com.twu.biblioteca.domain.model.Book;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class BookRepo {

    private final Map<String, Book> originalBooks = new HashMap<>();
    private final Map<String, String> checkedOutBooks = new HashMap<>();

    public BookRepo() {
        this.initBooks();
    }

    public Map<String, Book> getOriginalBooks() {
        return this.originalBooks;
    }

    public Book getBookById(String bookId) {
        return this.originalBooks.get(bookId);
    }

    public Map<String, String> getCheckedOutBooks() {
        return this.checkedOutBooks;
    }

    public void addCheckedOutBook(String bookId, String customerId) {
        this.checkedOutBooks.put(bookId, customerId);
    }

    public boolean isBookExist(String bookId) {
        return this.originalBooks.containsKey(bookId);
    }

    public boolean isBookCheckedOut(String bookId) {
        return this.checkedOutBooks.containsKey(bookId);
    }

    public void removeCheckedOutBook(String bookId) {
        this.checkedOutBooks.remove(bookId);
    }

    public boolean isBookCheckedOutForCurrentCustomer(String bookId, String customerId) {
        return this.checkedOutBooks.get(bookId) != null && this.checkedOutBooks.get(bookId).equals(customerId);
    }

    private void initBooks() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // TODO: must init books by parse file
            this.originalBooks.put("B-01", new Book("B-01", "Refactoring", "Martin Fowler & Kent Beck", formatter.parse("1999-07-08")));
            this.originalBooks.put("B-02", new Book("B-02", "Clean Code", "Robert C. Martin", formatter.parse("2008-08-11")));
            this.originalBooks.put("B-03", new Book("B-03", "Test Driven Development: By Example", "Kent Beck", formatter.parse("2002-11-18")));
            this.originalBooks.put("B-04", new Book("B-04", "The Art of Readable Code", "Dustin Boswell & Trevor Foucher", formatter.parse("2011-12-02")));
            this.originalBooks.put("B-05", new Book("B-05", "Patterns of Enterprise Application Architecture", "Martin Fowler", formatter.parse("2002-11-15")));
            this.originalBooks.put("B-06", new Book("B-06", "Extreme Programming Explained: Embrace Chang", "Kent Beck & Cynthia Andres", formatter.parse("2011-09-01")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
