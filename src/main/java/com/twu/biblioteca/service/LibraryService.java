package com.twu.biblioteca.service;

import com.twu.biblioteca.domain.model.Book;
import com.twu.biblioteca.helper.InteractionHelper;

import java.util.List;

public class LibraryService {

    private static final String CUSTOMER_ID = "C-01";
    private static final String WELCOME_MESSAGE = "\n----------------Welcome to Biblioteca!----------------\n";
    private static final String QUIT_MESSAGE = "\n----------------Thank you for use the Biblioteca!----------------\n";
    private static final String INVALID_MENU_OPTION = "Select a valid option!";
    private static final String RELAUNCH_MESSAGE = "\n----------------Press enter to relaunch!----------------";

    private final BookService bookService;
    private final InteractionHelper interactionHelper;


    public LibraryService(final BookService bookService, final InteractionHelper interactionHelper) {
        this.bookService = bookService;
        this.interactionHelper = interactionHelper;
    }

    public void launch() {
        boolean isLaunching = true;
        while (isLaunching) {
            interactionHelper.promptMessage(WELCOME_MESSAGE);
            interactionHelper.showMenu();
            int option = interactionHelper.getChooseOption();
            isLaunching = handleChooseOption(option);
            if (isLaunching) {
                relaunch();
            }
        }
    }

    private void relaunch() {
        interactionHelper.readUserInputWithPrompt(RELAUNCH_MESSAGE);
    }


    private boolean handleChooseOption(int option) {
        switch (option) {
            case 1:
                interactionHelper.showBooksCanCheckout(fetchBooksCanCheckout());
                break;
            case 2:
                interactionHelper.showBooksCanCheckout(fetchBooksCanCheckout());
                String bookId = interactionHelper.readUserInputWithPrompt("\nplease input book id: ");
                checkoutBook(bookId);
                break;
            case 3:
                String returnBookId = interactionHelper.readUserInputWithPrompt("\nplease input book id: ");
                returnBook(returnBookId);
                break;
            case 4:
                quit();
                return false;
            default:
                interactionHelper.promptMessage(INVALID_MENU_OPTION);
                launch();
                return false;
        }
        return true;
    }

    public void quit() {
        interactionHelper.promptMessage(QUIT_MESSAGE);
    }

    public List<Book> fetchBooksCanCheckout() {
        return bookService.fetchBooksCanCheckout();
    }

    public void checkoutBook(String bookId) {
        String message = bookService.checkoutBook(bookId, CUSTOMER_ID);
        interactionHelper.promptMessage(message);
    }

    public void returnBook(String bookId) {
        String message = bookService.returnBook(bookId, CUSTOMER_ID);
        interactionHelper.promptMessage(message);
    }
}
