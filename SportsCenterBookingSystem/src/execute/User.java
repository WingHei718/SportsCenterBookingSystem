package execute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class User {
	private String userID;
	private UserRole userRole;
    private String userPassword;
    private ArrayList<Booking> allBookings;

    public User (String userID, String userRole, String userPassword){
        this.userID = userID;
        this.userRole = UserRole.getUserRoleByChar(userRole);
        this.userPassword = userPassword;
        this.allBookings = new ArrayList<>();
    }

    public String getUserID() {
        return this.userID;
    }

    public String getUserPasword(){
        return this.userPassword;
    }
    
	public ArrayList<Booking> getAllBookings() {
		return allBookings;
	}
    
	public String toString() {
		//String save to txt file
		return userRole.toString(userID, userPassword);
	}
    
	public static User getUserByID (ArrayList<User> allUsers, String userID){
        for (User u: allUsers){
            if(u.userID.equals(userID)){return u;}
        }
        return null;
    }

	public String showActionMenu(){
        String action = userRole.showActionMenu();
        return action;
		
	}
	
	public void makeBooking() {
		userRole.makeBooking();
	}
	
	public void viewBooking(){
		userRole.viewBooking();
    }

	public void cancelBooking() {
		userRole.cancelBooking();
	}
	
	public void addBooking(Booking booking) {
		allBookings.add(booking);
		Collections.sort(allBookings);
	}
	
	public void makeUserBooking() {
		SportsCenter sportsCenter = SportsCenter.getInstance();
    	Scanner scanner = new Scanner(System.in);
    	
    	//sportsCenter.printAllClosingDate();
    	
		int roomTypeCount = sportsCenter.printAllRoomType();
		
		if (roomTypeCount>0) {
			
			System.out.println("Please input the Room Type ID you would like to book:");
			String roomTypeID = scanner.nextLine();
			RoomType roomType = sportsCenter.getRoomTypeByID(roomTypeID);
			while (roomType == null) {
				System.out.println("Room Type ID not found, please input again:");
				roomTypeID = scanner.nextLine();
				roomType = sportsCenter.getRoomTypeByID(roomTypeID);
			}
			
			System.out.println("Please input the Date and Time you would like to book (format: yyMMdd HH-HH (e.g.241001 15-20)):");
			//TODO: handle wrong input format (e.g. only input date)
			String dateAndTime = scanner.nextLine();
			while (!DateAndTime.isDateAndTimeValid(dateAndTime)) {
				System.out.println("Invalid Date or Time, please input again:");
				dateAndTime = scanner.nextLine();
			}
			String[] splittedDateAndTime = dateAndTime.split(" ");
			String date = splittedDateAndTime[0];
			while (sportsCenter.isClosingDate(date)) {
				System.out.printf("Sorry, the sports center will be closed on %s, please input again:\n", date);
				dateAndTime = scanner.nextLine();
				splittedDateAndTime = dateAndTime.split(" ");
				date = splittedDateAndTime[0];
			}
			String time = splittedDateAndTime[1];
			String[] splittedTime = time.split("-");
			int startTime = Integer.parseInt(splittedTime[0]);
			int endTime = Integer.parseInt(splittedTime[1]);
			
			//TODO: checkAvailability
			Room room = sportsCenter.checkAvailability(roomType, date, startTime, endTime);
			
			if (room != null) {
				int bookingPrice = roomType.getPrice()*DateAndTime.calculateHours(startTime, endTime);
				System.out.printf("Room available (Price: $%d), are you confirmed to book and pay? (Y/N):\n", bookingPrice);
				String action = scanner.nextLine();
				while (!action.equals("Y") && !action.equals("N")) {
	        		System.out.println("Invalid command, please input again:");
	        		action = scanner.nextLine();
	        	}
				switch(action) {
					case "y":
						System.out.println("Payment collected. Booking Success.");
						int nextBookingID = sportsCenter.getNextBookingID();
						Booking booking = new Booking(room, this.userID, date, startTime, endTime, bookingPrice, "N", String.valueOf(nextBookingID));
						this.addBooking(booking);
						room.addBooking(booking);
						sportsCenter.addBooking(booking);
						break;
						
					case "N":
						break;
				}
				
			} else {
				System.out.println("Sorry, the room is not available at the time you want. Would you like to book another time or room? (Y/N):");
				String action = scanner.nextLine();
				while (!action.equals("Y") && !action.equals("N")) {
	        		System.out.println("Invalid command, please input again:");
	        		action = scanner.nextLine();
	        	}
				switch(action) {
	                case "Y":
	                    makeBooking();
	                    break;
	                    
	                case "N":
	                    break;
				}
			}
		}
		
	}

	public int viewUserBooking() {
		int count = 0;
		for (Booking b: allBookings) {
			if (!b.getIsCancelled()){
				if (count==0) {
					System.out.println("The followings are all the booking:");
				}
				System.out.println(b.viewUserBookingString());
				count++;
			}
		}
		if (count==0) {
			System.out.println("No booking records.");
		}
		return count;
		
	}

	public void cancelUserBooking() {
    	Scanner scanner = new Scanner(System.in);
    	
    	int bookingCount = this.viewUserBooking();
    	
    	if (bookingCount>0) {
    		
    		System.out.println("Please input the Booking ID you would like to cancel:");
        	String bookingID = scanner.nextLine();
        	Booking booking = this.getNotCancelledBookingByID(bookingID);
        	while (booking == null) {
    			System.out.println("Booking ID not found, please input again:");
    			bookingID = scanner.nextLine();
    			booking = this.getNotCancelledBookingByID(bookingID);
    		}
        	
        	int refund = booking.getPricePaid()/2;
        	System.out.printf("Refund for cancelled booking: $%d, are you confirmed to cancel booking? (Y/N):\n", refund);
        	String action = scanner.nextLine();
        	while (!action.equals("y") && !action.equals("N")) {
        		System.out.println("Invalid command, please input again:");
        		action = scanner.nextLine();
        	}
        	switch(action) {
        		case "y":
        			booking.cancelBookingByUser();
        			System.out.println("Booking cancelled.");
        			break;
        			
        		case "N":
        			break;
        	}
    	}
		
	}

	private Booking getNotCancelledBookingByID(String bookingID) {
		for(Booking b: allBookings){
			if (!b.getIsCancelled()) {
				if(b.getBookingID().equals(bookingID)){return b;}
			}			
		}
		return null;
	}

	

 
}