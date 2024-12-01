package test;

import execute.Booking;
import execute.Room;
import execute.RoomType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

public class BookingTest  extends TestCase{

    @Test
    public void testGetIsCancelled_True() {
        Room room = new Room("5X5ZN7", new RoomType("Q49RGV", "Badminton", 10));
        Booking booking = new Booking(room, "668UXA", "20240520", 10, 12, 100, "Y", "EJ8T55");
        Assert.assertTrue(booking.getIsCancelled());
    }

    @Test
    public void testGetIsCancelled_False() {
        Room room = new Room("KD5BAE", new RoomType("M75INR", "Badminton", 10));
        Booking booking = new Booking(room, "211HOU", "20240520", 10, 12, 100, "N", "P79FR6");
        Assert.assertFalse(booking.getIsCancelled());
    }

    @Test
    public void testGetIsCancelled_InvalidValue() {
        Room room = new Room("8O6FEM", new RoomType("2XI4ID", "Badminton", 10));
        Booking booking = new Booking(room, "MOU72E", "20240520", 10, 12, 100, "X", "V6E35I");
        Assert.assertFalse(booking.getIsCancelled());
    }
    
    

    @Test
    public void testGetRoom() {
        RoomType roomType = new RoomType("3J2C1S", "Badminton", 10);
        Room room = new Room("N505LB", roomType);
        Booking booking = new Booking(room, "B7AF9Z", "20240520", 10, 12, 100, "N", "5S6VPP");
        Room retrievedRoom = booking.getRoom();
        assertEquals(room.getRoomID(), retrievedRoom.getRoomID());
    }
    
    @Test
    public void testGetUserID() {
        RoomType roomType = new RoomType("4BR3V4", "Badminton", 10);
        Room room = new Room("5EW2QR", roomType);
        Booking booking = new Booking(room, "SJLN0M", "20240520", 10, 12, 100, "N", "4S8TJA");
        String userID = booking.getUserID();
        assertEquals("Retrieved user ID should match the one set in the booking", "SJLN0M", userID);
    }

    @Test
    public void testGetDate() {
        RoomType roomType = new RoomType("WCA921", "Badminton", 10);
        Room room = new Room("4DB454", roomType);
        Booking booking = new Booking(room, "WJI77R", "20240520", 10, 12, 100, "N", "BQZ543");
        String date = booking.getDate();
        assertEquals("Retrieved date should match the one set in the booking", "20240520", date);
    }

    @Test
    public void testGetBookingID() {
        RoomType roomType = new RoomType("IUM32D", "Badminton", 10);
        Room room = new Room("CY5GE8", roomType);
        Booking booking = new Booking(room, "PJ9O8U", "20240520", 10, 12, 100, "N", "GT70RN");
        String bookingID = booking.getBookingID();
        assertEquals("Retrieved booking ID should match the one set in the booking", "GT70RN", bookingID);
    }

    @Test
    public void testGetStartTime() {
        RoomType roomType = new RoomType("LKM6YL", "Badminton", 10);
        Room room = new Room("ISXQF5", roomType);
        Booking booking = new Booking(room, "IY9G4B", "20240520", 10, 12, 100, "N", "6NXT80");
        int startTime = booking.getStartTime();
        assertEquals("Retrieved start time should match the one set in the booking", 10, startTime);
    }

    @Test
    public void testGetEndTime() {
        RoomType roomType = new RoomType("DUE59F", "Badminton", 10);
        Room room = new Room("FBAB5Z", roomType);
        Booking booking = new Booking(room, "ANX3NH", "20240520", 10, 12, 100, "N", "U4SZ0O");
        int endTime = booking.getEndTime();
        assertEquals("Retrieved end time should match the one set in the booking", 12, endTime);
    }

    @Test
    public void testGetPricePaid() {
        RoomType roomType = new RoomType("W55SZB", "Badminton", 10);
        Room room = new Room("101", roomType);
        Booking booking = new Booking(room, "GMY5K5", "20240520", 10, 12, 100, "N", "G5QAXP");
        int pricePaid = booking.getPricePaid();
        assertEquals("Retrieved price paid should match the one set in the booking", 100, pricePaid);
    }
    
    
    @Test
    public void testCancelBookingByUser() {
        RoomType roomType = new RoomType("SRD0K2", "Badminton", 10);
        Room room = new Room("GIID6A", roomType);
        Booking booking = new Booking(room, "K6X7L5", "20240520", 10, 12, 100, "N", "4TMJ3R");
        assertFalse("Booking should not be cancelled initially", booking.getIsCancelled());
        assertEquals("Price paid should be 100 initially", 100, booking.getPricePaid());

        booking.cancelBookingByUser();
        assertTrue("Booking should be cancelled after cancelBookingByUser", booking.getIsCancelled());
        assertEquals("Price paid should be halved after cancelBookingByUser", 50, booking.getPricePaid());
    }

