package execute;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

public class SportsCenter {
	private ArrayList<RoomType> allRoomTypes;
	private ArrayList<Room> allRooms;
    private ArrayList<User> allUsers;
    private ArrayList<Booking> allBookings;
    private ArrayList<String> allClosingDates;
    private static SportsCenter INSTANCE;
    private String mainClassPath = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    
    
    public enum FilePath{
		ROOMTYPE("src/execute/assets/room_type_data.txt"),
        ROOM("src/execute/assets/room_data.txt"),
        USER("src/execute/assets/user_data.txt"),
        BOOKING("src/execute/assets/booking_data.txt"),
        CLOSINGDATE("src/execute/assets/closing_date_data.txt");

		private String path;

		FilePath(String string) {
			path = string;
		}
		
		public String getPath() {return path;}

		public void setPath(String string) {
			path = string;
			
		}
	}
    
    
    
	private SportsCenter() {
		this.allRoomTypes = new ArrayList<>();
		this.allRooms = new ArrayList<>();
		this.allUsers = new ArrayList<>();
        this.allBookings = new ArrayList<>();
        this.allClosingDates = new ArrayList<>();
	}
    
    public static SportsCenter getInstance(){
        if(INSTANCE == null) {
            INSTANCE = new SportsCenter();
            INSTANCE.init();
        }
        return INSTANCE;
    }
	
	public ArrayList<Booking> getAllBookings() {
		return allBookings;
	}
    
	public void init() {
		 try {
			 	
	            // Get the decoded path to the Main class's location

	            String decodedPath = URLDecoder.decode(mainClassPath, "UTF-8");
	            if (decodedPath.startsWith("/") ) {
	                decodedPath = decodedPath.substring(1);
	            }
	            Path basePath = Paths.get(decodedPath).getParent();

	            // Resolve the data file paths relative to the base path
	            String roomTypePath = basePath.resolve(FilePath.ROOMTYPE.getPath()).toString();
	            String roomPath = basePath.resolve(FilePath.ROOM.getPath()).toString();
	            String userPath = basePath.resolve(FilePath.USER.getPath()).toString();
	            String bookingPath = basePath.resolve(FilePath.BOOKING.getPath()).toString();
	            String closingDatePath = basePath.resolve(FilePath.CLOSINGDATE.getPath()).toString();


	            // Load data using the resolved paths
	            loadRoomType(roomTypePath);
	            loadRoom(roomPath);
	            loadUser(userPath);
	            loadBooking(bookingPath);
	            loadClosingDate(closingDatePath);

	        } catch (Exception e) {
	            System.out.println("Cannot load files.");
	        }
		
	}

	//TODO: handle wrong data input (wrong data type/missing info etc)? use exception?
	//check array size 
	//check numeric for int attribute
	//exception for can't find room/room type

	private void loadRoomType(String path) {
		try{
			File file = new File(path);
			Scanner scanner = new Scanner(file);

			while (scanner.hasNextLine()){
				String data = scanner.nextLine();
				String[] splittedData = data.split(" ");
				//format: TypeID Type Price
				RoomType roomType = new RoomType(splittedData[0], splittedData[1], Integer.parseInt(splittedData[2]));
				allRoomTypes.add(roomType);
			}
			
			System.out.println("Finished loading room types.");
			scanner.close();
		}
		catch(FileNotFoundException e){
			System.out.println("Cannot find file at path: "+path);
		}
		
	}

	private void loadRoom(String path) {
		try{
			File file = new File(path);
			Scanner scanner = new Scanner(file);

			while (scanner.hasNextLine()){
				String data = scanner.nextLine();
				String[] splittedData = data.split(" ");
				//format: roomID roomTypeID
				
				//find roomType by id (pass back to class RoomType)
				RoomType roomType = getRoomTypeByID(splittedData[1]);
				if(roomType != null){
					Room room = new Room(splittedData[0], roomType);
					allRooms.add(room);
				} else{
					System.out.println("Cannot find room type: "+splittedData[1]);
				}
			}

			System.out.println("Finished loading rooms.");
			scanner.close();
		}
		catch(FileNotFoundException e){
			System.out.println("Cannot find file at path: "+path);
		}
		
    }
  
