package test;

import execute.CmdLogin;
import execute.SportsCenter;
import execute.User;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class CmdLoginTest  extends TestCase{

    @Test
    public void testLoginSuccess() {
        CmdLogin cmdLogin = new CmdLogin();
        String input = "K2LT1S\n111111\n";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    Scanner scanner = new Scanner(System.in);

		SportsCenter sportsCenter = SportsCenter.getInstance();

		User user = new User("K2LT1S", "A", "111111");
		sportsCenter.addUser(user);


	    cmdLogin.execute(scanner);
	    scanner.close();
    }

    @Test
    public void testLoginFailure_InvalidUserID() {
    	CmdLogin cmdLogin = new CmdLogin();
        String input = "999\n9 9\n123456\nSMUZ4S\n111111\n";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    Scanner scanner = new Scanner(System.in);

		SportsCenter sportsCenter = SportsCenter.getInstance();

		User user = new User("SMUZ4S", "A", "111111");
		sportsCenter.addUser(user);


	    cmdLogin.execute(scanner);
	    scanner.close();
    }

    @Test
    public void testLoginFailure_WrongPassword() {
    	CmdLogin cmdLogin = new CmdLogin();
        String input ="2J7O2Y\nwrongPassword\nnw assword001\n111111\n";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    Scanner scanner = new Scanner(System.in);

		SportsCenter sportsCenter = SportsCenter.getInstance();

		User user = new User("2J7O2Y", "A", "111111");
		sportsCenter.addUser(user);


	    cmdLogin.execute(scanner);
	    scanner.close();
	    
    }
}