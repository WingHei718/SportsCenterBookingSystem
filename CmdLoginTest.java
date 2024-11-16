package test;

import execute.CmdLogin;
import execute.Main;
import execute.SportsCenter;
import execute.User;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class CmdLoginTest {

    private CmdLogin cmdLogin;
    private SportsCenter sportsCenter;
    private ByteArrayOutputStream outContent;
    private PrintStream originalOut;

    @Test
    public void testLoginSuccess() {
    	cmdLogin = new CmdLogin();
        sportsCenter = SportsCenter.getInstance();
        outContent = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        String inputString = "001\n123456\n001\n123456\n";
        Scanner scanner = new Scanner(inputString);
        cmdLogin.execute(scanner);
        scanner.close();
    }

    @Test
    public void testLoginFailure_InvalidUserID() {
        cmdLogin = new CmdLogin();
        sportsCenter = SportsCenter.getInstance();
        outContent = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        String inputString = "999\n123456\n001\n123456\n";
        Scanner scanner = new Scanner(inputString);
        cmdLogin.execute(scanner);
        scanner.close();
    }

    @Test
    public void testLoginFailure_WrongPassword() {
        cmdLogin = new CmdLogin();
        sportsCenter = SportsCenter.getInstance();
        User user = new User("001", "A", "123456");
        sportsCenter.addUser(user);
        outContent = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        String inputString = "001\nwrongPassword\n001\n123456\n";
        Scanner scanner = new Scanner(inputString);
        cmdLogin.execute(scanner);
        scanner.close();
    }
}