  	private void loadUser(String path) {
		try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            
            while (scanner.hasNextLine()){
                String data = scanner.nextLine();
                String[] splittedData = data.split(" ");
                //format: userID, userRole, userPassword
                User user = new User(splittedData[0], splittedData[1], splittedData[2]);
                allUsers.add(user);
            }
            
            System.out.println("Finished loading users.");
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Cannot find file at path: "+path);
        }
		
	}

	private void loadBooking(String path) {
		try{
			File file = new File(path);
			Scanner scanner = new Scanner(file);

			while (scanner.hasNextLine()){
				String data = scanner.nextLine();
				String[] splittedData = data.split(" ");
				//format: RoomID UserID YYMMDD StartingTime EndingTime BookingID
				
				//find room by id (pass back to class RoomType)
				Room room = getRoomByID(splittedData[0]);
				User user = getUserByID(splittedData[1]);


				if (room != null && user != null) {
					Booking booking = new Booking(room, splittedData[1], splittedData[2], Integer.parseInt(splittedData[3]), Integer.parseInt(splittedData[4]), Integer.parseInt(splittedData[5]), splittedData[6], splittedData[7]);
					room.addBooking(booking);
					user.addBooking(booking);
					allBookings.add(booking);
					
				} else if (room == null) {
					System.out.println("Cannot find room: "+splittedData[0]);
					
				} else  {
					System.out.println("Cannot find user: "+splittedData[1]);
				}
			}

			System.out.println("Finished loading bookings.");
			scanner.close();
		}
		catch(FileNotFoundException e){
			System.out.println("Cannot find file at path: "+path);
		}
		
	}
	
	private void loadClosingDate(String path) {
		try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            
            while (scanner.hasNextLine()){
                String date = scanner.nextLine();
                allClosingDates.add(date);
            }
            
            System.out.println("Finished loading closing dates.");
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Cannot find file at path: "+path);
        }
		
	}

	public RoomType getRoomTypeByID(String roomTypeID) {
		return RoomType.getRoomTypeByID(allRoomTypes, roomTypeID);
	}

	public Room getRoomByID(String roomID) {
		return Room.getRoomByID(allRooms, roomID);
	}

	public User getUserByID(String userID){ 
        return User.getUserByID(allUsers, userID);
    }
	
	public Booking getBookingByID(String bookingID) {
		return Booking.getBookingByID(allBookings, bookingID);
	}
	
	public boolean isClosingDate(String date) {
		for (String d: allClosingDates) {
			if (d.equals(date)) {
				return true;
			}
		}
		return false;
	}
	
	public void addRoomType(RoomType roomType){
		allRoomTypes.add(roomType);
	}
	
	public void addRoom(Room room){
		allRooms.add(room);
	}

	public void addUser(User user){
		allUsers.add(user);
	}
	
	public void addBooking(Booking booking) {
		allBookings.add(booking);
		Collections.sort(allBookings);
	}
	
	public void addClosingDate(String date) {
		allClosingDates.add(date);
		//TODO: sorting needed for allClosingDates
		
		//handle those already booked bookings on closingDate
		ArrayList<Booking> bookingForDay = Booking.getBookingsOfSpecificDate(allBookings, date);
		if (bookingForDay.size()>0) {
			//TODO: need change the booking info string
			System.out.println("The followings are all the booking affected by the closing date, please contact all the relevant users:");
			for (Booking b: bookingForDay) {
				if (!b.getIsCancelled()) {
					System.out.println(b);
					b.cancelBookingByClosingDate();
				}
			}
		}
		
	}
	
	public void saveData() {
		String roomTypePath = "src/execute/assets/room_type_data.txt";
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(roomTypePath))) {
	        for (RoomType r: allRoomTypes) {
	            writer.write(r.toString());
	            writer.newLine();
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    
	    String roomPath = "src/execute/assets/room_data.txt";
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(roomPath))) {
	        for (Room r: allRooms) {
	            writer.write(r.toString());
	            writer.newLine();
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		
		String userPath = "src/execute/assets/user_data.txt";
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(userPath))) {
            for (User u: allUsers) {
                writer.write(u.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	    String bookingPath = "src/execute/assets/booking_data.txt";
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(bookingPath))) {
	        for (Booking b: allBookings) {
	            writer.write(b.toString());
	            writer.newLine();
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    
	    String closingDatePath = "src/execute/assets/closing_date_data.txt";
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(closingDatePath))) {
			for (String d: allClosingDates) {
				writer.write(d);
				writer.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	}

	public Room checkAvailability(RoomType roomType, String date, int startTime, int endTime) {
		
		//TODO: if room available, return room, else return null
		ArrayList<Booking> bookingForDay = Booking.getBookingsOfSpecificDate(allBookings, date);
		Map<String, ArrayList<Booking>> bookingOfRoomsForDay = new HashMap<String, ArrayList<Booking>>();
		
		for (Room r:allRooms){
            if(r.getRoomType().getType().equals(roomType.getType())){
                bookingOfRoomsForDay.put(r.getRoomID(), new ArrayList<Booking>());
            }
		}
		
		for (Booking b: bookingForDay) {
			Room room = b.getRoom();
			if(room.getRoomType().getType().equals(roomType.getType())) {
				if (bookingOfRoomsForDay.containsKey(room.getRoomID())) {
					bookingOfRoomsForDay.get(room.getRoomID()).add(b);
				}
			};
			
		}
		
	    Room bestRoom = null;
	    int minIdleTime = Integer.MAX_VALUE;

	    for (Map.Entry<String, ArrayList<Booking>> entry : bookingOfRoomsForDay.entrySet()) {
	        String roomID = entry.getKey();
	        ArrayList<Booking> bookings = entry.getValue();

	        if (!isOverlapping(bookings, startTime, endTime)) {
	            int idleTime = calculateIdleTime(bookings, startTime, endTime);

	            if (idleTime < minIdleTime) {
	                minIdleTime = idleTime;
	                bestRoom = Room.getRoomByID(allRooms, roomID);
	            }
	        }
	    }
	    

	    return bestRoom;
	}
	
	public boolean isOverlapping(ArrayList<Booking> bookings, int startTime, int endTime) {
	    for (Booking booking : bookings) {
	        if (startTime < booking.getEndTime() && endTime > booking.getStartTime()) {
	            return true;
	        }
	    }
	    return false;
	}

	public int calculateIdleTime(ArrayList<Booking> bookings, int startTime, int endTime) {
	    int idleTime = 0;
	    bookings.sort(Comparator.comparingInt(Booking::getStartTime));

	    int previousEndTime = 0;
	    for (Booking booking : bookings) {
	        idleTime += booking.getStartTime() - previousEndTime;
	        previousEndTime = booking.getEndTime();
	    }

	    idleTime += startTime - previousEndTime;
	    idleTime += 24 * 60 - endTime;

	    return idleTime;
	}

	public int getNextRoomTypeID() {
		return allRoomTypes.size() + 1;
	}
	
	public int getNextRoomID() {
		return allRooms.size() + 1;
	}
	
	public int getNextBookingID() {
		return allBookings.size() + 1;
	}
	
	public int printAllRoomType (){
		if (allRoomTypes.size()>0) {
			System.out.println("The followings are all the room type available:");
			for(RoomType r: allRoomTypes){
				System.out.println(r.printAllRoomTypeString());
			}
		} else {
			System.out.println("No room type available.");
		}
		return allRoomTypes.size();
		
	}

	public void printAllClosingDate() {
		if (allClosingDates.size()>0) {
			System.out.println("Notice:\nThe followings are all closing date of the sports center:");
			for (String d: allClosingDates) {
				String formatDate = Common.formatDate(d);
				System.out.println(formatDate);
			}
		}
		
		
	}

	

	
}
