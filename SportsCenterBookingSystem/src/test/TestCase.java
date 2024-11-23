package test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import execute.Booking;
import execute.Room;
import execute.RoomType;
import execute.SportsCenter;
import execute.User;
import execute.SportsCenter.FilePath;


public class TestCase{
    // 测试套件类内容
	
	private static SportsCenter sportsCenter = SportsCenter.getInstance();
	private static FilePath roomTypePath = FilePath.ROOMTYPE;
	private static FilePath roomPath = FilePath.ROOM;
	private static FilePath userPath = FilePath.USER;
	private static FilePath bookingPath = FilePath.BOOKING;
	private static FilePath closingdatePath = FilePath.CLOSINGDATE;


    @BeforeClass
	public static void setup() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

    	setPathToBackup();

		sportsCenter.saveData();
		
		restorePath();
	}


    @AfterClass
	public static void reset() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		Field roomTypeField = SportsCenter.class.getDeclaredField("allRoomTypes");
		Field roomField = SportsCenter.class.getDeclaredField("allRooms");
		Field userField = SportsCenter.class.getDeclaredField("allUsers");
		Field bookingField = SportsCenter.class.getDeclaredField("allBookings");
		Field closingField = SportsCenter.class.getDeclaredField("allClosingDates");
		
		roomTypeField.setAccessible(true);
		roomField.setAccessible(true);
		userField.setAccessible(true);
		bookingField.setAccessible(true);
		closingField.setAccessible(true);

		roomTypeField.set(sportsCenter, new ArrayList<>());
		roomField.set(sportsCenter, new ArrayList<>());
		userField.set(sportsCenter, new ArrayList<>());
		bookingField.set(sportsCenter, new ArrayList<>());
		closingField.set(sportsCenter, new ArrayList<>());
		
		setPathToBackup();
		
		sportsCenter.init();
		
		restorePath();

	    sportsCenter.saveData();
		
	}
    
    private static void setPathToBackup() {
		roomTypePath.setPath("src/execute/assets/test_cases_file/roomType_backup");
		roomPath.setPath("src/execute/assets/test_cases_file/room_backup");
		userPath.setPath("src/execute/assets/test_cases_file/user_backup");		
		bookingPath.setPath("src/execute/assets/test_cases_file/booking_backup");		
		closingdatePath.setPath("src/execute/assets/test_cases_file/closing_backup");
    }
    
    private static void restorePath() {
		roomTypePath.setPath("src/execute/assets/room_type_data.txt");
		roomPath.setPath("src/execute/assets/room_data.txt");
		userPath.setPath("src/execute/assets/user_data.txt");
		bookingPath.setPath("src/execute/assets/booking_data.txt");
		closingdatePath.setPath("src/execute/assets/closing_date_data.txt");
    }
    

}