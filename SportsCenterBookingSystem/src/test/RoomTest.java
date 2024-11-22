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

public class RoomTest extends TestCase{

    @Test
    public void testToString() {
        RoomType roomType = new RoomType("1", "badminton", 40);
        Room room = new Room("1", roomType);
        String roomString = room.toString();
    }

    @Test
    public void testGetRoomByID() {
        ArrayList<Room> allRooms = new ArrayList<>();
        RoomType roomType = new RoomType("1", "badminton", 40);
        Room room1 = new Room("1", roomType);
        Room room2 = new Room("2", roomType);
        allRooms.add(room1);
        allRooms.add(room2);

        Room retrievedRoom = Room.getRoomByID(allRooms, "1");
        Room retrievedRoom_null = Room.getRoomByID(allRooms, "4");
    }

    @Test
    public void testGetRoomByID_EmptyList() {
        ArrayList<Room> allRooms = new ArrayList<>();
        Room retrievedRoom = Room.getRoomByID(allRooms, "1");
        assertNull("The room should not be found in an empty list", retrievedRoom);
    }
    
    
    @Test
    public void testAddBooking_BookingListSorted() {
    	RoomType roomType = new RoomType("1", "badminton", 40);
        Room room = new Room("1", roomType);
        Booking booking1 = new Booking(room, "001", "20231201", 10, 12, 100, "N", "1");
        Booking booking2 = new Booking(room, "001", "20231201", 8, 10, 100, "N", "2");
        Booking booking3 = new Booking(room, "001", "20231201", 12, 14, 100, "N", "3");

        room.addBooking(booking1);
        room.addBooking(booking2);
        room.addBooking(booking3);
        room.getRoomType();
        ArrayList<Booking> sortedBookings = new ArrayList<>(room.getAllBookings());
        Collections.sort(sortedBookings, Comparator.comparingInt(Booking::getStartTime));
        assertEquals(sortedBookings, room.getAllBookings());
    }

    @Test
    public void testViewRoomBookingCalendar_NoBookings() {
    	RoomType roomType = new RoomType("1", "badminton", 40);
        Room room = new Room("1", roomType);
        String input = "q\n"; 
        StringReader stringReader = new StringReader(input);
        Scanner scanner = new Scanner(stringReader);
        room.viewRoomBookingCalendar(scanner);

    }
    @Test
    public void testViewRoomBookingCalendar_WithBookings() {
    	RoomType roomType = new RoomType("1", "badminton", 40);
        Room room = new Room("1", roomType);
        Booking booking = new Booking(room, "001", "20231201", 10, 12, 100, "N", "001");
        room.addBooking(booking);
        String input = "p\nq";
        StringReader stringReader = new StringReader(input);
        Scanner scanner = new Scanner(stringReader);
        room.viewRoomBookingCalendar(scanner);
        scanner.close();
    }
    
    @Test
    public void testGetRoomID() {
        RoomType roomType = new RoomType("1", "badminton", 40);
        Room room = new Room("1", roomType);
        String roomString = room.getRoomID();
    }
}