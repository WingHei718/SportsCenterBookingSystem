package test;


import execute.DateAndTime;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


public class DateAndTimeTest  extends TestCase{


    @Test
    public void testConstructor() throws IllegalAccessException, InstantiationException {
        final Class<?> cls = DateAndTime.class;
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

        Assert.assertFalse(DateAndTime.isDateAndTimeValid("2a0420 9-17")); // InValid date

    }
   
    
    @Test
    public void testInvalidDateAndTime5() {
        Assert.assertFalse(DateAndTime.isDateAndTimeValid("240030 9-17")); // Invalid date
    }
    

    @Test
    public void testInvalidDateAndTime6() {
        Assert.assertTrue(DateAndTime.isDateAndTimeValid("240830 9-17")); // Valid date
    }
    
    @Test
    public void testInvalidDateAndTimeA() {
        Assert.assertFalse(DateAndTime.isDateAndTimeValid("240840 9-17")); // InValid date
    }

    @Test
    public void testInvalidDateAndTimeB() {
        Assert.assertFalse(DateAndTime.isDateAndTimeValid("240940 9-17")); // InValid date
    }
    
    @Test
    public void testInvalidDateAndTimeC() {
        Assert.assertTrue(DateAndTime.isDateAndTimeValid("240228 9-17")); // Valid date
    }
    
    @Test
    public void testInvalidDateAndTimeD() {
        Assert.assertFalse(DateAndTime.isDateAndTimeValid("230240 9-17")); // InValid date
    }
    
    @Test
    public void testInvalidDateAndTimeF() {
        Assert.assertFalse(DateAndTime.isDateAndTimeValid("234023 9-17")); // InValid date
    }
    
    @Test
    public void testInvalidDateAndTime7() {
        Assert.assertFalse(DateAndTime.isDateAndTimeValid("240930 -8-10")); // Valid hour
    }
    
    @Test
    public void testInvalidDateAndTime8() {
        Assert.assertFalse(DateAndTime.isDateAndTimeValid("240930 -8-30")); // Valid hour
    }
    
    @Test
    public void testInvalidDateAndTime9() {
        Assert.assertFalse(DateAndTime.isDateAndTimeValid("240930 8-8")); // InValid hour
    } 
    
    @Test
    public void testInvalidDateAndTime10() {
        Assert.assertFalse(DateAndTime.isDateAndTimeValid("240930 8-25")); // InValid date
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
    public void testInvalidDateAndTime_RightDay() {
        Assert.assertTrue(DateAndTime.isDateAndTimeValid("230220 9-17")); // valid day in February
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

