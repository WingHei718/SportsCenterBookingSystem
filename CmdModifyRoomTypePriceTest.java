package test;

import static org.junit.Assert.assertEquals;

import java.io.StringReader;
import java.util.Scanner;

import org.junit.Test;

import execute.CmdModifyRoomTypePrice;
import execute.Main;
import execute.RoomType;
import execute.SportsCenter;
import execute.User;

public class CmdModifyRoomTypePriceTest {
	 @Test
	    public void testExecute_ModifyPrice_Success() {
	        // Arrange
	        SportsCenter sportsCenter = SportsCenter.getInstance();
	        RoomType roomType = new RoomType("B", "Badminton", 800);
	        sportsCenter.addRoomType(roomType);
	        String input = "B\n900\nY";
	        StringReader stringReader = new StringReader(input);
	        Scanner scanner = new Scanner(stringReader);

	        // Act
	        CmdModifyRoomTypePrice command = new CmdModifyRoomTypePrice();
	        command.execute(scanner);

	        // Assert
	        RoomType updatedRoomType = sportsCenter.getRoomTypeByID("B");
	        assertEquals("The room type price should be updated to 900", 900, updatedRoomType.getPrice());
	        scanner.close();
	    }

	    @Test
	    public void testExecute_RoomTypeIDNotExist() {
	        // Arrange
	    	SportsCenter sportsCenter = SportsCenter.getInstance();
	        RoomType roomType = new RoomType("B", "Badminton", 800);
	        sportsCenter.addRoomType(roomType);
	        String input = "X\nB\n800\nYB\n900\nY";
	        StringReader stringReader = new StringReader(input);
	        Scanner scanner = new Scanner(stringReader);

	        // Act
	        CmdModifyRoomTypePrice command = new CmdModifyRoomTypePrice();
	        command.execute(scanner);
	    }

	    @Test
	    public void testExecute_PriceSameAsOriginal() {
	        // Arrange
	        SportsCenter sportsCenter = SportsCenter.getInstance();
	        RoomType roomType = new RoomType("B", "Badminton", 800);
	        sportsCenter.addRoomType(roomType);
	        String input = "B\n800";
	        StringReader stringReader = new StringReader(input);
	        Scanner scanner = new Scanner(stringReader);

	        // Act
	        CmdModifyRoomTypePrice command = new CmdModifyRoomTypePrice();
	        command.execute(scanner);
	    }

	    @Test
	    public void testExecute_CancelPriceModification() {
	        // Arrange
	        SportsCenter sportsCenter = SportsCenter.getInstance();
	        RoomType roomType = new RoomType("B", "Badminton", 800);
	        sportsCenter.addRoomType(roomType);
	        String input = "B\n900\nN";
	        StringReader stringReader = new StringReader(input);
	        Scanner scanner = new Scanner(stringReader);

	        // Act
	        CmdModifyRoomTypePrice command = new CmdModifyRoomTypePrice();
	        command.execute(scanner);

	    }
	
}
