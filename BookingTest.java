package test;

import execute.Booking;
import execute.Common;
import execute.Room;
import execute.RoomType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

public class BookingTest {

    @Test
    public void testGetIsCancelled_True() {
        Room room = new Room("101", new RoomType("001", "Badminton", 10));
        Booking booking = new Booking(room, "001", "20240520", 10, 12, 100, "Y", "BK001");
        Assert.assertTrue(booking.getIsCancelled());
    }

    @Test
    public void testGetIsCancelled_False() {
        Room room = new Room("101", new RoomType("001", "Badminton", 10));
        Booking booking = new Booking(room, "001", "20240520", 10, 12, 100, "N", "BK001");
        Assert.assertFalse(booking.getIsCancelled());
    }

    @Test
    public void testGetIsCancelled_InvalidValue() {
        Room room = new Room("101", new RoomType("001", "Badminton", 10));
        Booking booking = new Booking(room, "001", "20240520", 10, 12, 100, "X", "BK001");
        Assert.assertFalse(booking.getIsCancelled());
    }
    
    

    @Test
    public void testGetRoom() {
        RoomType roomType = new RoomType("001", "Badminton", 10);
        Room room = new Room("101", roomType);
        Booking booking = new Booking(room, "001", "20240520", 10, 12, 100, "N", "BK001");
        Room retrievedRoom = booking.getRoom();
        assertEquals(room.getRoomID(), retrievedRoom.getRoomID());
    }
    
    @Test
    public void testGetUserID() {
        RoomType roomType = new RoomType("001", "Badminton", 10);
        Room room = new Room("101", roomType);
        Booking booking = new Booking(room, "001", "20240520", 10, 12, 100, "N", "BK001");
        String userID = booking.getUserID();
        assertEquals("Retrieved user ID should match the one set in the booking", "001", userID);
    }

    @Test
    public void testGetDate() {
        RoomType roomType = new RoomType("001", "Badminton", 10);
        Room room = new Room("101", roomType);
        Booking booking = new Booking(room, "001", "20240520", 10, 12, 100, "N", "BK001");
        String date = booking.getDate();
        assertEquals("Retrieved date should match the one set in the booking", "20240520", date);
    }

    @Test
    public void testGetBookingID() {
        RoomType roomType = new RoomType("001", "Badminton", 10);
        Room room = new Room("101", roomType);
        Booking booking = new Booking(room, "001", "20240520", 10, 12, 100, "N", "BK001");
        String bookingID = booking.getBookingID();
        assertEquals("Retrieved booking ID should match the one set in the booking", "BK001", bookingID);
    }

    @Test
    public void testGetStartTime() {
        RoomType roomType = new RoomType("001", "Badminton", 10);
        Room room = new Room("101", roomType);
        Booking booking = new Booking(room, "001", "20240520", 10, 12, 100, "N", "BK001");
        int startTime = booking.getStartTime();
        assertEquals("Retrieved start time should match the one set in the booking", 10, startTime);
    }

    @Test
    public void testGetEndTime() {
        RoomType roomType = new RoomType("001", "Badminton", 10);
        Room room = new Room("101", roomType);
        Booking booking = new Booking(room, "001", "20240520", 10, 12, 100, "N", "BK001");
        int endTime = booking.getEndTime();
        assertEquals("Retrieved end time should match the one set in the booking", 12, endTime);
    }

    @Test
    public void testGetPricePaid() {
        RoomType roomType = new RoomType("001", "Badminton", 10);
        Room room = new Room("101", roomType);
        Booking booking = new Booking(room, "001", "20240520", 10, 12, 100, "N", "BK001");
        int pricePaid = booking.getPricePaid();
        assertEquals("Retrieved price paid should match the one set in the booking", 100, pricePaid);
    }
    
    
    @Test
    public void testCancelBookingByUser() {
        RoomType roomType = new RoomType("001", "Badminton", 10);
        Room room = new Room("101", roomType);
        Booking booking = new Booking(room, "001", "20240520", 10, 12, 100, "N", "BK001");
        assertFalse("Booking should not be cancelled initially", booking.getIsCancelled());
        assertEquals("Price paid should be 100 initially", 100, booking.getPricePaid());

        booking.cancelBookingByUser();
        assertTrue("Booking should be cancelled after cancelBookingByUser", booking.getIsCancelled());
        assertEquals("Price paid should be halved after cancelBookingByUser", 50, booking.getPricePaid());
    }

