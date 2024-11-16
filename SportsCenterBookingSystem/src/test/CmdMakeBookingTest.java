package test;

import execute.CmdMakeBooking;
import execute.Main;
import execute.SportsCenter;
import execute.User;
import execute.UserSessionManager;
import execute.Room;
import execute.RoomType;
import execute.Booking;
import execute.CmdCancelBooking;
import execute.Common;
import execute.DateAndTime;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class CmdMakeBookingTest {

    @Test
    public void testExecuteMakeBooking_Success() {
    	   	User user = new User("001", "A", "123456");
	        UserSessionManager.getInstance().setCurrentUser(user);
	        String inputString = "001\n6\nN";
	        Scanner scanner = new Scanner(inputString);

	        CmdMakeBookingTest CmdMakeBookingTest = new CmdMakeBookingTest();
	        CmdMakeBookingTest.execute(scanner);
    }


}