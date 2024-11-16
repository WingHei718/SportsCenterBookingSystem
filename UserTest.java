package test;

import execute.User;
import execute.UserRole;
import execute.UserSessionManager;
import execute.Booking;
import execute.CmdCancelBooking;
import execute.CmdViewBooking;
import execute.Main;
import execute.NormalUser;
import execute.Room;
import execute.RoomType;
import execute.SportsCenter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {
	
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
    
   
}
    
    
