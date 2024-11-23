package test;

import execute.CmdMakeBooking;

import execute.User;
import execute.UserSessionManager;

import org.junit.Test;

import java.util.Scanner;

public class CmdMakeBookingTest extends TestCase{
	


    @Test
    public void testExecuteMakeBooking_Success() {
    	   	User user = new User("001", "A", "123456");
	        UserSessionManager.getInstance().setCurrentUser(user);
	        String inputString = "001\n1\n241001 8-9\nY";
	        Scanner scanner = new Scanner(inputString);

	        CmdMakeBooking CmdMakeBooking = new CmdMakeBooking();
	        CmdMakeBooking.execute(scanner);
    }
    
    @Test
    public void testExecuteMakeBooking_InvalidRoom() {
    	   	User user = new User("001", "A", "123456");
	        UserSessionManager.getInstance().setCurrentUser(user);
	        String inputString = "001\ninvalid\n66\n1\n241001 9-10\nN";
	        Scanner scanner = new Scanner(inputString);

	        CmdMakeBooking CmdMakeBooking = new CmdMakeBooking();
	        CmdMakeBooking.execute(scanner);
    }
    
    @Test
    public void testExecuteMakeBooking_ClosingDay() {
    	   	User user = new User("001", "A", "123456");
	        UserSessionManager.getInstance().setCurrentUser(user);
	        String inputString = "001\n1\n241003 15-20\n";
	        Scanner scanner = new Scanner(inputString);

	        CmdMakeBooking CmdMakeBooking = new CmdMakeBooking();
	        CmdMakeBooking.execute(scanner);
    }
    
    
    @Test
    public void testExecuteMakeBooking_InvalidTime() {
    	   	User user = new User("001", "A", "123456");
	        UserSessionManager.getInstance().setCurrentUser(user);
	        String inputString = "001\n1\n241033 15-20\n241001 8-9\nN\n";
	        Scanner scanner = new Scanner(inputString);

	        CmdMakeBooking CmdMakeBooking = new CmdMakeBooking();
	        CmdMakeBooking.execute(scanner);
    }




}