package test;
import java.lang.reflect.Field;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import execute.Booking;
import execute.Room;
import execute.RoomType;
import execute.SportsCenter;
import execute.User;

@RunWith(Suite.class)
@SuiteClasses({
	AdminTest.class,
//	BookingTest.class,
//	CmdAddNewRoomTest.class,
//	CmdCancelBookingTest.class,
//	CmdLoginTest.class,
//	CmdLogoutTest.class,
//	CmdMakeBookingTest.class,
//	CmdMarkClosingDateTest.class,
//	CmdModifyRoomTypePriceTest.class,
//	CmdRegisterAccountTest.class,
//	CmdViewBookingTest.class,
//	CommonTest.class,
//	DateAndTimeTest.class,	
	MainTest.class,
//	NormalUserTest.class,
//	RoomTest.class,
//	RoomTypeTest.class,
//	SportsCenterTest.class,
//	UserTest.class,
//	ViewBookingServiceTest.class
    
})
public class suiteTest {
    // 测试套件类内容
	
	private static SportsCenter sportsCenter = SportsCenter.getInstance();

	private static ArrayList<RoomType> allRoomTypes;
	private static ArrayList<Room> allRooms;
    private static ArrayList<User> allUsers;
    private static ArrayList<Booking> allBookings;
    private static ArrayList<String> allClosingDates;
	
	
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

		allRoomTypes=(ArrayList<RoomType>) roomTypeField.get(sportsCenter);
		allRooms= (ArrayList<Room>) roomField.get(sportsCenter);
		allUsers=(ArrayList<User>) userField.get(sportsCenter);
		allBookings=(ArrayList<Booking>) bookingField.get(sportsCenter);
		allClosingDates=(ArrayList<String>) closingField.get(sportsCenter);

		allRoomTypes=(ArrayList<RoomType>) allRoomTypes.clone();
		allRooms= (ArrayList<Room>) allRooms.clone();
		allUsers=(ArrayList<User>) allUsers.clone();
		allBookings=(ArrayList<Booking>) allBookings.clone();
		allClosingDates=(ArrayList<String>) allClosingDates.clone();



	    
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
	
}