package test;

import execute.CmdCancelBooking;
import execute.User;
import execute.UserSessionManager;

import org.junit.Test;

import java.util.Scanner;

public class CmdCancelBookingTest  extends TestCase{

	 @Test
	    public void testExecuteCancelBooking() {
	        User user = new User("001", "A", "123456");
	        UserSessionManager.getInstance().setCurrentUser(user);
	        String inputString = "001\n6\nN\n001\n2\nY\n";
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