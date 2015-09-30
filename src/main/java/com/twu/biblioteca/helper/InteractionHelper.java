package com.twu.biblioteca.helper;


import com.twu.biblioteca.domain.model.Book;
import com.twu.biblioteca.domain.model.Movie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static java.lang.System.out;

public class InteractionHelper {

    public void promptMessage(String message) {
        out.println(message);
    }

    public void showMenu() {
        out.println("       1 - List books\n" +
                    "       2 - Checkout a book\n" +
                    "       3 - Return a book\n" +
                    "       4 - List movies\n" +
                    "       5 - Checkout a movie\n" +
                    "       6 - Return a movie\n" +
                    "       7 - Profile\n" +
                    "       8 - Quit");
    }

    public int getChooseOption() {
        String input = readUserInputWithPrompt("\nPlease choose an option: ");
        if (input != null) {
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                return Double.MAX_EXPONENT;
            }
        }
        return Double.MAX_EXPONENT;
    }

    public String readUserInputWithPrompt(String prompt) {
        promptMessage(prompt);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void showBooksCanCheckout(List<Book> books) {
        promptMessage(Book.generateColumnHeader());
        for (Book book : books) {
            promptMessage(book.toString());
        }
    }

    public void showMoviesCanCheckout(List<Movie> movies) {
        promptMessage(Movie.generateColumnHeader());
        for (Movie movie : movies) {
            promptMessage(movie.toString());
        }
    }
}
