package com.twu.biblioteca.service;

import com.twu.biblioteca.domain.model.Book;
import com.twu.biblioteca.domain.repo.BookRepo;
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

    private BookRepo bookRepo;
    private LibraryService libraryService;
    private SimpleDateFormat formatter;


    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        bookRepo = new BookRepo();
        libraryService = new LibraryService(bookRepo);
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
        assertThat(outContent.toString(), is("Thank you! Enjoy the book."));
        assertTrue(bookRepo.getCheckedOutBooks().containsKey("B-03"));
    }

    @Test
    public void should_checkout_book_failure_when_book_is_not_exist() {
        libraryService.checkoutBook("nonexistence");
        List<Book> booksCanCheckout = libraryService.fetchBooksCanCheckout();

        assertThat(booksCanCheckout.size(), is(6));
        assertThat(booksCanCheckout.get(2).getId(), is("B-03"));
        assertThat(outContent.toString(), is("That book is not available."));
        assertThat(bookRepo.getCheckedOutBooks().size(), is(0));
    }

    @Test
    public void should_checkout_book_failure_when_book_is_exist_in_checked_out_books() {
        bookRepo.addCheckedOutBook("B-03", "C-01");
        List<Book> booksCanCheckout = libraryService.fetchBooksCanCheckout();
        libraryService.checkoutBook("B-03");

        assertThat(booksCanCheckout.size(), is(5));
        assertThat(booksCanCheckout.get(2).getId(), is("B-04"));
        assertThat(outContent.toString(), is("That book is not available."));
        assertThat(bookRepo.getCheckedOutBooks().size(), is(1));
        assertTrue(bookRepo.getCheckedOutBooks().containsKey("B-03"));
    }
}
