package com.twu.biblioteca;

import com.twu.biblioteca.domain.repo.BookRepo;
import com.twu.biblioteca.service.LibraryService;
import com.twu.biblioteca.helper.InteractionHelper;

public class BibliotecaApp {

    public static void main(String[] args) {
        InteractionHelper interactionHelper = new InteractionHelper();
        BookRepo bookRepo = new BookRepo();
        LibraryService libraryService = new LibraryService(bookRepo, interactionHelper);
        libraryService.launch();
    }
}
