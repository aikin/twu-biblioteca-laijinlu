package com.twu.biblioteca.domain.repo;


import com.twu.biblioteca.domain.model.Book;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class BookRepoTest {

    private BookRepo bookRepo;

    private SimpleDateFormat formatter;

    @Before
    public void setUp() {
        bookRepo = new BookRepo();
        formatter = new SimpleDateFormat("yyyy-MM-dd");
    }

    @After
    public  void tearDown() {
        bookRepo = null;
        formatter = null;
    }

    @Test
    public void should_can_get_list_of_books() throws ParseException {
        Map<String, Book> listOfBooks = bookRepo.getListOfBooks();

        assertThat(listOfBooks.size(), is(6));
        assertThat(listOfBooks.get("B-01").getId(), is("B-01"));
        assertThat(listOfBooks.get("B-01").getTitle(), is("Refactoring"));
        assertThat(listOfBooks.get("B-01").getAuthor(), is("Martin Fowler & Kent Beck"));
        assertThat(listOfBooks.get("B-01").getPublishedYear(), is(formatter.parse("1999-07-08")));
        assertThat(listOfBooks.get("B-04").getId(), is("B-04"));
        assertThat(listOfBooks.get("B-04").getTitle(), is("The Art of Readable Code"));
        assertThat(listOfBooks.get("B-04").getAuthor(), is("Dustin Boswell & Trevor Foucher"));
        assertThat(listOfBooks.get("B-04").getPublishedYear(), is(formatter.parse("2011-12-02")));
    }

    @Test
    public void should_get_book_by_id() throws ParseException {
        Book book = bookRepo.getBookById("B-03");

        assertThat(book.getId(), is("B-03"));
        assertThat(book.getTitle(), is("Test Driven Development: By Example"));
        assertThat(book.getAuthor(), is("Kent Beck"));
        assertThat(book.getPublishedYear(), is(formatter.parse("2002-11-18")));
    }

    @Test
    public void should_judge_correct_when_call_is_book_exist() {
        assertTrue(bookRepo.isBookExist("B-02"));
        assertFalse(bookRepo.isBookExist("B-08"));
    }

    @Test
    public void should_get_empty_checked_out_books() {
        assertThat(bookRepo.getCheckedOutBooks().size(), is(0));
    }

    @Test
    public void should_get_correct_checked_out_books_after_add() {
        bookRepo.addCheckedOutBook("B-01", "C-01");

        assertThat(bookRepo.getCheckedOutBooks().size(), is(1));
        assertThat(bookRepo.getCheckedOutBooks().get("B-01"), is("C-01"));
    }

    @Test
    public void should_judge_correct_when_call_is_book_checked_out() {
        assertFalse(bookRepo.isBookCheckedOut("B-01"));

        bookRepo.addCheckedOutBook("B-01", "C-01");

        assertTrue(bookRepo.isBookCheckedOut("B-01"));
    }

    @Test
    public void should_can_remove_checked_out_book() {

        bookRepo.addCheckedOutBook("B-01", "C-01");

        assertTrue(bookRepo.isBookCheckedOut("B-01"));

        bookRepo.removeCheckedOutBook("B-01");

        assertFalse(bookRepo.isBookCheckedOut("B-01"));
    }
}
