package test;

import execute.User;
import execute.UserRole;
import execute.UserSessionManager;
import execute.Booking;

import execute.Room;
import execute.RoomType;
import execute.SportsCenter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest  extends TestCase{

	PrintStream oldPrintStream;
	ByteArrayOutputStream bos;


	private void setOutput() throws Exception {
		oldPrintStream = System.out;
		bos = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bos));
	}

	private String getOutput() { // throws Exception
		System.setOut(oldPrintStream);
		return bos.toString();
	}
	
	
	
	  @Test
	    public void testUserConstructorAndGetters() {
	        String userID = "001";
	        String userRole = "A";
	        String userPassword = "123456";
	        User user = new User(userID, userRole, userPassword);
	        assertEquals("User ID should match", userID, user.getUserID());
	        assertEquals("User Password should match", userPassword, user.getUserPasword());
	    }

	    @Test
	    public void testGetAllBookings_InitiallyEmpty() {
	        String userID = "001";
	        String userRole = "A";
	        String userPassword = "123456";
	        User user = new User(userID, userRole, userPassword);
	        ArrayList<Booking> bookings = user.getAllBookings();
	        assertTrue("Bookings list should be empty initially", bookings.isEmpty());
	    }

	    @Test
	    public void testToString() {
	        String userID = "001";
	        String userRole = "A";
	        String userPassword = "123456";

	        User user = new User(userID, userRole, userPassword);
	        String userString = user.toString();

	        String expectedString = UserRole.getUserRoleByChar(userRole).toString(userID, userPassword);
	        assertEquals("toString should return expected string", expectedString, userString);
	    }

	    @Test
	    public void testGetUserByID_Found() {
	        ArrayList<User> allUsers = new ArrayList<>();
	        User testUser = new User("001", "A", "123456");
	        allUsers.add(testUser);
	        User foundUser = User.getUserByID(allUsers, "001");
	    }

	    @Test
	    public void testGetUserByID_NotFound() {
	        ArrayList<User> allUsers = new ArrayList<>();
	        User testUser = new User("001", "A", "123456");
	        allUsers.add(testUser);
	        User foundUser = User.getUserByID(allUsers, "002");
	        assertNull("User should not be found", foundUser);
	    }
	    
	    @Test
	    public void testShowActionMenu_Admin() {
	        User adminUser = new User("001", "A", "123456");
	        Scanner testScanner = new Scanner("a\n"); 
	        String action = adminUser.showActionMenu(testScanner);
	        testScanner.close();
	    }
	 @Test
	    public void testMakeBooking_normalUser() {
	    	
			SportsCenter sportsCenter = SportsCenter.getInstance();

			User user = new User("1JXFQU", "A", "111111");
			sportsCenter.addUser(user);
	
			RoomType roomType = new RoomType("QA9XT6", "QA9XT6", 0);
			sportsCenter.addRoomType(roomType);
	
			Room room = new Room("08LNWC", roomType);
			sportsCenter.addRoom(room);

	        UserSessionManager.getInstance().setCurrentUser(user);

	        String input = "1JXFQU\nQA9XT6\n240701 8-10\nY\n";
	        InputStream in = new ByteArrayInputStream(input.getBytes());
	        System.setIn(in);
	        user.makeBooking(new Scanner(System.in));
	    }
	 

	 
	 @Test
	    public void testMakeBooking_noRoomType() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
	    	User user=new User("002", "N", "123456");
	        UserSessionManager.getInstance().setCurrentUser(user);
	        String input = "1\n240701 8-10\nY\n";
	        InputStream in = new ByteArrayInputStream(input.getBytes());
	        
	        
			SportsCenter sportsCenter = SportsCenter.getInstance();
			
		    Field field = SportsCenter.class.getDeclaredField("allRoomTypes");
		    field.setAccessible(true); // Make private field accessible
		    
		    sportsCenter.saveData();

		    // Modify the private field
		    field.set(sportsCenter, new ArrayList<>());
	        
	        
	        System.setIn(in);
	        user.makeBooking(new Scanner(System.in));
	        
	        sportsCenter.init();
	    }
	 
	 @Test
	    public void testMakeBooking_notAvail() {
			SportsCenter sportsCenter = SportsCenter.getInstance();

			User user = new User("VXE30R", "N", "111111");
			sportsCenter.addUser(user);
	
			RoomType roomType = new RoomType("AT60SE", "AT60SE", 0);
			sportsCenter.addRoomType(roomType);
	
			Room room = new Room("5G3KZO", roomType);
			sportsCenter.addRoom(room);

			Booking booking = new Booking(room, "VXE30R", "240101", 1, 2, 0, "N", "HT0HY2");
			sportsCenter.addBooking(booking);
			user.addBooking(booking);
			room.addBooking(booking);


	        UserSessionManager.getInstance().setCurrentUser(user);
	        String input = "AT60SE\n240101 1-2\nn\n";
	        InputStream in = new ByteArrayInputStream(input.getBytes());
	        

	        System.setIn(in);
	        user.makeBooking(new Scanner(System.in));
	    }
	 
	 	@Test
	    public void testMakeBooking_notAvail_2() {
			SportsCenter sportsCenter = SportsCenter.getInstance();

			User user = new User("PP000", "N", "111111");
			sportsCenter.addUser(user);
	
			RoomType roomType = new RoomType("AA000", "AA000", 0);
			sportsCenter.addRoomType(roomType);
	
			Room room = new Room("HH000", roomType);
			sportsCenter.addRoom(room);

			Booking booking = new Booking(room, "PP000", "240101", 1, 2, 0, "N", "BB000");
			sportsCenter.addBooking(booking);
			user.addBooking(booking);
			room.addBooking(booking);


	        UserSessionManager.getInstance().setCurrentUser(user);
	        String input = "AA000\n240101 1-2\ny\nAA000\n231202 20-21\ny\n";
	        InputStream in = new ByteArrayInputStream(input.getBytes());
	        
  
	        System.setIn(in);
	        user.makeBooking(new Scanner(System.in));
	    }


	 
	 
