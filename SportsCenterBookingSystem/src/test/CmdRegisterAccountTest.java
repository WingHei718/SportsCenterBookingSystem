package test;


import execute.CmdRegisterAccount;
import java.util.Scanner;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CmdRegisterAccountTest extends TestCase{
	

	
	
	 @Test
	    public void testExecute_RegisterAdminSuccess() {
		    CmdRegisterAccount command = new CmdRegisterAccount();
	        String inputString = "a\n003\n123456\n123456\n";
	        Scanner scanner = new Scanner(inputString);
	        command.execute(scanner);
	        scanner.close();

	    }

	    @Test
	    public void testExecute_RegisterNormalUserSuccess() {
	        CmdRegisterAccount command = new CmdRegisterAccount();
	        String inputString = "n\n004\n123456\n123456\n";
	        Scanner scanner = new Scanner(inputString);
	        command.execute(scanner);
	        scanner.close();

	    }

	    @Test
	    public void testExecute_UserIdAlreadyExists() {
	    	CmdRegisterAccount command = new CmdRegisterAccount();
	        String inputString = "invalid\na\n001\ninvalid\n005\n123456\n123456\n";
	        Scanner scanner = new Scanner(inputString);
	        command.execute(scanner);
	        scanner.close();
	        }
	    @Test
	    public void testExecute_PasswordsDoNotMatch() {
	    	CmdRegisterAccount command = new CmdRegisterAccount();
	        String inputString = "a\n001\n006\n123456\n12345\n123456\n";
	        Scanner scanner = new Scanner(inputString);
	        command.execute(scanner);
	        scanner.close();
	        }


	}
