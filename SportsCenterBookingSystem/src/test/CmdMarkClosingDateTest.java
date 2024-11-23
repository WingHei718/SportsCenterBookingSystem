package test;


import execute.CmdMarkClosingDate;

import org.junit.Test;

import java.util.Scanner;

public class CmdMarkClosingDateTest  extends TestCase{
	

	
    @Test
    public void testExecuteMarkClosingDate() {
        CmdMarkClosingDate cmdMarkClosingDate = new CmdMarkClosingDate();
        String inputString = "241001\nY\n";
        Scanner scanner = new Scanner(inputString);
        cmdMarkClosingDate.execute(scanner);
        scanner.close();
    }

    @Test
    public void testExecuteAlreadyMarkedClosingDate() {
        CmdMarkClosingDate cmdMarkClosingDate = new CmdMarkClosingDate();
        String inputString = "241001\n";
        Scanner scanner = new Scanner(inputString);
        cmdMarkClosingDate.execute(scanner);
        scanner.close();
    }
}