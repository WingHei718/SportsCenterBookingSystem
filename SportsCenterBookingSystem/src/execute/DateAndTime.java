package execute;

import java.time.DateTimeException;
import java.time.LocalDate;

public class DateAndTime {

    private DateAndTime() throws InstantiationException{
        throw new InstantiationException("Instances of this type are forbidden.");
    }	

	
	public static boolean isDateAndTimeValid(String DateAndTime) {
		String[] splittedDateAndTime = DateAndTime.split(" ");
		if(splittedDateAndTime.length!=2){return false;}
		String date = splittedDateAndTime[0];
		String time = splittedDateAndTime[1];
		if (!isDateValid(date)) {
			return false;
		}
		if (!isTimeValid(time)) {
			return false;
		}
		return true;
	}
	
	public static boolean isDateValid(String date) {
		try{
			if(date.length()!=6) {return false;}
			int year = Integer.parseInt("20"+date.substring(0,2));
			int month= Integer.parseInt(date.substring(2,4));
			int day= Integer.parseInt(date.substring(4,6));
			
			LocalDate.of(year, month, day);
			return true;
			
			
		}
		catch (NumberFormatException e){return false;}
		catch (DateTimeException e) {return false;}
		
	}
	
	public static boolean isTimeValid(String time) {
		String[] splittedTime = time.split("-");
		if(splittedTime.length!=2){return false;}
		try{
			int startTime = Integer.parseInt(splittedTime[0]);
			int endTime = Integer.parseInt(splittedTime[1]);

			if(startTime>23) {
				return false;
				
			}
			if(endTime>23) {
				return false;
			}
			
			if (endTime<=startTime && endTime!=0) {
				return false;
			}
			
			return true;
		}
		catch (NumberFormatException e){return false;}
		}
	
	public static int calculateHours(int startTime, int endTime) {
		if (endTime<=startTime) {
			return (endTime+24)-startTime;
		}
		return endTime-startTime;
	}
	


	public static boolean isValidYearMonth(int year, int month) {
        try {

            LocalDate.of(year, month, 1);
            return true;
        } catch (DateTimeException e) {

            return false;
        }
    }
	
	
}
