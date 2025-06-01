package org.alvio;

import java.util.Scanner;
import java.util.List;


public class MainMenu {

    private static final String TITLE = "\n=== Theatre Ticket Reservation App ===";
    private static final String MENU_OPTIONS = """
            (1) Reserve a seat
            (2) Cancel seat reservation
            (3) Check seat availability
            (0) Exit
            """;
    private static final String MENU_PROMPT = "    Enter your choice: ";
    private static final String INPUT_ROW = "--> Enter row number: ";
    private static final String INPUT_SEAT = "--> Enter seat number: ";
    private static final String INVALID_CHOICE = "<!> Invalid choice. Try again.";
    private static final String PROGRAM_EXIT = "Program terminated...";
    private static final String SEAT_INVALID_POSITION = "<!> Invalid seat position.";
    private static final String SEAT_RESERVED_SUCCESS = " reserved successfully.";
    private static final String SEAT_ALREADY_RESERVED = " is already reserved.";
    private static final String SEAT_CANCEL_SUCCESS = " reservation canceled.";
    private static final String SEAT_NOT_RESERVED = " was not reserved.";
    private static final String SEAT_AVAILABLE = " is available.";
    private static final String SEAT_NEARBY_AVAILABILITY = "Nearby available seats: ";

    private final TheatreService theatreService;
    private final Theatre theatre;
    private final Scanner scanner;

    public MainMenu(Theatre theatre, TheatreService theatreService, Scanner scanner) {
        this.theatreService = theatreService;
        this.theatre = theatre;
        this.scanner = scanner;
    }

    public void run() {
        while (true) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> handleReserveSeat();
                case "2" -> handleCancelReservation();
                case "3" -> handleCheckAvailability();
                case "0" -> {
                    System.out.println(PROGRAM_EXIT);
                    return;
                }
                default -> System.out.println(INVALID_CHOICE);
            }
        }
    }

    private void printMenu() {
        System.out.println(TITLE);
        System.out.print(MENU_OPTIONS);
        System.out.print(MENU_PROMPT);
    }

    private void handleReserveSeat() {
        Integer row = Utils.getValidInput(scanner, INPUT_ROW, 1, theatre.getNumberOfRows(), false);
        Integer seat = Utils.getValidInput(scanner, INPUT_SEAT, 1, theatre.getSeatsPerRow(), false);

        Boolean result = theatreService.reserveSeat(row, seat);
        if (result == null) {
            System.out.println(SEAT_INVALID_POSITION);
        } else if (result) {
            System.out.println(theatre.getSeat(row, seat) + SEAT_RESERVED_SUCCESS);
        } else {
            System.out.println(theatre.getSeat(row, seat) + SEAT_ALREADY_RESERVED);
        }
    }

    private void handleCancelReservation() {
        Integer row = Utils.getValidInput(scanner, INPUT_ROW, 1, theatre.getNumberOfRows(), false);
        Integer seat = Utils.getValidInput(scanner, INPUT_SEAT, 1, theatre.getSeatsPerRow(), false);

        Boolean result = theatreService.cancelReservation(row, seat);
        if (result == null) {
            System.out.println(SEAT_INVALID_POSITION);
        } else if (result) {
            System.out.println(theatre.getSeat(row, seat) + SEAT_CANCEL_SUCCESS);
        } else {
            System.out.println(theatre.getSeat(row, seat) + SEAT_NOT_RESERVED);
        }
    }

    private void handleCheckAvailability() {
        Integer row = Utils.getValidInput(scanner, INPUT_ROW, 1, theatre.getNumberOfRows(), false);
        Integer seat = Utils.getValidInput(scanner, INPUT_SEAT, 1, theatre.getSeatsPerRow(), false);

        Boolean result = theatreService.isSeatAvailable(row, seat);
        if (result == null) {
            System.out.println(SEAT_INVALID_POSITION);
        } else if (result) {
            System.out.println(theatre.getSeat(row, seat) + SEAT_AVAILABLE);
        } else {
            System.out.println(theatre.getSeat(row, seat) + SEAT_ALREADY_RESERVED);
            List<Seat> nearby = theatreService.findNearbyAvailableSeats(row, seat);
            if (!nearby.isEmpty()) {
                String suggestion = String.join(", ",
                        nearby.stream().map(Seat::toString).toList());
                System.out.println(SEAT_NEARBY_AVAILABILITY + suggestion);
            }

        }
    }
}
