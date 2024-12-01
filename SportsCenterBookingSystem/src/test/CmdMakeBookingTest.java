package test;

import execute.Booking;
import execute.CmdMakeBooking;
import execute.Room;
import execute.RoomType;
import execute.SportsCenter;
import execute.User;
import execute.UserSessionManager;

import org.junit.Test;

import java.util.Scanner;

public class CmdMakeBookingTest extends TestCase{
	


    @Test
    public void testExecuteMakeBooking_Success() {

	        String inputString = "767V0F\n3L9L3J\n241001 8-9\nY";
	        Scanner scanner = new Scanner(inputString);

			SportsCenter sportsCenter = SportsCenter.getInstance();

			User user = new User("767V0F", "A", "111111");
			sportsCenter.addUser(user);
			UserSessionManager.getInstance().setCurrentUser(user);
	
			RoomType roomType = new RoomType("3L9L3J", "3L9L3J", 0);
			sportsCenter.addRoomType(roomType);
	
			Room room = new Room("TLM423", roomType);
			sportsCenter.addRoom(room);


	        CmdMakeBooking CmdMakeBooking = new CmdMakeBooking();
	        CmdMakeBooking.execute(scanner);
    }
    
    @Test
    public void testExecuteMakeBooking_InvalidRoom() {
		SportsCenter sportsCenter = SportsCenter.getInstance();

		User user = new User("767V0F", "A", "111111");
		sportsCenter.addUser(user);
		UserSessionManager.getInstance().setCurrentUser(user);

		RoomType roomType = new RoomType("3L9L3J", "3L9L3J", 0);
		sportsCenter.addRoomType(roomType);

		Room room = new Room("TLM423", roomType);
		sportsCenter.addRoom(room);


		String inputString = "767V0F\ninvalid\n66\n3L9L3J\n241001 9-10\nN";
		Scanner scanner = new Scanner(inputString);

		CmdMakeBooking CmdMakeBooking = new CmdMakeBooking();
		CmdMakeBooking.execute(scanner);
    }
    
    @Test
    public void testExecuteMakeBooking_ClosingDay() {
		SportsCenter sportsCenter = SportsCenter.getInstance();

		User user = new User("GO6E8L", "A", "111111");
		sportsCenter.addUser(user);
		UserSessionManager.getInstance().setCurrentUser(user);

		RoomType roomType = new RoomType("WN0HE7", "WN0HE7", 0);
		sportsCenter.addRoomType(roomType);

		Room room = new Room("XMZ90P", roomType);
		sportsCenter.addRoom(room);

		sportsCenter.addClosingDate("241003");

		String inputString = "GO6E8L\nWN0HE7\n241003 15-20\n";
		Scanner scanner = new Scanner(inputString);

		CmdMakeBooking CmdMakeBooking = new CmdMakeBooking();
		CmdMakeBooking.execute(scanner);
    }
    
    
    @Test
    public void testExecuteMakeBooking_InvalidTime() {

			SportsCenter sportsCenter = SportsCenter.getInstance();

			User user = new User("SPP411", "A", "111111");
			sportsCenter.addUser(user);
			UserSessionManager.getInstance().setCurrentUser(user);
	
			RoomType roomType = new RoomType("HP4Y1Y", "HP4Y1Y", 0);
			sportsCenter.addRoomType(roomType);
	
			Room room = new Room("L26SJ5", roomType);
			sportsCenter.addRoom(room);

			Booking booking = new Booking(room, "SPP411", "240101", 1, 2, 0, "N", "SVRLH1");
			sportsCenter.addBooking(booking);
			user.addBooking(booking);
			room.addBooking(booking);



	        String inputString = "SPP411\nHP4Y1Y\n241033 15-20\n241001 8-9\nN\n";
	        Scanner scanner = new Scanner(inputString);

	        CmdMakeBooking CmdMakeBooking = new CmdMakeBooking();
	        CmdMakeBooking.execute(scanner);
    }




}