package org.alvio;

import java.util.Scanner;

public class TicketApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Integer numberOfRows = Utils.getValidInput(
                scanner,
                "--> Enter number of rows (1–" + Theatre.MAX_ROWS + ", Enter=" + Theatre.DEFAULT_ROWS + "): ",
                1, Theatre.MAX_ROWS, false, Theatre.DEFAULT_ROWS
        );

        Integer seatsPerRow = Utils.getValidInput(
                scanner,
                "--> Enter number of seats per row (1–" + Theatre.MAX_SEATS_PER_ROW + ", Enter=" + Theatre.DEFAULT_MAX_SEATS_PER_ROW + "): ",
                1, Theatre.MAX_SEATS_PER_ROW, false, Theatre.DEFAULT_MAX_SEATS_PER_ROW
        );

        @SuppressWarnings("DataFlowIssue")
        Theatre theatre = new Theatre(numberOfRows, seatsPerRow);

        TheatreService service = new TheatreService(theatre);
        System.out.println("Theatre " + theatre + " was created successfully.");

        MainMenu menu = new MainMenu(theatre, service, scanner);
        menu.run();
        scanner.close();
    }
}
