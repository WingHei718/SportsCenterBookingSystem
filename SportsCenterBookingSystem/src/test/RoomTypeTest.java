package test;

import execute.RoomType;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

public class RoomTypeTest  extends TestCase{

    @Test
    public void testRoomTypeConstructorAndGetters() {
        String typeID = "001";
        String type = "Badminton";
        int price = 800;
        RoomType roomType = new RoomType(typeID, type, price);
        assertEquals("TypeID should match", typeID, roomType.getTypeID());
        assertEquals("Type should match", type, roomType.getType());
        assertEquals("Price should match", price, roomType.getPrice());
    }

    @Test
    public void testSetPrice() {
        String typeID = "001";
        String type = "Badminton";
        int initialPrice = 800;
        int newPrice = 850;
        RoomType roomType = new RoomType(typeID, type, initialPrice);
        roomType.setPrice(newPrice);
        assertEquals("Price should be updated", newPrice, roomType.getPrice());
    }

    @Test
    public void testGetRoomTypeByID_Exists() {
        ArrayList<RoomType> allRoomTypes = new ArrayList<>();
        RoomType roomType1 = new RoomType("001", "Badminton", 800);
        RoomType roomType2 = new RoomType("002", "Tennis", 1000);
        allRoomTypes.add(roomType1);
        allRoomTypes.add(roomType2);
        RoomType foundRoomType = RoomType.getRoomTypeByID(allRoomTypes, "001");
        assertNotNull("RoomType should be found", foundRoomType);
        assertEquals("Found RoomType should match", "001", foundRoomType.getTypeID());
    }

    @Test
    public void testGetRoomTypeByID_NotExists() {
        ArrayList<RoomType> allRoomTypes = new ArrayList<>();
        RoomType roomType1 = new RoomType("001", "Badminton", 800);
        allRoomTypes.add(roomType1);
        RoomType foundRoomType = RoomType.getRoomTypeByID(allRoomTypes, "002");
        assertNull("RoomType should not be found", foundRoomType);
    }

    @Test
    public void testPrintAllRoomTypeString() {
        String typeID = "001";
        String type = "Badminton";
        int price = 800;
        RoomType roomType = new RoomType(typeID, type, price);
        String roomTypeString = roomType.printAllRoomTypeString();

        String expectedString = "ID: " + typeID + " Name: " + type + " Price: $" + price + "/hr";
        assertEquals("String representation should match", expectedString, roomTypeString);
    }

    @Test
    public void testToString() {
        String typeID = "001";
        String type = "Badminton";
        int price = 800;
        RoomType roomType = new RoomType(typeID, type, price);
        String roomTypeString = roomType.toString();
        String expectedString = typeID + " " + type + " " + price;
        assertEquals("String representation should match", expectedString, roomTypeString);
    }
}