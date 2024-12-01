package test;


import execute.CmdAddNewRoom;
import execute.RoomType;
import execute.SportsCenter;
import execute.User;

import org.junit.Test;

import java.io.ByteArrayInputStream;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Scanner;

public class CmdAddNewRoomTest  extends TestCase{


	@Test
    public void testAddRoomType() {
        CmdAddNewRoom cmdAddNewRoom = new CmdAddNewRoom();
        String input = "t\ninvalid\nbadminton 40\n";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    Scanner scanner = new Scanner(System.in);
	    cmdAddNewRoom.execute(scanner);
	    scanner.close();
    }
	@Test
    public void testAddRoomToExsitingType() {
        CmdAddNewRoom cmdAddNewRoom = new CmdAddNewRoom();
        String input = "r\n7P1P7Y\n";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    Scanner scanner = new Scanner(System.in);

		SportsCenter sportsCenter = SportsCenter.getInstance();
		RoomType roomType = new RoomType("7P1P7Y", "7P1P7Y", 0);
		sportsCenter.addRoomType(roomType);
			


	    cmdAddNewRoom.execute(scanner);
	    scanner.close();
    }
	@Test
    public void testAddRoomToExsitingType_NotExsit() {
        CmdAddNewRoom cmdAddNewRoom = new CmdAddNewRoom();
        String input = "r\n6\ninvalid\n97FJF8\n";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    Scanner scanner = new Scanner(System.in);

		SportsCenter sportsCenter = SportsCenter.getInstance();

		RoomType roomType = new RoomType("97FJF8", "97FJF8", 0);
		sportsCenter.addRoomType(roomType);
	

	    cmdAddNewRoom.execute(scanner);
	    scanner.close();
    }
	
	@Test
    public void testNoRoomType() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        CmdAddNewRoom cmdAddNewRoom = new CmdAddNewRoom();
        String input = "uio4899f 1\n";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    Scanner scanner = new Scanner(System.in);
	    
	    
		SportsCenter sportsCenter = SportsCenter.getInstance();
		
	    Field field = SportsCenter.class.getDeclaredField("allRoomTypes");
	    field.setAccessible(true); // Make private field accessible

	    field.set(sportsCenter, new ArrayList<>());
        

	    cmdAddNewRoom.execute(scanner);
	    scanner.close();
	    //sportsCenter.init();
    }

	
		
	}