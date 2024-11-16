package test;

import execute.Common;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Scanner;

public class CommonTest {

    @Test
    public void testGetValidInput() {
    	
        String[] validCommands = {"start", "stop", "pause"};
        Common.InputType type = Common.InputType.COMMAND;
        String inputString = "start\n";
        Scanner scanner = new Scanner(inputString);
        String result = Common.getValidInput(scanner, validCommands, type);
        assertEquals("The valid input should be returned", "start", result);

        scanner.close();
    }
    @Test
    public void testGetValidInputWithInvalidInput() {
        String[] validCommands = {"start", "stop", "pause"};
        Common.InputType type = Common.InputType.COMMAND;

        String inputString = "invalid\nstart\n";
        Scanner scanner = new Scanner(inputString);
        String result = Common.getValidInput(scanner, validCommands, type);
        assertEquals("The valid input should be returned after invalid input", "start", result);

        scanner.close();
    }
    @Test
    public void testGetValidDate() {
        String inputString = "20240520\n20240521\n";
        Scanner scanner = new Scanner(inputString);

        String result = Common.getValidDate(scanner);
     
    }

    @Test
    public void testGetValidDateWithInvalidInput() {
        String inputString = "20241357\n20240521\n";
        Scanner scanner = new Scanner(inputString);

        String result = Common.getValidDate(scanner);
        assertEquals("20240521", result);

        scanner.close();
    }

    @Test
    public void testGetValidDateandTime() {
        String inputString = "20240531 9-17\n";
        Scanner scanner = new Scanner(inputString);

        String result = Common.getValidDateandTime(scanner);
        assertEquals("The valid date and time should be returned", "20240531 9-17", result);

        scanner.close();
    }

    @Test
    public void testGetValidDateandTimeWithInvalidInput() {
        String inputString = "20240531 25-17\n20240531 9-17\n";
        Scanner scanner = new Scanner(inputString);

        String result = Common.getValidDateandTime(scanner);
        assertEquals("The valid date and time should be returned after invalid input", "20240531 9-17", result);

        scanner.close();
    }
    
    
    
    @Test
    public void testGetValidYearAndMonth() {
        // 传入一个有效的年份和月份字符串
        String inputString = "2024 05\n";
        Scanner scanner = new Scanner(inputString);

        int[] result = Common.getValidYearAndMonth(scanner);
        assertArrayEquals("The valid year and month should be returned", new int[]{2024, 5}, result);

        scanner.close();
    }

    @Test
    public void testGetValidYearAndMonthWithInvalidInput() {
        // 传入一个无效的年份和月份字符串，然后是一个有效的
        String inputString = "2024 13\n2024 05\n";
        Scanner scanner = new Scanner(inputString);

        int[] result = Common.getValidYearAndMonth(scanner);
        assertArrayEquals("The valid year and month should be returned after invalid input", new int[]{2024, 5}, result);

        scanner.close();
    }

    @Test
    public void testGetValidYearAndMonthWithNonNumericInput() {
        // 传入非数字的年份和月份字符串
        String inputString = "abc 05\n2024 05\n";
        Scanner scanner = new Scanner(inputString);

        int[] result = Common.getValidYearAndMonth(scanner);
        assertArrayEquals("The valid year and month should be returned after non-numeric input", new int[]{2024, 5}, result);

        scanner.close();
    }
    @Test
    public void testGetValidYearAndMonthWithNonNumericInput1() {
        // 传入非数字的年份和月份字符串
        String inputString = "2024 abc\n2024 05\n";
        Scanner scanner = new Scanner(inputString);

        int[] result = Common.getValidYearAndMonth(scanner);
        assertArrayEquals("The valid year and month should be returned after non-numeric input", new int[]{2024, 5}, result);

        scanner.close();
    }

    @Test
    public void testGetValidYearAndMonthWithWrongFormat() {
        // 传入格式错误的年份和月份字符串
        String inputString = "2147483648 05\n2100 05\n1100 05\n2024abc\n2024 05\n";
        Scanner scanner = new Scanner(inputString);

        int[] result = Common.getValidYearAndMonth(scanner);
        assertArrayEquals("The valid year and month should be returned after wrong format input", new int[]{2024, 5}, result);

        scanner.close();
    }
    

    @Test
    public void testGetValidPositiveNumber() {
        // 传入一个有效的正整数
        String inputString = "5\n";
        Scanner scanner = new Scanner(inputString);

        int result = Common.getValidPositiveNumber(scanner, Common.InputType.COMMAND);
        assertEquals("The valid positive number should be returned", 5, result);

        scanner.close();
    }
    @Test
    public void testGetValidPositiveNumber0() {
        // 传入一个有效的正整数
        String inputString = "0\n5\n";
        Scanner scanner = new Scanner(inputString);

        int result = Common.getValidPositiveNumber(scanner, Common.InputType.COMMAND);
        assertEquals("The valid positive number should be returned", 5, result);

        scanner.close();
    }

    @Test
    public void testGetValidPositiveNumberWithInvalidInput() {
        // 传入一个无效的输入，然后是一个有效的正整数
        String inputString = "-1\n5\n";
        Scanner scanner = new Scanner(inputString);

        int result = Common.getValidPositiveNumber(scanner, Common.InputType.COMMAND);
        assertEquals("The valid positive number should be returned after invalid input", 5, result);

        scanner.close();
    }

    @Test
    public void testGetValidPositiveNumberWithNonNumericInput() {
        // 传入非数字的输入，然后是一个有效的正整数
        String inputString = "abc\n5\n";
        Scanner scanner = new Scanner(inputString);

        int result = Common.getValidPositiveNumber(scanner, Common.InputType.COMMAND);
        assertEquals("The valid positive number should be returned after non-numeric input", 5, result);

        scanner.close();
    }

    @Test
    public void testGetValidPositiveNumberWithLargeInput() {
        // 传入一个过大的数字，可能会抛出 NumberFormatException
        String inputString = "9223372036854775808\n5\n"; // Long.MAX_VALUE + 1
        Scanner scanner = new Scanner(inputString);

        int result = Common.getValidPositiveNumber(scanner, Common.InputType.COMMAND);
        assertEquals("The valid positive number should be returned after input that is too large", 5, result);

        scanner.close();
    }
 
    @Test
    public void testFormatDateWithDifferentYear() {
        // 测试不同年份的日期格式化
        String originalDate = "1130";
        String expectedDate = "30-Nov-2011";
        String formattedDate = Common.formatDate("20111310");

    }


}




