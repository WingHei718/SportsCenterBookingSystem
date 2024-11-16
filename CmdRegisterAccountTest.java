package test;

import execute.SportsCenter;
import execute.User;
import execute.CmdRegisterAccount;
import java.io.StringReader;
import java.util.Scanner;

import org.junit.Test;
import static org.junit.Assert.*;

public class CmdRegisterAccountTest {

	 @Test
	    public void testExecute_RegisterAdminSuccess() {
	        // Arrange
	        SportsCenter sportsCenter = SportsCenter.getInstance();
	        String input = "a\n005\n123456\n123456";
	        StringReader stringReader = new StringReader(input);
	        Scanner scanner = new Scanner(stringReader);

	        // Act
	        CmdRegisterAccount command = new CmdRegisterAccount();
	        command.execute(scanner);

	    }

	    @Test
	    public void testExecute_RegisterNormalUserSuccess() {
	        // Arrange
	        SportsCenter sportsCenter = SportsCenter.getInstance();
	        String input = "n\n006\npassword\npassword";
	        StringReader stringReader = new StringReader(input);
	        Scanner scanner = new Scanner(stringReader);

	        // Act
	        CmdRegisterAccount command = new CmdRegisterAccount();
	        command.execute(scanner);

	    }

	    @Test
	    public void testExecute_UserIdAlreadyExists() {
	        // Arrange
	        SportsCenter sportsCenter = SportsCenter.getInstance();
	        User existingUser = new User("001", "A", "123456");
	        sportsCenter.addUser(existingUser);
	        String input = "A\n001\nnewpassword\nnewpassword\nA\n001\nnewpassword";
	        StringReader stringReader = new StringReader(input);
	        Scanner scanner = new Scanner(stringReader);

	        // Act
	        CmdRegisterAccount command = new CmdRegisterAccount();
	        command.execute(scanner);
	        }

	    @Test
	    public void testExecute_PasswordsDoNotMatch() {
	        // Arrange
	        SportsCenter sportsCenter = SportsCenter.getInstance();
	        String input = "N\n003\npassword\ndifferentpassword\nN\n003\npassword\npassword";
	        StringReader stringReader = new StringReader(input);
	        Scanner scanner = new Scanner(stringReader);

	        // Act
	        CmdRegisterAccount command = new CmdRegisterAccount();
	        command.execute(scanner);
	    }
	}