    @Test
    public void testCancelBookingByClosingDate() {
        RoomType roomType = new RoomType("LMM6WJ", "Badminton", 10);
        Room room = new Room("64XAOY", roomType);
        Booking booking = new Booking(room, "VTN0IG", "20240520", 10, 12, 100, "N", "BK001CKPQH0");

        assertFalse("Booking should not be cancelled initially", booking.getIsCancelled());
        assertEquals("Price paid should be 100 initially", 100, booking.getPricePaid());

        booking.cancelBookingByClosingDate();
        assertTrue("Booking should be cancelled after cancelBookingByClosingDate", booking.getIsCancelled());
        assertEquals("Price paid should be 0 after cancelBookingByClosingDate", 0, booking.getPricePaid());
    }

    @Test
    public void testToString() {
        RoomType roomType = new RoomType("PQTM1X", "Badminton", 10);
        Room room = new Room("K07SRW", roomType);
        Booking booking = new Booking(room, "4CVSX8", "240520", 10, 12, 100, "N", "9U2MLP");

        String bookingString = booking.toString();
        assertTrue("String should contain room ID", bookingString.contains(room.getRoomID()));
        assertTrue("String should contain user ID", bookingString.contains("4CVSX8"));
        assertTrue("String should contain date", bookingString.contains("240520"));
        assertTrue("String should contain start time", bookingString.contains("10"));
        assertTrue("String should contain end time", bookingString.contains("12"));
        assertTrue("String should contain price paid", bookingString.contains("100"));
        assertTrue("String should contain is cancelled", bookingString.contains("N"));
        assertTrue("String should contain booking ID", bookingString.contains("9U2MLP"));
    }
    
	@Test
	public void testViewUserBookingString() {
		Room room = new Room("4YBIH1", new RoomType("AWV0BA", "Badminton", 10));
		Booking booking = new Booking(room, "IAPR6U", "240520", 10, 12, 100, "N", "1JCV92");
		String result = booking.viewUserBookingString();
	    String expected = "Booking ID: 1JCV92 Room ID: 4YBIH1 Room Type: Badminton Date: 20-May-2024 Start Time: 10 End Time: 12 Price Paid: $100";
	    Assert.assertEquals(expected, result);
	}
	 @Test
	    public void testViewRoomBookingString() {
	        Room room = new Room("68WY6L", new RoomType("2L9OZ6", "Badminton", 10));
	        Booking booking = new Booking(room, "22NVBG", "240520", 10, 12, 100, "N", "QN091M");
	        String result = booking.viewRoomBookingString();
	        String expected = "Booking ID: QN091M User ID: 22NVBG Date: 20-May-2024 Start Time: 10 End Time: 12";
	        Assert.assertEquals(expected, result);
	    }
	@Test
    public void testGetBookingByID_Null() {
        Room room = new Room("R0Q79J", new RoomType("BKIN3R", "Badminton", 10));
        Booking booking = new Booking(room, "PO7QTP", "20240520", 10, 12, 100, "N", "SEQ4S8");
        ArrayList<Booking> allBookings = new ArrayList<>();
        allBookings.add(booking);
        Booking retrievedBooking = Booking.getBookingByID(allBookings, "SEQ4S");
        Assert.assertNull(retrievedBooking);
    }
	@Test
	public void testGetBookingByID_Valid() {
	    Room room = new Room("LC4T4K", new RoomType("7LW9FP", "Badminton", 10));
	    Booking booking = new Booking(room, "956L1A", "20240520", 10, 12, 100, "N", "35Z4Q8");
	    ArrayList<Booking> allBookings = new ArrayList<>();
	    allBookings.add(booking);
	    Booking retrievedBooking = Booking.getBookingByID(allBookings, "35Z4Q8");
	    Assert.assertEquals(booking, retrievedBooking);
	}

