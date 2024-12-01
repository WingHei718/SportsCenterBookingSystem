package test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;

import execute.Admin;
import execute.Booking;
import execute.Room;
import execute.RoomType;
import execute.SportsCenter;
import execute.User;


public class AdminTest extends TestCase{
			
	@Test
	public void testShowActionMenu_InputM() {
	    Admin admin = new Admin();
	    InputStream in = new ByteArrayInputStream("m\n".getBytes());
	    System.setIn(in);
	    Scanner scanner = new Scanner(in);
	    assertEquals("m", admin.showActionMenu(scanner));
	    scanner.close();
	}

	@Test
	public void testShowActionMenu_InputV() {
	    Admin admin = new Admin();
	    InputStream in = new ByteArrayInputStream("v\n".getBytes());
	    System.setIn(in);
	    Scanner scanner = new Scanner(in);
	    assertEquals("v", admin.showActionMenu(scanner));
	    scanner.close();
	}

	@Test
	public void testShowActionMenu_InputC() {
	    Admin admin = new Admin();
	    InputStream in = new ByteArrayInputStream("c\n".getBytes());
	    System.setIn(in);
	    Scanner scanner = new Scanner(in);
	    assertEquals("c", admin.showActionMenu(scanner));
	    scanner.close();
	}

	@Test
	public void testShowActionMenu_InputL() {
	    Admin admin = new Admin();
	    InputStream in = new ByteArrayInputStream("l\n".getBytes());
	    System.setIn(in);
	    Scanner scanner = new Scanner(in);
	    assertEquals("l", admin.showActionMenu(scanner));
	    scanner.close();
	}

	@Test
	public void testShowActionMenu_InputD() {
	    Admin admin = new Admin();
	    InputStream in = new ByteArrayInputStream("d\n".getBytes());
	    System.setIn(in);
	    Scanner scanner = new Scanner(in);
	    assertEquals("d", admin.showActionMenu(scanner));
	    scanner.close();
	}

	@Test
	public void testShowActionMenu_InputA() {
	    Admin admin = new Admin();
	    InputStream in = new ByteArrayInputStream("a\n".getBytes());
	    System.setIn(in);
	    Scanner scanner = new Scanner(in);
	    assertEquals("a", admin.showActionMenu(scanner));
	    scanner.close();
	}

	@Test
	public void testShowActionMenu_InputP() {
	    Admin admin = new Admin();
	    InputStream in = new ByteArrayInputStream("p\n".getBytes());
	    System.setIn(in);
	    Scanner scanner = new Scanner(in);
	    assertEquals("p", admin.showActionMenu(scanner));
	    scanner.close();
	}


	@Test
	public void testMakeBooking_UserExsit() {
	    Admin admin = new Admin();
		SportsCenter sportsCenter = SportsCenter.getInstance();

		User user = new User("VXE30R", "A", "111111");
		sportsCenter.addUser(user);

		RoomType roomType = new RoomType("AT60SE", "AT60SE", 0);
		sportsCenter.addRoomType(roomType);

		Room room = new Room("5G3KZO", roomType);
		sportsCenter.addRoom(room);

	    String input = "VXE30R\nAT60SE\n241001 15-20\nN\n";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    boolean result = admin.makeBooking(new Scanner(System.in));
	    Assert.assertEquals(false, result);
	}
	@Test
	public void testMakeBooking_UserNotExsit() {
	    Admin admin = new Admin();
	    String input = "004\n608GS8\n7YEV4H\n241001 15-20\nN\n";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);

		SportsCenter sportsCenter = SportsCenter.getInstance();

		User user = new User("608GS8", "A", "111111");
		sportsCenter.addUser(user);

		RoomType roomType = new RoomType("7YEV4H", "7YEV4H", 0);
		sportsCenter.addRoomType(roomType);

		Room room = new Room("O0VST0", roomType);
		sportsCenter.addRoom(room);


