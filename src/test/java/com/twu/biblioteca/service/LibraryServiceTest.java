package com.twu.biblioteca.service;

import com.twu.biblioteca.helper.InteractionHelper;
import com.twu.biblioteca.util.TestFixtures;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class LibraryServiceTest extends TestFixtures {

    private InteractionHelper interactionHelper;
    private BookService bookService;
    private LibraryService libraryService;


    @Before
    public void setUp() {

        interactionHelper = mock(InteractionHelper.class);
        bookService = mock(BookService.class);

        libraryService = new LibraryService(bookService, interactionHelper);
    }

    @After
    public void tearDown() {
        interactionHelper = null;
        bookService = null;
        libraryService = null;
    }

    @Test
    public void should_test_launch() {
        libraryService.launch();

        
    }
}
