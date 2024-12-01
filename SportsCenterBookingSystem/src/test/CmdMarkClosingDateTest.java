package test;


import execute.CmdMarkClosingDate;
import execute.SportsCenter;

import org.junit.Test;

import java.util.Scanner;

public class CmdMarkClosingDateTest  extends TestCase{
	

	
    @Test
    public void testExecuteMarkClosingDate() {
        CmdMarkClosingDate cmdMarkClosingDate = new CmdMarkClosingDate();
        String inputString = "000101\nY\n";
        Scanner scanner = new Scanner(inputString);
        cmdMarkClosingDate.execute(scanner);
        scanner.close();
    }

    @Test
    public void testExecuteAlreadyMarkedClosingDate() {
        SportsCenter sportsCenter = SportsCenter.getInstance();
        sportsCenter.addClosingDate("000101");

        CmdMarkClosingDate cmdMarkClosingDate = new CmdMarkClosingDate();
        String inputString = "000101\n";
        Scanner scanner = new Scanner(inputString);
        cmdMarkClosingDate.execute(scanner);
        scanner.close();
    }
}