	    boolean result = admin.makeBooking(new Scanner(System.in));
	    Assert.assertEquals(false, result);
	}

	@Test
	public void testViewBooking_a() {
	    Admin admin = new Admin();
	    String input = "a\nq\n";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    Scanner scanner = new Scanner(System.in);
	    admin.viewBooking(scanner);
	    scanner.close();
	}
	@Test
	public void testViewBooking_u() {
	    Admin admin = new Admin();
	    String input = "u\n5R565D\nq\n";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    Scanner scanner = new Scanner(System.in);

		SportsCenter sportsCenter = SportsCenter.getInstance();

		User user = new User("5R565D", "A", "111111");
		sportsCenter.addUser(user);

		
	    admin.viewBooking(scanner);
	    scanner.close();
	}
	@Test
	public void testViewBooking_uWrongUserID() {
	    Admin admin = new Admin();

		SportsCenter sportsCenter = SportsCenter.getInstance();

		User user = new User("24Q8RR", "A", "111111");
		sportsCenter.addUser(user);


	    String input = "u\n004\n24Q8RR\nq\n";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    Scanner scanner = new Scanner(System.in);
	    admin.viewBooking(scanner);
	    scanner.close();
	}
	@Test
	public void testViewBooking_r() {
	    Admin admin = new Admin();

		SportsCenter sportsCenter = SportsCenter.getInstance();

		RoomType roomType = new RoomType("1D3S9W", "1D3S9W", 0);
		sportsCenter.addRoomType(roomType);


		Room room = new Room("DCD75C", roomType);
		sportsCenter.addRoom(room);


	    String input = "r\nDCD75C\nq\n";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    Scanner scanner = new Scanner(System.in);
	    admin.viewBooking(scanner);
	    scanner.close();
	}
	@Test
	public void testViewBooking_rWrongRoomID() {
	    Admin admin = new Admin();

		SportsCenter sportsCenter = SportsCenter.getInstance();

		RoomType roomType = new RoomType("GGV6TR", "GGV6TR", 0);
		sportsCenter.addRoomType(roomType);

		Room room = new Room("9Q603Z", roomType);
		sportsCenter.addRoom(room);


	    String input = "r\n5\n9Q603Z\nq\n";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    Scanner scanner = new Scanner(System.in);
	    admin.viewBooking(scanner);
	    scanner.close();
	}	


	    
	    @Test
	    public void testCancelBooking() {
	        Admin admin = new Admin();
	        String input = "c\nVXE30R\n4\nHT0HY2\nY\n";
	        InputStream in = new ByteArrayInputStream(input.getBytes());
	        System.setIn(in);
	        Scanner scanner = new Scanner(System.in);

			SportsCenter sportsCenter = SportsCenter.getInstance();

			User user = new User("VXE30R", "A", "111111");
			sportsCenter.addUser(user);
	
			RoomType roomType = new RoomType("AT60SE", "AT60SE", 0);
			sportsCenter.addRoomType(roomType);
	
			Room room = new Room("5G3KZO", roomType);
			sportsCenter.addRoom(room);

			Booking booking = new Booking(room, "VXE30R", "240101", 1, 2, 0, "N", "HT0HY2");
			sportsCenter.addBooking(booking);
			user.addBooking(booking);
			room.addBooking(booking);

	        admin.cancelBooking(scanner);
	        scanner.close();
	    }
	    
	    @Test
	    public void testCancelBookingWrongUserID() {
	    	Admin admin = new Admin();
	        String input = "c\nwrongid\nTS4PU9\nPP12MF\nN\n";
	        InputStream in = new ByteArrayInputStream(input.getBytes());
	        System.setIn(in);
	        Scanner scanner = new Scanner(System.in);


			SportsCenter sportsCenter = SportsCenter.getInstance();

			User user = new User("TS4PU9", "A", "111111");
			sportsCenter.addUser(user);
	
			RoomType roomType = new RoomType("3KH510", "3KH510", 0);
			sportsCenter.addRoomType(roomType);
	
			Room room = new Room("P3K5TF", roomType);
			sportsCenter.addRoom(room);

			Booking booking = new Booking(room, "TS4PU9", "240101", 1, 2, 0, "N", "PP12MF");
			sportsCenter.addBooking(booking);
			user.addBooking(booking);
			room.addBooking(booking);



	        admin.cancelBooking(scanner);
	        scanner.close();
	    }
	    
	    
	    @Test
	    public void testToString() {
	        Admin admin = new Admin();
	        String result = admin.toString("12345", "password123");
	        String expected = "12345 A password123";
	        Assert.assertEquals(expected, result);
	    }
	    
	}