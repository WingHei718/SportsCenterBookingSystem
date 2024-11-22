package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;
import java.util.Scanner;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import execute.Admin;
import execute.Booking;
import execute.CmdAddNewRoom;
import execute.Main;
import execute.NormalUser;
import execute.Room;
import execute.RoomType;
import execute.SportsCenter;
import execute.User;
import execute.UserSessionManager;

public class MainTest extends TestCase{
	

	
	User testadmin;
	RoomType testRoomType;
	Room testRoom;
	SportsCenter sportsCenter = SportsCenter.getInstance();
	

	
	@Before
	public void init() {
		SportsCenter sportsCenter = SportsCenter.getInstance();
		

		testadmin = sportsCenter.getUserByID("testadmin");
		if(testadmin==null){
			testadmin = new User("testadmin", "A", "testadmin");
			sportsCenter.addUser(testadmin);
		}
		
		
		testRoomType = sportsCenter.getRoomTypeByID("BJv9gH");
		if(testRoomType==null){
			testRoomType = new RoomType("BJv9gH", "testRoomType", 100);
			sportsCenter.addRoomType(testRoomType);
		}

		testRoom = sportsCenter.getRoomByID("7y1k4V");
		if(testRoom==null){
			testRoom = new Room("7y1k4V", testRoomType);
			sportsCenter.addRoom(testRoom);
		}
		
	}
	
	
	@Test
	public void testRegist() {
		String randomUserIdStr;
		
		Random random = new Random();
		SportsCenter sportsCenter = SportsCenter.getInstance();
		do {
			int randomUserId = 1 + random.nextInt(999); 
			randomUserIdStr = Integer.toString(randomUserId); 
		}
		while(sportsCenter.getUserByID(randomUserIdStr)!=null);
		
        
        
        String input = "r\n\nA\n001\n" + randomUserIdStr + "\n123456\n123457\n123456\nl\ne\n";

	    Scanner testScanner = new Scanner(input); 
	    Main.scanner = testScanner; 
        
        Main.main(new String[]{});

        Main.scanner.close();

        testScanner.close();
	}
	
	
	@Test
	public void testAdminFunction_m() {
		String input = "l\ntestadmin\ntestadmin\n" + "m\ntestadmin\nBJv9gH\n241122 12-13\ny\n"+"l\ne\n";
	    
	    Scanner testScanner = new Scanner(input);
	    Main.scanner = testScanner; 

	    Main.main(new String[]{});
	    Main.scanner.close();
	}	
	
	@Test
	public void testAdminFunction_v() {
		String input = "l\ntestadmin\ntestadmin\n" + "v\nu\ntestadmin\nq\n"+"l\ne\n";
	    
	    Scanner testScanner = new Scanner(input);
	    Main.scanner = testScanner; 

	    Main.main(new String[]{});
	    Main.scanner.close();
	}	
	
	
	@Test
	public void testAdminFunction_c() {
		String input = "l\ntestadmin\ntestadmin\n" + "c\ntestadmin\n6XhIYY\ny\n"+"l\ne\n";
		
		SportsCenter sportsCenter = SportsCenter.getInstance();
		Booking booking = new Booking(testRoom, "testadmin", "240101", 11, 12, 50, "N", "6XhIYY");
		testadmin.addBooking(booking);
		testRoom.addBooking(booking);
		sportsCenter.addBooking(booking);
		


		
	    Scanner testScanner = new Scanner(input);
	    Main.scanner = testScanner; 

	    Main.main(new String[]{});
	    Main.scanner.close();
	}	
	
	@Test
	public void testAdminFunction_d() {
		String input = "l\ntestadmin\ntestadmin\n" + "d\n241121\ny\n"+"l\ne\n";
		

		
	    Scanner testScanner = new Scanner(input);
	    Main.scanner = testScanner; 

	    Main.main(new String[]{});
	    Main.scanner.close();
	}	
	
	@Test
	public void testAdminFunction_a() {
		String input = "l\ntestadmin\ntestadmin\n" + "a\nr\nBJv9gH\n"+"l\ne\n";
		

		
	    Scanner testScanner = new Scanner(input);
	    Main.scanner = testScanner; 

	    Main.main(new String[]{});
	    Main.scanner.close();
	}	
	
	
	@Test
	public void testAdminFunction_p() {
		String input = "l\ntestadmin\ntestadmin\n" + "p\nBJv9gH\n100\n"+"l\ne\n";
		

		
	    Scanner testScanner = new Scanner(input);
	    Main.scanner = testScanner; 

	    Main.main(new String[]{});
	    Main.scanner.close();
	}	
	
	@Test
	public void testConstructor() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> cls = Main.class;
	    Constructor<?> cons = cls.getDeclaredConstructor();
	    cons.setAccessible(true);
	    cons.newInstance(null);
	}	
	
	

	
}