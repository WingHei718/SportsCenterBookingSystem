package execute;

import java.util.ArrayList;

public class Room {
	private String roomID;
	private RoomType roomType;
	private ArrayList<Booking> allBookings;
	
	public Room(String roomID, RoomType roomType) {
		this.roomID = roomID;
		this.roomType=roomType;
		this.allBookings = new ArrayList<>();
	}
	
	public String getRoomID() {
		return this.roomID;
	}

	public RoomType getRoomType(){
		return roomType;
	}
	
	public ArrayList<Booking> getAllBookings() {
		return allBookings;
	}
	
	public static Room getRoomByID(ArrayList<Room> allRooms, String roomID) {
		for(Room r: allRooms){
			if(r.roomID.equals(roomID)){return r;}
		}
		return null;
	}
	
	public void addBooking(Booking booking) {
		allBookings.add(booking);
	}

	public void removeBooking(Booking booking) {
		allBookings.remove(booking);
	}

	public String toString(){
		//roomID roomTypeID
		return roomID + " " + roomType.getType();
	}

	public void viewRoomBooking() {
		if (allBookings.size()>0) {
			System.out.println("The followings are all the booking:");
			for (Booking b: allBookings) {
				System.out.println(b.viewRoomBookingString());
			}
		} else {
			System.out.println("No booking records.");
		}
	}
}
