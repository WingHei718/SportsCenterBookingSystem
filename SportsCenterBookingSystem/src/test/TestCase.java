package test;

import java.lang.reflect.Field;

import java.util.ArrayList;


import org.junit.*;

import execute.SportsCenter;

import execute.SportsCenter.FilePath;


public class TestCase{
    // 测试套件类内容
	
	private static SportsCenter sportsCenter = SportsCenter.getInstance();
	private static FilePath roomTypePath = FilePath.ROOMTYPE;
	private static FilePath roomPath = FilePath.ROOM;
	private static FilePath userPath = FilePath.USER;
	private static FilePath bookingPath = FilePath.BOOKING;
	private static FilePath closingdatePath = FilePath.CLOSINGDATE;


    @Before
	public void setup() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

    	setPathToBackup();

		sportsCenter.saveData();
		
		restorePath();
	}


    @After
	public void reset() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
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
		roomTypePath.setPath("assets/test_cases_file/roomType_backup");
		roomPath.setPath("assets/test_cases_file/room_backup");
		userPath.setPath("assets/test_cases_file/user_backup");		
		bookingPath.setPath("assets/test_cases_file/booking_backup");		
		closingdatePath.setPath("assets/test_cases_file/closing_backup");
    }
    
    private static void restorePath() {
		roomTypePath.setPath("assets/room_type_data.txt");
		roomPath.setPath("assets/room_data.txt");
		userPath.setPath("assets/user_data.txt");
		bookingPath.setPath("assets/booking_data.txt");
		closingdatePath.setPath("assets/closing_date_data.txt");
    }
    

}