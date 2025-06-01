package org.alvio;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MainMenuTest {

    @Test
    void testReserveSeatFlow() {
        // simulating user input - 1(reserve), then 2(row), then 3(seat), then 0 (exit)
        String input = String.join("\n", "1", "2", "3", "0") + "\n";
        Scanner testScanner = new Scanner(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));

        // capturing program output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // setup test
        Theatre theatre = new Theatre(5, 5);
        TheatreService service = new TheatreService(theatre);
        MainMenu menu = new MainMenu(theatre, service, testScanner);

        menu.run();

        // now seat (2,3) should be already reserved
        assertFalse(service.isSeatAvailable(2, 3));

        // captured output should say success that seat "reserved successfully"
        String output = outputStream.toString();
        assertTrue(output.contains("reserved successfully"));
    }
}
