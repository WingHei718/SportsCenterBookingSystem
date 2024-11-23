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

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest  extends TestCase{
	@Before
	public void init() {
		SportsCenter sportsCenter = SportsCenter.getInstance();
        

        RoomType roomType1 = new RoomType("999999", "Testing", 800);

        sportsCenter.addRoomType(roomType1);


        Room room1 = new Room("123456", roomType1);

        sportsCenter.addRoom(room1);


        Booking booking1 = new Booking(room1, "testingUser", "231201", 10, 12, 100, "N", "001");
        Booking booking2 = new Booking(room1, "testingUser", "231201", 14, 16, 100, "N", "002");
        sportsCenter.addBooking(booking1);
        sportsCenter.addBooking(booking2);
        testingUser.addBooking(booking1);
        testingUser.addBooking(booking2);
        room1.addBooking(booking1);
        room1.addBooking(booking2);
	}
	
	User testingUser = new User("testingUser", "N", "123456");
	
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
	    	User user=new User("002", "N", "123456");
	        UserSessionManager.getInstance().setCurrentUser(user);
	        String input = "1\n240701 8-10\nY\n";
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

		    // Modify the private field
		    field.set(sportsCenter, new ArrayList<>());
	        
	        
	        System.setIn(in);
	        user.makeBooking(new Scanner(System.in));
	        
	        sportsCenter.init();
	    }
	 
	 @Test
	    public void testMakeBooking_notAvail() {
	    	User user=new User("002", "N", "123456");
	        UserSessionManager.getInstance().setCurrentUser(user);
	        String input = "999999\n231201 13-15\nn\n";
	        InputStream in = new ByteArrayInputStream(input.getBytes());
	        
	        
	        
	        
	        
	        System.setIn(in);
	        user.makeBooking(new Scanner(System.in));
	    }
	 
	 @Test
	    public void testMakeBooking_notAvail_2() {
	    	User user=new User("002", "N", "123456");
	        UserSessionManager.getInstance().setCurrentUser(user);
	        String input = "999999\n231201 13-15\ny\n999999\n231201 18-19\ny\n";
	        InputStream in = new ByteArrayInputStream(input.getBytes());
	        
  
	        System.setIn(in);
	        user.makeBooking(new Scanner(System.in));
	    }
	 
	 
	 
	 
@Test
public void testMakeBooking_admin() {
	User user = new User("001", "A", "123456");
    UserSessionManager.getInstance().setCurrentUser(user);
    String input = "001\n6\n1\n240701 11-12\nN\n";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    user.makeBooking(new Scanner(System.in));
}
@Test
public void testMakeBooking_DateOrRoomError() {
	User user = new User("001", "A", "123456");
    UserSessionManager.getInstance().setCurrentUser(user);
    String input = "001\n6\n1\n241003 15-20\na\nY";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    user.makeBooking(new Scanner(System.in));
}


@Test
public void testViewBooking_a_admin() {
	User user = new User("001", "A", "123456");
    UserSessionManager.getInstance().setCurrentUser(user);
    String input = "a\np\nn\ns\n2024 1\nt\nq\n";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    user.viewBooking(new Scanner(System.in));
}

@Test
public void testViewBooking_admin() {
	User user = new User("001", "A", "123456");
    UserSessionManager.getInstance().setCurrentUser(user);
    String input = "\nr\n4\n1\nq\n";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    user.viewBooking(new Scanner(System.in));
}
@Test
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
    	User user = new User("001", "A", "123456");
        UserSessionManager.getInstance().setCurrentUser(user);
        String input = "001\n6\nN\n";
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
    public void testCancelBooking_bookingNotFound() throws Exception {
    	setOutput();
    	
    	UserSessionManager.getInstance().setCurrentUser(testingUser);
        String input = "999999999\n98654235\nY\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        
        SportsCenter sportsCenter = SportsCenter.getInstance();
        
        Room room = sportsCenter.getRoomByID("123456");
        
        Booking booking1 = new Booking(room, "testingUser", "231201", 9, 10, 100, "N", "98654235");
        Booking booking2 = new Booking(room, "testingUser", "231201", 9, 10, 100, "Y", "5dfg5f56vb");
        sportsCenter.addBooking(booking1);
        sportsCenter.addBooking(booking2);
        testingUser.addBooking(booking1);
        testingUser.addBooking(booking2);


 
        
        testingUser.cancelBooking(new Scanner(System.in));
        String[] result = getOutput().split("\n");
        
        int index= result.length-3;
        
        assertEquals("Booking ID not found, please input again:",result[index].trim());

    }
    
    
    
    @Test
    public void testViewUserBookingCalendar() {

        UserSessionManager.getInstance().setCurrentUser(testingUser);
        String input = "q\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        testingUser.viewBooking(new Scanner(System.in));
    }
    

   
}
    
    
