package org.alvio;

import java.util.ArrayList;
import java.util.List;

/**
 * Theatre class seat reservation and cancellation logic.
 */
public class TheatreService {

    private static final int DEFAULT_DISTANCE = 1;

    private final Theatre theatre;

    public TheatreService(Theatre theatre) {
        this.theatre = theatre;
    }

    /**
     * attempts to reserve a seat.
     * @return true if reserved, false if already reserved, null if invalid seat position.
     */
    public Boolean reserveSeat(int seatRow, int seatNumber) {
        Seat seat = theatre.getSeat(seatRow, seatNumber);
        if (seat == null) {
            return null;
        }
        if (seat.isReserved()) {
            return false;
        }
        seat.reserve();
        return true;
    }

    /**
     * attempts to cancel a reservation.
     * @return true if canceled, false if not reserved, null if invalid seat position.
     */
    public Boolean cancelReservation(int seatRow, int seatNumber) {
        Seat seat = theatre.getSeat(seatRow, seatNumber);
        if (seat == null) {
            return null;
        }
        if (!seat.isReserved()) {
            return false;
        }
        seat.cancelReservation();
        return true;
    }

    /**
     * checks if a seat is available.
     * @return true if available, false if reserved, null if invalid position.
     */
    public Boolean isSeatAvailable(int seatRow, int seatNumber) {
        Seat seat = theatre.getSeat(seatRow, seatNumber);
        return (seat == null) ? null : !seat.isReserved();
    }

    /**
     * finds available seats near a preferred seat location.
     * searches in all four directions up to a specified index-based maxDistance.
     */
    public List<Seat> findNearbyAvailableSeats(int preferredRow, int preferredSeatNumber) {
        return findNearbyAvailableSeats(preferredRow, preferredSeatNumber, DEFAULT_DISTANCE);
    }

    public List<Seat> findNearbyAvailableSeats(int preferredRow, int preferredSeatNumber, int maxDistance) {
        List<Seat> nearbyAvailable = new ArrayList<>();

        for (int row = preferredRow - maxDistance; row <= preferredRow + maxDistance; row++) {
            for (int number = preferredSeatNumber - maxDistance; number <= preferredSeatNumber + maxDistance; number++) {

                Seat seat = theatre.getSeat(row, number);
                if (seat != null && !seat.isReserved()) {
                    nearbyAvailable.add(seat);
                }
            }
        }

        return nearbyAvailable;
    }

}
