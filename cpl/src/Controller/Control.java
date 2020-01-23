package Controller;

import java.sql.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import CPLDB.*;
import Model.Event;
import Model.Song;
import Model.User;
import View.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.Collections;

public class Control {
	public static int dbex = 0;

	/************************
	 * createDB
	 ***********************************************************/
	public static void createDB() {
		DB.createDataBase();

	}

	/************************ checkLogin **********************************/
	public static int checkLogin(HttpServletRequest request) {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		// login User is the object only with login data
		User loginUser = new User(email, password);

		// dbUser is the object with the data from db
		User dbUser = DB.getUser(email);
		System.out.println("control got this user from the session" + dbUser);
		if (dbUser == null)
			return -1;

		if (1 == loginUser.validPassword(dbUser)) {
			if (dbUser.getType() == 2) {
				// If it is the admin put his data in the session
				request.getSession(true).setAttribute("admin", dbUser);
				Event e1 = DB.getEvent(dbUser.getEventPin());
				System.out.println(e1);
				request.getSession(true).setAttribute("event", DB.getEvent(dbUser.getEventPin()));
				return 2;
			} else if (dbUser.getEventPin() != 0) {
				return 3;
			} else {
				// If it is the user put his data in the session
				request.getSession(true).setAttribute("user", dbUser);
				return 1;
			}

		}
		return 0;
	}

	/***************************** checkIfEventExist *****************************/
	public static String checkIfEventExist(HttpServletRequest request) {
		Event e1 = null;
		String req_pinSTR = request.getParameter("pin");
		String returmString = null;
		int pin = Integer.parseInt(req_pinSTR);
		e1 = DB.getEvent(pin);
		// if event isn't exists return 0
		if (e1 == null) {
			returmString = "Invalid pin number try again or create new event";
			return returmString;
		}

		// check if there is empty space
		if (e1.getUsersConfirmed() >= e1.getMax_Paticipating())
			returmString = "There event is fully registered, try make your own party"
					+ "!";

		if(returmString==null) {
			request.getSession(true).removeAttribute("event");
			request.getSession(true).setAttribute("event", e1);
		}
		return returmString;
	}

