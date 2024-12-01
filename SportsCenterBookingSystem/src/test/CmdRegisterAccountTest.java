package test;


import execute.CmdRegisterAccount;
import execute.SportsCenter;
import execute.User;

import java.util.Scanner;


import org.junit.Test;


public class CmdRegisterAccountTest extends TestCase{
	

	
	
	 @Test
	    public void testExecute_RegisterAdminSuccess() {
		    CmdRegisterAccount command = new CmdRegisterAccount();
	        String inputString = "a\nC7XEGU\n123456\n123456\n";
	        Scanner scanner = new Scanner(inputString);
	        command.execute(scanner);
	        scanner.close();

	    }

	    @Test
	    public void testExecute_RegisterNormalUserSuccess() {
	        CmdRegisterAccount command = new CmdRegisterAccount();
	        String inputString = "n\n3BEQ2L\n123456\n123456\n";
	        Scanner scanner = new Scanner(inputString);
	        command.execute(scanner);
	        scanner.close();

	    }

	    @Test
	    public void testExecute_UserIdAlreadyExists() {
			SportsCenter sportsCenter = SportsCenter.getInstance();

			User user = new User("IITEW5", "A", "111111");
			sportsCenter.addUser(user);
	

	    	CmdRegisterAccount command = new CmdRegisterAccount();
	        String inputString = "invalid\na\nIITEW5\nM5B3RD\n123456\n123456\n";
	        Scanner scanner = new Scanner(inputString);
	        command.execute(scanner);
	        scanner.close();
	        }


	    @Test
	    public void testExecute_PasswordsDoNotMatch() {
	    	CmdRegisterAccount command = new CmdRegisterAccount();
	        String inputString = "a\nV4V5EH\n123456\n12345\n123456\n";
	        Scanner scanner = new Scanner(inputString);
	        command.execute(scanner);
	        scanner.close();
	        }


	}
