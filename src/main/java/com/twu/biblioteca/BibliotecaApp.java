package com.twu.biblioteca;

import com.twu.biblioteca.domain.repo.BookRepo;
import com.twu.biblioteca.service.BookService;
import com.twu.biblioteca.service.LibraryService;
import com.twu.biblioteca.helper.InteractionHelper;

public class BibliotecaApp {

    public static void main(String[] args) {
        InteractionHelper interactionHelper = new InteractionHelper();
        BookService bookService = new BookService(new BookRepo());
        LibraryService libraryService = new LibraryService(bookService, interactionHelper);
        libraryService.launch();
    }
}
