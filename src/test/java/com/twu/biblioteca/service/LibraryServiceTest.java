package com.twu.biblioteca.service;

import com.twu.biblioteca.domain.model.Book;
import com.twu.biblioteca.domain.repo.BookRepo;
import com.twu.biblioteca.helper.InteractionHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


public class LibraryServiceTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    private static final String CUSTOMER_ID = "C-01";
    private static final String SUCCESS_CHECKOUT_BOOK_MESSAGE = "Thank you! Enjoy the book.";
    private static final String FAILURE_CHECKOUT_BOOK_MESSAGE = "That book is not available.";
    private static final String SUCCESS_RETURN_BOOK_MESSAGE = "Thank you for returning the book.";
    private static final String FAILURE_RETURN_BOOK_MESSAGE = "That is not a valid book to return.";

    private BookRepo bookRepo;
    private InteractionHelper interactionHelper;
    private LibraryService libraryService;
    private SimpleDateFormat formatter;


    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        bookRepo = new BookRepo();
        interactionHelper = new InteractionHelper();
        BookService bookService = new BookService(bookRepo);
        libraryService = new LibraryService(bookService, bookRepo, interactionHelper);
        formatter = new SimpleDateFormat("yyyy-MM-dd");
    }

    @After
    public void tearDown() {
        System.setOut(null);
        System.setErr(null);

        bookRepo = null;
        libraryService = null;
        formatter = null;
    }

    @Test
    public void should_fetch_list_of_books_when_checked_out_books_is_empty() throws ParseException {
        List<Book> booksCanCheckout = libraryService.fetchBooksCanCheckout();

        assertThat(booksCanCheckout.size(), is(6));
        assertThat(booksCanCheckout.get(0).getId(), is("B-01"));
        assertThat(booksCanCheckout.get(0).getTitle(), is("Refactoring"));
        assertThat(booksCanCheckout.get(0).getAuthor(), is("Martin Fowler & Kent Beck"));
        assertThat(booksCanCheckout.get(0).getPublishedYear(), is(formatter.parse("1999-07-08")));
    }

    @Test
    public void should_checkout_book_success_when_book_is_can_be_checked_out() {
        libraryService.checkoutBook("B-03");
        List<Book> booksCanCheckout = libraryService.fetchBooksCanCheckout();

        assertThat(booksCanCheckout.size(), is(5));
        assertThat(booksCanCheckout.get(2).getId(), is("B-04"));
        assertThat(outContent.toString(), is(SUCCESS_CHECKOUT_BOOK_MESSAGE + "\n"));
        assertTrue(bookRepo.getCheckedOutBooks().containsKey("B-03"));
    }

    @Test
    public void should_checkout_book_failure_when_book_is_not_exist() {
        libraryService.checkoutBook("nonexistence");
        List<Book> booksCanCheckout = libraryService.fetchBooksCanCheckout();

        assertThat(booksCanCheckout.size(), is(6));
        assertThat(booksCanCheckout.get(2).getId(), is("B-03"));
        assertThat(outContent.toString(), is(FAILURE_CHECKOUT_BOOK_MESSAGE + "\n"));
        assertThat(bookRepo.getCheckedOutBooks().size(), is(0));
    }

    @Test
    public void should_checkout_book_failure_when_book_is_exist_in_checked_out_books() {
        bookRepo.addCheckedOutBook("B-03", CUSTOMER_ID);
        List<Book> booksCanCheckout = libraryService.fetchBooksCanCheckout();
        libraryService.checkoutBook("B-03");

        assertThat(booksCanCheckout.size(), is(5));
        assertThat(booksCanCheckout.get(2).getId(), is("B-04"));
        assertThat(outContent.toString(), is(FAILURE_CHECKOUT_BOOK_MESSAGE + "\n"));
        assertThat(bookRepo.getCheckedOutBooks().size(), is(1));
        assertTrue(bookRepo.getCheckedOutBooks().containsKey("B-03"));
    }

    @Test
    public void should_return_book_success_when_book_is_can_be_return() {
        bookRepo.addCheckedOutBook("B-03", CUSTOMER_ID);
        bookRepo.addCheckedOutBook("B-04", CUSTOMER_ID);
        libraryService.returnBook("B-03");
        List<Book> booksCanCheckout = libraryService.fetchBooksCanCheckout();

        assertThat(booksCanCheckout.size(), is(5));
        assertThat(outContent.toString(), is(SUCCESS_RETURN_BOOK_MESSAGE + "\n"));
        assertThat(bookRepo.getCheckedOutBooks().size(), is(1));
        assertTrue(bookRepo.getCheckedOutBooks().containsKey("B-04"));
    }

    @Test
    public void should_return_book_success_when_book_is_checked_out_by_current_customer() {
        bookRepo.addCheckedOutBook("B-03", "C-02");
        bookRepo.addCheckedOutBook("B-04", CUSTOMER_ID);
        libraryService.returnBook("B-04");
        List<Book> booksCanCheckout = libraryService.fetchBooksCanCheckout();

        assertThat(booksCanCheckout.size(), is(5));
        assertThat(outContent.toString(), is(SUCCESS_RETURN_BOOK_MESSAGE + "\n"));
        assertThat(bookRepo.getCheckedOutBooks().size(), is(1));
        assertTrue(bookRepo.getCheckedOutBooks().containsKey("B-03"));
    }

    @Test
    public void should_return_book_failure_when_book_is_not_checked_out_by_current_customer() {
        bookRepo.addCheckedOutBook("B-03", "C-02");
        bookRepo.addCheckedOutBook("B-04", CUSTOMER_ID);
        libraryService.returnBook("B-03");
        List<Book> booksCanCheckout = libraryService.fetchBooksCanCheckout();

        assertThat(booksCanCheckout.size(), is(4));
        assertThat(outContent.toString(), is(FAILURE_RETURN_BOOK_MESSAGE + "\n"));
        assertThat(bookRepo.getCheckedOutBooks().size(), is(2));
        assertTrue(bookRepo.getCheckedOutBooks().containsKey("B-03"));
        assertTrue(bookRepo.getCheckedOutBooks().containsKey("B-04"));
    }


    @Test
    public void should_return_book_failure_when_book_is_not_exist() {
        bookRepo.addCheckedOutBook("B-03", CUSTOMER_ID);
        libraryService.returnBook("B-08");
        List<Book> booksCanCheckout = libraryService.fetchBooksCanCheckout();

        assertThat(booksCanCheckout.size(), is(5));
        assertThat(outContent.toString(), is(FAILURE_RETURN_BOOK_MESSAGE + "\n"));
        assertThat(bookRepo.getCheckedOutBooks().size(), is(1));
        assertTrue(bookRepo.getCheckedOutBooks().containsKey("B-03"));
    }

    @Test
    public void should_return_book_failure_when_book_is_not_checked_out() {
        bookRepo.addCheckedOutBook("B-03", CUSTOMER_ID);
        libraryService.returnBook("B-04");
        List<Book> booksCanCheckout = libraryService.fetchBooksCanCheckout();

        assertThat(booksCanCheckout.size(), is(5));
        assertThat(outContent.toString(), is(FAILURE_RETURN_BOOK_MESSAGE + "\n"));
        assertThat(bookRepo.getCheckedOutBooks().size(), is(1));
        assertTrue(bookRepo.getCheckedOutBooks().containsKey("B-03"));
    }
}
