package org.alvio;

import static org.alvio.Utils.isIntegerWithinBounds;

/**
 * theatre with a fixed number of rows and seats per row.
 * rows and seats are 1-based.
 */
public class Theatre {
    public static final int MAX_ROWS = 100;
    public static final int MAX_SEATS_PER_ROW = 100;
    public static final int DEFAULT_ROWS = 20;
    public static final int DEFAULT_MAX_SEATS_PER_ROW = 30;

    public static final String ERROR_INVALID_DIMENSIONS =
            "Theatre dimensions must be > 0 and ≤ " + MAX_ROWS + " rows and " + MAX_SEATS_PER_ROW + " seats per row.";

    private final int numberOfRows;
    private final int seatsPerRow;
    private final Seat[][] seats;

    /**
     * constructor for Theatre [rows][seats per row]
     * throws IllegalArgumentException if invalid dimensions.
     */
    public Theatre(int numberOfRows, int seatsPerRow) {
        if (!isIntegerWithinBounds(numberOfRows, 1, MAX_ROWS) || !isIntegerWithinBounds(seatsPerRow, 1, MAX_SEATS_PER_ROW)) {
            throw new IllegalArgumentException(ERROR_INVALID_DIMENSIONS);
        }
        this.numberOfRows = numberOfRows;
        this.seatsPerRow = seatsPerRow;
        this.seats = new Seat[numberOfRows][seatsPerRow];
        initializeSeats();
    }

    private void initializeSeats() {
        for (int seatRow = 1; seatRow <= this.numberOfRows; seatRow++) {
            for (int seatNumber = 1; seatNumber <= this.seatsPerRow; seatNumber++) {
                this.seats[seatRow - 1][seatNumber - 1] = new Seat(seatRow, seatNumber);
            }
        }
    }

    /**
     * returns the Seat object at [seat row, seat number] (1-based).
     * returns null if invalid seat position.
     */
    public Seat getSeat(int seatRow, int seatNumber) {
        if (!isValidSeatPosition(seatRow, seatNumber)) {
            return null;
        }
        return this.seats[seatRow - 1][seatNumber - 1];
    }

    private boolean isValidSeatPosition(int seatRow, int seatNumber) {
        return seatRow >= 1 && seatRow <= this.numberOfRows &&
                seatNumber >= 1 && seatNumber <= this.seatsPerRow;
    }

    public int getNumberOfRows() {
        return this.numberOfRows;
    }

    public int getSeatsPerRow() {
        return this.seatsPerRow;
    }

    public String toString() {
        return "(" + this.numberOfRows + " Rows, " +  + this.seatsPerRow + " Seats per row )";
    }

}
