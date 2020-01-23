package CPLDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import Controller.Control;
import Model.Event;
import Model.Song;
import Model.User;

public class DB {

	/****************************** createDataBase ********************/
	public static void createDataBase() {
		System.out.println("Starting createDataBase...");
		String sql;
		Connection conn = null;
		Statement stmt = null;
		try {
			// Create connection to the driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			// Connect to SQL
			conn = DriverManager.getConnection("jdbc:mysql://localhost", "root", "");
			stmt = conn.createStatement();
			
			
			ResultSet rs = null;
			int flag = 0;
			rs = conn.getMetaData().getCatalogs();			// Checks all DB catalogs to see if the desirable site catalog is listed
			while (rs.next())
			{
				String catalog = rs.getString(1);
				if ("cpldb".equals(catalog))					// If the catalog is in the DB, turn flag to 1
				{
					flag = 1;
				}
			}
			if (flag == 0)									// Catalog does not exist
			{
				stmt = conn.createStatement();
				stmt.executeUpdate("CREATE DATABASE " + "cpldb");
				conn = DriverManager.getConnection("jdbc:mysql://localhost/cpldb", "root", "");
				stmt = conn.createStatement();
			
			
			// ***** we should add here drop of previous db if existed
			
			//sql = "CREATE DATABASE IF NOT EXISTS cpldb";
			//stmt.executeUpdate(sql);
		

			// ***** Create Users table *****//
			sql = "CREATE TABLE IF NOT EXISTS users " + " (`first name` VARCHAR(32), " + " `last name` VARCHAR(32), "
					+ " phone VARCHAR(10), " + "  email VARCHAR(32), " + " type INTEGER, " + " password VARCHAR(32),"
					+ "eventpin INTEGER)";
		
			stmt.execute(sql);
			
			
			sql = "ALTER TABLE `users` ADD PRIMARY KEY(`email`)";
			stmt.execute(sql);

			// ***** Create Events table *****//
			sql = "CREATE TABLE `cpldb`.`events` " + " ( `PIN` INT(5) AUTO_INCREMENT , " + " `event name` VARCHAR(32), "
					+ " `number of participants` INT," + " `current number of participants` INT," + " `duration` INT,"
					+ " `type` VARCHAR(1), " + " `number of songs` INT," + " PRIMARY KEY (`PIN`)) "
					+ " ENGINE = InnoDB";
			// PIN have 5 digits,
			// type have 1 digits- A or B
			stmt.execute(sql);
			sql = "ALTER TABLE events AUTO_INCREMENT = 32654";
			stmt.execute(sql);
			sql = "SET @@auto_increment_increment = 5173";
			stmt.execute(sql);

			
			sql = "INSERT INTO `users` " + "(`first name`, `last name`, `phone`, `email`, `type`, `password`, `eventpin`)"
					+ " VALUES ('Igor', 'Rochlin', '054111', 'Igor@gmail.com', '2', '1234', '23456')";
			stmt.execute(sql);
			
			// Dor
			sql = "INSERT INTO `users` " + "(`first name`, `last name`, `phone`, `email`, `type`, `password`, `eventpin`)"
					+ " VALUES ('Dor', 'Karni', '054111', 'Dor@gmail.com', '1', '1234', '23456')";
			stmt.execute(sql);
			// Barak
			sql = "INSERT INTO `users` " + "(`first name`, `last name`, `phone`, `email`, `type`, `password`, `eventpin`)"
					+ " VALUES ('Barak', 'Roth', '054222', 'Barak@gmail.com', '1', '1234', '23456')";
			stmt.execute(sql);
			// Dovi
			sql = "INSERT INTO `users` " + "(`first name`, `last name`, `phone`, `email`, `type`, `password`, `eventpin`)"
					+ " VALUES ('Dov', 'Twersky', '054333', 'Dov@gmail.com', '1', '1234', '23456')";
			stmt.execute(sql);
			
			
			sql="CREATE TABLE `songs23456` ("
					  +"`ID` int(5) NOT NULL,"
					 +" `owner` varchar(64) DEFAULT NULL,"
					 +" `name` varchar(64) CHARACTER SET hebrew COLLATE hebrew_bin NOT NULL,"
					 +" `youtubelink` varchar(500) DEFAULT NULL"
					+") ENGINE=InnoDB DEFAULT CHARSET=latin1";
			stmt.execute(sql);
			
			
			sql="INSERT INTO `songs23456` (`ID`, `owner`, `name`, `youtubelink`) VALUES"
					+"(1, 'Dov', '[A]  Castle On The Hill†', 'https://www.youtube.com/watch?v=K0ibBPhiaG0&list=RDEM_Ktu-TilkxtLvmc9wX1MLQ&index=7'),"
					+"	(2, 'Dov', '[B] Tudo Bom', 'https://www.youtube.com/watch?v=Y_OLslE3bX8'),"
					+"(3, 'Dov', '[C] twenty one pilots: Stressed Out†', 'https://www.youtube.com/watch?v=pXRviuL6vMY&index=23&list=RDfLexgOxsZu0'),"
					+"(4, 'Dov', '[D] OneRepublic - Counting Stars', 'https://www.youtube.com/watch?v=hT_nvWreIhg'),"
					+"(5, 'Barak', '[G] Sing', 'https://www.youtube.com/watch?v=tlYcUqEPN58&index=24&list=RDEM_Ktu-TilkxtLvmc9wX1MLQ'),"
					+"(6, 'Barak', '[E] Shape of You', 'https://www.youtube.com/watch?v=JGwWNGJdvx8&list=RDEM_Ktu-TilkxtLvmc9wX1MLQ&index=1'),"
					+"(7, 'Barak', '[C] twenty one pilots: Stressed Out†', 'https://www.youtube.com/watch?v=pXRviuL6vMY&index=23&list=RDfLexgOxsZu0'),"
					+"(8, 'Barak', '[D] OneRepublic - Counting Stars', 'https://www.youtube.com/watch?v=hT_nvWreIhg'),"
					+"(9, 'Dor', '[A] Castle On The Hill†', 'https://www.youtube.com/watch?v=K0ibBPhiaG0&list=RDEM_Ktu-TilkxtLvmc9wX1MLQ&index=7'),"
					+"(10, 'Dor', '[C] twenty one pilots: Stressed Out†', 'https://www.youtube.com/watch?v=pXRviuL6vMY&index=23&list=RDfLexgOxsZu0'),"
					+"(11, 'Dor', '[B] Tudo Bom', 'https://www.youtube.com/watch?v=Y_OLslE3bX8'),"
					+"(12, 'Dor', '[E] Shape of You', 'https://www.youtube.com/watch?v=JGwWNGJdvx8&list=RDEM_Ktu-TilkxtLvmc9wX1MLQ&index=1')";
			stmt.execute(sql);
			
			
			sql="ALTER TABLE `songs23456`"
					 +"ADD PRIMARY KEY (`ID`)";
			stmt.execute(sql);
			
			sql = "ALTER TABLE `songs23456`"
			  +"MODIFY `ID` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13";
			stmt.execute(sql);
			
			
			  // test event 
			sql = "INSERT INTO `events` " +
			  " (`PIN`,`event name`, `number of participants`, `duration`, `type`, `number of songs`, `current number of participants`)"
			  +
			  " VALUES ('23456','test', '4', '25', 'A', '4', '3')"
			  ; stmt.execute(sql);
			  
			  System.out.println("there is no CPLB so new one created");
			}
			// Control.dbex = 1;
			System.out.println("createDataBase func ended successfully");
		} catch (Exception e) {
			System.out.println("Error");
		}

	}

