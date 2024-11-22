package test;

import execute.CmdMakeBooking;
import execute.CmdMarkClosingDate;
import execute.Common;
import execute.SportsCenter;
import execute.User;
import execute.UserSessionManager;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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