    @Test
    public void testCancelBookingByClosingDate() {
        RoomType roomType = new RoomType("001", "Badminton", 10);
        Room room = new Room("101", roomType);
        Booking booking = new Booking(room, "001", "20240520", 10, 12, 100, "N", "BK001");

        assertFalse("Booking should not be cancelled initially", booking.getIsCancelled());
        assertEquals("Price paid should be 100 initially", 100, booking.getPricePaid());

        booking.cancelBookingByClosingDate();
        assertTrue("Booking should be cancelled after cancelBookingByClosingDate", booking.getIsCancelled());
        assertEquals("Price paid should be 0 after cancelBookingByClosingDate", 0, booking.getPricePaid());
    }

    @Test
    public void testToString() {
        RoomType roomType = new RoomType("001", "Badminton", 10);
        Room room = new Room("101", roomType);
        Booking booking = new Booking(room, "001", "240520", 10, 12, 100, "N", "BK001");

        String bookingString = booking.toString();
        assertTrue("String should contain room ID", bookingString.contains(room.getRoomID()));
        assertTrue("String should contain user ID", bookingString.contains("001"));
        assertTrue("String should contain date", bookingString.contains("240520"));
        assertTrue("String should contain start time", bookingString.contains("10"));
        assertTrue("String should contain end time", bookingString.contains("12"));
        assertTrue("String should contain price paid", bookingString.contains("100"));
        assertTrue("String should contain is cancelled", bookingString.contains("N"));
        assertTrue("String should contain booking ID", bookingString.contains("BK001"));
    }
    
	@Test
	public void testViewUserBookingString() {
		Room room = new Room("101", new RoomType("001", "Badminton", 10));
		Booking booking = new Booking(room, "001", "240520", 10, 12, 100, "N", "BK001");
		String result = booking.viewUserBookingString();
	    String expected = "Booking ID: BK001 Room ID: 101 Room Type: Badminton Date: 20-May-2024 Start Time: 10 End Time: 12 Price Paid: $100";
	    Assert.assertEquals(expected, result);
	}
	 @Test
	    public void testViewRoomBookingString() {
	        Room room = new Room("101", new RoomType("001", "Badminton", 10));
	        Booking booking = new Booking(room, "001", "240520", 10, 12, 100, "N", "BK001");
	        String result = booking.viewRoomBookingString();
	        String expected = "Booking ID: BK001 User ID: 001 Date: 20-May-2024 Start Time: 10 End Time: 12";
	        Assert.assertEquals(expected, result);
	    }
	@Test
    public void testGetBookingByID_Null() {
        Room room = new Room("101", new RoomType("001", "Badminton", 10));
        Booking booking = new Booking(room, "001", "20240520", 10, 12, 100, "N", "BK001");
        ArrayList<Booking> allBookings = new ArrayList<>();
        allBookings.add(booking);
        Booking retrievedBooking = Booking.getBookingByID(allBookings, "BK999");
        Assert.assertNull(retrievedBooking);
    }
	@Test
	public void testGetBookingByID_Valid() {
	    Room room = new Room("101", new RoomType("001", "Badminton", 10));
	    Booking booking = new Booking(room, "001", "20240520", 10, 12, 100, "N", "BK001");
	    ArrayList<Booking> allBookings = new ArrayList<>();
	    allBookings.add(booking);
	    Booking retrievedBooking = Booking.getBookingByID(allBookings, "BK001");
	    Assert.assertEquals(booking, retrievedBooking);
	}