@Test
public void testMakeBooking_admin() {
	
	SportsCenter sportsCenter = SportsCenter.getInstance();

	User user = new User("RK40KL", "A", "111111");
	sportsCenter.addUser(user);

	RoomType roomType = new RoomType("A04YHH", "A04YHH", 0);
	sportsCenter.addRoomType(roomType);

	Room room = new Room("HKRG8H", roomType);
	sportsCenter.addRoom(room);
    UserSessionManager.getInstance().setCurrentUser(user);


    String input = "RK40KL\nA04YHH\n240701 11-12\nN\n";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    user.makeBooking(new Scanner(System.in));
}
@Test
public void testMakeBooking_DateOrRoomError() {
	SportsCenter sportsCenter = SportsCenter.getInstance();

	User user = new User("RFMH39", "A", "111111");
	sportsCenter.addUser(user);

	RoomType roomType = new RoomType("R4GPNC", "R4GPNC", 0);
	sportsCenter.addRoomType(roomType);

	Room room = new Room("C2SU6X", roomType);
	sportsCenter.addRoom(room);
    UserSessionManager.getInstance().setCurrentUser(user);

    String input = "RFMH39\nJ2PHLG\nR4GPNC\n241003 15-20\na\nY";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    user.makeBooking(new Scanner(System.in));
}


@Test
public void testViewBooking_a_admin() {
	SportsCenter sportsCenter = SportsCenter.getInstance();

	User user = new User("W3QTIC", "A", "111111");
	sportsCenter.addUser(user);

    UserSessionManager.getInstance().setCurrentUser(user);
    String input = "a\np\nn\ns\n2024 1\nt\nq\n";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    user.viewBooking(new Scanner(System.in));
}

