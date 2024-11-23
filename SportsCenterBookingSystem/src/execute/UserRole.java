package execute;


import java.util.Scanner;

public interface UserRole {
	
	public static UserRole getUserRoleByChar(String userRole) {
        switch(userRole){
            case "A","a":
                return new Admin();
            default:
                return new NormalUser();
        }

    }
	
	public String showActionMenu(Scanner scanner);
	public boolean makeBooking(Scanner scanner);
    public void viewBooking(Scanner scanner);
    public boolean cancelBooking(Scanner scanner);
    public String toString(String userID, String userPassword);

}
