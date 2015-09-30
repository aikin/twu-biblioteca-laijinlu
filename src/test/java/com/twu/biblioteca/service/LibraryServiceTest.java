package com.twu.biblioteca.service;

import com.twu.biblioteca.domain.model.User;
import com.twu.biblioteca.helper.InteractionHelper;
import com.twu.biblioteca.util.TestFixtures;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class LibraryServiceTest extends TestFixtures {

    private InteractionHelper interactionHelper;
    private BookService bookService;
    private MovieService movieService;
    private LibraryService libraryService;


    @Before
    public void setUp() {

        interactionHelper = mock(InteractionHelper.class);
        bookService = mock(BookService.class);
        movieService = mock(MovieService.class);
        User user = new User("C00-0001", "twu46", "aikin", "aikin@biblioteca.com", "18282828562", "CUSTOMER");
        libraryService = new LibraryService(bookService, movieService, interactionHelper, user);
    }

    @After
    public void tearDown() {
        interactionHelper = null;
        bookService = null;
        libraryService = null;
        movieService = null;
    }

    @Test
    public void should_test_choose_quit_option() {
        when(interactionHelper.getChooseOption()).thenReturn(8);
        libraryService.launch();

        verify(interactionHelper, times(1)).promptMessage(WELCOME_MESSAGE);
        verify(interactionHelper, times(1)).showMenu();
        verify(interactionHelper, times(1)).getChooseOption();
        verify(interactionHelper, times(1)).promptMessage(QUIT_MESSAGE);
    }
}
