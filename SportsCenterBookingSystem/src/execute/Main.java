package execute;

import java.util.Scanner;

public class Main {

	private static User currentUser = null;

	public static void main(String[] args) {
		System.out.println("Welcome to the Sports Centre Booking System!");
		SportsCenter sportsCenter = SportsCenter.getInstance();
		
        
		boolean isExit = false;
		Scanner scanner = new Scanner(System.in);
		String action;
        
        while (!isExit) {
        	
        	try {
        		
        		if(currentUser==null) {
        			System.out.println("Please input your action ([r] for Register, [l] for login, [e] for exit system) :");
        			action = scanner.nextLine();
        			
        			switch(action) {
	        			case "r":
	        				(new CmdRegisterAccount()).execute();
	        				break;
	        				
	        			case "l":
	        				(new CmdLogin()).execute();
	        				break;
	        				
	        			case "e":
	        				isExit = true;
	        				System.out.println("System exit.");
	        				break;
	        				
	        			default:
	        				throw new ExInvalidCommand();
        			}
        			
        		}
        		else {
        			action = currentUser.showActionMenu();
        			
        	        switch (action){
	        	        case "m":
	        	        	(new CmdMakeBooking()).execute();
	        	            break;
	                
	        	        case "v":
	        	        	(new CmdViewBooking()).execute();
	        	            break;
	        	
	        	        case "c":
	        	            (new CmdCancelBooking()).execute();
	        	            break;
	        	            
	        	        case "l":
	        	        	(new CmdLogout()).execute();
	        	            break;
	        	            
	        	        case "d":
	        	        	(new CmdMarkClosingDate()).execute();
	        	        	break;
	        	        	
	        	        case "a":
	        	        	(new CmdAddNewRoom()).execute();
	        	        	break;
	        	        
	        	        case "p":
	        	        	(new CmdModifyRoomTypePrice()).execute();
	        	        	break;
	        	            
	        			default:
	        				throw new ExInvalidCommand();
        	        }
        	        
        		}
        	
        	}
        	catch(ExInvalidCommand e) {System.out.println("Invalid command!");}

        }
        
        scanner.close();
        
	}
	
	public static User getCurrentUser() {
		return currentUser;
	}

	public static void setCurrentUser(User user) {
		currentUser = user;
	}

}
