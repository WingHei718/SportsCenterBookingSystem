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
	
	public void addBooking(Booking booking) {
//		if (getBookingsOfDay(booking.getDate()) == null) {
//			BookingsForDay bookingsForDay = new BookingsForDay(booking.getDate());
//			bookingsForDay.addBooking(booking);
//			this.allbookingsforday.add(bookingsForDay);
//		} else {
//			getBookingsOfDay(booking.getDate()).addBooking(booking);
//		}
	}
	
//	public boolean removeBooking(String bookingID) { 
//		//true if success, otherwise fail
//		return BookingsForDay.removeBookingById(allbookingsforday,bookingID);
//	}



//	public BookingsForDay getBookingsOfDay(String date) {
//		return BookingsForDay.getBookingsForDayByDate(allbookingsforday,date);
//	}

	public static Room getRoomByID(ArrayList<Room> allRooms, String roomID) {
		for(Room r: allRooms){
			if(r.roomID.equals(roomID)){
				return r;
			}
		}
		return null;
	}

	


	public String toString(){
		//roomID roomTypeID
		return roomID+" "+roomType.getType();
	}
	
}