	/************************ createUser **********************************/
	public static int createUser(HttpServletRequest request) {
		// Extract the parameters and create the user
		String first_name = request.getParameter("first name");
		String last_name = request.getParameter("last name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		int type = 1;
		User newUser = new User(first_name, last_name, email, phone, type, password);
		if (DB.getUser(email) != null) {
			return -1;
		}
		DB.insertUser(newUser);
		// put the object in the sesison for next pages
		request.getSession(true).removeAttribute("user");
		request.getSession().setAttribute("user", newUser);
		return 1;
	}

	/************************ createEvent **********************************/
	public static Event createEvent(HttpServletRequest request) {

		// get information from the request
		String event_name = request.getParameter("event name");
		String participants_number = request.getParameter("participants num");
		int participants_num = Integer.parseInt(participants_number); // convert the request's string to int
		String event_duration = request.getParameter("duration");
		int duration = Integer.parseInt(event_duration); // ditto
		String altype = request.getParameter("type");/// ****we need to check if the type is valid

		// calculate songs number per user in integer int numOfSongs =
		int numOfSongs = (int) (duration / participants_num) + 1;

		// if(numOfSongs < 1) return 0;///***** we need to print(on http screen)
		// "too short event/too much participants"

		Event newEvent = new Event(event_name, participants_num, duration, altype, numOfSongs);
		System.out.println("new event=" + newEvent);
		// newEvent.setEvent(54321, "test", 20, 6000, "A", 3);
		DB.insertEvent(newEvent);
		DB.createSongsTable(newEvent.getPIN());
		System.out.println("The new event is:" + newEvent);

		// In every creating of event we update the user to be manager
		User toBeAdmin = (User) request.getSession(true).getAttribute("user");
		request.getSession(true).removeAttribute("user");

		// Update the user to the pin number of the event
		toBeAdmin.setEventPin(newEvent.getPIN());

		// Upgrade the user to be a manager
		toBeAdmin.setType(2);
		System.out.println("to be admin is" + toBeAdmin);
		DB.updateAdmin(toBeAdmin);

		// Update the session with the information of admin & event
		request.getSession(true).setAttribute("admin", toBeAdmin);
		request.getSession(true).setAttribute("event", newEvent);
		return newEvent;

	}

	/************************ insertSongs ****************************************/
	public static int insertSongs(HttpServletRequest request) {

		Event e1 = (Event) request.getSession(true).getAttribute("event");
		User u1 = (User) request.getSession(true).getAttribute("user");
		String link = "", name = "";
		Song temp;
		int invalid = 0;
		int numberOfSongs = 0;
		String last_invalid = (String)request.getSession().getAttribute("invalid");//we have in the past invalid link so we try again
		if (last_invalid != null)
		{
			numberOfSongs = Integer.parseInt(last_invalid);
		}
		else
		{
			numberOfSongs = e1.getSongsNumberPerUser();
		}
		System.out.println("numberOfSongs: " + numberOfSongs);
		for (int i = 1; i <= numberOfSongs; i++) {

			link = request.getParameter("song" + i).toString();
			boolean valid_link = link.startsWith("https://www.youtube.com/watch?v=");//check if the user insert valid link
			name = request.getParameter("name" + i).toString();
			temp = new Song(name, link, 0, u1.getEmail());
			if(valid_link)
				DB.addSong(e1.getPIN(), temp);
			else
				invalid++;
		}
		System.out.println("value of invalid: " + invalid);
		if(invalid == 0)
		{
			request.getSession().removeAttribute("invalid");
		}
		return invalid;//the number of invalid links

	}
	//***************************************************
		// Sub function isContaind
		public static boolean isContained(Vector<Song> vector, Song s1) {
		
			for (int i = 0; i < vector.size(); i++) {
				if ((vector.elementAt(i).getYouTubeId()).equals(s1.getYouTubeId())) {
					return true;
				}
			}
			return false;

		}
	//**********************************************************
	/************************
	 * createPlaylistEquality
	 * 
	 * 
	 ****************************************/
	public static String createPlaylistEquality(Event e1, User u1) {
		System.out.println("equlity function");
		String finalList="";
		String finalLink = "http://www.youtube.com/watch_videos?video_ids=";
		int pin = e1.getPIN();

		// get vector contain sort list by owner
		Vector<Song> songList = DB.getSongList(pin, e1.getaltype());
		int n = songList.size();

		int songsPerUser = e1.getSongsNumberPerUser();
		Vector<Song> finalSongList= new Vector<Song>();;
		// put the first song from every user
		// if exists take the next sonf from the user

		int i = 0;
		float over_time = (float) e1.getEventDuration(); // get the duration
		float dur_count = 0; // for each song will add 3.5 min
		String current_owner = ""; // initial the first owner to
		boolean taken = false;
		int repeatSamePerson=0;
		while (dur_count <= over_time && !songList.isEmpty()) { // if there is enough length to add another song &&
			// there is more songs in the vector
//System.out.println(over_time);
//System.out.println(dur_count);
System.out.println(songList);
System.out.println(finalSongList);
			if (!taken)// we didn't take yet song from the current_owner this round
			{
				if (!isContained(finalSongList, songList.elementAt(i))) // the list not contain this song
				{
					finalSongList.add(songList.elementAt(i));
					songList.remove(i);
					i = i % songList.size();
					dur_count += 3.5;
					current_owner = songList.elementAt(i).getOwner();
					taken = true;
				} else// the song already exist
				{
					System.out.println("remove");
					songList.remove(i);
					if(songList.size()!=0)
					i = i % songList.size();
				}
			} else if (!current_owner.equals(songList.elementAt(i).getOwner()) || repeatSamePerson>=songList.size())// we found the next owner
			{
				repeatSamePerson=0;
				taken = false;
				} else {
					repeatSamePerson++;
					i++;// we already take song from this owner and look for the next owner
					i = i % songList.size();
				}

		}
		// now we have the playlist as a vector and we need to convert it to "finalLink"

		while (!finalSongList.isEmpty()) { // if there is more songs in the vector
			finalLink += (finalSongList.firstElement()).getYouTubeId();
			finalList+=finalSongList.firstElement().getSongName()+",";
			finalLink += ",";
			finalSongList.remove(0);
		}

		System.out.println(finalLink);
		System.out.println(finalList);
		return finalLink;
	}

	/************************
	 * createPlaylistPopularity
	 * 
	 * 
	 ****************************************/
	public static String createPlaylistPopularity(Event e1, User u1) {
		System.out.println("popularity function");
		String finalList="";
		String finalLink = "http://www.youtube.com/watch_videos?video_ids=";
		int pin = e1.getPIN();

		Vector<Song> songList = DB.getSongList(pin, e1.getaltype());// get vector contain sort list by link
		int n = songList.size();
		String s1, s2;
		// remove multiple songs and increase their rank
		int i = 0;
		while (i < (songList.size() - 1)) {
			s1 = (songList.get(i)).getYouTubeId();
			s2 = (songList.get(i + 1)).getYouTubeId();
			if (s1.equals(s2)) {
				songList.remove(i + 1);
				(songList.get(i)).incRank();

			} else
				i++;
		}

		// algorithm B
		n = songList.size();
		System.out.println("********************after rank****************");
		for (int j = 0; j < songList.size(); j++) {
			System.out.println(songList.elementAt(j));
		}

		Collections.sort(songList);

//		boolean flag_not_full = true;
//		
//		
//		while(flag_not_full)
//		{
//			
//		}

		System.out.println("********************after sort****************");
		for (int j = 0; j < songList.size(); j++) {
			System.out.println(songList.elementAt(j));
		}

		// add the songs to the playlist according the event duration:

		float over_time = (float) e1.getEventDuration(); // get the duration
		float dur_count = 0; // for each song will add 3.5 min
		while (dur_count <= over_time && !songList.isEmpty()) { // if there is enough length to add another song &&
																// there is more songs in the vector
			finalLink += songList.firstElement().getYouTubeId();
			finalList+=songList.firstElement().getSongName()+",";
			finalLink += ",";
			dur_count += 3.5;
			songList.remove(0);
		}

		System.out.println(finalLink);
		System.out.println(finalList);
		return finalLink;
	}
//*****************************************************************
	public static String createPlaylist(HttpServletRequest request) {

		String url;
		// Extract the event and the user
		Event e1 = (Event) request.getSession(true).getAttribute("event");
		User u1 = (User) request.getSession(true).getAttribute("admin");
		System.out.println(e1);
		System.out.println(u1);
		if (e1.getaltype().equals("B")) {
			url = createPlaylistEquality(e1, u1);
		} else {
			url = createPlaylistPopularity(e1, u1);
		}
		return url;

	}

	public static int UserJoin(HttpServletRequest request) {
		User u1 = (User) request.getSession(true).getAttribute("user");
		Event e1 = (Event) request.getSession(true).getAttribute("event");
		/*
		 * if (u1.getEventPin() != 0) { return 0; }
		 */
		u1.setEventPin(e1.getPIN());
		request.getSession(true).removeAttribute("user");
		request.getSession(true).setAttribute("user", u1);
		DB.updateUser(u1);

		e1.incUsersConfirmed();
		request.getSession(true).removeAttribute("event");
		request.getSession(true).setAttribute("event", e1);
		DB.updateEvent(e1);

		return 1;
	}

	// ************************dropEvent****************************************//
	public static int endEvent(HttpServletRequest request) {
		Event e1 = (Event) request.getSession(true).getAttribute("event");
		// Here we call the db inn order to delete the song table & make all the users
		// free from the event & set admin to regular user
		DB.dropEvent(e1);
		return 0;
	}

//************************main****************************************//	
	public static void main(String[] args) throws SQLException {
		// createDB();

	}

	public static void updateEvent(Event e1) {
		DB.updateEvent(e1);
		
	}

}
