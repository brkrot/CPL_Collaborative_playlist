package Model;
import java.util.Vector;

import CPLDB.*;
public class User {
	String first_name;
	String last_name;
	String email;
	String phone;
	String password;
	int eventPin;
	int type;
	Vector <Song> listOfSongs;
	
	
public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public Vector<Song> getListOfSongs() {
		return listOfSongs;
	}

	public void setListOfSongs(Vector<Song> listOfSongs) {
		this.listOfSongs = listOfSongs;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setPassword(String password) {
		this.password = password;
	}

//************************constructor***********************************************************//	
public User(String first_name,String last_name,String email,String phone,int type,String password) {
	this.first_name = first_name;
	this.last_name = last_name;
	this.email =  email;
	this.phone= phone;
	this.type= type;
	this.password=password;
	this.listOfSongs = null;
	this.eventPin=0;
}

public void setType(int type) {
	this.type = type;
}

//************************default constructor***********************************************************//
public User() {
	// TODO Auto-generated constructor stub
}

//************************semi-constructor***********************************************************//
public User(String email, String password) {
	this.email =  email;
	this.password=password;
}

//************************validPassword**********************************************************//
// get User(original from DB) and check if our password(this) identity to the original(from DB) 
public int validPassword(User dbuser) {
	if(this.password.equals(dbuser.password))
		return 1;
	return 0;
}



//************************set**********************************************************//
//set all value of some User
public void set(String first_name,String last_name,String email,String phone,int type,String password) {
	this.first_name = first_name;
	this.last_name = last_name;
	this.email =  email;
	this.phone= phone;
	this.type= type;
	this.password=password;
}

public String getFirst() {
	return this.first_name;
}

public String getLast() {
	return this.last_name;
}

//************************getEmail**********************************************************//
public String getEmail() {
	return this.email;
}

public String getPhone() {
	return this.phone;
}

public int getType() {
	return this.type;
}

public String getPassword() {
	return this.password;
}

public int getEventPin() {
	return this.eventPin;
}

public void setEventPin(int eventPin) {
	this.eventPin = eventPin;
}

@Override
public String toString() {
	return "User [first_name=" + first_name + ", last_name=" + last_name + ", email=" + email + ", phone=" + phone
			+ ", password=" + password + ", eventPin=" + eventPin + ", type=" + type + ", listOfSongs=" + listOfSongs
			+ "]";
}




}//class