package test;

import execute.CmdLogout;

import org.junit.Test;

import java.util.Scanner;

public class CmdLogoutTest extends TestCase{
	
	

    @Test
    public void testExecute() {
    	CmdLogout cmdLogout = new CmdLogout();
    	cmdLogout.execute(new Scanner(System.in));
        
    }
}