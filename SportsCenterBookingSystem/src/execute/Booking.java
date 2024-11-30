package execute;

import java.util.ArrayList;

public class Booking implements Comparable<Booking> {
	private Room room;
	private String userID;
	private String date;
	private int startTime;
	private int endTime;
	private int pricePaid;
	private String isCancelled;
	private String bookingID;
	
	public Booking(Room room, String userID, String date, int startTime, int endTime, int pricePaid, String isCancelled, String bookingID) {
        this.room = room;
        this.userID = userID;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.pricePaid = pricePaid;
        this.isCancelled = isCancelled;
        this.bookingID = bookingID;
    }
	
	public Room getRoom() {
		return this.room;
	}
	
	public String getUserID() {
		return this.userID;
	}
	
	public String getDate() {
		return this.date;
	}
	
	public String getBookingID() {
		return this.bookingID;
	}
	
	public int getStartTime() {
		return this.startTime;
	}
	
	public int getEndTime() {
		return this.endTime;
	}
	
	public int getPricePaid() {
		return this.pricePaid;
		
	}
	
	public boolean getIsCancelled() {
		if (isCancelled.equals("Y")) {
			return true;
		} else if (isCancelled.equals("N")){
			return false;
		}
		return false;
	}
	
	public void setRoom(Room room) {
		this.room = room;
	}
	
//	public void setUserID(String UserID) {
//		this.UserID = UserID;
//	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
//	public void setBookingID(String bookingID) {
//		this.bookingID = bookingID;
//	}
	
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}
	
	public void cancelBookingByUser() {
		this.isCancelled = "Y";
		this.pricePaid = pricePaid/2;
	}
	
	public void cancelBookingByClosingDate() {
		this.isCancelled = "Y";
		this.pricePaid = 0;
	}
	
	public String toString(){
		//String saved to txt file
		//RoomID UserID YYMMDD StartingTime EndingTime bookingID
        return room.getRoomID() + " " + userID + " " +  date + " " + startTime + " " + endTime + " " + pricePaid + " " + isCancelled + " " + bookingID;
    }
	
	public String viewUserBookingString() {
		SportsCenter sportsCenter = SportsCenter.getInstance();
		RoomType roomType = room.getRoomType();
		return "Booking ID: " + bookingID + " Room ID: " + room.getRoomID() + " Room Type: " + roomType.getType() + " Date: " + date + " Start Time: " + startTime + " End Time: " + endTime + " Price Paid: $" + pricePaid;
	}
	
	public String viewRoomBookingString() {
		return "Booking ID: " + bookingID + " User ID: " + userID + " Date: " + date + " Start Time: " + startTime + " End Time: " + endTime;
	}



	public static Booking getBookingByID(ArrayList<Booking> allBookings, String bookingID){
		for(Booking b: allBookings){
			if(b.bookingID.equals(bookingID)){return b;}
		}
		return null;
	}

	public static ArrayList<Booking> getBookingsOfSpecificDate(ArrayList<Booking> allBookings, String date) {
		ArrayList<Booking> result = new ArrayList<>();
		for (Booking b: allBookings) {
			if (b.date.equals(date)) {
				result.add(b);
			}
		}
		return result;
	}

	@Override
	public int compareTo(Booking another) {
		if (Integer.parseInt(this.date)<Integer.parseInt(another.date)) {
			return -1;
			
		} else if (Integer.parseInt(this.date)>Integer.parseInt(another.date)) {
			return 1;
			
		} else {
			if (this.startTime<another.startTime) {
				return -1;
				
			} else if (this.startTime>another.startTime) {
				return 1;
				
			} else {
				if (this.endTime<another.endTime) {
					return -1;
				} else if (this.endTime>another.endTime) {
					return 1;
				} else {
					return 0;
				}
			}
		}
	}

	

	

	
    
}
