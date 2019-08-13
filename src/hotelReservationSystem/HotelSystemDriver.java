package hotelReservationSystem;

import java.time.LocalDate;
import java.util.*;
import java.sql.*;

public class HotelSystemDriver {

	/** ----------------Set up JDBC---------------- **/
	// JDBC driver name and db URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/hotel";

	// DB credentials
	static final String USER = ""; // USE YOUR MYSQL CREDENTIALS
	static final String PASS = ""; // USE YOUR MYSQL CREDENTIALS

	/** ----------------Main Method---------------- **/
	public static void main(String[] args) {
		printWelcome();
		runMenu();
	}

	/** ----------------CLI Driver Method---------------- **/
	public static boolean runMenu() {
		boolean loop = true;
		while (loop == true) {
			Scanner in = new Scanner(System.in);
			System.out.println("[Main menu] Enter command: ");
			String selection = in.nextLine();

			if (selection.equals("/quit")) {
				loop = false;
				System.out.println("Bye");
			} else if (selection.equals("/help")) {
				printHelp();
			} else if (selection.equals("/welcome")) {
				printWelcome();
			} else if (selection.equals("/accessAccounts")) {
				System.out.println("Enter the uID of the account: ");
				String uIDInput = in.nextLine();
				loop = false;
				accessAccounts(uIDInput);
			} else if (selection.equals("/accessRooms")) {
				System.out.println("Enter the rID of the room: ");
				String rIDInput = in.nextLine();
				loop = false;
				accessRooms(rIDInput);
			} else if (selection.equals("/accessReservations")) {
				System.out.println("Enter the reservation ID of the reservation: ");
				String resIDInput = in.nextLine();
				loop = false;
				accessReservations(resIDInput);
			} else if (selection.equals("/accessHotels")) {
				System.out.println("Enter the hID of the Hotel: ");
				String hIDInput = in.nextLine();
				loop = false;
				accessHotels(hIDInput);
			} else if (selection.equals("/accessDetails")) {
				System.out.println("Enter the hID of the Hotel: ");
				String hIDInput = in.nextLine();
				loop = false;
				accessDetails(hIDInput);
			} else {
				System.out.println("Command not recognized" + "\n");
			}
		}
		return false;
	}

	/** ----------------Define CLI options---------------- **/
	public static void printWelcome() {
		System.out.println("Now running the Hotel CLI. Type /help for a list of commands.");
	}

	public static void printHelp() {
		System.out.println("The current list of available commands are:" + "\n" +
				"  " + "/quit" + "\t\t\t" + "Quit the CLI" + "\n" +
				"  " + "/welcome" + "\t\t" + "Print the welcome message" + "\n" +
				"  " + "/accessAccounts" + "\t" + "Get the username of an account given a uID" + "\n" +
				"  " + "/accessRooms" + "\t\t" + "Get the room information given the rID" + "\n" +
				"  " + "/accessReservations" + "\t" + "Get the reservation information given the resID" + "\n" +
				"  " + "/accessHotels" + "\t\t" + "Get the hotel information given the hID" + "\n" +
				"  " + "/accessDetails" + "\t" + "Get the details about hotel given the hID" + "\n") ;
				//Add more lines as commands are introduced
	}

	public static void printAccountsHelp() {
		System.out.println("The current list of available commands are:" + "\n" +
				"  " + "/quit" + "\t\t\t" + "Quit the CLI" + "\n" +
				"  " + "/back" + "\t\t" + "Go back to the main menu" + "\n" +
				"  " + "/changeUID" + "\t" + "Change the current uID selected" + "\n" +
				"  " + "/getUName" + "\t" + "Get the username of an account given a uID" + "\n" +
				"  " + "/getPassword" + "\t" + "Get the password of an account given a uID" + "\n" +
				"  " + "/getFName" + "\t" + "Get the first name of an account given a uID" + "\n" +
				"  " + "/getLName" + "\t" + "Get the last name of the account given a uID" + "\n" + 
				"  " + "/addAccount" + "\t" + "Add a new account" + "\n" + 
				"  " + "/delAccount" + "\t" + "Delete an existing account" + "\n");
				//Add more lines as commands are introduced
	}

