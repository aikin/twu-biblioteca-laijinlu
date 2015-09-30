package com.twu.biblioteca.service;

import com.twu.biblioteca.domain.model.User;
import com.twu.biblioteca.helper.InteractionHelper;

public class LibraryService {

    private static final String WELCOME_MESSAGE = "\n----------------Welcome to Biblioteca!----------------\n";
    private static final String QUIT_MESSAGE = "\n----------------Thank you for use the Biblioteca!----------------\n";
    private static final String INVALID_MENU_OPTION = "Select a valid option!";
    private static final String RELAUNCH_MESSAGE = "\n----------------Press enter to relaunch!----------------";

    private final BookService bookService;
    private final MovieService movieService;
    private final InteractionHelper interactionHelper;
    private final User currentUser;


    public LibraryService(final BookService bookService, final MovieService movieService, final InteractionHelper interactionHelper, User currentUser) {
        this.bookService = bookService;
        this.movieService = movieService;
        this.interactionHelper = interactionHelper;
        this.currentUser = currentUser;
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
                showMovies();
                break;
            case 5:
                checkoutMovie();
                break;
            case 6:
                returnMovie();
                break;
            case 7:
                showCurrentUser();
                break;
            case 8:
                quit();
                return false;
            default:
                showValidPrompt();
                return false;
        }
        return true;
    }

    private void quit() {
        interactionHelper.promptMessage(QUIT_MESSAGE);
    }

    private void showBooks() {
        interactionHelper.showBooksCanCheckout(bookService.fetchBooksCanCheckout());
    }

    private void checkoutBook() {
        showBooks();
        String bookId = interactionHelper.readUserInputWithPrompt("\nplease input book id: ");
        String message = bookService.checkoutBook(bookId, currentUser.getLibraryNumber());
        interactionHelper.promptMessage(message);
    }

    private void returnBook() {
        String bookId = interactionHelper.readUserInputWithPrompt("\nplease input book id: ");
        String message = bookService.returnBook(bookId, currentUser.getLibraryNumber());
        interactionHelper.promptMessage(message);
    }

    private void showValidPrompt() {
        interactionHelper.promptMessage(INVALID_MENU_OPTION);
        launch();
    }

    private void showCurrentUser() {
        interactionHelper.promptMessage(currentUser.toString());
    }

    private void showMovies() {
        interactionHelper.showMoviesCanCheckout(movieService.fetchMoviesCanCheckout());
    }

    private void checkoutMovie() {
        showMovies();
        String movieId = interactionHelper.readUserInputWithPrompt("\nplease input movie id: ");
        String message = movieService.checkoutMovie(movieId, currentUser.getLibraryNumber());
        interactionHelper.promptMessage(message);
    }

    private void returnMovie() {
        String movieId = interactionHelper.readUserInputWithPrompt("\nplease input movie id: ");
        String message = movieService.returnMovie(movieId, currentUser.getLibraryNumber());
        interactionHelper.promptMessage(message);
    }
}
