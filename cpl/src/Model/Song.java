package Model;

import java.util.*;

public class Song implements Comparable<Song>{
	String songName;
	String YouTubeLink;
	int songDuration;
	String youTubeId;
	String owner;
	int rank;

	/*
	 * Song Default Ctor
	 */
	public Song() {
		songName = null;
		YouTubeLink = null;
		songDuration = 0;
		youTubeId = null;
		owner = null;
		rank= 0;
		
	}

	public void setSongName(String songName) {
		this.songName = songName;
	}

	/*
	 */
	public Song(String songName, String YouTubeLink, int songDuration, String owner) {
		this.songName = songName;
		this.YouTubeLink = YouTubeLink;
		this.songDuration = songDuration;
		this.owner = owner;
		this.rank = 1;
		this.youTubeId = extractID();
	}

	public Song(String YouTubeLink, String owner) {
		this.songName = null;
		this.YouTubeLink = YouTubeLink;
		this.songDuration = 0;
		this.owner = owner;
		this.rank = 1;
		this.youTubeId = extractID();
	}
	/**
	 * @return
	 */
	public String extractID() {
		boolean flag = false;
		String id = "";
		char[] str;
		str = this.YouTubeLink.toCharArray();
		for (int i = 0; i < str.length; i++) {
			if ((str[i] == 'v') && (str[i + 1] == '=')) {
				i = i + 2;
				flag = true;
			}
			if (flag && str[i] != '&') {
				id += str[i];
			} else {
				flag = false;
			}
		}
		return id;
	}

	public int compareTo(Song s) {
		if(rank==s.getRank())
			return 0;
		else
			if(rank>s.getRank())
				return -1;
			else 
				return 1;
			
	}
	public String getSongName() {
		return songName;
	}

	public String getYouTubeLink() {
		return YouTubeLink;
	}

	public int getSongDuration() {
		return songDuration;
	}

	public String getYouTubeId() {
		return youTubeId;
	}

	public String getOwner() {
		return owner;
	}
	
	public int getRank() {
		return rank;
	}
	
	@Override
	public String toString() {
		return "Song [ rank=" + rank +", owner=" + owner +",songName=" + songName  +  ", YouTubeLink=" + YouTubeLink + ", songDuration=" + songDuration
				+ ", youTubeId=" + youTubeId + "]";
	}

	public static void main(String[] args) {
		String check = "";
		/*Scanner s = new Scanner(System.in);
		check = s.next();
		Song s1 = new Song("name", check, 180,"A@g.com");
		System.out.println(s1);/=*/

	}

	public void incRank() {
	this.rank++;
		
	}
	
}
