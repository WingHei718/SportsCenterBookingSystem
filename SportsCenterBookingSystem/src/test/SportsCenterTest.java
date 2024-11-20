package test;

import execute.SportsCenter;
import execute.SportsCenter.FilePath;
import execute.User;
import execute.Booking;
import execute.CmdModifyRoomTypePrice;
import execute.Main;
import execute.Room;
import execute.RoomType;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;

public class SportsCenterTest {
	
	private SportsCenter sportsCenter = SportsCenter.getInstance();
	
	PrintStream oldPrintStream;
	ByteArrayOutputStream bos;


	private void setOutput() throws Exception {
		oldPrintStream = System.out;
		bos = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bos));
	}

	private String getOutput() { // throws Exception
		System.setOut(oldPrintStream);
		return bos.toString();
	}
    
    

	
	
	
	
	
	 @Test
	    public void testGetInstance_InitCalled() {
	        SportsCenter sportsCenter = SportsCenter.getInstance();
	        ArrayList<Booking> bookings = sportsCenter.getAllBookings();
	    }
    
    @Test
    public void testSportsCenterOperations() {
        SportsCenter sportsCenter = SportsCenter.getInstance();
        String roomTypeID = "001";
        String roomTypeName = "Badminton";
        int roomTypePrice = 800;
        RoomType roomType = new RoomType(roomTypeID, roomTypeName, roomTypePrice);
        sportsCenter.addRoomType(roomType);
        String roomID = "101";
        Room room = new Room(roomID, roomType);
        sportsCenter.addRoom(room);
        String userID = "001";
        String userRole = "A";
        String userPassword = "123456";
        User user = new User(userID, userRole, userPassword);
        sportsCenter.addUser(user);
        String closingDate = "231212";
        sportsCenter.addClosingDate(closingDate);
        String bookingID = "booking1";
        Booking booking = new Booking(room, userID, "231201", 10, 12, 100, "N", bookingID);
        sportsCenter.addBooking(booking);
        
        sportsCenter.getNextRoomTypeID();
        sportsCenter.getNextRoomID();
        sportsCenter.getNextBookingID();
      
        RoomType retrievedRoomType = sportsCenter.getRoomTypeByID(roomTypeID);
        assertEquals(roomTypeID, retrievedRoomType.getTypeID());

        Room retrievedRoom = sportsCenter.getRoomByID(roomID);
        assertEquals(roomID, retrievedRoom.getRoomID());

        User retrievedUser = sportsCenter.getUserByID(userID);
        assertEquals(userID, retrievedUser.getUserID());

        Booking retrievedBooking = sportsCenter.getBookingByID(bookingID);
        assertEquals(bookingID, retrievedBooking.getBookingID());

        boolean isClosing = sportsCenter.isClosingDate(closingDate);
        assertTrue(isClosing);

        boolean isNotClosing = sportsCenter.isClosingDate("231213");
        assertFalse(isNotClosing);

        boolean isNullClosing = sportsCenter.isClosingDate(null);
        assertFalse(isNullClosing);

        boolean isInvalidFormatClosing = sportsCenter.isClosingDate("2312");
        assertFalse(isInvalidFormatClosing);
    }
    
   @Test
    public void testAddClosingDate() {
	   SportsCenter sportsCenter = SportsCenter.getInstance();
	   RoomType roomType = new RoomType("001", "Badminton", 800);
       sportsCenter.addRoomType(roomType);
       Room room = new Room("101", roomType);
       sportsCenter.addRoom(room);
       sportsCenter.addUser(new User("001", "A", "123456"));
       
       String closingDate = "231212";
       String closingDate1 = "231213";
       sportsCenter.addClosingDate(closingDate);
        User user = new User( "001", "A", "123456");
        sportsCenter.addUser(user);
        
        Booking booking1 = new Booking(room, "001", closingDate, 11, 12, 100, "N", "1");
        sportsCenter.addBooking(booking1);
        
        Booking booking2 = new Booking(room, "001", closingDate, 13, 14, 100, "Y", "1");
        booking2.cancelBookingByClosingDate();
        sportsCenter.addBooking(booking2);
       // do not why ??
        sportsCenter.addClosingDate(closingDate);
       
    }

    
    @Test
    public void testCheckAvailability() {
    	SportsCenter sportsCenter = SportsCenter.getInstance();
        RoomType roomType = new RoomType("001", "Badminton", 800);
        sportsCenter.addRoomType(roomType);

        Room room1 = new Room("101", roomType);
        Room room2 = new Room("102", roomType);
        sportsCenter.addRoom(room1);
        sportsCenter.addRoom(room2);

        Booking booking1 = new Booking(room1, "001", "231201", 10, 12, 100, "N", "001");
        Booking booking2 = new Booking(room2, "001", "231201", 14, 16, 100, "N", "002");
        sportsCenter.addBooking(booking1);
        sportsCenter.addBooking(booking2);

        Room availableRoom = sportsCenter.checkAvailability(roomType, "231201", 13, 15);

    }

    @Test
    public void testIsOverlapping() {
    	SportsCenter sportsCenter = SportsCenter.getInstance();
        ArrayList<Booking> bookings = new ArrayList<>();
        Booking booking1 = new Booking(null, "001", "231201", 10, 12, 100, "N", "001");
        Booking booking2 = new Booking(null, "001", "231201", 12, 14, 100, "N", "002");
        bookings.add(booking1);
        bookings.add(booking2);

        // Act & Assert
        assertTrue("Bookings should overlap", sportsCenter.isOverlapping(bookings, 11, 13));
        assertFalse("Bookings should not overlap", sportsCenter.isOverlapping(bookings, 8, 9));
    }

    @Test
    public void testCalculateIdleTime() {
    	SportsCenter sportsCenter = SportsCenter.getInstance();
        ArrayList<Booking> bookings = new ArrayList<>();
        Booking booking1 = new Booking(null, "001", "231201", 10, 12, 100, "N", "001");
        Booking booking2 = new Booking(null, "001", "231201", 14, 16, 100, "N", "002");
        bookings.add(booking1);
        bookings.add(booking2);
        int idleTime = sportsCenter.calculateIdleTime(bookings, 13, 15);

    }


    @Test
    public void testPrintAllRoomType() {
        SportsCenter sportsCenter = SportsCenter.getInstance();
        sportsCenter.printAllRoomType();
    }
    
    
    @Test
    public void testPrintAllRoomType_MultipleRoomTypes() {
        SportsCenter sportsCenter = SportsCenter.getInstance();
        sportsCenter.init(); 
        RoomType roomType1 = new RoomType("001", "Badminton", 800);
        RoomType roomType2 = new RoomType("002", "Tennis", 1000);
        sportsCenter.addRoomType(roomType1);
        sportsCenter.addRoomType(roomType2);
        sportsCenter.printAllRoomType();
    }

    @Test
    public void testPrintAllRoomType_NoRoomTypes() {
        // Arrange
        SportsCenter sportsCenter = SportsCenter.getInstance();
        sportsCenter.printAllRoomType();
    }

    
    
    

 
   
    @Test
    public void testPrintAllRoomType_WhenNotEmpty() {
    	SportsCenter sportsCenter = SportsCenter.getInstance();
        RoomType roomType1 = new RoomType("001", "Badminton", 800);
        RoomType roomType2 = new RoomType("002", "Tennis", 1000);
        sportsCenter.addRoomType(roomType1);
        sportsCenter.addRoomType(roomType2);
        sportsCenter.printAllRoomType();
    }

    @Test
    public void testPrintAllRoomType_WhenEmpty() {
    	SportsCenter sportsCenter = SportsCenter.getInstance();
        sportsCenter.printAllRoomType();
    }

    @Test
    public void testPrintAllClosingDate_WhenNotEmpty() {
    	SportsCenter sportsCenter = SportsCenter.getInstance();
        sportsCenter.addClosingDate("231212");
        sportsCenter.addClosingDate("231213");
        sportsCenter.printAllClosingDate();
    }

    @Test
    public void testPrintAllClosingDate_WhenEmpty() {
    	SportsCenter sportsCenter = SportsCenter.getInstance();
        sportsCenter.printAllClosingDate();
    }
    
    
    @Test
    public void testNoRoomType() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {

		SportsCenter sportsCenter = SportsCenter.getInstance();
		
		Class<?> cls = SportsCenter.class;
	    Constructor<?> cons = cls.getDeclaredConstructor();
	    cons.setAccessible(true);
	    SportsCenter emptyInstance = (SportsCenter) cons.newInstance();
	    
	    // Access private field using reflection
	    Field field = SportsCenter.class.getDeclaredField("INSTANCE");
	    field.setAccessible(true); // Make private field accessible

	    // Modify the private field
	    field.set(sportsCenter, emptyInstance);
	    

        String input = "B\n900\nN";
        StringReader stringReader = new StringReader(input);
        Scanner scanner = new Scanner(stringReader);

        // Act
        CmdModifyRoomTypePrice command = new CmdModifyRoomTypePrice();
        command.execute(scanner);

    }
    
    
    @Test
    public void testRoomTypeFileNotFound() throws Exception {
    	setOutput();

		SportsCenter sportsCenter = SportsCenter.getInstance();
		
		Class<?> cls = SportsCenter.class;
	    Method method= cls.getDeclaredMethod("loadRoomType", String.class);
	    method.setAccessible(true);

	    String path = "no_this_file.txt";
	    
	    method.invoke(sportsCenter, path);
	    
	    
	    
	    assertEquals("Cannot find file at path: "+path, getOutput().trim());

    }
    
    @Test
    public void testRoomFileNotFound() throws Exception {
    	

		SportsCenter sportsCenter = SportsCenter.getInstance();
		
		Class<?> cls = SportsCenter.class;
	    Method method= cls.getDeclaredMethod("loadRoom", String.class);
	    method.setAccessible(true);

	    String path = "no_this_file.txt";
	    
	    setOutput();
	    
	    method.invoke(sportsCenter, path);
	    
	    
	    
	    assertEquals("Cannot find file at path: "+path, getOutput().trim());

    }
    
    @Test
    public void testUserFileNotFound() throws Exception {
    	

		SportsCenter sportsCenter = SportsCenter.getInstance();
		
		Class<?> cls = SportsCenter.class;
	    Method method= cls.getDeclaredMethod("loadUser", String.class);
	    method.setAccessible(true);

	    String path = "no_this_file.txt";
	    
	    setOutput();
	    
	    method.invoke(sportsCenter, path);
	    
	    
	    
	    assertEquals("Cannot find file at path: "+path, getOutput().trim());

    }
    
    
    @Test
    public void testBookingFileNotFound() throws Exception {
    	

		SportsCenter sportsCenter = SportsCenter.getInstance();
		
		Class<?> cls = SportsCenter.class;
	    Method method= cls.getDeclaredMethod("loadBooking", String.class);
	    method.setAccessible(true);

	    String path = "no_this_file.txt";
	    
	    setOutput();
	    
	    method.invoke(sportsCenter, path);
	    
	    
	    
	    assertEquals("Cannot find file at path: "+path, getOutput().trim());

    }
    
    @Test
    public void testClosingDateFileNotFound() throws Exception {
    	

		SportsCenter sportsCenter = SportsCenter.getInstance();
		
		Class<?> cls = SportsCenter.class;
	    Method method= cls.getDeclaredMethod("loadClosingDate", String.class);
	    method.setAccessible(true);

	    String path = "no_this_file.txt";
	    
	    setOutput();
	    
	    method.invoke(sportsCenter, path);
	    
	    
	    
	    assertEquals("Cannot find file at path: "+path, getOutput().trim());

    }
    
    @Test
    public void testInitWithSlash() throws Exception {
		SportsCenter sportsCenter = SportsCenter.getInstance();
		
		String path = "/";
	    
	    // Access private field using reflection
	    Field field = SportsCenter.class.getDeclaredField("mainClassPath");
	    field.setAccessible(true); // Make private field accessible

	    // Modify the private field
	    field.set(sportsCenter, path);
	    

        sportsCenter.init();

    }
    
    @Test
    public void testInitWithoutSlash() throws Exception {
    	
 
		SportsCenter sportsCenter = SportsCenter.getInstance();
		
		String path = "";
	    
	    // Access private field using reflection
	    Field field = SportsCenter.class.getDeclaredField("mainClassPath");
	    field.setAccessible(true); // Make private field accessible

	    // Modify the private field
	    field.set(sportsCenter, path);
	    

        sportsCenter.init();
        
        
        field.set(sportsCenter, Main.class.getProtectionDomain().getCodeSource().getLocation().getPath());




    }
    
    
    @Test
    public void testLoadRoomTypeNullRoom() throws Exception {
    	setOutput();
		
		FilePath filepath = FilePath.ROOM;
		filepath.setPath("src/execute/assets/room_data_for_test_case");
		
		SportsCenter sportsCenter = SportsCenter.getInstance();
		
		sportsCenter.init();
		
		filepath.setPath("src/execute/assets/room_data.txt");
		
		String[] split = getOutput().split("\n");
		

		
		assertEquals("Cannot find room type: testLoadRoomTypeNullRoom", split[1].trim());



    }
    
    
    @Test
    public void testLoadBookingNullRoom() throws Exception {
    	setOutput();
    	
    	
		
		FilePath filepath = FilePath.BOOKING;
		filepath.setPath("src/execute/assets/booking_data_for_test_case");
		
		
		SportsCenter sportsCenter = SportsCenter.getInstance();
		User user=new User("testLoadBookingNullRoom", "N", "123456");
		sportsCenter.addUser(user);
		
		
		sportsCenter.init();
		
		filepath.setPath("src/execute/assets/booking_data.txt");
		

		
		String[] split = getOutput().split("\n");
		
		
		assertEquals("Cannot find room: testLoadBookingNullRoom", split[3].trim());


    }
    
    
    @Test
    public void testLoadBookingNullUser() throws Exception {
    	setOutput();
    	
    	
		
		FilePath filepath = FilePath.BOOKING;
		filepath.setPath("src/execute/assets/booking_data_for_test_case_2");
		
		
		SportsCenter sportsCenter = SportsCenter.getInstance();
		RoomType roomType = new RoomType("testLoadBookingNullUser", "testLoadBookingNullUser", 0);
		Room room = new Room("testLoadBookingNullUser", roomType);
		sportsCenter.addRoomType(roomType);
		sportsCenter.addRoom(room);
		
		
		sportsCenter.init();
		
		filepath.setPath("src/execute/assets/booking_data.txt");
		

		
		String[] split = getOutput().split("\n");
		
		
		assertEquals("Cannot find user: testLoadBookingNullUser", split[3].trim());


    }
    

    
    
    

    
    
}

    
    
    
    
    
    
    
    
    
    
    
