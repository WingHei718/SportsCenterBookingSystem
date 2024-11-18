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
	    public void testExecute_viewAllBookings() {
		 	User user = new User("001", "A", "123456");
	        UserSessionManager.getInstance().setCurrentUser(user);
		 	CmdViewBooking command = new CmdViewBooking();
	        String inputString = "a\nq\n";
	        Scanner scanner = new Scanner(inputString);
	        command.execute(scanner);
	        scanner.close();
	    }
	 
	 @Test
	    public void testExecute_BookingExists_previous() {
	 	User user = new User("001", "A", "123456");
        UserSessionManager.getInstance().setCurrentUser(user);
	 	CmdViewBooking command = new CmdViewBooking();
        String inputString = "a\np\nq\n";
        Scanner scanner = new Scanner(inputString);
        command.execute(scanner);
        scanner.close();
    }
	 @Test
	    public void testExecute_BookingExists_next() {
			User user = new User("001", "A", "123456");
		    UserSessionManager.getInstance().setCurrentUser(user);
		 	CmdViewBooking command = new CmdViewBooking();
		    String inputString = "a\nn\nq\n";
		    Scanner scanner = new Scanner(inputString);
		    command.execute(scanner);
		    scanner.close();
}
	 @Test
	    public void testExecute_BookingExists_specific() {
			User user = new User("001", "A", "123456");
		    UserSessionManager.getInstance().setCurrentUser(user);
		 	CmdViewBooking command = new CmdViewBooking();
		    String inputString = "a\ns\ninvalid\n2024 13\n0000\n2024 1\nq";
		    Scanner scanner = new Scanner(inputString);
		    command.execute(scanner);
		    scanner.close();
}
	 @Test
	    public void testExecute_BookingExists_jump() {
			User user = new User("001", "A", "123456");
		    UserSessionManager.getInstance().setCurrentUser(user);
		 	CmdViewBooking command = new CmdViewBooking();
		    String inputString = "a\nt\nq\n";
		    Scanner scanner = new Scanner(inputString);
		    command.execute(scanner);
		    scanner.close();
}
	 @Test
	    public void testExecute_NoBookings() {
			User user = new User("002", "N", "123456");
		    UserSessionManager.getInstance().setCurrentUser(user);
		 	CmdViewBooking command = new CmdViewBooking();
		    String inputString = "a\n";
		    Scanner scanner = new Scanner(inputString);
		    command.execute(scanner);
		    scanner.close();
}
   
}