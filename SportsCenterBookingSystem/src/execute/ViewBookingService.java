package execute;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.*;
import java.util.*;


public class ViewBookingService {
	
	private LocalDate currentDate;
	
	private LocalDate viewingDate;
	
	private Calendar calendar = Calendar.getInstance();
	
	private TreeMap<Integer, TreeMap<Integer, TreeSet<Booking>>> yearMap;
	
	public ViewBookingService() {
		currentDate = LocalDate.now();
		viewingDate = currentDate;
		yearMap= new TreeMap<>();

		
	}
	
	public void viewBooking(ArrayList<Booking> allBookings,Scanner scanner) {
		yearMap = makeMap(allBookings);
		//printMap(yearMap);
		
		String action="";
		printCalendar();

		
		while (!action.equals("q")) {
			
			
			printMenu();
			
			
			
			String[] validCommand= {"p","n","s","t","q"};
			action = Common.getValidInput(scanner, validCommand, Common.InputType.COMMAND);
			
			switch(action) {
				case "p":
					viewingDate=viewingDate.minusMonths(1);
					printCalendar();

					break;
				
				case "n":
					viewingDate=viewingDate.plusMonths(1);
					printCalendar();

					break;
				
				case "s":
					System.out.println("Please input year and month: (format: 2024 1)");

					int[] input = Common.getValidYearAndMonth(scanner);
					int newYear = input[0];
					int newMonth = input[1];
				
					viewingDate= LocalDate.of(newYear, newMonth, 1);
					printCalendar();

					break;
				case "t":
					viewingDate = currentDate;
					printCalendar();

					break;
				case "q":

					break;
					
			}
			

			
		
		}
		
		//scanner.close();
		
	}
	
	private void printMenu() {
		System.out.println();
		System.out.printf("Change displaying month:%n[p] for previous month%n[n] for next month%n[s] for specific year and month%n[t] for jump to today%n");
		System.out.println("--------");
		System.out.println("[q] for quit view booking");
	}
	
