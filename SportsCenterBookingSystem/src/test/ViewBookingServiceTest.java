package test;


import execute.ViewBookingService;
import execute.Booking;
import execute.Common;
import execute.Room;
import execute.RoomType;
import execute.SportsCenter;
import execute.User;
import execute.UserRole;
import execute.UserSessionManager;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class ViewBookingServiceTest {
	@Before
	public void init() {
		SportsCenter sportsCenter = SportsCenter.getInstance();
        

        RoomType roomType1 = new RoomType("999999", "Testing", 800);

        sportsCenter.addRoomType(roomType1);


        Room room1 = new Room("123456", roomType1);

        sportsCenter.addRoom(room1);


        Booking booking1 = new Booking(room1, "testingUser", "231201", 10, 12, 100, "N", "001");
        Booking booking2 = new Booking(room1, "testingUser", "231201", 14, 16, 100, "Y", "002");
        Booking booking3 = new Booking(room1, "testingUser", "231202", 14, 16, 100, "N", "894845");
        sportsCenter.addBooking(booking1);
        sportsCenter.addBooking(booking2);
        sportsCenter.addBooking(booking3);
        testingUser.addBooking(booking1);
        testingUser.addBooking(booking2);
        testingUser.addBooking(booking3);
        room1.addBooking(booking1);
        room1.addBooking(booking2);
        room1.addBooking(booking3);
	}
	
	User testingUser = new User("testingUser", "N", "123456");

    @Test
    public void testViewBooking_p() {
    	User user = new User("001", "A", "123456");
    	ArrayList<Booking> allBookings=user.getAllBookings();
        ViewBookingService viewBookingService = new ViewBookingService();
        String inputString = "p\nq\n";
        Scanner scanner = new Scanner(inputString);
        viewBookingService.viewBooking(allBookings, scanner);
        scanner.close();
    }

    @Test
    public void testViewBooking_n() {
    	User user = new User("001", "A", "123456");
    	ArrayList<Booking> allBookings=user.getAllBookings();
         ViewBookingService viewBookingService = new ViewBookingService();
         String inputString = "n\nq\n";
         Scanner scanner = new Scanner(inputString);
         viewBookingService.viewBooking(allBookings, scanner);
         scanner.close();
     }

    @Test
    public void testViewBooking_s() {
    	User user = new User("001", "A", "123456");
    	ArrayList<Booking> allBookings=user.getAllBookings(); ViewBookingService viewBookingService = new ViewBookingService();
         String inputString = "s\n2024 1\nq\n";
         Scanner scanner = new Scanner(inputString);
         viewBookingService.viewBooking(allBookings, scanner);        
         scanner.close();
     }

    @Test
    public void testViewBooking_t() {
    	User user = new User("001", "A", "123456");
    	ArrayList<Booking> allBookings=user.getAllBookings();
         ViewBookingService viewBookingService = new ViewBookingService();
         String inputString = "t\nq\n";
         Scanner scanner = new Scanner(inputString);
         viewBookingService.viewBooking(allBookings, scanner);         
         scanner.close();
     }
    @Test
    public void testViewBooking_q() {
    	User user = new User("001", "A", "123456");
    	ArrayList<Booking> allBookings=user.getAllBookings();
         ViewBookingService viewBookingService = new ViewBookingService();
         String inputString = "q\n";
         Scanner scanner = new Scanner(inputString);
         viewBookingService.viewBooking(allBookings, scanner);         
         scanner.close();
     }
    @Test
    public void testViewBookingMultipleCommands() {
    	User user = new User("001", "A", "123456");
    	ArrayList<Booking> allBookings=user.getAllBookings();
        ViewBookingService viewBookingService = new ViewBookingService();
        String inputString = "p\nn\ns\n2023 12\nq\n"; 
        Scanner scanner = new Scanner(inputString);
        viewBookingService.viewBooking(allBookings, scanner);
        scanner.close();
    }
    @Test
    public void testViewBookingInvalidInput() {
    	User user = new User("001", "A", "123456");
    	ArrayList<Booking> allBookings=user.getAllBookings();
        ViewBookingService viewBookingService = new ViewBookingService();
        String inputString = "s\n2024 13\n2025 12\nq\n"; 
        Scanner scanner = new Scanner(inputString);
        viewBookingService.viewBooking(allBookings, scanner);
        scanner.close();
    }
 
    
    @Test
    public void testViewBooking_haveRecord() {

    	ArrayList<Booking> allBookings=testingUser.getAllBookings();
        ViewBookingService viewBookingService = new ViewBookingService();
        String inputString = "q\n"; 
        Scanner scanner = new Scanner(inputString);
        viewBookingService.viewBooking(allBookings, scanner);
        scanner.close();
    }
    
    @Test
    public void testViewBooking_yearsummary() {

    	ArrayList<Booking> allBookings=testingUser.getAllBookings();
        ViewBookingService viewBookingService = new ViewBookingService();
        String inputString = "s\n2000 01\nq\n"; 
        Scanner scanner = new Scanner(inputString);
        viewBookingService.viewBooking(allBookings, scanner);
        scanner.close();
    }
    
    @Test
    public void testViewBooking_monthsummary() {

    	ArrayList<Booking> allBookings=testingUser.getAllBookings();
        ViewBookingService viewBookingService = new ViewBookingService();
        String inputString = "s\n2023 01\nq\n"; 
        Scanner scanner = new Scanner(inputString);
        viewBookingService.viewBooking(allBookings, scanner);
        scanner.close();
    }
    
    @Test
    public void testViewBooking_monthview_1() {

    	ArrayList<Booking> allBookings=testingUser.getAllBookings();
        ViewBookingService viewBookingService = new ViewBookingService();
        String inputString = "s\n2023 12\nq\n"; 
        Scanner scanner = new Scanner(inputString);
        viewBookingService.viewBooking(allBookings, scanner);
        scanner.close();
    }
    
    @Test
    public void testViewBooking_monthview_2() {

    	ArrayList<Booking> allBookings=testingUser.getAllBookings();
        ViewBookingService viewBookingService = new ViewBookingService();
        
        LocalDate currentDate = LocalDate.now();
        int monthOfToday = currentDate.getMonthValue();
        
        
        

        
        String inputString = String.format("s\n2000 %d\nq\n", monthOfToday);
        Scanner scanner = new Scanner(inputString);
        viewBookingService.viewBooking(allBookings, scanner);
        scanner.close();
    }



	
}
	    
	
	
	