    @Test
    public void testGetBookingsOfSpecificDate() {
        RoomType roomType = new RoomType("001", "Badminton", 10);
        Room room = new Room("101", roomType);
        Booking booking1 = new Booking(room, "001", "20240520", 10, 12, 100, "N", "BK001");
        Booking booking2 = new Booking(room, "002", "20240520", 13, 15, 150, "N", "BK002");
        Booking booking3 = new Booking(room, "003", "20240521", 14, 16, 200, "N", "BK003");
        Booking booking4 = new Booking(room, "004", "20240520", 17, 19, 250, "Y", "BK004");
        ArrayList<Booking> allBookings = new ArrayList<>();
        allBookings.add(booking1);
        allBookings.add(booking2);
        allBookings.add(booking3);
        allBookings.add(booking4);
        ArrayList<Booking> result = Booking.getBookingsOfSpecificDate(allBookings, "20240520");
        Assert.assertEquals(2, result.size());
        Assert.assertTrue(result.contains(booking1));
        Assert.assertTrue(result.contains(booking2));
        Assert.assertFalse(result.contains(booking3));
        Assert.assertFalse(result.contains(booking4));
    }
    @Test
    public void testCompareTo_BeforeDate() {
        Room room = new Room("101", new RoomType("001", "Badminton", 10));
        Booking booking1 = new Booking(room, "001", "20240519", 10, 12, 100, "N", "BK001");
        Booking booking2 = new Booking(room, "002", "20240520", 10, 12, 100, "N", "BK002");

        Assert.assertEquals(booking1.compareTo(booking2) ,-1);
    }

    @Test
    public void testCompareTo_AfterDate() {
        Room room = new Room("101", new RoomType("001", "Badminton", 10));
        Booking booking1 = new Booking(room, "001", "20240520", 10, 12, 100, "N", "BK001");
        Booking booking2 = new Booking(room, "002", "20240519", 10, 12, 100, "N", "BK002");

        Assert.assertEquals(booking1.compareTo(booking2) ,1);
    }

    @Test
    public void testCompareTo_SameDate_BeforeTime() {
        Room room = new Room("101", new RoomType("001", "Badminton", 10));
        Booking booking1 = new Booking(room, "001", "20240520", 9, 12, 100, "N", "BK001");
        Booking booking2 = new Booking(room, "002", "20240520", 10, 12, 100, "N", "BK002");

        Assert.assertEquals(booking1.compareTo(booking2) ,-1);
    }

    @Test
    public void testCompareTo_SameDate_AfterTime() {
        Room room = new Room("101", new RoomType("001", "Badminton", 10));
        Booking booking1 = new Booking(room, "001", "20240520", 10, 12, 100, "N", "BK001");
        Booking booking2 = new Booking(room, "002", "20240520", 9, 12, 100, "N", "BK002");

        Assert.assertEquals(booking1.compareTo(booking2) ,1);
    }

    @Test
    public void testCompareTo_SameDate_SameTime_BeforeEndTime() {
        Room room = new Room("101", new RoomType("001", "Badminton", 10));
        Booking booking1 = new Booking(room, "001", "20240520", 10, 11, 100, "N", "BK001");
        Booking booking2 = new Booking(room, "002", "20240520", 10, 12, 100, "N", "BK002");

        Assert.assertEquals(booking1.compareTo(booking2) ,-1);
    }

    @Test
    public void testCompareTo_SameDate_SameTime_AfterEndTime() {
        Room room = new Room("101", new RoomType("001", "Badminton", 10));
        Booking booking1 = new Booking(room, "001", "20240520", 10, 12, 100, "N", "BK001");
        Booking booking2 = new Booking(room, "002", "20240520", 10, 11, 100, "N", "BK002");

        Assert.assertEquals(booking1.compareTo(booking2) ,1);
    }

    @Test
    public void testCompareTo_Equal() {
        Room room = new Room("101", new RoomType("001", "Badminton", 10));
        Booking booking1 = new Booking(room, "001", "20240520", 10, 12, 100, "N", "BK001");
        Booking booking2 = new Booking(room, "002", "20240520", 10, 12, 100, "N", "BK002");

        Assert.assertEquals(0, booking1.compareTo(booking2));
    }
    
    @Test
    public void testPrintDetail() {
        RoomType roomType = new RoomType("001", "Badminton", 10);
        Room room = new Room("101", roomType);
        
        Booking booking = new Booking(room, "001", "240520", 10, 12, 100, "N", "BK001");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        booking.printDetail();
        System.setOut(originalOut);

        String output = outContent.toString();
        assertTrue("Should contain booking ID", output.contains("Booking ID: BK001"));
        assertTrue("Should contain user ID", output.contains("User ID: 001"));
        assertTrue("Should contain room type", output.contains("Room Type: Badminton"));
        assertTrue("Should contain room ID", output.contains("Room ID: 101"));
        assertTrue("Should contain formatted date", output.contains("Date: 20-May-2024"));
        assertTrue("Should contain time", output.contains("Time: 10 - 12"));
    }
}