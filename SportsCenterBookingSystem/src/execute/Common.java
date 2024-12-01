package execute;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Common {

    private Common() throws InstantiationException{
        throw new InstantiationException("Instances of this type are forbidden.");
    }



    public enum InputType{
		COMMAND("command"),
        DATE("date"),
        USERROLE("user role"),
        PRICE("price"),
        PASSWORD("password"),
        USERID("user id");
		
		private String name;

		InputType(String string) {
			name = string;
		}
		
		public String getName() {return name;}
	}
	
	
	public static String getValidInput(Scanner scanner, String[] validCommands, InputType type) {
        String input;
        while (true) {
            input = scanner.nextLine().trim();
            // Check if the input matches any of the valid commands
            for (String validCommand : validCommands) {
                if (input.equalsIgnoreCase(validCommand)) {
                    return input.toLowerCase();
                }
            }
            System.out.printf("Invalid %s, please input again.\n", type.getName());
        }
    }

    public static String getValidDate(Scanner scanner) {
        String input;
        while (true) {
            input = scanner.nextLine().trim();

            if (DateAndTime.isDateValid(input)) {
                return input;
            }
            
            System.out.printf("Invalid date, please input again.\n");
        }
    }

    public static String getValidDateandTime(Scanner scanner) {
        String input;
        while (true) {
            input = scanner.nextLine().trim();

            if (DateAndTime.isDateAndTimeValid(input)) {
                return input;
            }
            
            System.out.printf("Invalid date or time, please input again.\n");
        }
    }
    public static int[] getValidYearAndMonth(Scanner scanner) {
        int[] inputInt = new int[2];
        String message = "";

        while (true) {

            String input = scanner.nextLine().trim();
            String[] splitInput = input.split("\\s+"); // Split input by spaces

            if (splitInput.length != 2) {
                message = "Input does not match the format 'year month'";
            } else if (!splitInput[0].matches("-?\\d+") || !splitInput[1].matches("-?\\d+")) {
                message = "Both inputs must be numeric";
            } else {
                try {
                    inputInt[0] = Integer.parseInt(splitInput[0]);
                    inputInt[1] = Integer.parseInt(splitInput[1]);
                    if (DateAndTime.isValidYearMonth(inputInt[0], inputInt[1])) {
                        if(inputInt[0]<2000){message = "Year must not earlier than 2000";}
                        else if(inputInt[0]>2099){message = "Year must not later than 2099";}
                        else{return inputInt;} // Valid year and month}
                        
                    } else {
                        message = "Invalid year or month";
                    }
                } catch (NumberFormatException e) {
                    message = "Error parsing input as numbers";
                }
            }

            System.out.printf("%s, please input again.\n", message);
        }
    }

    public static String getValidRoomTypeAndPrice(Scanner scanner) {

        String message = "";
        int price;

        while (true) {

            String input = scanner.nextLine().trim();
            String[] splitInput = input.split("\\s+"); // Split input by spaces

            if (splitInput.length != 2) {
                message = "Input does not match the format";
            } else if (!splitInput[1].matches("-?\\d+")) {
                message = "Price must be numeric";
            } else {
                try {

                    price = Integer.parseInt(splitInput[1]);

                    if(price<=0){message = "Price must be larger than zero";}
                    else{return input;} // Valid year and month}
                        
                    
                } catch (NumberFormatException e) {
                    message = "Error parsing input as numbers";
                }
            }

            System.out.printf("%s, please input again.\n", message);
        }
    }



    public static int getValidPositiveNumber(Scanner scanner, InputType type) {
        String input;
        String message = "";
    
        while (true) {
            //System.out.printf("Please input a valid positive number for %s:\n", type.getName());
            input = scanner.nextLine().trim();
    
            if (input.matches("\\d+")) { // Match positive integers
                try {
                    int inputInt = Integer.parseInt(input); // Parse input
                    if (inputInt > 0) {
                        return inputInt; // Valid positive number
                    } else {
                        message = String.format("The input %s cannot be smaller or equal to zero", type.getName());
                    }
                } catch (NumberFormatException e) {
                    message = String.format("The input %s is too large to process", type.getName());
                }
            } else {
                message = String.format("The input %s is not numeric", type.getName());
            }
    
            System.out.printf("%s, please input again.\n", message);
        }
    }
    
    public static String getValidInputLength(Scanner scanner, InputType type, int length) {
        String input;
        while (true) {
            input = scanner.nextLine().trim();

            if (!input.contains(" ") && input.length()>=length) {
                return input;
            }
            
            System.out.printf("The %s length should not be less than %d digits and space is not allowed.\n", type.getName(), length);
        }
    }

	
	public static String formatDate(String orginalDate) {
		int year= Integer.parseInt("20"+orginalDate.substring(0, 2));
		int month = Integer.parseInt(orginalDate.substring(2, 4));
		int day = Integer.parseInt(orginalDate.substring(4, 6));
		
		LocalDate localdate = LocalDate.of(year, month, day);
		
		return localdate.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy",Locale.ENGLISH));
		
	}


}
