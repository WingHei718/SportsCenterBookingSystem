package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import execute.Booking;
import execute.CmdCancelBooking;
import execute.CmdViewBooking;
import execute.Main;
import execute.NormalUser;
import execute.Room;
import execute.RoomType;
import execute.SportsCenter;
import execute.User;
import execute.UserSessionManager;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringReader;

public class NormalUserTest {
	
	
	@Test
	public void testShowActionMenu() {
	    NormalUser normalUser = new NormalUser();
	    Scanner scanner = new Scanner(new ByteArrayInputStream("m\n".getBytes()));
	    String action = normalUser.showActionMenu(scanner);
	    assertEquals("m", action);
	    scanner.close();
	}

	@Test
	public void testMakeBooking_failed() {
	    User user=new User("001", "N", "123456");
	    NormalUser normalUser = new NormalUser();
	    UserSessionManager.getInstance().setCurrentUser(user);
	    String input = "1\n240701 8-10\nN\n";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    normalUser.makeBooking(new Scanner(System.in));
	}
	@Test
	 public void testMakeBooking_valid() {
	        User user=new User("001", "N", "123456");
	        NormalUser normalUser = new NormalUser();
	        UserSessionManager.getInstance().setCurrentUser(user);
	        String input = "1\n240701 8-10\nY\n";
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
		 	SportsCenter sportsCenter = SportsCenter.getInstance();
		 	User user=new User("002", "N", "123456");
	        sportsCenter.addUser(user);
	        RoomType roomType = new RoomType("B", "Badminton", 800);
	        sportsCenter.addRoomType(roomType);
	        Room room = new Room("101", roomType);
	        sportsCenter.addRoom(room);
	        Booking booking = new Booking(room, "001", "20231201", 10, 12, 100, "N", "001");
	        user.addBooking(booking); 
	        sportsCenter.addBooking(booking); 
	        String input = "q\n"; 
	        Scanner scanner = new Scanner(input);
		    NormalUser normalUser = new NormalUser();
		    UserSessionManager.getInstance().setCurrentUser(user);
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
    }

@Test
public void testToString() {
    NormalUser normalUser = new NormalUser();
    String result = normalUser.toString("001", "123456");
    assertEquals("001 N 123456", result);
}
	 
	}