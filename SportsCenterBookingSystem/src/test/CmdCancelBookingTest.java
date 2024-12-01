package test;

import execute.Booking;
import execute.CmdCancelBooking;
import execute.Room;
import execute.RoomType;
import execute.SportsCenter;
import execute.User;
import execute.UserSessionManager;

import org.junit.Test;

import java.util.Scanner;

public class CmdCancelBookingTest  extends TestCase{

	 @Test
	    public void testExecuteCancelBooking() {


	        String inputString = "HQHZ4Q\nZX9R4R\nN\nHQHZ4Q\nZX9R4R\nY\n";
	        Scanner scanner = new Scanner(inputString);

			SportsCenter sportsCenter = SportsCenter.getInstance();

			User user = new User("HQHZ4Q", "A", "111111");
			sportsCenter.addUser(user);
			UserSessionManager.getInstance().setCurrentUser(user);
	
			RoomType roomType = new RoomType("61ZK7F", "61ZK7F", 0);
			sportsCenter.addRoomType(roomType);
	
			Room room = new Room("19QGLO", roomType);
			sportsCenter.addRoom(room);

			Booking booking = new Booking(room, "HQHZ4Q", "240101", 1, 2, 0, "N", "ZX9R4R");
			sportsCenter.addBooking(booking);
			user.addBooking(booking);
			room.addBooking(booking);


	        CmdCancelBooking cmdCancelBooking = new CmdCancelBooking();
	        cmdCancelBooking.execute(scanner);
	    }

	    @Test
	    public void testExecuteCancelBooking_BookingDoesNotExist() {


	        String inputString = "4DDKES\n5\nG5GKK6\nN";
	        Scanner scanner = new Scanner(inputString);


			SportsCenter sportsCenter = SportsCenter.getInstance();

			User user = new User("4DDKES", "A", "111111");
			sportsCenter.addUser(user);
			UserSessionManager.getInstance().setCurrentUser(user);
	
			RoomType roomType = new RoomType("6647XW", "6647XW", 0);
			sportsCenter.addRoomType(roomType);
	
			Room room = new Room("UO1PTH", roomType);
			sportsCenter.addRoom(room);

			Booking booking = new Booking(room, "4DDKES", "240101", 1, 2, 0, "N", "G5GKK6");
			sportsCenter.addBooking(booking);
			user.addBooking(booking);
			room.addBooking(booking);


	        CmdCancelBooking cmdCancelBooking = new CmdCancelBooking();
	        cmdCancelBooking.execute(scanner);
	    }
	}