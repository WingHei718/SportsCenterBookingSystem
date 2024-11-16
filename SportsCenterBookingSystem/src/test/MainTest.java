package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Assert;
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
import execute.UserRole;
import execute.UserSessionManager;

public class MainTest {
	
	
//	@Test
//	public void testRegist() {
//	    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//	    PrintStream originalOut = System.out;
//	    Scanner testScanner = new Scanner("r\nA\n003\n123456\n123456\nl\n"); 
//	    Main.scanner = testScanner; 
//
//	    Main.main(new String[]{});
//	    Main.scanner.close();
//	}
//	

	
