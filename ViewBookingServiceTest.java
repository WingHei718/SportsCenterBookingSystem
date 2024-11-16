package test;

import execute.ViewBookingService;
import execute.Booking;
import execute.Common;
import execute.Room;
import execute.RoomType;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;
import org.junit.Test;
import static org.junit.Assert.*;

public class ViewBookingServiceTest {

    @Test
    public void testViewBooking_p() {
        ArrayList<Booking> allBookings = new ArrayList<>();
        Room room1 = new Room("101", new RoomType("001", "Badminton", 10));
        Booking booking1 = new Booking(room1, "001", "20240520", 10, 12, 100, "X", "BK001");
        Room room2 = new Room("102", new RoomType("001", "Badminton", 10));
        Booking booking2 = new Booking(room2, "002", "20240521", 10, 12, 100, "X", "BK002");
        booking2.cancelBookingByUser();
        allBookings.add(booking1);
        allBookings.add(booking2);

        ViewBookingService viewBookingService = new ViewBookingService();
        String inputString = "p\nq\n";
        Scanner scanner = new Scanner(inputString);

        viewBookingService.viewBooking(allBookings, scanner);
        
        scanner.close();
    }

    @Test
    public void testViewBooking_n() {
    	 ArrayList<Booking> allBookings = new ArrayList<>();
         Room room1 = new Room("101", new RoomType("001", "Badminton", 10));
         Booking booking1 = new Booking(room1, "001", "20240520", 10, 12, 100, "X", "BK001");
         Room room2 = new Room("102", new RoomType("001", "Badminton", 10));
         Booking booking2 = new Booking(room2, "002", "20240521", 10, 12, 100, "X", "BK002");
         booking2.cancelBookingByUser();
         allBookings.add(booking1);
         allBookings.add(booking2);

         ViewBookingService viewBookingService = new ViewBookingService();
         String inputString = "n\nq\n";
         Scanner scanner = new Scanner(inputString);

         viewBookingService.viewBooking(allBookings, scanner);
         
         scanner.close();
     }

    @Test
    public void testViewBooking_s() {
    	 ArrayList<Booking> allBookings = new ArrayList<>();
         Room room1 = new Room("101", new RoomType("001", "Badminton", 10));
         Booking booking1 = new Booking(room1, "001", "20240520", 10, 12, 100, "X", "BK001");
         Room room2 = new Room("102", new RoomType("001", "Badminton", 10));
         Booking booking2 = new Booking(room2, "002", "20240521", 10, 12, 100, "X", "BK002");
         booking2.cancelBookingByUser();
         allBookings.add(booking1);
         allBookings.add(booking2);

         ViewBookingService viewBookingService = new ViewBookingService();
         String inputString = "s\n2024 1\nq\n";
         Scanner scanner = new Scanner(inputString);

         viewBookingService.viewBooking(allBookings, scanner);
         
         scanner.close();
     }

    @Test
    public void testViewBooking_t() {
    	 ArrayList<Booking> allBookings = new ArrayList<>();
         Room room1 = new Room("101", new RoomType("001", "Badminton", 10));
         Booking booking1 = new Booking(room1, "001", "20240520", 10, 12, 100, "X", "BK001");
         Room room2 = new Room("102", new RoomType("001", "Badminton", 10));
         Booking booking2 = new Booking(room2, "002", "20240521", 10, 12, 100, "X", "BK002");
         booking2.cancelBookingByUser();
         allBookings.add(booking1);
         allBookings.add(booking2);

         ViewBookingService viewBookingService = new ViewBookingService();
         String inputString = "t\nq\n";
         Scanner scanner = new Scanner(inputString);

         viewBookingService.viewBooking(allBookings, scanner);
         
         scanner.close();
     }
    @Test
    public void testViewBooking_q() {
    	 ArrayList<Booking> allBookings = new ArrayList<>();
         Room room1 = new Room("101", new RoomType("001", "Badminton", 10));
         Booking booking1 = new Booking(room1, "001", "20240520", 10, 12, 100, "X", "BK001");
         Room room2 = new Room("102", new RoomType("001", "Badminton", 10));
         Booking booking2 = new Booking(room2, "002", "20240521", 10, 12, 100, "X", "BK002");
         booking2.cancelBookingByUser();
         allBookings.add(booking1);
         allBookings.add(booking2);

         ViewBookingService viewBookingService = new ViewBookingService();
         String inputString = "q\n";
         Scanner scanner = new Scanner(inputString);

         viewBookingService.viewBooking(allBookings, scanner);
         
         scanner.close();
     }
    @Test
    public void testViewBookingMultipleCommands() {
        ArrayList<Booking> allBookings = new ArrayList<>();
        ViewBookingService viewBookingService = new ViewBookingService();
        String inputString = "p\nn\ns\n2023 12\nq\n"; // 先前一个月，然后下一个月，然后指定年份和月份
        Scanner scanner = new Scanner(inputString);
        viewBookingService.viewBooking(allBookings, scanner);
        scanner.close();
    }
    @Test
    public void testViewBookingInvalidInput() {
        ArrayList<Booking> allBookings = new ArrayList<>();
        ViewBookingService viewBookingService = new ViewBookingService();
        String inputString = "s\n2024 13\n2025 12\nq\n"; // 13是无效的月份
        Scanner scanner = new Scanner(inputString);
        viewBookingService.viewBooking(allBookings, scanner);
        scanner.close();
    }
 

}