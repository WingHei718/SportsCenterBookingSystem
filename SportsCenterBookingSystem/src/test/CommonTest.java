package test;

import execute.Common;
import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class CommonTest {
	
    @Test
    public void testConstructor() throws IllegalAccessException, InstantiationException {
        final Class<?> cls = Common.class;
        final Constructor<?> c = cls.getDeclaredConstructors()[0];
        c.setAccessible(true);

        Throwable targetException = null;
        try {
            c.newInstance((Object[])null);
        } catch (InvocationTargetException ite) {
            targetException = ite.getTargetException();
        }

        assertNotNull(targetException);
        assertEquals(targetException.getClass(), InstantiationException.class);
    }
	
	
	@Test
	public void testGetValidInput() {

		String[] validCommands = { "start", "stop", "pause" };
		Common.InputType type = Common.InputType.COMMAND;
		String inputString = "start\n";
		Scanner scanner = new Scanner(inputString);
		String result = Common.getValidInput(scanner, validCommands, type); // 調用common.java默認constructor
		assertEquals("The valid input should be returned", "start", result);

		scanner.close();
	}

	@Test
	public void testGetValidInputWithInvalidInput() {
		String[] validCommands = { "start", "stop", "pause" };
		Common.InputType type = Common.InputType.COMMAND;

		String inputString = "invalid\nstart\n";
		Scanner scanner = new Scanner(inputString);
		String result = Common.getValidInput(scanner, validCommands, type);
		assertEquals("The valid input should be returned after invalid input", "start", result);

		scanner.close();
	}

	@Test
	public void testGetValidDate() {
		String inputString = "240520\n240521\n";
		Scanner scanner = new Scanner(inputString);

		String result = Common.getValidDate(scanner);
		scanner.close();

	}

	@Test
	public void testGetValidDateWithInvalidInput() {
		String inputString2 = "241357\n240521\n";
		Scanner scanner55 = new Scanner(inputString2);

		String result = Common.getValidDate(scanner55);
		assertEquals("240521", result);

		scanner55.close();
	}

	@Test
	public void testGetValidDateandTime() {
		String inputString = "240531 9-17\n";
		Scanner scanner = new Scanner(inputString);

		String result = Common.getValidDateandTime(scanner);
		assertEquals("The valid date and time should be returned", "240531 9-17", result);

		scanner.close();
	}

	@Test
	public void testGetValidDateandTimeWithInvalidInput() {
		String inputString = "240531 25-17\n240531 9-17\n";
		Scanner scanner = new Scanner(inputString);

		String result = Common.getValidDateandTime(scanner);
		assertEquals("The valid date and time should be returned after invalid input", "240531 9-17", result);

		scanner.close();
	}

	@Test
	public void testGetValidYearAndMonth() {
		// 传入一个有效的年份和月份字符串
		String inputString = "2024 05\n";
		Scanner scanner = new Scanner(inputString);

		int[] result = Common.getValidYearAndMonth(scanner);
		assertArrayEquals("The valid year and month should be returned", new int[] { 2024, 5 }, result);

		scanner.close();
	}

	@Test
	public void testGetValidYearAndMonthWithInvalidInput() {
		// 传入一个无效的年份和月份字符串，然后是一个有效的
		String inputString = "2024 13\n2024 05\n";
		Scanner scanner = new Scanner(inputString);

		int[] result = Common.getValidYearAndMonth(scanner);
		assertArrayEquals("The valid year and month should be returned after invalid input", new int[] { 2024, 5 },
				result);

		scanner.close();
	}

	@Test
	public void testGetValidYearAndMonthWithNonNumericInput() {
		// 传入非数字的年份和月份字符串
		String inputString = "abc 05\n2024 05\n";
		Scanner scanner = new Scanner(inputString);

		int[] result = Common.getValidYearAndMonth(scanner);
		assertArrayEquals("The valid year and month should be returned after non-numeric input", new int[] { 2024, 5 },
				result);

		scanner.close();
	}

	@Test
	public void testGetValidYearAndMonthWithNonNumericInput1() {
		// 传入非数字的年份和月份字符串
		String inputString = "2024 abc\n2024 05\n";
		Scanner scanner = new Scanner(inputString);

		int[] result = Common.getValidYearAndMonth(scanner);
		assertArrayEquals("The valid year and month should be returned after non-numeric input", new int[] { 2024, 5 },
				result);

		scanner.close();
	}

	@Test
	public void testGetValidYearAndMonthWithWrongFormat() {
		// 传入格式错误的年份和月份字符串
		String inputString = "2147483648 05\n2100 05\n1100 05\n2024abc\n2024 05\n";
		Scanner scanner = new Scanner(inputString);

		int[] result = Common.getValidYearAndMonth(scanner);
		assertArrayEquals("The valid year and month should be returned after wrong format input", new int[] { 2024, 5 },
				result);

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
	
	@Test
	public void testgetValidInputLength() {
		String inputString = "4848 484848\n3\n 33\n3445878744";
		Scanner scanner = new Scanner(inputString);

		
		
		Common.getValidInputLength(scanner,Common.InputType.COMMAND,3) ;

		scanner.close();
	}

	 @Test
	    public void testGetValidRoomTypeAndPriceWithInvalidFormat() {
	        // Arrange
	        String inputString = "Deluxe -50\nDeluxe ABC\nInvalidInput\nDeluxe 99999999999999999999\nDeluxe 150\n"; // Invalid format first, then valid
	        Scanner scanner = new Scanner(inputString);

	        // Capture the output to verify the error message
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        System.setOut(new PrintStream(outputStream));

	        // Act
	        Common.getValidRoomTypeAndPrice(scanner);
	        scanner.close();
	 }

	

}
