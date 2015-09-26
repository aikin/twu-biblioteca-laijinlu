package com.twu.biblioteca.service;

import com.twu.biblioteca.domain.model.Book;
import com.twu.biblioteca.domain.repo.BookRepo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class LibraryService {

    private BookRepo bookRepo;
    private static final String CUSTOMER_ID = "C-01";
    private static final String SUCCESS_CHECKOUT_BOOK_MESSAGE = "Thank you! Enjoy the book.";

    public LibraryService(BookRepo bookRepo) {
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

    public void checkoutBook(String bookId) {
        if (bookRepo.isBookExist(bookId) && !bookRepo.isBookCheckedOut(bookId)) {
            bookRepo.addCheckedOutBook(bookId, CUSTOMER_ID);
            System.out.printf(SUCCESS_CHECKOUT_BOOK_MESSAGE);
        }
    }
}
