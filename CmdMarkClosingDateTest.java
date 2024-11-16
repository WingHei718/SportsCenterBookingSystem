package test;

import execute.CmdMarkClosingDate;
import execute.Common;
import execute.SportsCenter;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class CmdMarkClosingDateTest {

    @Test
    public void testExecuteMarkClosingDate() {
        // Arrange
        CmdMarkClosingDate cmdMarkClosingDate = new CmdMarkClosingDate();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        String inputString = "241001\nY\n";
        Scanner scanner = new Scanner(inputString);
        cmdMarkClosingDate.execute(scanner);
        String output = outContent.toString();
        assertTrue("Should contain confirmation message", output.contains("The date you would like to mark as closing date is 241001, are you confirmed to mark? (Y/N):"));

        System.setOut(originalOut);

        scanner.close();
    }

    @Test
    public void testExecuteAlreadyMarkedClosingDate() {
        CmdMarkClosingDate cmdMarkClosingDate = new CmdMarkClosingDate();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        SportsCenter sportsCenter = SportsCenter.getInstance();
        sportsCenter.addClosingDate("241001");
        String inputString = "241001\n";
        Scanner scanner = new Scanner(inputString);
        cmdMarkClosingDate.execute(scanner);

        String output = outContent.toString();
        assertTrue("Should contain already marked message", output.contains("The date was already marked as closing date."));

        System.setOut(originalOut);

        scanner.close();
    }
}