	/****************** Insert User *********************************/ // working
	public static void insertUser(User newUser) {
		System.out.println("Starting insertUser...");
		String sql;
		Connection conn = null;
		Statement stmt = null;
		try {
			// Create connection to the driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			// Connect to db
			conn = DriverManager.getConnection("jdbc:mysql://localhost/cpldb", "root", "");
			stmt = conn.createStatement();

			// Create Users member
			/*
			 * sql = "SET auto_increment_increment = 5173"; stmt.execute(sql);
			 */

			sql = "INSERT INTO `users` " + "(`first name`, `last name`, `phone`, `email`, `type`, `password`)"
					+ " VALUES ('" + newUser.getFirst() + "', '" + newUser.getLast() + "', '" + newUser.getPhone()
					+ "', '" + newUser.getEmail() + "', '" + newUser.getType() + "', '" + newUser.getPassword() + "')";
			stmt.execute(sql);
			System.out.println("insertUser ended successfully");
		} catch (Exception e) {
			System.out.println("error");
		}
	}

	/*************** Insert Event ***********************************/ // working
	public static void insertEvent(Event newEvent) {
		System.out.println("Starting insertEvent...");
		String sql;
		Connection conn = null;
		Statement stmt = null;
		try {
			// Create connection to the driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			// Connect to db
			conn = DriverManager.getConnection("jdbc:mysql://localhost/cpldb", "root", "");
			stmt = conn.createStatement();

			
			// Create Event
			sql = "INSERT INTO `events` "
					+ " (`event name`, `number of participants`, `current number of participants`, `duration`, `type`, `number of songs`)"
					+ " VALUES ('" + newEvent.getName() + "'  , '" + newEvent.getMax_Paticipating() + "', '" + 0
					+ "', '" + newEvent.getEventDuration() + "', '" + newEvent.getaltype() + "', '"
					+ newEvent.getSongsNumberPerUser() + "')";
			// System.out.println(sql);
			stmt.execute(sql);

			String sql2 = "SELECT * FROM `events`";
			PreparedStatement preparedStatement = conn.prepareStatement(sql2);
			ResultSet result = preparedStatement.executeQuery();
			int pin = 1;
			while (result.next()) {
				pin = result.getInt("PIN");

			}
			System.out.println("we checked and found that the pin is" + pin);
			newEvent.setPIN(pin);
			System.out.println("insertEvent ended successfully");

		} catch (Exception e) {

			System.out.println("error");
		}

	}

