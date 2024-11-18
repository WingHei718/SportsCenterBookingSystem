package test;

import static org.junit.Assert.assertEquals;

import java.io.StringReader;
import java.util.Scanner;

import org.junit.Test;

import execute.CmdMarkClosingDate;
import execute.CmdModifyRoomTypePrice;
import execute.Main;
import execute.RoomType;
import execute.SportsCenter;
import execute.User;

public class CmdModifyRoomTypePriceTest {
	
	 @Test
	    public void testExecute_ModifyPrice_Success() {
	        CmdModifyRoomTypePrice command = new CmdModifyRoomTypePrice();
	        String inputString = "2\n20\nY\n";
	        Scanner scanner = new Scanner(inputString);
	        command.execute(scanner);
	        scanner.close();
	    }
	 @Test
	    public void testExecute_ModifyPrice_Fail() {
	        CmdModifyRoomTypePrice command = new CmdModifyRoomTypePrice();
	        String inputString = "2\ninvalid\n20\nN\n";
	        Scanner scanner = new Scanner(inputString);
	        command.execute(scanner);
	        scanner.close();
	    }

	    @Test
	    public void testExecute_RoomTypeIDNotExist() {
	         CmdModifyRoomTypePrice command = new CmdModifyRoomTypePrice();
		     String inputString = "invalid\n1\n20\nN\n";
		     Scanner scanner = new Scanner(inputString);
		     command.execute(scanner);
		     scanner.close();
	    }

	    @Test
	    public void testExecute_PriceSameAsOriginal() {
	    	 CmdModifyRoomTypePrice command = new CmdModifyRoomTypePrice();
		     String inputString = "1\n40\n";
		     Scanner scanner = new Scanner(inputString);
		     command.execute(scanner);
		     scanner.close();
	    }


	
}
