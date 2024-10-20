package execute;

import java.util.Scanner;

public class AccountController {
	private User user;

    public AccountController(User user){
        this.user = user;
    }

    //maybe need change the name of this function?
    public void actionHandler() {
        SportsCenter sportsCenter = SportsCenter.getInstance();

        String action = user.showActionMenu();
        
        switch (action){
	        case "m":
	            sportsCenter.printAllRoomType();
	            System.out.println("Please input your preferred timeslot (format: badminton 2024-08-10 1000-1200):");
	            
	            break;
        
	        case "v":
	            user.viewBooking();
	            this.actionHandler();
	            break;
	
	        case "c":
	            break;
	
	        case "l":
	        	AccountController.userRegisterLogin(sportsCenter);
	            break;
	    
	    }
        
        
    }
    
    public static void userRegisterLogin(SportsCenter sportsCenter) {
    	Scanner scanner = new Scanner(System.in);
		String action;
		do {
			System.out.println("Please input your action ([r] for Register, [l] for login) :");
			action = scanner.nextLine();
		} while (!action.equals("r") && !action.equals("l"));
		
		String userID;
		String userRole;
		String userPassword;
		String userPassword2;
		User user;
		AccountController accountController;
		
		switch (action){
		
		    case "r":
		    	do {
		    		System.out.println("Please input your Role ([A] for Admin, [N] for Normal User):");
            		userRole = scanner.nextLine();
            	} while (!userRole.equals("A") && !userRole.equals("N"));
			
			    System.out.println("Please input your User ID:");
			    userID = scanner.nextLine();
			
			    //check if id already exist
			    while (sportsCenter.userIdExist(userID)){
			        System.out.println();
			        System.out.println("This ID is not available.");
			        System.out.println("Please input your User ID:");
			        userID = scanner.nextLine();
			    }
			
			    System.out.println("Please input your Password:");
			    userPassword = scanner.nextLine();
			    do {
			        System.out.println("Please input your Password again:");
			        userPassword2 = scanner.nextLine();
			    } while (!userPassword2.equals(userPassword));
			     
				switch (userRole) {
					case "A":
						user = new Admin(userID, userPassword);
						sportsCenter.addUser(user);
						System.out.println("Registration Success.");
					    accountController = new AccountController(user);
					    accountController.actionHandler();
						break;
					case "N":
						user = new User(userID, userPassword);
						sportsCenter.addUser(user);
						System.out.println("Registration Success.");
					    accountController = new AccountController(user);
					    accountController.actionHandler();
						break;
					}
			    
				
			
			    break;
		
		
		    case "l":
		    	do {
		    		System.out.println("Please input your User ID:");
		    		userID = scanner.nextLine();
		    		user = sportsCenter.getUserByUserID(userID);
		    	} while (user==null);
		    	do {
		    		System.out.println("Please input your Password:");
		            userPassword = scanner.nextLine();
		        } while (!user.getUserPasword().equals(userPassword));
		
		        accountController = new AccountController(user);
		        accountController.actionHandler();
		         
		        break;
		
		}
	    scanner.close();
    }

}
