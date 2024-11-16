package test;

import execute.DateAndTime;
import org.junit.Assert;
import org.junit.Test;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;


public class DateAndTimeTest {

    @Test
    public void testValidDateAndTime() {
        Assert.assertTrue(DateAndTime.isDateAndTimeValid("240531 9-17")); // YYMMDD format
    }

    @Test
    public void testInvalidDateAndTime1() {
        Assert.assertFalse(DateAndTime.isDateAndTimeValid("000000 9-17")); // Invalid date
    }
    
    @Test
    public void testInvalidDateAndTime2() {
        Assert.assertFalse(DateAndTime.isDateAndTimeValid("240531 9-17 17")); // Invalid format
    }
    
    @Test
    public void testInvalidDateAndTime3() {
        Assert.assertTrue(DateAndTime.isDateAndTimeValid("240420 9-17")); // Valid date
    }
    
    @Test
    public void testInvalidDateAndTime4() {
        Assert.assertFalse(DateAndTime.isDateAndTimeValid("2a0420 9-17")); // Valid date
    }
   
    
    @Test
    public void testInvalidDateAndTime5() {
        Assert.assertFalse(DateAndTime.isDateAndTimeValid("240030 9-17")); // Invalid date
    }
    
    
    @Test
    public void testInvalidDateAndTime_InvalidTime() {
        Assert.assertFalse(DateAndTime.isDateAndTimeValid("240531 25-17")); // Invalid hour
    }

    @Test
    public void testInvalidDateAndTime_NegativeTime() {
        Assert.assertFalse(DateAndTime.isDateAndTimeValid("240531 -5-17")); // Negative hour
    }

 
    @Test
    public void testInvalidDateAndTime_WrongDay() {
        Assert.assertFalse(DateAndTime.isDateAndTimeValid("240230 9-17")); // Invalid day in February
    }

   
 
    @Test
    public void testValidYearMonth() {
        Assert.assertTrue(DateAndTime.isValidYearMonth(2024, 5)); // Valid year and month
    }
    
    @Test
    public void testInvalidYearMonth() {
        Assert.assertFalse(DateAndTime.isValidYearMonth(2024, 13)); // Invalid month
    }
    
    @Test
    public void testCalculateHours_SameDay() {
        Assert.assertEquals(5, DateAndTime.calculateHours(9, 14)); // From 9 to 14
    }

    @Test
    public void testCalculateHours_NextDay() {
        Assert.assertEquals(5, DateAndTime.calculateHours(23, 4)); // From 23 to 4
    }

   
    @Test
    public void testIsTimeValid_InvalidTime_LetterInTime() {
        Assert.assertFalse(DateAndTime.isTimeValid("a-9")); // Invalid time format
    }
    
    @Test
    public void testIsDateValid_NegativeYear() {
        Assert.assertFalse(DateAndTime.isDateValid("-240531")); // Invalid year
    }

    @Test
    public void testIsDateValid_NegativeMonth() {
        Assert.assertFalse(DateAndTime.isDateValid("2405-31")); // Invalid format
    }
    

}