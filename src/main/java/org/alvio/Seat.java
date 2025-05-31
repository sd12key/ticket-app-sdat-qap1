package org.alvio;

public class Seat {
    private final int row;
    private final int number;
    private boolean reserved;

    public Seat(int row, int number) {
        this.row = row;
        this.number = number;
        this.reserved = false;
    }

    public int getRow() {
        return row;
    }

    public int getNumber() {
        return number;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void reserve() {
        this.reserved = true;
    }

    public void cancelReservation() {
        this.reserved = false;
    }
}