	public static void printRoomsHelp() {
		System.out.println("The current list of available commands are:" + "\n" +
				"  " + "/quit" + "\t\t\t" + "Quit the CLI" + "\n" +
				"  " + "/back" + "\t\t\t" + "Go back to the main menu" + "\n" +
				"  " + "/getIsReserved" + "\t" + "Check if the room is reserved" + "\n" +
				"  " + "/getType" + "\t\t" + "Get the type of the room" + "\n");

	}

	public static void printReservationsHelp() {
		System.out.println("The current list of available commands are:" + "\n" +
				"  " + "/quit" + "\t\t\t" + "Quit the CLI" + "\n" +
				"  " + "/back" + "\t\t\t" + "Go back to the main menu" + "\n" +
				"  " + "/getUID" + "\t\t" + "Get the uID of the user who booked the reservation" + "\n" +
				"  " + "/getRID" + "\t\t" + "Get the rID of the room booked by the reservation" + "\n" +
				"  " + "/getHID" + "\t\t" + "Get the hID of the hotel where the reservation is booked" + "\n" +
				"  " + "/getStartDate" + "\t\t" + "Get the start date of the reservation" + "\n" +
				"  " + "/getEndDate" + "\t\t" + "Get the end date of the reservation" + "\n" +
				"  " + "/newReserve" + "\t\t" + "Create a new reservation" + "\n" + 
				"  " + "/delReserve" + "\t\t" + "Delete an existing reservation" + "\n");
	}

	public static void printHotelsHelp() {
		System.out.println("The current list of available commands are:" + "\n" +
				"  " + "/quit" + "\t\t\t" + "Quit the CLI" + "\n" +
				"  " + "/back" + "\t\t\t" + "Go back to the main menu" + "\n" +
				"  " + "/getName" + "\t\t" + "Get the name of the hotel" + "\n");
	}

	public static void printDetailsHelp() {
		System.out.println("The current list of available commands are:" + "\n" +
				"  " + "/quit" + "\t\t\t" + "Quit the CLI" + "\n" +
				"  " + "/back" + "\t\t\t" + "Go back to the main menu" + "\n" +
				"  " + "/getHNumber" + "\t\t" + "Get the number of the hotel" + "\n" +
				"  " + "/getCheckIn" + "\t\t\t" + "Get the check in time of the hotel" + "\n" +
				"  " + "/getCheckout" + "\t\t" + "Get the check out time of the hotel" + "\n");
	}

	/** ----------------Define relation access loops---------------- **/
	public static void accessAccounts(String uIDInput) {
		String currentUID = uIDInput;
		System.out.println("Enter the operation to perform. Type /help to see available operations.");

		boolean loop = true;
		while (loop == true) {
			Scanner in = new Scanner(System.in);
			System.out.println("Current uID: " + currentUID);
			System.out.println("[Accounts] Enter command: ");
			String selection = in.nextLine();

			if (selection.equals("/back")) {
				loop = false;
			} else if (selection.equals("/quit")) {
				System.out.println("Bye");
				loop = false;
				return;
			} else if (selection.equals("/changeUID")) {
				System.out.println("Enter the uID of the account: ");
				currentUID = in.nextLine();
			} else if (selection.equals("/help")) {
				printAccountsHelp();
			} else if (selection.equals("/getUName")) {
				connectToAccounts(currentUID, 0);
			} else if (selection.equals("/getPassword")) {
				connectToAccounts(currentUID, 1);
			} else if (selection.equals("/getFName")) {
				connectToAccounts(currentUID, 2);
			} else if (selection.equals("/getLName")) {
				connectToAccounts(currentUID, 3);
			} else if (selection.equals("/addAccount")) {
				addNewAccount();
			} else if (selection.equals("/delAccount")) {
				deleteAccount();
			} else {
				System.out.println("Command not recognized" + "\n");
			}
		}

		// Go back to main menu
		runMenu();
	}

