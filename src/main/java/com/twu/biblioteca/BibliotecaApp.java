package com.twu.biblioteca;

import com.twu.biblioteca.domain.model.User;
import com.twu.biblioteca.domain.repo.BookRepo;
import com.twu.biblioteca.domain.repo.MovieRepo;
import com.twu.biblioteca.domain.repo.UserRepo;
import com.twu.biblioteca.helper.InteractionHelper;
import com.twu.biblioteca.helper.SessionHelper;
import com.twu.biblioteca.service.BookService;
import com.twu.biblioteca.service.LibraryService;
import com.twu.biblioteca.service.MovieService;

public class BibliotecaApp {

    public static void main(String[] args) {
        InteractionHelper interactionHelper = new InteractionHelper();
        User currentUser = SessionHelper.login(interactionHelper, new UserRepo());

        BookService bookService = new BookService(new BookRepo());
        MovieService movieService = new MovieService(new MovieRepo());
        LibraryService libraryService = new LibraryService(bookService, movieService, interactionHelper, currentUser);
        libraryService.launch();
    }
}
