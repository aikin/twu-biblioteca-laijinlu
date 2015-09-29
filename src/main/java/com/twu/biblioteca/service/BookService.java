package com.twu.biblioteca.service;


import com.twu.biblioteca.domain.model.Book;
import com.twu.biblioteca.domain.repo.BookRepo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class BookService {

    private static final String SUCCESS_CHECKOUT_BOOK_MESSAGE = "Thank you! Enjoy the book.";
    private static final String FAILURE_CHECKOUT_BOOK_MESSAGE = "That book is not available.";
    private static final String SUCCESS_RETURN_BOOK_MESSAGE = "Thank you for returning the book.";
    private static final String FAILURE_RETURN_BOOK_MESSAGE = "That is not a valid book to return.";

    private final BookRepo bookRepo;

    public BookService(final BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<Book> fetchBooksCanCheckout() {
        Map<String, Book> originalBooks = bookRepo.getOriginalBooks();
        Map<String, String> checkedOutBooks = bookRepo.getCheckedOutBooks();
        List<Book> books = new ArrayList<>();

        for(Map.Entry<String, Book> entry : originalBooks.entrySet()) {
            if (!checkedOutBooks.containsKey(entry.getKey())) {
                books.add(entry.getValue());
            }
        }
        Collections.sort(books);
        return books;
    }
}
