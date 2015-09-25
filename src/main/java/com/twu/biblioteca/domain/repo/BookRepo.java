package com.twu.biblioteca.domain.repo;


import com.twu.biblioteca.domain.model.Book;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class BookRepo {

    private final Map<Integer, Book> listOfBooks = new HashMap<>();

    public BookRepo() {
        this.initBooks();
    }

    public Map<Integer, Book> getListOfBooks() {
        return listOfBooks;
    }


    private void initBooks() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            listOfBooks.put(1, new Book(1, "Refactoring", "Martin Fowler & Kent Beck", formatter.parse("1999-07-08")));
            listOfBooks.put(2, new Book(2, "Clean Code", "Robert C. Martin", formatter.parse("2008-08-11")));
            listOfBooks.put(3, new Book(3, "Test Driven Development: By Example", "Kent Beck", formatter.parse("2002-11-18")));
            listOfBooks.put(4, new Book(4, "The Art of Readable Code", "Dustin Boswell & Trevor Foucher", formatter.parse("2011-12-02")));
            listOfBooks.put(5, new Book(5, "Patterns of Enterprise Application Architecture", "Martin Fowler", formatter.parse("2002-11-15")));
            listOfBooks.put(6, new Book(6, "Extreme Programming Explained: Embrace Chang", "Kent Beck & Cynthia Andres ", formatter.parse("2011-09-01")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
