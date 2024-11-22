package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Random;
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
import execute.UserSessionManager;

public class MainTest {
	
	
	@Test
	public void testRegist() {
		Random random = new Random();
        int randomUserId = 1 + random.nextInt(999); 
        String randomUserIdStr = Integer.toString(randomUserId); 
        String input = "r\n\nA\n001\n" + randomUserIdStr + "\n123456\n123457\n123456\nl\ne\n";

	    Scanner testScanner = new Scanner(input); 
	    Main.scanner = testScanner; 
        
        Main.main(new String[]{});

        Main.scanner.close();

        testScanner.close();
	}
	@Test
	public void testAdminFunction() {
	    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    PrintStream originalOut = System.out;
	    
	    Scanner testScanner = new Scanner("a\nl\n23543\n001\n12345678\n123456\nj\nm\n9876\n002\n9\n1\n241001 15-25\n20241003 15-20\n241001 15-20\nN"
		+ "\nv\na\np\nn\ns\n2024 13\n2024 1\nt\nq\nv\nr\n1234\n1\nq\nv\nr\n1234\n1\nq\nc\n001\n7\n6\nN\nc\n001\n7\n6\nY\nd\n2410011\n241011\nN"
		+ "\nd\n2410011\n241011\nY\na\nq\nt\ntennis 40\na\nr\n9\n4\np\n4\na\n50\nN\np\nabc\n4\na\n40\n50\ny\nc\n12345\n001\n1\nN\nv\nu\n1345\n001\nq\n"
		+ ""
		
		 
		+ "");
	    Main.scanner = testScanner; 

	    Main.main(new String[]{});
	    Main.scanner.close();
	}	

	
}