package com.twu.biblioteca;

import com.twu.biblioteca.domain.repo.BookRepo;
import com.twu.biblioteca.service.BookService;
import com.twu.biblioteca.service.LibraryService;
import com.twu.biblioteca.helper.InteractionHelper;

public class BibliotecaApp {

    public static void main(String[] args) {
        InteractionHelper interactionHelper = new InteractionHelper();
        BookRepo bookRepo = new BookRepo();
        BookService bookService = new BookService(bookRepo);
        LibraryService libraryService = new LibraryService(bookService, bookRepo, interactionHelper);
        libraryService.launch();
    }
}
