package com.twu.biblioteca.service;

import com.twu.biblioteca.domain.model.Book;
import com.twu.biblioteca.domain.repo.BookRepo;
import com.twu.biblioteca.helper.InteractionHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class LibraryService {

    private static final String CUSTOMER_ID = "C-01";
    private static final String WELCOME_MESSAGE = "\n----------------Welcome to Biblioteca!----------------\n";
    private static final String SUCCESS_CHECKOUT_BOOK_MESSAGE = "Thank you! Enjoy the book.";
    private static final String FAILURE_CHECKOUT_BOOK_MESSAGE = "That book is not available.";
    private static final String SUCCESS_RETURN_BOOK_MESSAGE = "Thank you for returning the book.";
    private static final String FAILURE_RETURN_BOOK_MESSAGE = "That is not a valid book to return.";

    private final BookRepo bookRepo;
    private final InteractionHelper interactionHelper;

    public LibraryService(final BookRepo bookRepo, final InteractionHelper interactionHelper) {
        this.bookRepo = bookRepo;
        this.interactionHelper = interactionHelper;
    }

    public void launch() {
        interactionHelper.promptMessage(WELCOME_MESSAGE);
        interactionHelper.showMenu();
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
        Boolean isCanCheckoutBook = bookRepo.isBookExist(bookId) && !bookRepo.isBookCheckedOut(bookId);
        if (isCanCheckoutBook) {
            bookRepo.addCheckedOutBook(bookId, CUSTOMER_ID);
            interactionHelper.promptMessage(SUCCESS_CHECKOUT_BOOK_MESSAGE);
            return;
        }
        interactionHelper.promptMessage(FAILURE_CHECKOUT_BOOK_MESSAGE);
    }

    public void returnBook(String bookId) {
        Boolean isCanReturnBook = bookRepo.isBookExist(bookId)
            && bookRepo.isBookCheckedOutForCurrentCustomer(bookId, CUSTOMER_ID);
        if (isCanReturnBook) {
            bookRepo.removeCheckedOutBook(bookId);
            interactionHelper.promptMessage(SUCCESS_RETURN_BOOK_MESSAGE);
            return;
        }
        interactionHelper.promptMessage(FAILURE_RETURN_BOOK_MESSAGE);
    }
}