	/************************* getUser *******************************/
	public static User getUser(String email) {

		// go to db
		System.out.println("Starting getUser...");
		User dbUser = null;
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection("jdbc:mysql://localhost/cpldb", "root", "");
			String sql = "SELECT * FROM `users` WHERE `email` = '" + email + "'";
			preparedStatement = connection.prepareStatement(sql);
			ResultSet result = preparedStatement.executeQuery();

			if (result.next()) {
				String first_name = result.getString("first name");
				String last_name = result.getString("last name");
				String phone = result.getString("phone");
				String password = result.getString("password");
				email = result.getString("email");
				int type = result.getInt("type");
				int eventPin = result.getInt("eventpin");
				dbUser = new User(first_name, last_name, email, phone, type, password);
				dbUser.setEventPin(eventPin);
				System.out.println("getUser ended successfully");
			}
		} catch (Exception e) {
			System.out.println("error");
		}
		return dbUser;

	}

	/*********************** getEvent ******************************/ // working
	public static Event getEvent(int eventPIN) {
		System.out.println("Starting getEvent...");
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		Event dbEvent = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection("jdbc:mysql://localhost/cpldb", "root", "");
			String sql = "SELECT * FROM `events` WHERE `PIN` = ' " + eventPIN + "'";
			preparedStatement = connection.prepareStatement(sql);
			ResultSet result = preparedStatement.executeQuery();

			if (result.next()) {
				String event_name = result.getString("event name");
				int number_of_participants = result.getInt("number of participants");
				int duration = result.getInt("duration");
				String type = result.getString("type");
				int number_of_songs = result.getInt("number of songs");
				int pin = result.getInt("PIN");
				int current_number_of_participants = result.getInt("current number of participants");
				dbEvent = new Event(pin, event_name, number_of_participants, duration, type, number_of_songs);
				dbEvent.setUsersConfirmed(current_number_of_participants);
				System.out.println(dbEvent);

			}
			System.out.println("getEvent ended successfully");
		} catch (Exception e) {
			System.out.println("error");
		}
		System.out.println("getting event" + dbEvent + "with the pin:" + eventPIN);
		return dbEvent;
	}

	/*********************** updateAdmin ******************************/
	public static void updateAdmin(User u1) {

		System.out.println("Starting updateUser...");
		String sql;
		Connection conn = null;
		Statement stmt = null;
		try {
			// Create connection to the driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			// Connect to db
			conn = DriverManager.getConnection("jdbc:mysql://localhost/cpldb", "root", "");
			stmt = conn.createStatement();

			sql = "UPDATE `users` SET`type`=2,`eventpin`=" + u1.getEventPin() + " WHERE email=\"" + u1.getEmail()
					+ "\"";
			stmt.execute(sql);
			System.out.println("updateAdmin ended successfully");
		} catch (Exception e) {

			System.out.println("error");
		}
	}

	/*********************** updateEvent ******************************/
	public static void updateEvent(Event e1) {

		System.out.println("Starting updateEvent...");
		String sql;
		Connection conn = null;
		Statement stmt = null;
		try {
			// Create connection to the driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			// Connect to db
			conn = DriverManager.getConnection("jdbc:mysql://localhost/cpldb", "root", "");
			stmt = conn.createStatement();

			sql = "UPDATE `events` SET `current number of participants`=" + e1.getUsersConfirmed() + ",`type`='"+e1.getaltype()+"' WHERE PIN="
					+ e1.getPIN();
			stmt.execute(sql);
			System.out.println("updateEvent ended successfully");
		} catch (Exception e) {

			System.out.println("error");
		}
	}

	/*********************** createSongsTable ******************************/
	public static void createSongsTable(int pin) {
		System.out.println("Starting createSongsTable...");
		String sql;
		Connection conn = null;
		Statement stmt = null;
		try {
			// Create connection to the driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			// Connect to SQL
			conn = DriverManager.getConnection("jdbc:mysql://localhost/cpldb", "root", "");
			stmt = conn.createStatement();

			sql = "CREATE TABLE `cpldb`.`songs" + pin + "` " + " ( `ID` INT(5) AUTO_INCREMENT , "
					+ " `owner` VARCHAR(32), "
					+ " `name` VARCHAR(64) CHARACTER SET hebrew COLLATE hebrew_bin NOT NULL, "
					+ " `youtubelink` VARCHAR(500)," + " PRIMARY KEY (`ID`)) " + " ENGINE = InnoDB";
			// PIN have 5 digits,
			// type have 1 digits- A or B
			stmt.execute(sql);

			System.out.println("createSongsTable ended successfully");

		} catch (Exception e) {
			System.out.println("Error");
		}
	}

	/************************ addSong ************************************/
	public static void addSong(int pin, Song temp) {
		System.out.println("Starting addSong...");
		String sql;
		Connection conn = null;
		Statement stmt = null;
		try {
			// Create connection to the driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			// Connect to db
			conn = DriverManager.getConnection("jdbc:mysql://localhost/cpldb", "root", "");
			stmt = conn.createStatement();

			// Create Event
			/*
			 * sql = "INSERT INTO `songs"+pin+"` (`id`, `email`, `url`)" +
			 * " VALUES ('', 'brk.rot@gmail.com', '"+so1+"')"; stmt.execute(sql);
			 */
			sql = "INSERT INTO `songs" + pin + "`  (`owner`, `name`, `youtubelink`)" + " VALUES ('" + temp.getOwner()
					+ "', '" + temp.getSongName() + "', '" + temp.getYouTubeLink() + "')";
			stmt.execute(sql);

			System.out.println("addSong ended successfully");

		} catch (Exception e) {

			System.out.println("error");
		}

	}

	/************************ updateUser ************************************/
	public static void updateUser(User u1) {
		System.out.println("Starting updateUser...");
		String sql;
		Connection conn = null;
		Statement stmt = null;
		try {
			// Create connection to the driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			// Connect to db
			conn = DriverManager.getConnection("jdbc:mysql://localhost/cpldb", "root", "");
			stmt = conn.createStatement();

			sql = "UPDATE `users` SET`type`=1,`eventpin`=" + u1.getEventPin() + " WHERE email=\"" + u1.getEmail()
					+ "\"";
			stmt.execute(sql);
			System.out.println("updateUser ended successfully");
		} catch (Exception e) {

			System.out.println("error");
		}
	}

	/************************ getSongList ************************************/
	public static Vector<Song> getSongList(int pin,String type) {
		System.out.println("Starting getSongList...");
		Vector<Song> my_sort_list = new Vector<Song>();
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection("jdbc:mysql://localhost/cpldb", "root", "");
			String sql;
			//Choose how to get the data from the DB according the algorithm type
			if(type.equals("B")) {
				 sql = "SELECT * FROM `songs" + pin + "` ORDER BY `owner` ASC";
			}else {
				 sql = "SELECT * FROM `songs" + pin + "` ORDER BY `youtubelink` ASC";
			}
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet result = preparedStatement.executeQuery();
			System.out.println("********************from  the db****************");
			while (result.next()) {
				String youtube_link = result.getString("youtubelink");
				String owner = result.getString("owner");
				Song s = new Song(youtube_link, owner);
				String name =result.getString("name");
				s.setSongName(name);
				my_sort_list.add(s);
				System.out.println(s);
			}
			System.out.println("create sort song list successfully");
		} catch (Exception e) {
			System.out.println("error");
		}
		return my_sort_list;

	}
	
	
	
	/************************ dropEvent ************************************/
	public static void dropEvent(Event e1) {
		System.out.println("Starting dropEvent...");
		String sql;
		Connection conn = null;
		Statement stmt = null;
		try {
			// Create connection to the driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			// Connect to db
			conn = DriverManager.getConnection("jdbc:mysql://localhost/cpldb", "root", "");
			stmt = conn.createStatement();

			//Delete the song table
			sql = "DROP TABLE songs" + e1.getPIN();
			stmt.execute(sql);
			
			sql = "DELETE FROM `events` WHERE `events`.`PIN` = " + e1.getPIN();
			stmt.execute(sql);
			
			sql = "UPDATE `users` SET `type`=1, `eventpin`=null WHERE eventpin=" + e1.getPIN();
			stmt.execute(sql);
			
			
			
			
			System.out.println("dropEvent ended successfully");
		} catch (Exception e) {

			System.out.println("error");

		}

	}

	/************************ main ************************************/
	public static void main(String[] args) throws SQLException {
		/*
		 * Event newEvent = new Event("test", 20, 6000, "A", 3); insertEvent(newEvent);
		 * createSongsTable(1234); createSongsTable(123); Song s1 = new
		 * Song("‚Ú‚‚Ú","tyty",0,"brk.rot"); addSong(20694,s1);
		 * addSong(123,"hi2","brk.rot@gmail.com"); Vector<Song> songList =
		 * getSongList(20695);
		 */
	}


}