	public static void accessRooms(String rIDInput) {
		String currentID = rIDInput;
		System.out.println("Enter the operation to perform. Type /help to see available operations.");

		boolean loop = true;
		while (loop == true) {
			Scanner in = new Scanner(System.in);
			System.out.println("Current rID: " + currentID);
			System.out.println("[Rooms] Enter command: ");
			String selection = in.nextLine();

			if (selection.equals("/back")) {
				loop = false;
			} else if (selection.equals("/quit")) {
				System.out.println("Bye");
				loop = false;
				return;
			} else if (selection.equals("/help")) {
				printRoomsHelp(); // need to implement
			} else if (selection.equals("/getIsReserved")) {
				connectToRooms(currentID, 0);
			} else if (selection.equals("/getType")) {
				connectToRooms(currentID, 1);
			} else {
				System.out.println("Command not recognized" + "\n");
			}
		}
		// Go back to main menu
		runMenu();
	}

	public static void accessReservations(String resIDInput) {
		String currentResID = resIDInput;
		System.out.println("Enter the operation to perform. Type /help to see available operations.");

		boolean loop = true;
		while (loop == true) {
			Scanner in = new Scanner(System.in);
			System.out.println("Current resID: " + currentResID);
			System.out.println("[Reservations] Enter command: ");
			String selection = in.nextLine();

			if (selection.equals("/back")) {
				loop = false;
			} else if (selection.equals("/quit")) {
				System.out.println("Bye");
				loop = false;
				return;
			} else if (selection.equals("/help")) {
				printReservationsHelp();
			} else if (selection.equals("/getUID")) {
				connectToReservations(currentResID, 1);
			} else if (selection.equals("/getRID")) {
				connectToReservations(currentResID, 2);
			} else if (selection.equals("/getHID")) {
				connectToReservations(currentResID, 3);
			} else if (selection.equals("/getStartDate")) {
				connectToReservations(currentResID, 4);
			} else if (selection.equals("/getEndDate")) {
				connectToReservations(currentResID, 5);
			} else if (selection.equals("/newReserve")) {
				makeReservation();
			} else if (selection.equals("/delReserve")) {
				deleteReservation();
			} else {
				System.out.println("Command not recognized" + "\n");
			}
		}

		// Go back to main menu
		runMenu();
	}

	public static void accessHotels(String hIDInput) {
		String currentHID = hIDInput;
		System.out.println("Enter the operation to perform. Type /help to see available operations.");

		boolean loop = true;
		while (loop == true) {
			Scanner in = new Scanner(System.in);
			System.out.println("Current hID: " + currentHID);
			System.out.println("[Hotels] Enter command: ");
			String selection = in.nextLine();

			if (selection.equals("/back")) {
				loop = false;
			} else if (selection.equals("/quit")) {
				System.out.println("Bye");
				loop = false;
				return;
			} else if (selection.equals("/help")) {
				printHotelsHelp();
			} else if (selection.equals("/getName")) {
				connectToHotels(currentHID, 0);
			} else {
				System.out.println("Command not recognized" + "\n");
			}
		}

		// Go back to main menu
		runMenu();
	}

	public static void accessDetails(String hIDInput) {
		String currentHID = hIDInput;
		System.out.println("Enter the operation to perform. Type /help to see available operations.");

		boolean loop = true;
		while (loop == true) {
			Scanner in = new Scanner(System.in);
			System.out.println("Current hID: " + currentHID);
			System.out.println("[Details] Enter command: ");
			String selection = in.nextLine();

			if (selection.equals("/back")) {
				loop = false;
			} else if (selection.equals("/quit")) {
				System.out.println("Bye");
				loop = false;
				return;
			} else if (selection.equals("/help")) {
				printDetailsHelp(); // need to implement
			} else if (selection.equals("/getHNumber")) {
				connectToDetails(currentHID, 0);
			} else if (selection.equals("/getCheckIn")) {
				connectToDetails(currentHID, 1);
			} else if (selection.equals("/getCheckout")) {
				connectToDetails(currentHID, 2);
			} else {
				System.out.println("Command not recognized" + "\n");
			}
		}
		// Go back to main menu
		runMenu();
	}

