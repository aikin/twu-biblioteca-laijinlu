package com.twu.biblioteca.helper;

import com.twu.biblioteca.domain.model.Book;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InteractionHelperTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    private InteractionHelper interactionHelper;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        interactionHelper = new InteractionHelper();
    }

    @After
    public void tearDown() {
        System.setOut(null);
        System.setErr(null);

        interactionHelper = null;
    }

    @Test
    public void should_prompt_message() {
        String expectedOutContent = "test prompt message";
        interactionHelper.promptMessage(expectedOutContent);

        assertThat(outContent.toString(), is(expectedOutContent + "\n"));
    }

    @Test
    public void should_show_menu() {
        String expectedOutContent =
            "       1 - List books\n" +
                "       2 - Checkout a book\n" +
                "       3 - Return a book\n" +
                "       4 - Quit";
        interactionHelper.showMenu();

        assertThat(outContent.toString(), is(expectedOutContent + "\n"));
    }

    @Test
    public void should_show_books_can_checkout() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("B-01", "Refactoring", "Martin Fowler & Kent Beck", new Date()));
        books.add(new Book("B-02", "Clean Code", "Robert C. Martin", new Date()));
        String expectedOutContent = "ID         Title         Author     Published Year\n" +
                                    "B-01,   Refactoring,  Martin Fowler & Kent Beck,  2015-09-29    \n" +
                                    "B-02,   Clean Code,  Robert C. Martin,  2015-09-29    \n";
        interactionHelper.showBooksCanCheckout(books);

        assertThat(outContent.toString(), is(expectedOutContent));
    }

    @Test
    public void should_get_choose_option() {
        interactionHelper = mock(InteractionHelper.class);
        when(interactionHelper.getChooseOption()).thenReturn(10);
        int option = interactionHelper.getChooseOption();

        assertThat(option, is(10));
    }

    @Test
    public void should_read_user_input() {
        interactionHelper = mock(InteractionHelper.class);
        when(interactionHelper.readUserInputWithPrompt("\nPlease choose an option: ")).thenReturn("10");
        String input = interactionHelper.readUserInputWithPrompt("\nPlease choose an option: ");

        assertThat(input, is("10"));
    }
}
