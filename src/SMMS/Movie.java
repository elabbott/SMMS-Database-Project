package SMMS;

import java.util.ArrayList;

public class Movie {

	String title, rtPictureURL, imdbPictureURL, directorName, actorName;
	ArrayList<String> tags = new ArrayList<String>();
	int year, rtAudienceScore;
	
	public Movie(){
		
	}
	public Movie(String title, int year, int rtAudienceScore, String rtPictureURL, String imdbPictureURL, ArrayList<String> tags){
		this.title =title;
		this.year= year;
		this.rtAudienceScore = rtAudienceScore;
		this.rtPictureURL = rtPictureURL;
		this.imdbPictureURL = imdbPictureURL;
		this.tags = tags;
	}
	public Movie(String title, int year, int rtAudienceScore, String rtPictureURL, String imdbPictureURL){
		this.title =title;
		this.year= year;
		this.rtAudienceScore = rtAudienceScore;
		this.rtPictureURL = rtPictureURL;
		this.imdbPictureURL = imdbPictureURL;
	}
	public String getTitle(){
		return title;
	}
	public int getYear(){
		return year;
	}
	public int getRTAudienceScore(){
		return rtAudienceScore;
	}
	public String getRTPictureURL(){
		return rtPictureURL;
	}
	public String getIMDBPictureURL(){
		return imdbPictureURL;
	}
	public ArrayList<String> getTags(){
		return tags;
	}
	public String getDirectorName(){
		return directorName;
	}
	public String getActorName(){
		return actorName;
	}
	public void setTitle(String title){
		this.title = title;
	}
	public void setDirectorName(String directorName){
		this.directorName = directorName;
	}
	public void setActorName(String actorName){
		this.actorName = actorName;
	}
	
}