	/** ----------------Define relation modifier loops---------------- **/
	public static void addNewAccount() {
		int uIDNew;
		String passwordNew;
		String fNameNew;
		String lNameNew;
		String uNameNew;
		
		Scanner in = new Scanner(System.in);
		
		//Take the new account info
		System.out.println("Enter the userID for the new account");
		uIDNew = Integer.parseInt(in.nextLine());
		System.out.println("Enter the password for the new account");
		passwordNew = in.nextLine();
		System.out.println("Enter the first name for the new account");
		fNameNew = in.nextLine();
		System.out.println("Enter the last name for the new account");
		lNameNew = in.nextLine();
		System.out.println("Enter the username for the new account");
		uNameNew = in.nextLine();
		
		//Pass the new info through a connection
		createNewAccount(uIDNew, passwordNew, fNameNew, lNameNew, uNameNew);
	}
	
	public static void deleteAccount() {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the uID of the account to delete");
		int deleteUID = Integer.parseInt(in.nextLine());
		
		//Pass the new info through a connection
		removeAccount(deleteUID);
	}
	
	public static void makeReservation() {
		int resIDNew;
		int uIDNew;
		int rIDNew;
		int hIDNew;
		String startDateNew;
		String endDateNew;
		
		Scanner in = new Scanner(System.in);
		
		//Take the new reservation's info
		System.out.println("Enter the resID for the new reservation");
		resIDNew = Integer.parseInt(in.nextLine());
		System.out.println("Enter the userID of the account making the reservation");
		uIDNew = Integer.parseInt(in.nextLine());
		System.out.println("Enter the rID of the room being reserved");
		rIDNew = Integer.parseInt(in.nextLine());
		System.out.println("Enter the hID of the hotel of of the reservation");
		hIDNew = Integer.parseInt(in.nextLine());
		System.out.println("Enter the start date");
		startDateNew = in.nextLine();
		System.out.println("Enter the end date");
		endDateNew = in.nextLine();
		
		//Pass the new info through a connection
		makeReservation(resIDNew, uIDNew, rIDNew, hIDNew, startDateNew, endDateNew);
	}
	
	public static void deleteReservation() { 
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the resID of the account to delete");
		int deleteResID = Integer.parseInt(in.nextLine());
		
		//Pass the new info through a connection
		removeReservation(deleteResID);
	}
	
	/**----------------Define connections for relation commands----------------**/ 
	public static void connectToAccounts(String userID, int select) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			// Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// Execute the query
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * from accounts WHERE uID=" + userID);
			
