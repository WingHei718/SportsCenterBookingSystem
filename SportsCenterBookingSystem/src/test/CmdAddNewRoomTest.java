package test;


import execute.CmdAddNewRoom;
import execute.SportsCenter;

import org.junit.Test;

import java.io.ByteArrayInputStream;

import java.io.InputStream;

import java.util.Scanner;

public class CmdAddNewRoomTest {


	@Test
    public void testAddRoomType() {
        CmdAddNewRoom cmdAddNewRoom = new CmdAddNewRoom();
        String input = "t\nbadminton 40\n";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    Scanner scanner = new Scanner(System.in);
	    cmdAddNewRoom.execute(scanner);
	    scanner.close();
    }
	@Test
    public void testAddRoomToExsitingType() {
        CmdAddNewRoom cmdAddNewRoom = new CmdAddNewRoom();
        String input = "r\n1\n";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    Scanner scanner = new Scanner(System.in);
	    cmdAddNewRoom.execute(scanner);
	    scanner.close();
    }
	@Test
    public void testAddRoomToExsitingType_NotExsit() {
        CmdAddNewRoom cmdAddNewRoom = new CmdAddNewRoom();
        String input = "r\n5\n1\n";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    Scanner scanner = new Scanner(System.in);
	    cmdAddNewRoom.execute(scanner);
	    scanner.close();
    }
	@Test
    public void testAddRoomTypeNot() {
		SportsCenter sportsCenter = SportsCenter.getInstance();
        CmdAddNewRoom cmdAddNewRoom = new CmdAddNewRoom();
        String input = "t\nbadminton 40\n";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    Scanner scanner = new Scanner(System.in);
	    cmdAddNewRoom.execute(scanner);
	    scanner.close();
    }
	
		
	}