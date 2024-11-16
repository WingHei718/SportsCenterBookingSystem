package test;

import execute.Room;
import execute.RoomType;
import execute.User;
import execute.Booking;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import org.junit.Test;
import static org.junit.Assert.*;

public class RoomTest {

    @Test
    public void testToString() {
        RoomType roomType = new RoomType("001", "Badminton", 800);
        Room room = new Room("101", roomType);
        String roomString = room.toString();
        assertEquals("toString should return roomID and roomTypeID", "101 001", roomString);
    }

    @Test
    public void testGetRoomByID_RoomExists() {
        ArrayList<Room> allRooms = new ArrayList<>();
        RoomType roomType = new RoomType("001", "Badminton", 800);
        Room room1 = new Room("101", roomType);
        Room room2 = new Room("102", roomType);
        allRooms.add(room1);
        allRooms.add(room2);

        Room retrievedRoom = Room.getRoomByID(allRooms, "101");

        assertEquals("Room ID should match", "101", room1.getRoomID());
        assertEquals("Room type should match", "001", room1.getRoomType().getTypeID());
        assertTrue("All bookings should be empty initially", room1.getAllBookings().isEmpty());
        assertNotNull("The room should be found", retrievedRoom);
        assertEquals("The retrieved room ID should match", "101", retrievedRoom.getRoomID());
    }

    @Test
    public void testGetRoomByID_RoomDoesNotExist() {
        ArrayList<Room> allRooms = new ArrayList<>();
        RoomType roomType = new RoomType("001", "Badminton", 800);
        Room room1 = new Room("101", roomType);
        allRooms.add(room1);
        Room retrievedRoom = Room.getRoomByID(allRooms, "102");

        assertNull("The room should not be found", retrievedRoom);
    }

    @Test
    public void testGetRoomByID_EmptyList() {
        ArrayList<Room> allRooms = new ArrayList<>();
        Room retrievedRoom = Room.getRoomByID(allRooms, "101");
        assertNull("The room should not be found in an empty list", retrievedRoom);
    }
    
    
    @Test
    public void testAddBooking_BookingListSorted() {
        RoomType roomType = new RoomType("001", "Badminton", 800);
        Room room = new Room("101", roomType);
        User user = new User("001", "N", "password");
        RoomType roomType1 = new RoomType("001", "Badminton", 800);
        Room room1 = new Room("101", roomType1);

        Booking booking1 = new Booking(room1, "001", "20231201", 10, 12, 100, "N", "001");
        Booking booking2 = new Booking(room1, "001", "20231201", 8, 10, 100, "N", "002");
        Booking booking3 = new Booking(room1, "001", "20231201", 12, 14, 100, "N", "003");

        room.addBooking(booking1);
        room.addBooking(booking2);
        room.addBooking(booking3);

        ArrayList<Booking> sortedBookings = new ArrayList<>(room.getAllBookings());
        Collections.sort(sortedBookings, Comparator.comparingInt(Booking::getStartTime));
        assertEquals("Bookings should be sorted by start time", sortedBookings, room.getAllBookings());
    }

    @Test
    public void testViewRoomBookingCalendar_NoBookings() {
        RoomType roomType = new RoomType("001", "Badminton", 800);
        Room room = new Room("101", roomType);
        String input = "q"; 
        StringReader stringReader = new StringReader(input);
        Scanner scanner = new Scanner(stringReader);
        room.viewRoomBookingCalendar(scanner);

    }
    @Test
    public void testViewRoomBookingCalendar_WithBookings() {
        RoomType roomType = new RoomType("001", "Badminton", 800);
        Room room = new Room("101", roomType);
        User user = new User("001", "N", "password");
        RoomType roomType1 = new RoomType("001", "Badminton", 800);
        Room room1 = new Room("101", roomType1);
        Booking booking = new Booking(room1, "001", "20231201", 10, 12, 100, "N", "001");
        room.addBooking(booking);
        String input = "p\nq";
        StringReader stringReader = new StringReader(input);
        Scanner scanner = new Scanner(stringReader);
        room.viewRoomBookingCalendar(scanner);

        scanner.close();
    }
}