package test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;

import execute.Admin;


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
	    String input = "001\n1\n241001 15-20\nN\n";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    boolean result = admin.makeBooking(new Scanner(System.in));
	    Assert.assertEquals(false, result);
	}
	@Test
	public void testMakeBooking_UserNotExsit() {
	    Admin admin = new Admin();
	    String input = "004\n001\n1\n241001 15-20\nN\n";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
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
	    String input = "u\n001\nq\n";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    Scanner scanner = new Scanner(System.in);
	    admin.viewBooking(scanner);
	    scanner.close();
	}
	@Test
	public void testViewBooking_uWrongUserID() {
	    Admin admin = new Admin();
	    String input = "u\n004\n001\nq\n";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    Scanner scanner = new Scanner(System.in);
	    admin.viewBooking(scanner);
	    scanner.close();
	}
	@Test
	public void testViewBooking_r() {
	    Admin admin = new Admin();
	    String input = "r\n4\nq\n";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    Scanner scanner = new Scanner(System.in);
	    admin.viewBooking(scanner);
	    scanner.close();
	}
	@Test
	public void testViewBooking_rWrongRoomID() {
	    Admin admin = new Admin();
	    String input = "r\n5\n4\nq\n";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    Scanner scanner = new Scanner(System.in);
	    admin.viewBooking(scanner);
	    scanner.close();
	}	


	    
	    @Test
	    public void testCancelBooking() {
	        Admin admin = new Admin();
	        String input = "c\n001\n4\n6\nY\n";
	        InputStream in = new ByteArrayInputStream(input.getBytes());
	        System.setIn(in);
	        Scanner scanner = new Scanner(System.in);
	        admin.cancelBooking(scanner);
	        scanner.close();
	    }
	    
	    @Test
	    public void testCancelBookingWrongUserID() {
	    	Admin admin = new Admin();
	        String input = "c\n004\n001\n1\nN\n";
	        InputStream in = new ByteArrayInputStream(input.getBytes());
	        System.setIn(in);
	        Scanner scanner = new Scanner(System.in);
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