package org.alvio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TheatreServiceTest {

    private TheatreService theatreService;

    @BeforeEach
    void setUp() {
        Theatre theatre = new Theatre(4, 4);
        theatreService = new TheatreService(theatre);
    }

    // test cases for reserveSeat method
    @Test
    void reserveSeatShouldReturnTrueForAvailableSeat() {
        Boolean result = theatreService.reserveSeat(1, 1);
        assertTrue(result);
    }

    @Test
    void reserveSeatShouldReturnFalseForAlreadyReservedSeat() {
        theatreService.reserveSeat(1, 1);
        Boolean result = theatreService.reserveSeat(1, 1);
        assertFalse(result);
    }

    @Test
    void reserveSeatShouldReturnNullForInvalidSeat() {
        Boolean result = theatreService.reserveSeat(1, 5);
        assertNull(result);
    }

    // test cases for cancelReservation method
    @Test
    void cancelReservationShouldReturnTrueForReservedSeat() {
        theatreService.reserveSeat(2, 2);
        Boolean result = theatreService.cancelReservation(2, 2);
        assertTrue(result);
    }

    @Test
    void cancelReservationShouldReturnFalseForUnreservedSeat() {
        Boolean result = theatreService.cancelReservation(2, 2);
        assertFalse(result);
    }

    @Test
    void cancelReservationShouldReturnNullForInvalidSeat() {
        Boolean result = theatreService.cancelReservation(0, 0);
        assertNull(result);
    }

    // test cases for isSeatAvailable method
    @Test
    void isSeatAvailableShouldReturnTrueForUnreservedSeat() {
        Boolean result = theatreService.isSeatAvailable(3, 3);
        assertTrue(result);
    }

    @Test
    void isSeatAvailableShouldReturnFalseForReservedSeat() {
        theatreService.reserveSeat(3, 3);
        Boolean result = theatreService.isSeatAvailable(3, 3);
        assertFalse(result);
    }

    @Test
    void isSeatAvailableShouldReturnNullForInvalidSeat() {
        Boolean result = theatreService.isSeatAvailable(1, 99);
        assertNull(result);
    }

    // test cases for findNearbyAvailableSeats method
    @Test
    void findNearbyAvailableSeatsShouldReturnListOfUnreservedSeatsAroundReservedOne() {
        // Reserve a seat in the middle
        theatreService.reserveSeat(2, 2);
        List<Seat> nearby = theatreService.findNearbyAvailableSeats(2, 2, 1);

        // should not be null
        assertNotNull(nearby);

        // should get 8 available seats around the reserved one
        assertEquals(8, nearby.size());

        // should not contain the reserved seat
        assertFalse(nearby.stream().anyMatch(s -> s.getRow() == 2 && s.getNumber() == 2));

        // now reserve a seat in the upper right corner
        theatreService.reserveSeat(4, 4);
        nearby = theatreService.findNearbyAvailableSeats(4, 4, 1);

        // should not be null
        assertNotNull(nearby);

        // should get 3 available seats around the reserved one
        assertEquals(3, nearby.size());
    }
}
