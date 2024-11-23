package test;

import static org.junit.Assert.*;


import org.junit.Test;


import execute.NormalUser;
import execute.User;
import execute.UserSessionManager;

import java.util.Scanner;
import java.io.ByteArrayInputStream;
import java.io.InputStream;


public class NormalUserTest  extends TestCase{
	
	
	@Test
	public void testShowActionMenu() {
	    NormalUser normalUser = new NormalUser();
	    Scanner scanner = new Scanner(new ByteArrayInputStream("m\n".getBytes()));
	    String action = normalUser.showActionMenu(scanner);
	    assertEquals("m", action);
	    scanner.close();
	}

	@Test
	public void testMakeBooking_failedWrongDate() {
	    User user=new User("002", "N", "123456");
	    NormalUser normalUser = new NormalUser();
	    UserSessionManager.getInstance().setCurrentUser(user);
	    String input = "1\ninvalid\n241041 15-20\n241001\n241001 8-9\nN\n";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    normalUser.makeBooking(new Scanner(System.in));
	}
	@Test
	public void testMakeBooking_failedWrongRoom() {
	    User user=new User("002", "N", "123456");
	    NormalUser normalUser = new NormalUser();
	    UserSessionManager.getInstance().setCurrentUser(user);
	    String input = "invalid\n1\n241001 8-9\nN\n";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    normalUser.makeBooking(new Scanner(System.in));
	}
	@Test
	 public void testMakeBooking_valid() {
	        User user=new User("002", "N", "123456");
		    NormalUser normalUser = new NormalUser();
		    UserSessionManager.getInstance().setCurrentUser(user);
		    String input = "1\n241001 8-9\nY\n";
		    InputStream in = new ByteArrayInputStream(input.getBytes());
		    System.setIn(in);
		    normalUser.makeBooking(new Scanner(System.in));

	    }


	@Test
	public void testViewBooking_null() {
	    User user=new User("002", "N", "123456");
	    NormalUser normalUser = new NormalUser();
	    UserSessionManager.getInstance().setCurrentUser(user);
	    String input = "q\n";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    normalUser.viewBooking(new Scanner(System.in));
	}
	@Test
	public void testViewBooking_valid() {
	    User user=new User("001", "A", "123456");
	    NormalUser normalUser = new NormalUser();
	    UserSessionManager.getInstance().setCurrentUser(user);
	    String input = "q\n";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    normalUser.viewBooking(new Scanner(System.in));
	}
	@Test
    public void testCancelBooking_null() {
        User user=new User("002", "N", "123456");
	    NormalUser normalUser = new NormalUser();
	    UserSessionManager.getInstance().setCurrentUser(user);
	    String input = "q\n";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    normalUser.cancelBooking(new Scanner(System.in));
    }
	@Test
    public void testCancelBooking_valid() {
		User user = new User("002", "N", "123456");
        UserSessionManager.getInstance().setCurrentUser(user);
        String inputString = "001\n6\nN";
        Scanner scanner = new Scanner(inputString);
        NormalUser normalUser = new NormalUser();
        UserSessionManager.getInstance().setCurrentUser(user);
        normalUser.cancelBooking(new Scanner(System.in));
		scanner.close();
    }

@Test
public void testToString() {
    NormalUser normalUser = new NormalUser();
    String result = normalUser.toString("001", "123456");
    assertEquals("001 N 123456", result);
}
	 
	}