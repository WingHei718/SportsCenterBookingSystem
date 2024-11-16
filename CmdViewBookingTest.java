package test;

import execute.SportsCenter;
import execute.User;
import execute.UserSessionManager;
import execute.Booking;
import execute.Room;
import execute.RoomType;
import execute.CmdViewBooking;
import execute.Main;

import java.io.StringReader;
import java.util.Scanner;

import org.junit.Test;
import static org.junit.Assert.*;

public class CmdViewBookingTest {

	 @Test
	    public void testExecute_BookingExists() {
	        SportsCenter sportsCenter = SportsCenter.getInstance();
	        User user = new User("001", "N", "password");
	        sportsCenter.addUser(user);
	        UserSessionManager.getInstance().setCurrentUser(user);

	        RoomType roomType = new RoomType("B", "Badminton", 800);
	        sportsCenter.addRoomType(roomType);
	        Room room = new Room("101", roomType);
	        sportsCenter.addRoom(room);
	        Booking booking = new Booking(room, "001", "20231201", 10, 12, 100, "N", "001");
	        user.addBooking(booking); 
	        sportsCenter.addBooking(booking); 

	        String input = "p\nq\n"; 
	        StringReader stringReader = new StringReader(input);
	        Scanner scanner = new Scanner(stringReader);
	        CmdViewBooking command = new CmdViewBooking();
	        command.execute(scanner);
	    }
	 @Test
	    public void testExecute_BookingExists_previous() {
	        SportsCenter sportsCenter = SportsCenter.getInstance();
	        User user = new User("001", "N", "password");
	        sportsCenter.addUser(user);
	        UserSessionManager.getInstance().setCurrentUser(user);

	        RoomType roomType = new RoomType("B", "Badminton", 800);
	        sportsCenter.addRoomType(roomType);
	        Room room = new Room("101", roomType);
	        sportsCenter.addRoom(room);
	        Booking booking = new Booking(room, "001", "20231201", 10, 12, 100, "N", "001");
	        user.addBooking(booking); 
	        sportsCenter.addBooking(booking); 

	        String input = "p\np\nq\n"; 
	        StringReader stringReader = new StringReader(input);
	        Scanner scanner = new Scanner(stringReader);
	        CmdViewBooking command = new CmdViewBooking();
	        command.execute(scanner);
	    }
	 @Test
	    public void testExecute_BookingExists_next() {
	        SportsCenter sportsCenter = SportsCenter.getInstance();
	        User user = new User("001", "N", "password");
	        sportsCenter.addUser(user);
	        UserSessionManager.getInstance().setCurrentUser(user);

	        RoomType roomType = new RoomType("B", "Badminton", 800);
	        sportsCenter.addRoomType(roomType);
	        Room room = new Room("101", roomType);
	        sportsCenter.addRoom(room);
	        Booking booking = new Booking(room, "001", "20231201", 10, 12, 100, "N", "001");
	        user.addBooking(booking); 
	        sportsCenter.addBooking(booking); 

	        String input = "p\nn\nq\n"; 
	        StringReader stringReader = new StringReader(input);
	        Scanner scanner = new Scanner(stringReader);
	        CmdViewBooking command = new CmdViewBooking();
	        command.execute(scanner);
	    }
	 @Test
	    public void testExecute_BookingExists_specific() {
	        SportsCenter sportsCenter = SportsCenter.getInstance();
	        User user = new User("001", "N", "password");
	        sportsCenter.addUser(user);
	        UserSessionManager.getInstance().setCurrentUser(user);

	        RoomType roomType = new RoomType("B", "Badminton", 800);
	        sportsCenter.addRoomType(roomType);
	        Room room = new Room("101", roomType);
	        sportsCenter.addRoom(room);
	        Booking booking = new Booking(room, "001", "20231201", 10, 12, 100, "N", "001");
	        user.addBooking(booking); 
	        sportsCenter.addBooking(booking); 

	        String input = "p\ns\n2024 1\nq"; 
	        StringReader stringReader = new StringReader(input);
	        Scanner scanner = new Scanner(stringReader);
	        CmdViewBooking command = new CmdViewBooking();
	        command.execute(scanner);
	    }
	 @Test
	    public void testExecute_BookingExists_jump() {
	        SportsCenter sportsCenter = SportsCenter.getInstance();
	        User user = new User("001", "N", "password");
	        sportsCenter.addUser(user);
	        UserSessionManager.getInstance().setCurrentUser(user);

	        RoomType roomType = new RoomType("B", "Badminton", 800);
	        sportsCenter.addRoomType(roomType);
	        Room room = new Room("101", roomType);
	        sportsCenter.addRoom(room);
	        Booking booking = new Booking(room, "001", "20231201", 10, 12, 100, "N", "001");
	        user.addBooking(booking); 
	        sportsCenter.addBooking(booking); 

	        String input = "p\nt\nq\n"; 
	        StringReader stringReader = new StringReader(input);
	        Scanner scanner = new Scanner(stringReader);
	        CmdViewBooking command = new CmdViewBooking();
	        command.execute(scanner);
	    }
	    @Test
	    public void testExecute_NoBookings() {
	        // Arrange
	        SportsCenter sportsCenter = SportsCenter.getInstance();
	        User user = new User("002", "N", "password");
	        sportsCenter.addUser(user);
	        UserSessionManager.getInstance().setCurrentUser(user);

	        String input = ""; 
	        StringReader stringReader = new StringReader(input);
	        Scanner scanner = new Scanner(stringReader);
	        CmdViewBooking command = new CmdViewBooking();
	        command.execute(scanner);
	    }
   
}