    @Test
    public void testGetBookingsOfSpecificDate() {
        RoomType roomType = new RoomType("P53OCM", "Badminton", 10);
        Room room = new Room("3EQYGO", roomType);
        Booking booking1 = new Booking(room, "1H2CFW", "20240520", 10, 12, 100, "N", "OV782T");
        Booking booking2 = new Booking(room, "O9T4PN", "20240520", 13, 15, 150, "N", "PQFC6S");
        Booking booking3 = new Booking(room, "WZ7CAT", "20240521", 14, 16, 200, "N", "30D6L4");
        Booking booking4 = new Booking(room, "N2NR13", "20240520", 17, 19, 250, "Y", "4SVAWE");
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
        Room room = new Room("8TM9K9", new RoomType("0CIXZ7", "Badminton", 10));
        Booking booking1 = new Booking(room, "0NZ6QD", "20240519", 10, 12, 100, "N", "V8RHF8");
        Booking booking2 = new Booking(room, "4C4PEB", "20240520", 10, 12, 100, "N", "YP28KA");

        Assert.assertEquals(booking1.compareTo(booking2) ,-1);
    }

    @Test
    public void testCompareTo_AfterDate() {
        Room room = new Room("D9EX5Q", new RoomType("8WX8Q7", "Badminton", 10));
        Booking booking1 = new Booking(room, "G8IFCG", "20240520", 10, 12, 100, "N", "UKP7NZ");
        Booking booking2 = new Booking(room, "0O3MXU", "20240519", 10, 12, 100, "N", "5E7C4N");

        Assert.assertEquals(booking1.compareTo(booking2) ,1);
    }

    @Test
    public void testCompareTo_SameDate_BeforeTime() {
        Room room = new Room("X747KC", new RoomType("7Z317H", "Badminton", 10));
        Booking booking1 = new Booking(room, "G8KDVC", "20240520", 9, 12, 100, "N", "QUE66N");
        Booking booking2 = new Booking(room, "DYW51U", "20240520", 10, 12, 100, "N", "PM8M70");

        Assert.assertEquals(booking1.compareTo(booking2) ,-1);
    }

    @Test
    public void testCompareTo_SameDate_AfterTime() {
        Room room = new Room("J750TW", new RoomType("5V417J", "Badminton", 10));
        Booking booking1 = new Booking(room, "F5X01T", "20240520", 10, 12, 100, "N", "VJBZ0K");
        Booking booking2 = new Booking(room, "E42J8N", "20240520", 9, 12, 100, "N", "PFNG1O");

        Assert.assertEquals(booking1.compareTo(booking2) ,1);
    }

    @Test
    public void testCompareTo_SameDate_SameTime_BeforeEndTime() {
        Room room = new Room("R9TFI6", new RoomType("WD5OT3", "Badminton", 10));
        Booking booking1 = new Booking(room, "2IEO72", "20240520", 10, 11, 100, "N", "ZF0YL7");
        Booking booking2 = new Booking(room, "8PKL52", "20240520", 10, 12, 100, "N", "KSJ55U");

        Assert.assertEquals(booking1.compareTo(booking2) ,-1);
    }

    @Test
    public void testCompareTo_SameDate_SameTime_AfterEndTime() {
        Room room = new Room("MSJK9D", new RoomType("YBI7EO", "Badminton", 10));
        Booking booking1 = new Booking(room, "0V9KYU", "20240520", 10, 12, 100, "N", "U0I4HB");
        Booking booking2 = new Booking(room, "284QP3", "20240520", 10, 11, 100, "N", "TV2KX9");

        Assert.assertEquals(booking1.compareTo(booking2) ,1);
    }

    @Test
    public void testCompareTo_Equal() {
        Room room = new Room("FECRK6", new RoomType("W0NRFF", "Badminton", 10));
        Booking booking1 = new Booking(room, "YN08LJ", "20240520", 10, 12, 100, "N", "WD2XO6");
        Booking booking2 = new Booking(room, "I90AA7", "20240520", 10, 12, 100, "N", "SG0ZZX");

        Assert.assertEquals(0, booking1.compareTo(booking2));
    }
    
    @Test
    public void testPrintDetail() {
        RoomType roomType = new RoomType("N8T4V3", "Badminton", 10);
        Room room = new Room("W58SZE", roomType);
        
        Booking booking = new Booking(room, "ZJQ6LD", "240520", 10, 12, 100, "N", "C3W9CG");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        booking.printDetail();
        System.setOut(originalOut);

        String output = outContent.toString();
        assertTrue("Should contain booking ID", output.contains("Booking ID: C3W9CG"));
        assertTrue("Should contain user ID", output.contains("User ID: ZJQ6LD"));
        assertTrue("Should contain room type", output.contains("Room Type: Badminton"));
        assertTrue("Should contain room ID", output.contains("Room ID: W58SZE"));
        assertTrue("Should contain formatted date", output.contains("Date: 20-May-2024"));
        assertTrue("Should contain time", output.contains("Time: 10 - 12"));
    }
}