package com.twu.biblioteca.service;

import com.twu.biblioteca.helper.InteractionHelper;

public class LibraryService {

    private static final String USER_ID = "C00-0001";
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
                showBooks();
                break;
            case 2:
                checkoutBook();
                break;
            case 3:
                returnBook();
                break;
            case 4:
                quit();
                return false;
            default:
                showValidPrompt();
                return false;
        }
        return true;
    }

    private void showBooks() {
        interactionHelper.showBooksCanCheckout(bookService.fetchBooksCanCheckout());
    }

    private void checkoutBook() {
        showBooks();
        String bookId = interactionHelper.readUserInputWithPrompt("\nplease input book id: ");
        String message = bookService.checkoutBook(bookId, USER_ID);
        interactionHelper.promptMessage(message);
    }

    private void returnBook() {
        String bookId = interactionHelper.readUserInputWithPrompt("\nplease input book id: ");
        String message = bookService.returnBook(bookId, USER_ID);
        interactionHelper.promptMessage(message);
    }

    private void quit() {
        interactionHelper.promptMessage(QUIT_MESSAGE);
    }

    private void showValidPrompt() {
        interactionHelper.promptMessage(INVALID_MENU_OPTION);
        launch();
    }
}
