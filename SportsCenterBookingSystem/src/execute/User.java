package execute;

import java.util.ArrayList;

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

    

    public static User getUserByUserID (ArrayList<User> allUsers, String id){
        for (User u: allUsers){
            if(u.userID.equals(id)){return u;}
        }

        return null;
    }

	public String showActionMenu(){
        String action = userRole.showActionMenu();
        return action;
		
	}
	
	public void viewBooking(){
		userRole.viewBooking(allBookings);
    }

	public void addBooking(Booking booking) {
		// TODO Auto-generated method stub
		
	}

 
}