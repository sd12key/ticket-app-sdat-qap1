package org.alvio;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SeatTest {

    @Test
    void testSeat() {
        Seat seat = new Seat(3, 5);
        assertEquals(3, seat.getRow());
        assertEquals(5, seat.getNumber());
    }

    @Test
    void newSeatShouldNotBeReserved() {
        Seat seat = new Seat(33, 1);
        assertFalse(seat.isReserved());
    }

    @Test
    void reserveShouldMarkSeatAsReserved() {
        Seat seat = new Seat(7, 55);
        seat.reserve();
        assertTrue(seat.isReserved());
    }

    @Test
    void cancelReservationShouldUnmarkSeat() {
        Seat seat = new Seat(3, 77);
        seat.reserve();
        seat.cancelReservation();
        assertFalse(seat.isReserved());
    }

}