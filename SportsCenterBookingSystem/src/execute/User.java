package execute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class User {
	private String userID;
	private UserRole userRole;
	private String userPassword;
	private ArrayList<Booking> allBookings;

	public User(String userID, String userRole, String userPassword) {
		this.userID = userID;
		this.userRole = UserRole.getUserRoleByChar(userRole);
		this.userPassword = userPassword;
		this.allBookings = new ArrayList<>();
	}

	public String getUserID() {
		return this.userID;
	}

	public String getUserPasword() {
		return this.userPassword;
	}

	public ArrayList<Booking> getAllBookings() {
		return allBookings;
	}

	public String toString() {
		// String save to txt file
		return userRole.toString(userID, userPassword);
	}

	public static User getUserByID(ArrayList<User> allUsers, String userID) {
		for (User u : allUsers) {
			if (u.userID.equals(userID)) {
				return u;
			}
		}
		return null;
	}

	public String showActionMenu(Scanner scanner) {
		String action = userRole.showActionMenu(scanner);
		return action;

	}

	public void makeBooking(Scanner scanner) {
		userRole.makeBooking(scanner);
	}

	public void viewBooking(Scanner scanner) {
		userRole.viewBooking(scanner);
	}

	public void cancelBooking(Scanner scanner) {
		userRole.cancelBooking(scanner);
	}

	public void addBooking(Booking booking) {
		allBookings.add(booking);
		Collections.sort(allBookings);
	}

	public boolean makeUserBooking(Scanner scanner)  {
		SportsCenter sportsCenter = SportsCenter.getInstance();
		// Scanner scanner = new Scanner(System.in);

		sportsCenter.printAllClosingDate();

		int roomTypeCount = sportsCenter.printAllRoomType();

		boolean isBookingSuccess = false;

		try {

			if (roomTypeCount > 0) {

				System.out.println("Please input the Room Type ID you would like to book:");
				String roomTypeID = scanner.nextLine().trim();
				RoomType roomType = sportsCenter.getRoomTypeByID(roomTypeID);
				while (roomType == null) {
					System.out.println("Room Type ID not found, please input again:");
					roomTypeID = scanner.nextLine().trim();
					roomType = sportsCenter.getRoomTypeByID(roomTypeID);
				}

				System.out.println(
						"Please input the Date and Time you would like to book (format: yyMMdd HH-HH (e.g.241001 15-20)):");

				String dateAndTime = Common.getValidDateandTime(scanner);

				String[] splittedDateAndTime = dateAndTime.split(" ");
				String date = splittedDateAndTime[0];
				String time = splittedDateAndTime[1];
				String[] splittedTime = time.split("-");
				int startTime = Integer.parseInt(splittedTime[0]);
				int endTime = Integer.parseInt(splittedTime[1]);

				if (sportsCenter.isClosingDate(date)) {
					String formatDate = Common.formatDate(date);
					System.out.printf("Sorry, the sports center will be closed on %s\n", formatDate);
					throw new ExBookingFailed(ExBookingFailed.FailReason.CLOSEDATE);

				} else {

					Room room = sportsCenter.checkAvailability(roomType, date, startTime, endTime);

					if (room != null) {
						int bookingPrice = roomType.getPrice() * DateAndTime.calculateHours(startTime, endTime);
						System.out.printf("Room available (Price: $%d), are you confirmed to book and pay? (Y/N):\n",
								bookingPrice);
						String[] validCommand= {"Y","N"};
						String action = Common.getValidInput(scanner, validCommand, Common.InputType.COMMAND);


						
						switch (action) {
						case "y":
							System.out.println("Payment collected. Booking Success.");
							int nextBookingID = sportsCenter.getNextBookingID();
							Booking booking = new Booking(room, this.userID, date, startTime, endTime, bookingPrice,
									"N", String.valueOf(nextBookingID));
							this.addBooking(booking);
							room.addBooking(booking);
							sportsCenter.addBooking(booking);
							
							
							booking.printDetail();
							
							
							isBookingSuccess = true;
							break;

						default:
							throw new ExBookingFailed(ExBookingFailed.FailReason.NOTPAID);
						}

					} 
					
					else {
						System.out.println(
								"Sorry, no room is not available at the time you want. Would you like to book another time or room? (Y/N):");

						String[] validCommand= {"Y","N"};
						String action = Common.getValidInput(scanner, validCommand, Common.InputType.COMMAND);



						switch (action) {
						case "y":
							makeBooking(scanner);
							break;

						default:
							throw new ExBookingFailed(ExBookingFailed.FailReason.NOTAVAIL);
						}
					}
				}
			} else {
				throw new ExBookingFailed(ExBookingFailed.FailReason.NOROOMTYPE);
			}
		} catch (ExBookingFailed e) {
			e.printMessage();
		}

		return isBookingSuccess;

	}

	public void viewUserBookingCalendar(Scanner scanner) {
		if (allBookings.size() > 0) {
			ViewBookingService viewBookingService = new ViewBookingService();
			viewBookingService.viewBooking(allBookings, scanner);
		} else {
			System.out.println("No booking records.");
		}
	}

	public int viewUserBooking() {
		int count = 0;
		for (Booking b : allBookings) {
			if (!b.getIsCancelled()) {
				if (count == 0) {
					System.out.println("The followings are all the booking:");
				}
				System.out.println(b.viewUserBookingString());
				count++;
			}
		}
		if (count == 0) {
			System.out.println("No booking records.");
		}
		return count;

	}

	public void cancelUserBooking(Scanner scanner) {
		// Scanner scanner = new Scanner(System.in);

		int bookingCount = this.viewUserBooking();

		if (bookingCount > 0) {

			System.out.println("Please input the Booking ID you would like to cancel:");
			String bookingID = scanner.nextLine().trim();
			Booking booking = this.getNotCancelledBookingByID(bookingID);
			while (booking == null) {
				System.out.println("Booking ID not found, please input again:");
				bookingID = scanner.nextLine().trim();
				booking = this.getNotCancelledBookingByID(bookingID);
			}

			int refund = booking.getPricePaid() / 2;
			System.out.printf("Refund for cancelled booking: $%d, are you confirmed to cancel booking? (Y/N):\n",
					refund);
			String[] validCommand= {"Y","N"};
			String action = Common.getValidInput(scanner, validCommand, Common.InputType.COMMAND);

			
			switch (action) {
			case "y":
				booking.cancelBookingByUser();
				System.out.println("Booking cancelled.");
				break;

			case "n":
				break;
			}
		}

	}

	private Booking getNotCancelledBookingByID(String bookingID) {
		for (Booking b : allBookings) {
			if (!b.getIsCancelled()) {
				if (b.getBookingID().equals(bookingID)) {
					return b;
				}
			}
		}
		return null;
	}

}