package org.alvio;

public final class Utils {

    private Utils() {
    }

    public static boolean isIntegerWithinBounds(int value, int min, int max) {
        return value >= min && value <= max;
    }
}
