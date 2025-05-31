package org.alvio;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TheatreTest {

    @Test
    void shouldCreateTheatreWithValidDimensions() {
        Theatre theatre = new Theatre(5, 10);
        assertEquals(5, theatre.getNumberOfRows());
        assertEquals(10, theatre.getSeatsPerRow());
    }

    @Test
    void shouldThrowIfDimensionsAreOutOfBounds() {
        IllegalArgumentException ex1 = assertThrows(IllegalArgumentException.class, () ->
                new Theatre(0, 5)
        );
        assertEquals(Theatre.ERROR_INVALID_DIMENSIONS, ex1.getMessage());

        IllegalArgumentException ex2 = assertThrows(IllegalArgumentException.class, () ->
                new Theatre(3, -1)
        );
        assertEquals(Theatre.ERROR_INVALID_DIMENSIONS, ex2.getMessage());
    }

    @Test
    void shouldReturnSeatForValidPosition() {
        Theatre theatre = new Theatre(3, 3);
        Seat seat = theatre.getSeat(2, 2);
        assertNotNull(seat);
        assertEquals(2, seat.getRow());
        assertEquals(2, seat.getNumber());
    }

    @Test
    void shouldReturnNullForOutOfBoundsSeat() {
        Theatre theatre = new Theatre(2, 2);
        assertNull(theatre.getSeat(0, 1)); // too low
        assertNull(theatre.getSeat(3, 1)); // too high
        assertNull(theatre.getSeat(1, 3)); // too high
        assertNull(theatre.getSeat(1, 0)); // too low
    }
}