			// Retrieve from result set based on
			while (rs.next()) {
				if (select == 0) { // getUName selected
					System.out.println(
							"User ID: " + rs.getString("uID") + "\t" + "User name: " + rs.getString("uName") + "\n");
				} else if (select == 1) { // getPassword selected
					System.out.println(
							"User ID: " + rs.getString("uID") + "\t" + "Password: " + rs.getString("password") + "\n");
				} else if (select == 2) { // getFName selected
					System.out.println(
							"User ID: " + rs.getString("uID") + "\t" + "First name: " + rs.getString("fName") + "\n");
				} else { // getLName selected
					System.out.println(
							"User ID: " + rs.getString("uID") + "\t" + "Last name: " + rs.getString("lName") + "\n");
				}
			}

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
	}

	public static void createNewAccount(int newUID, String newPassword, String newFName, String newLName, String newUName) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			// Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// Execute the query
			stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO Accounts VALUES (" + newUID + ", '" + newPassword + "', '" + newFName + "', '" + newLName + "', '" + newUName + "')");
			
		System.out.println("Account added");

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
	}
	
	public static void removeAccount(int uIDToRemove) {
		Connection conn = null;
		Statement stmt = null;
		
		try {
			// Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// Execute the query
			stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM accounts WHERE uID=" + uIDToRemove);
			
		System.out.println("Account " + uIDToRemove + " deleted");

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
	}
	
	public static void connectToRooms(String rID, int select) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// Execute a query
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * from rooms WHERE rID=" + rID);

			// TODO: Print a message if rID is not in the DB

			// Retrieve from result set based on
			while (rs.next()) {
				if (select == 0) { // getIsReserved selected
					System.out.println("Room ID: " + rs.getString("rID") + "\t" + "Reservtion status: "
							+ rs.getString("isReserved") + "\n");
				} else if (select == 1) {
					System.out.println(
							"Room ID: " + rs.getString("rID") + "\t" + "Type of room: " + rs.getString("type") + "\n");
				}
			}

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
	}

	public static void connectToReservations(String resID, int select) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// Execute a query
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * from reservations WHERE resID=" + resID);

			// TODO: Print a message if resID is not in the DB

			// Retrieve from result set based on
			while (rs.next()) {
				if (select == 1) { // getUID selected
					System.out.println("Reservation ID: " + rs.getString("resID") + "\t" + "Reserving user's uID: " + rs.getString("uID") + "\n");
				} else if (select == 2) { // getRID selected
					System.out.println("Reservation ID: " + rs.getString("resID") + "\t" + "Reservation room's rID: " + rs.getString("rID") + "\n");
				} else if (select == 3) { // getHID selected
					System.out.println("Reservation ID: " + rs.getString("resID") + "\t" + "Reservation hotel's hID: " + rs.getString("hID") + "\n");
				} else if (select == 4) { // getStartDate selected
					System.out.println("Reservation ID: " + rs.getString("resID") + "\t" + "Start date is: " + rs.getString("startDate") + "\n");
				} else if (select == 5) { // getEndDate selected
					System.out.println("Reservation ID: " + rs.getString("resID") + "\t" + "End date is: " + rs.getString("endDate") + "\n");
				} 
			}

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
	}
	
	public static void makeReservation(int resIDNew, int uIDNew, int rIDNew, int hIDNew, String startDateNew, String endDateNew) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			// Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// Execute the query
			stmt = conn.createStatement();			
			stmt.executeUpdate("INSERT INTO Reservations VALUES(" + resIDNew + ", " + uIDNew + ", " + rIDNew + ", " + hIDNew + ", '" + startDateNew + "', '" + endDateNew + "')");
			
		System.out.println("Account added");

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
	}

	public static void removeReservation(int resIDToRemove) {
		Connection conn = null;
		Statement stmt = null;
		
		try {
			// Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// Execute the query
			stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM reservations WHERE resID=" + resIDToRemove);
			
		System.out.println("Account " + resIDToRemove + " deleted");

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
	}
	
	public static void connectToHotels(String hID, int select) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// Execute a query
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * from hotels WHERE hID=" + hID);

			// TODO: Print a message if resID is not in the DB

			// Retrieve from result set based on
			while (rs.next()) {
				if (select == 0) { // getStartDate selected
					System.out
							.println("Hotel ID: " + rs.getString("hID") + "\t" + "Name: " + rs.getString("hID") + "\n");
				}
			}

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
	}

	public static void connectToDetails(String hID, int select) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// Execute a query
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * from details WHERE hID=" + hID);

			// TODO: Print a message if currentUID is not in the DB

			// Retrieve from result set based on
			while (rs.next()) {
				if (select == 0) { // getHNumber selected
					System.out.println("Hotel ID: " + rs.getString("hID") + "\t" + "Hotel number: "
							+ rs.getString("hNumber") + "\n");
				} else if (select == 1) { // getCheckIn selected
					System.out.println(
							"Hotel ID: " + rs.getString("hID") + "\t" + "Check in: " + rs.getString("checkIn") + "\n");
				} else if (select == 2) { // getCheckOut selected
					System.out.println("Hotel ID: " + rs.getString("hID") + "\t" + "Check out: "
							+ rs.getString("checkOut") + "\n");
				}
			}

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
	}
}