	public void printCalendar() {
		System.out.printf("%n%n");
		System.out.println("Today is: "+currentDate.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy",Locale.ENGLISH)));
		printYearSummary(yearMap);
		printMonthSummary(yearMap);
		printMonthView(yearMap);
	
	}
	
	private TreeMap<Integer, TreeMap<Integer, TreeSet<Booking>>> makeMap(ArrayList<Booking> allBookings) {
		TreeMap<Integer, TreeMap<Integer, TreeSet<Booking>>> yearMap = new TreeMap<>();
		
		for(Booking b:allBookings) {
			if(b.getIsCancelled()) {continue;}
			String bookDate= b.getDate();
			int year= Integer.parseInt("20"+bookDate.substring(0, 2));
			int month = Integer.parseInt(bookDate.substring(2, 4));
			
			yearMap.putIfAbsent(year, new TreeMap<Integer, TreeSet<Booking>>());
			
			TreeMap<Integer, TreeSet<Booking>> monthMap = yearMap.get(year);
			monthMap.putIfAbsent(month, new TreeSet<Booking>());
			
			TreeSet<Booking> set = monthMap.get(month);
			
			set.add(b);
		}
		
		return yearMap;
	}
	
	
	
	
	private void printYearSummary(TreeMap<Integer, TreeMap<Integer, TreeSet<Booking>>> yearMap) {
		int viewingYear = viewingDate.getYear();
		
		
		
		System.out.println("════");
		System.out.println("Year Summary: (⚫: exists record ⚪: no record ►: displaying year)");
		
		int col = 0;

		int firstYear;
		int lastYear ;
		
		if(yearMap.isEmpty()){
			firstYear = viewingYear;
			lastYear = viewingYear;
		}
		else{
			firstYear = yearMap.firstKey();
			lastYear = yearMap.lastKey();
		}
		

		

		
		if (lastYear<viewingYear) {lastYear=viewingYear;}
		if (firstYear>viewingYear) {firstYear = viewingYear;}
		
		
		for(int year=firstYear; year<=lastYear; year++) {
			String symbol;
			
			if(year==viewingYear) {symbol = "►";}
			else if (yearMap.containsKey(year)) {symbol = "⚫";}
			else {symbol = "⚪";}
			
			System.out.printf("%-2s%-7d", symbol, year);
			
			if(col==4) {
				System.out.printf("%n");
				col=0;
			}
			else {col++;}
			
		}
		System.out.printf("%n");
		
		//System.out.println("════");
		
		
	}
	
	
	private void printMonthSummary(TreeMap<Integer, TreeMap<Integer, TreeSet<Booking>>> yearMap) {
		int viewingMonth = viewingDate.getMonthValue();
		int viewingYear = viewingDate.getYear();
		
		
		System.out.println("════");
		System.out.println(viewingYear+": (►: current displaying month)");
		
		int col = 0;
		
		TreeMap <Integer, Integer> monthCount = new TreeMap<>();
		

		
		if(yearMap.containsKey(viewingYear)) {
			TreeMap<Integer, TreeSet<Booking>> monthMap = yearMap.get(viewingYear);
			
			for(Map.Entry<Integer, TreeSet<Booking>> entry: monthMap.entrySet()) {
				int month = entry.getKey();
				int count = entry.getValue().size();
				
				monthCount.put(month,count);
			}
		}
		
		


		
		for(int m=1; m<=12; m++) {
			String symbol;
			
			if(m==viewingMonth) {symbol = "►";}
			else {symbol = " ";}
			
			int c = monthCount.getOrDefault(m, 0);
			
			LocalDate localDate = LocalDate.of(viewingYear, m, 1);
			String monthName = localDate.format(DateTimeFormatter.ofPattern("MMM",Locale.ENGLISH));
			
			System.out.printf("%3s%4s (%d)  ", symbol, monthName, c);
			
			if(col==5) {
				System.out.printf("%n");
				col=0;
			}
			else {col++;}
			
		}
		
		System.out.println("════");
		
		
	}
	
	
	private void printMonthView(TreeMap<Integer, TreeMap<Integer, TreeSet<Booking>>> yearMap) {
		
		int viewingMonth = viewingDate.getMonthValue();
		int viewingYear = viewingDate.getYear();
		
		String monthName = viewingDate.format(DateTimeFormatter.ofPattern("MMM",Locale.ENGLISH));
		System.out.println(monthName+": (■: Today)");
		
		
		printTop();
		
		for(int i=1;i<=7;i++) {
			System.out.printf("│");
			
			calendar.set(Calendar.DAY_OF_WEEK, i);

			System.out.printf("%20s",calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.ENGLISH));
			
		}
		System.out.printf("│%n");
		//printMid();
		
		calendar.set(viewingYear, viewingMonth-1, 1);
		//calendar.set(viewingYear, 11, 1);
		int numofWeek = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
		int numofDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		int weekofFirst = calendar.get(Calendar.DAY_OF_WEEK);
		int firstNum= 1-weekofFirst+1;
		int lastNum = 7*numofWeek+firstNum-1;
		//System.out.println(lastNum+" "+ numofWeek);
		int daycount=1;
		
		TreeMap <Integer, TreeSet<Booking>> dayMap = new TreeMap<>();
		TreeMap <Integer, Integer> weekDepth = new TreeMap<>();
		
		TreeSet<Booking> bookSet = yearMap.getOrDefault(viewingYear, new TreeMap<>()).getOrDefault(viewingMonth, new TreeSet<>());
		
		for(Booking b: bookSet) {
			int day = Integer.parseInt(b.getDate().substring(4,6));
			dayMap.putIfAbsent(day, new TreeSet<Booking>());
			TreeSet<Booking> dayBookSet = dayMap.get(day);
			dayBookSet.add(b);
		}
		
		
		
		for(Map.Entry<Integer, TreeSet<Booking>> entry: dayMap.entrySet()) {
			int day = entry.getKey();
			calendar.set(Calendar.DATE, day);
			int weekNumofDay = calendar.get(Calendar.WEEK_OF_MONTH);
			

			
			int depth = weekDepth.getOrDefault(weekNumofDay, 0);
			int count = entry.getValue().size();
			if(count>depth) {
				weekDepth.put(weekNumofDay, count);
			}
		}
		

		int printNum = firstNum;
		
		for (int week = 1; week<= numofWeek;week++) {
			printMid();
			int depth = weekDepth.getOrDefault(week, 0);
			
			
			
			//print date num row
			for(int i=printNum;i<printNum+7;i++) {
				System.out.printf("│");
				if(i<1||i>numofDay) {System.out.printf("%20s","");}
				else if(i==currentDate.getDayOfMonth()&&
						viewingMonth==currentDate.getMonthValue()&&
						viewingYear==currentDate.getYear()) {System.out.printf("%20s","■ "+i);}
				else {System.out.printf("%20d",i);}
			}
			System.out.printf("│%n");
			
			
			//print record row
			for(int d=0;d<depth;d++) {
				for (int r=0;r<3;r++) {
					for(int i=printNum;i<printNum+7;i++) {
						int count = dayMap.containsKey(i)?dayMap.get(i).size():0;
						System.out.printf("│");
						if(count<d+1) {System.out.printf("%18s%2s","","");}
						else {
							Booking b = (Booking)dayMap.get(i).toArray()[d];
							switch(r) {
								case 0: //time and booking id
									System.out.printf("%s%-6s%10s%2s",
														"• ",
														b.getStartTime()+"-"+b.getEndTime(),
														"ID:"+b.getBookingID(),
														"");
									break;
								case 1: //user id
									System.out.printf("%18s%2s","User:"+b.getUserID(),"");
									break;
									
								case 2: //room id
									System.out.printf("%18s%2s","Room:"+b.getRoom().getRoomID(),"");
									break;
							
							}
						}
					}
					System.out.printf("│%n");
				}
				//System.out.printf("│%n");
			}
			
			printNum+=7;
			
		}
		
		
		
		
		
		//printMid();
		printBot();
		
	}
	
	
	private void printTop() {
		String edgeStr ="────────────────────";
		
		for(int i=0;i<7;i++) {
			if(i==0) {System.out.printf("┌");}
			else {System.out.printf("┬");}
			
			System.out.printf("%s",edgeStr);
			
		}
		System.out.printf("┐%n");
	}
	
	
	private void printMid() {
		String edgeStr ="────────────────────";
		
		for(int i=0;i<7;i++) {
			if(i==0) {System.out.printf("├");}
			else {System.out.printf("┼");}
			
			System.out.printf("%s",edgeStr);
			
		}
		System.out.printf("┤%n");
	}
	
	private void printBot() {
		String edgeStr ="────────────────────";
		
		for(int i=0;i<7;i++) {
			if(i==0) {System.out.printf("└");}
			else {System.out.printf("┴");}
			
			System.out.printf("%s",edgeStr);
			
		}
		System.out.printf("┘%n");
	}


	
	
	
	
}
