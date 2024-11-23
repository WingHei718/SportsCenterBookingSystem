package execute;

import java.util.Scanner;

public class Main {


	public static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		System.out.println("Welcome to the Sports Centre Booking System!");
        
		boolean isExit = false;
		
		String action;

		SportsCenter sportsCenter = SportsCenter.getInstance();

        
        while (!isExit) {
    		User currentUser = UserSessionManager.getInstance().getCurrentUser();

        		
        		if(currentUser==null) {
        			System.out.println("Please input your action ([r] for register, [l] for login, [e] for exit system) :");
					String[] validCommand= {"r","l","e"};
					action = Common.getValidInput(scanner, validCommand, Common.InputType.COMMAND);
			
        			
        			switch(action) {
	        			case "r":
	        				(new CmdRegisterAccount()).execute(scanner);
	        				break;
	        				
	        			case "l":
	        				(new CmdLogin()).execute(scanner);
	        				break;
	        				
	        			case "e":
	        				isExit = true;
	        				System.out.println("System exit.");
	        				break;
	        				
        			}
        			
        		}
        		else {
        			action = currentUser.showActionMenu(scanner);
        			
        	        switch (action){
	        	        case "m":
	        	        	(new CmdMakeBooking()).execute(scanner);
	        	            break;
	                
	        	        case "v":
	        	        	(new CmdViewBooking()).execute(scanner);
	        	            break;
	        	
	        	        case "c":
	        	            (new CmdCancelBooking()).execute(scanner);
	        	            break;
	        	            
	        	        case "l":
	        	        	(new CmdLogout()).execute(scanner);
	        	            break;
	        	            
	        	        case "d":
	        	        	(new CmdMarkClosingDate()).execute(scanner);
	        	        	break;
	        	        	
	        	        case "a":
	        	        	(new CmdAddNewRoom()).execute(scanner);
	        	        	break;
	        	        
	        	        case "p":
	        	        	(new CmdModifyRoomTypePrice()).execute(scanner);
	        	        	break;
	        	            
        	        }
        	        
        		}
        	
        	


        }
        
        scanner.close();
        
	}

}
