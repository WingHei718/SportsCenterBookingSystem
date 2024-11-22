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

	private static ArrayList<RoomType> allRoomTypes = new ArrayList<>();
	private static ArrayList<Room> allRooms = new ArrayList<>();
    private static ArrayList<User> allUsers = new ArrayList<>();
    private static ArrayList<Booking> allBookings = new ArrayList<>();
    private static ArrayList<String> allClosingDates = new ArrayList<>();
	
	/*

    @BeforeClass
	public static void setup() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

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

		allRoomTypes=deepCopy((ArrayList<RoomType>) roomTypeField.get(sportsCenter));
		allRooms= deepCopy((ArrayList<Room>) roomField.get(sportsCenter));
		allUsers=deepCopy((ArrayList<User>) userField.get(sportsCenter));
		allBookings=deepCopy((ArrayList<Booking>) bookingField.get(sportsCenter));
		allClosingDates=deepCopy((ArrayList<String>) closingField.get(sportsCenter));



	    
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

		roomTypeField.set(sportsCenter, allRoomTypes);
		roomField.set(sportsCenter, allRooms);
		userField.set(sportsCenter, allUsers);
		bookingField.set(sportsCenter, allBookings);
		closingField.set(sportsCenter, allClosingDates);

		sportsCenter.saveData();
	}
    


    private static <T> ArrayList<T> deepCopy(ArrayList<T> originalList) {
        return originalList.stream()
            .map(TestCase::deepCopyObject)
            .collect(Collectors.toCollection(ArrayList::new));
    }

    private static <T> T deepCopyObject(T object) {
        try {
            // Assuming the object implements Cloneable and has a public clone method
            if (object instanceof Cloneable) {
                return (T) object.getClass().getMethod("clone").invoke(object);
            }
            // For custom objects, implement your deep copy logic here
            throw new UnsupportedOperationException("Deep copy not implemented for class: " + object.getClass().getName());
        } catch (Exception e) {
            throw new RuntimeException("Deep copy failed for object: " + object, e);
        }
    }

	*/


    @BeforeClass
	public static void setup() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		
		FilePath roomTypePath = FilePath.ROOMTYPE;
		roomTypePath.setPath("src/execute/assets/test_cases_file/roomType_backup");
		
		FilePath roomPath = FilePath.ROOM;
		roomPath.setPath("src/execute/assets/test_cases_file/room_backup");
		
		FilePath userPath = FilePath.USER;
		userPath.setPath("src/execute/assets/test_cases_file/user_backup");
		
		FilePath bookingPath = FilePath.BOOKING;
		bookingPath.setPath("src/execute/assets/test_cases_file/booking_backup");
		
		FilePath closingdatePath = FilePath.CLOSINGDATE;
		closingdatePath.setPath("src/execute/assets/test_cases_file/closing_backup");
		

		sportsCenter.saveData();
		
		
		
		
		roomTypePath.setPath("src/execute/assets/room_type_data.txt");
		roomPath.setPath("src/execute/assets/room_data.txt");
		userPath.setPath("src/execute/assets/user_data.txt");
		bookingPath.setPath("src/execute/assets/booking_data.txt");
		closingdatePath.setPath("src/execute/assets/closing_date_data.txt");

	    
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
		
		FilePath roomTypePath = FilePath.ROOMTYPE;
		roomTypePath.setPath("src/execute/assets/test_cases_file/roomType_backup");
		
		FilePath roomPath = FilePath.ROOM;
		roomPath.setPath("src/execute/assets/test_cases_file/room_backup");
		
		FilePath userPath = FilePath.USER;
		userPath.setPath("src/execute/assets/test_cases_file/user_backup");
		
		FilePath bookingPath = FilePath.BOOKING;
		bookingPath.setPath("src/execute/assets/test_cases_file/booking_backup");
		
		FilePath closingdatePath = FilePath.CLOSINGDATE;
		closingdatePath.setPath("src/execute/assets/test_cases_file/closing_backup");
		
		sportsCenter.init();
		
		roomTypePath.setPath("src/execute/assets/room_type_data.txt");
		roomPath.setPath("src/execute/assets/room_data.txt");
		userPath.setPath("src/execute/assets/user_data.txt");
		bookingPath.setPath("src/execute/assets/booking_data.txt");
		closingdatePath.setPath("src/execute/assets/closing_date_data.txt");

	    
		
	}
    

}