package Model;

import java.util.Vector;

import CPLDB.*;

public class Event {
	int PIN;
	String name;
	int max_Paticipating;
	int eventDuration;// in min's
	String altype;
	int songsNumberPerUser;
	int usersConfirmed;// how many did sign details
	Vector<User> registered;

	//************************constructor***********************************************************//	
	public Event(int PIN, String name, int max_Paticipating, int eventDuration, String altype, int songsNumberPerUser) {
		this.PIN = PIN;
		this.name = name;
		this.max_Paticipating = max_Paticipating;
		this.eventDuration = eventDuration;
		this.altype = altype;
		this.songsNumberPerUser = songsNumberPerUser;
		this.registered = null;
		this.usersConfirmed = 0;
	}

	// ************************semi-constructor***********************************************************//
	public Event(String name, int max_Paticipating, int eventDuration, String altype, int songsNumberPerUser) {
		this.name = name;
		this.max_Paticipating = max_Paticipating;
		this.eventDuration = eventDuration;
		this.altype = altype;
		this.songsNumberPerUser = songsNumberPerUser;
	}

	// ************************default
	// constructor***********************************************************//
	public Event() {

	}

//************************set + get**********************************************************//
	public void setEvent(int PIN, String name, int max_Paticipating, int eventDuration, String altype,
			int songsNumberPerUser) {
		this.PIN = PIN;
		this.name = name;
		this.max_Paticipating = max_Paticipating;
		this.eventDuration = eventDuration;
		this.altype = altype;
		this.songsNumberPerUser = songsNumberPerUser;
	}

	public int getPIN() {
		return PIN;
	}

	@Override
	public String toString() {
		return "Event [PIN=" + PIN + ", name=" + name + ", max_Paticipating=" + max_Paticipating + ", eventDuration="
				+ eventDuration + ", altype=" + altype + ", songsNumberPerUser=" + songsNumberPerUser
				+ ", usersConfirmed=" + usersConfirmed + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMax_Paticipating() {
		return max_Paticipating;
	}

	/*public void setMax_Paticipating(int max_Paticipating) {
		this.max_Paticipating = max_Paticipating;
	}*/

	public int getEventDuration() {
		return eventDuration;
	}

	/*public void setEventDuration(int eventDuration) {
		this.eventDuration = eventDuration;
	}*/

	public String getaltype() {
		return altype;
	}

	/*public void setaltype(String altype) {
		this.altype = altype;
	}*/

	public int getSongsNumberPerUser() {
		return songsNumberPerUser;
	}

	/*public void setSongsNumberPerUser(int songsNumberPerUser) {
		this.songsNumberPerUser = songsNumberPerUser;
	}*/

	public int getUsersConfirmed() {
		return usersConfirmed;
	}
	
	public void switchAlgorithm() {
		if((this.altype).equals("A")) {
			this.altype="B";
		}else if((this.altype).equals("B")) {
			this.altype = "A";
		}
	}
	
	public void setUsersConfirmed(int usersConfirmed) {
		this.usersConfirmed = usersConfirmed;
	}

	public void incUsersConfirmed() {
		this.usersConfirmed++;
	}

	public static void main(String[] args) {
		
	}

	public void setPIN(int pIN) {
		PIN = pIN;
	}



}
