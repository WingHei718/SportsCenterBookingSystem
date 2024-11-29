package execute;


import java.util.Scanner;

public class Admin implements UserRole {
	
	@Override
	public String showActionMenu(Scanner scanner) {

		String action="";
		System.out.println("Please input your action ([m] for make booking, [v] for view booking, [c] for cancel booking, [l] for logout, [d] for mark closing date of sports center, [a] for add room, [p] for modify room type price):");
		
		String[] validCommand= {"m","v","c","l","d","a","p"};
		action = Common.getValidInput(scanner, validCommand, Common.InputType.COMMAND);

		
		return action;
	}
	
	@Override
    public boolean makeBooking(Scanner scanner) {
		SportsCenter sportsCenter = SportsCenter.getInstance();
    	//Scanner scanner = new Scanner(System.in);
    	
    	System.out.println("Please input the User ID you would like to make booking for:");
    	String userID = scanner.nextLine().trim();
    	User user = sportsCenter.getUserByID(userID);
    	while (user == null) {
			System.out.println("User ID not found, please input again:");
			userID = scanner.nextLine().trim();
			user = sportsCenter.getUserByID(userID);
		};
		
		return user.makeUserBooking(scanner);
		

    }
	
	@Override
	public void viewBooking(Scanner scanner) {
		SportsCenter sportsCenter = SportsCenter.getInstance();
		//Scanner scanner = new Scanner(System.in);
		String action;
		
		System.out.println("Please input your action ([a] for view all user booking, [u] for view specific user booking, [r] for view specific room booking):");
		String[] validCommand= {"a","u","r"};
		action = Common.getValidInput(scanner, validCommand, Common.InputType.COMMAND);
		
		switch (action) {
			case "a":
					ViewBookingService viewBookingService = new ViewBookingService();
					viewBookingService.viewBooking(sportsCenter.getAllBookings(), scanner);
					break;
			case "u":
				System.out.println("Please input the User ID you would like to view booking for:");
				String userID = scanner.nextLine().trim();
				User user = sportsCenter.getUserByID(userID);

		    	while (user == null) {
					System.out.println("User ID not found, please input again:");
					userID = scanner.nextLine().trim();
					user = sportsCenter.getUserByID(userID);
				};
				
				user.viewUserBookingCalendar(scanner);
				
				break;
				
			case "r":
				System.out.println("Please input the Room ID you would like to view booking for:");
				String roomID = scanner.nextLine().trim();
				Room room = sportsCenter.getRoomByID(roomID);

				while (room == null) {
					System.out.println("Room ID not found, please input again:");
					roomID = scanner.nextLine().trim();
					room = sportsCenter.getRoomByID(roomID);
				}
				
				room.viewRoomBookingCalendar(scanner);
				
			    break;
			    
		
		}
        
    }

    @Override
    public boolean cancelBooking(Scanner scanner) {
    	SportsCenter sportsCenter = SportsCenter.getInstance();
    	//Scanner scanner = new Scanner(System.in);
    	
    	System.out.println("Please input the User ID you would like to cancel booking for:");
    	String userID = scanner.nextLine().trim();
    	User user = sportsCenter.getUserByID(userID);
    	
		while (user == null) {
			System.out.println("User ID not found, please input again:");
			userID = scanner.nextLine().trim();
			user = sportsCenter.getUserByID(userID);
		};
		
		user.cancelUserBooking(scanner);
		
        return false;
    }

	@Override
	public String toString(String userID, String userPassword) {
		return userID + " A " + userPassword;
	}

}
