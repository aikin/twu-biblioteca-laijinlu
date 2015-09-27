package com.twu.biblioteca.helper;


import java.util.Scanner;

import static java.lang.System.out;

public class InteractionHelper {

    public void promptMessage(String message) {
        out.println(message);
    }

    public void showMenu() {
        out.println("       1 - List books\n       2 - Checkout a book\n       3 - Quit");

        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                try {
                    out.println(scanner.nextLine());
                } catch (Exception e) {
                    out.println(e.getMessage());
                }
            }
        }
    }


}