@Test
public void testViewBooking_admin() {
	SportsCenter sportsCenter = SportsCenter.getInstance();

	User user = new User("C1OCG8", "A", "111111");
	sportsCenter.addUser(user);

	RoomType roomType = new RoomType("A5JYRF", "A5JYRF", 0);
	sportsCenter.addRoomType(roomType);

	Room room = new Room("R64B5Q", roomType);
	sportsCenter.addRoom(room);

    String input = "\nr\nAZOK3V\nR64B5Q\nq\n";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    user.viewBooking(new Scanner(System.in));
}
//@Test
public void testViewBooking_user() {
	User user = new User("002", "N", "123456");
    UserSessionManager.getInstance().setCurrentUser(user);
    String input = "a\np\nn\ns\n2024 1\nt\nq\n";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    user.viewBooking(new Scanner(System.in));
}

    @Test
    public void testCancelBooking_admin() {
		SportsCenter sportsCenter = SportsCenter.getInstance();

		User user = new User("391XRG", "A", "111111");
		sportsCenter.addUser(user);

		RoomType roomType = new RoomType("W09ISK", "W09ISK", 0);
		sportsCenter.addRoomType(roomType);

		Room room = new Room("3U28T8", roomType);
		sportsCenter.addRoom(room);

		Booking booking = new Booking(room, "391XRG", "240101", 1, 2, 0, "N", "VK6R2D");
		sportsCenter.addBooking(booking);
		user.addBooking(booking);
		room.addBooking(booking);

        UserSessionManager.getInstance().setCurrentUser(user);

        String input = "391XRG\nVK6R2D\nN\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        user.cancelBooking(new Scanner(System.in));
    }
    @Test
    public void testCancelBooking_user() {
    	User user = new User("002", "N", "123456");
    	UserSessionManager.getInstance().setCurrentUser(user);
        String input = "6\nY\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        user.cancelBooking(new Scanner(System.in));

    }

	@Test
    public void testViewUserBooking() {
		SportsCenter sportsCenter = SportsCenter.getInstance();

		User testingUser = new User("5VJUVHB", "N", "111111");
		sportsCenter.addUser(testingUser);

		RoomType roomType = new RoomType("vgdu78", "vgdu78", 0);
		sportsCenter.addRoomType(roomType);

		Room room = new Room("5d5he4g", roomType);
		sportsCenter.addRoom(room);


		Booking booking1 = new Booking(room, "testingUser", "231201", 10, 12, 100, "N", "001");
        Booking booking2 = new Booking(room, "testingUser", "231201", 14, 16, 100, "Y", "002");
        Booking booking3 = new Booking(room, "testingUser", "231202", 14, 16, 100, "N", "894845");
        sportsCenter.addBooking(booking1);
        sportsCenter.addBooking(booking2);
        sportsCenter.addBooking(booking3);
        testingUser.addBooking(booking1);
        testingUser.addBooking(booking2);
        testingUser.addBooking(booking3);
        room.addBooking(booking1);
        room.addBooking(booking2);
        room.addBooking(booking3);

    	UserSessionManager.getInstance().setCurrentUser(testingUser);
        String input = "";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertEquals(2, testingUser.viewUserBooking());

    }
    
    
    @Test
    public void testCancelBooking_bookingNotFound() throws Exception {
    	setOutput();
		SportsCenter sportsCenter = SportsCenter.getInstance();

		User user = new User("DF789UH", "N", "111111");
		sportsCenter.addUser(user);


    	
    	UserSessionManager.getInstance().setCurrentUser(user);
        String input = "DF789UH\n98654235\nY\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        
		RoomType roomType = new RoomType("OFJF9390", "OFJF9390", 0);
		sportsCenter.addRoomType(roomType);

		Room room = new Room("FIFJK98", roomType);
		sportsCenter.addRoom(room);

        Booking booking1 = new Booking(room, "DF789UH", "231201", 9, 10, 100, "N", "98654235");
        Booking booking2 = new Booking(room, "DF789UH", "231201", 9, 10, 100, "Y", "5dfg5f56vb");
        sportsCenter.addBooking(booking1);
        sportsCenter.addBooking(booking2);
        user.addBooking(booking1);
        user.addBooking(booking2);


 
        
        user.cancelBooking(new Scanner(System.in));
        String[] result = getOutput().split("\n");
        
        int index= result.length-3;
        
        assertEquals("Booking ID not found, please input again:",result[index].trim());

    }
    
    
    
    @Test
    public void testViewUserBookingCalendar() {
		SportsCenter sportsCenter = SportsCenter.getInstance();
		User user = new User("KF8945J", "N", "111111");
		sportsCenter.addUser(user);


        UserSessionManager.getInstance().setCurrentUser(user);
        String input = "q\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        user.viewBooking(new Scanner(System.in));
    }
    
    @Test
    public void testViewUserBookingCalendar2() {

		SportsCenter sportsCenter = SportsCenter.getInstance();
		User user = new User("EYVB732", "N", "111111");
		sportsCenter.addUser(user);


        UserSessionManager.getInstance().setCurrentUser(user);

        String input = "q\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        user.viewBooking(new Scanner(System.in));
    }
    
    

   
}
    
    
