package test;

import execute.CmdCancelBooking;
import execute.Main;
import execute.SportsCenter;
import execute.User;
import execute.UserSessionManager;
import execute.Room;
import execute.RoomType;
import execute.Booking;
import execute.CmdAddNewRoom;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class CmdCancelBookingTest {

	 @Test
	    public void testExecuteCancelBooking() {
		 	
	        User user = new User("001", "A", "123456");
	        UserSessionManager.getInstance().setCurrentUser(user);
	        String inputString = "001\n6\nN";
	        Scanner scanner = new Scanner(inputString);

	        CmdCancelBooking cmdCancelBooking = new CmdCancelBooking();
	        cmdCancelBooking.execute(scanner);
	    }

	    @Test
	    public void testExecuteCancelBooking_BookingDoesNotExist() {

	    	 User user = new User("001", "A", "123456");
		        UserSessionManager.getInstance().setCurrentUser(user);
		        String inputString = "001\n5\n6\nN";
		        Scanner scanner = new Scanner(inputString);

		        CmdCancelBooking cmdCancelBooking = new CmdCancelBooking();
		        cmdCancelBooking.execute(scanner);
		    }
	}