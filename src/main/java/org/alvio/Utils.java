package org.alvio;

import java.util.Scanner;

/**
 * utility class providing methods for input validation and processing.
 * This class cannot be instantiated.
 */
public final class Utils {

    private Utils() {} // prevent instantiation

    public static boolean isIntegerWithinBounds(int value, int min, int max) {
        return value >= min && value <= max;
    }

    /**
     * gets a validated integer input from the user with various validation options.
     * validates that the input is an integer within specified bounds
     * supports cancellation by entering -1 (if allowed),
     * and accepts a default value when input is empty (user presses Enter)
     *
     * @param scanner the Scanner object to read input from
     * @param prompt the prompt message to display
     * @param min the minimum allowed value (inclusive)
     * @param max the maximum allowed value (inclusive)
     * @param allowCancel whether to allow cancellation (returns null if -1 is entered)
     * @param defaultValue the default value to return if input is empty (can be null)
     * @return the validated integer, the default value if input is empty,
     *         or null if cancellation is allowed and requested
     */
    public static Integer getValidInput(Scanner scanner, String prompt, int min, int max, boolean allowCancel, Integer defaultValue) {
        final String CANCEL = "-1";

        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if (input.isEmpty() && defaultValue != null) {
                return defaultValue;
            }

            if (allowCancel && input.equals(CANCEL)) {
                return null;
            }

            try {
                int value = Integer.parseInt(input);
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.println("<!> Please enter a number between " + min + " and " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("<!> Invalid input. Please enter a number" +
                        (allowCancel ? ", " + CANCEL + " to cancel" : "") +
                        (defaultValue != null ? ", or Enter for default (" + defaultValue + ")" : "") + ".");
            }
        }
    }

    /**
     * overloaded version of {@link #getValidInput(Scanner, String, int, int, boolean, Integer)}
     * without a default value parameter.
     */
    public static Integer getValidInput(Scanner scanner, String prompt, int min, int max, boolean allowCancel) {
        return getValidInput(scanner, prompt, min, max, allowCancel, null);
    }

}
