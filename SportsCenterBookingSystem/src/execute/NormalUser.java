package execute;

import java.util.Scanner;

public class NormalUser implements UserRole {
	
	@Override
	public String showActionMenu(Scanner scanner) {

		String action;
		System.out.println("Please input your action ([m] for make booking, [v] for view booking, [c] for cancel booking, [l] for logout):");
		String[] validCommand= {"m","v","c","l"};
		action = Common.getValidInput(scanner, validCommand, Common.InputType.COMMAND);
		
		return action;
	}
	
	@Override
    public boolean makeBooking(Scanner scanner) {
		User currentUser = UserSessionManager.getInstance().getCurrentUser();

		return currentUser.makeUserBooking(scanner);
		

    }

    @Override
    public void viewBooking(Scanner scanner) {
    	User currentUser = UserSessionManager.getInstance().getCurrentUser();

    	currentUser.viewUserBookingCalendar(scanner);
    }

    @Override
    public boolean cancelBooking(Scanner scanner) {
    	User currentUser = UserSessionManager.getInstance().getCurrentUser();

    	currentUser.cancelUserBooking(scanner);
    	
        return false;
    }
    
	@Override
	public String toString(String userID, String userPassword) {
		return userID + " N " + userPassword;
	}
	
}