package test;

import execute.CmdAddNewRoom;
import execute.CmdLogin;
import execute.Main;
import execute.SportsCenter;
import execute.User;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class CmdLoginTest  extends TestCase{

    @Test
    public void testLoginSuccess() {
        CmdLogin cmdLogin = new CmdLogin();
        String input = "001\n123456\n001\n123456\n";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    Scanner scanner = new Scanner(System.in);
	    cmdLogin.execute(scanner);
	    scanner.close();
    }

    @Test
    public void testLoginFailure_InvalidUserID() {
    	CmdLogin cmdLogin = new CmdLogin();
        String input = "999\n9 9\n123456\n001\n123456\n";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    Scanner scanner = new Scanner(System.in);
	    cmdLogin.execute(scanner);
	    scanner.close();
    }

    @Test
    public void testLoginFailure_WrongPassword() {
    	CmdLogin cmdLogin = new CmdLogin();
        String input ="001\nwrongPassword\nnw assword001\n123456\n";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    Scanner scanner = new Scanner(System.in);
	    cmdLogin.execute(scanner);
	    scanner.close();
	    
    }
}