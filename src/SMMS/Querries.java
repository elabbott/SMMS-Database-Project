package SMMS;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Querries extends Connect{
	public ArrayList<Movie> query_1() throws ClassNotFoundException, SQLException{
		Connection con = openConnection();
		Statement stmt = con.createStatement();
		String query = "SELECT M.title, M.year, M.rtAudienceScore, M.rtPictureURL, M.imdbPictureURL ";
		query += "FROM MOVIES M "; 
		query += "ORDER BY M.rtAudienceScore desc ";
		query += "limit 20;";
		ResultSet rs = stmt.executeQuery(query);
		ArrayList<Movie> movieArray = new ArrayList<Movie>();
		while(rs.next()){
			//get values
			String title = rs.getString("title");
			int year = rs.getInt("year");
			int rtAudienceScore = rs.getInt("rtAudienceScore");
			String rtPictureURL = rs.getString("rtPictureURL");
			String imdbPictureURL = rs.getString("imdbPictureURL");
			
			Movie movie = new Movie(title, year, rtAudienceScore, rtPictureURL, imdbPictureURL);
			movieArray.add(movie);
		}
		con.close();
		return movieArray;
	}
	public ArrayList<Movie> query_2(String Movie_Title_Input) throws ClassNotFoundException, SQLException{
		Connection con = openConnection();
		Statement stmt = con.createStatement();
		String query = "SELECT M.title, M.year, M.rtAudienceScore, M.rtPictureURL, M.imdbPictureURL, group_concat(T.value separator ', ') as valu  ";
		query += "FROM MOVIES M, MOVIE_TAGS MT, TAGS T ";
		query += "WHERE M.title like '%" + Movie_Title_Input + "%' and MT.movieID=M.id and T.id=MT.tagID ";
		query += "GROUP BY M.title;";
		
		ResultSet rs = stmt.executeQuery(query);
		ArrayList<Movie> movieArray = new ArrayList<Movie>();
		
		while(rs.next()){
			//get values
			String title = rs.getString("title");
			int year = rs.getInt("year");
			int rtAudienceScore = rs.getInt("rtAudienceScore");
			String rtPictureURL = rs.getString("rtPictureURL");
			String imdbPictureURL = rs.getString("imdbPictureURL");
			Blob tags = rs.getBlob("valu");
			byte[] tags_data = tags.getBytes(1, (int) tags.length());
			String tags_string = new String(tags_data);
			
			tags_string = tags_string.replace("\n", "");
			ArrayList<String> tag_list= new ArrayList<String>(Arrays.asList(tags_string.split(" , ")));
			System.out.print(tag_list.toString());
			
			
			Movie movie = new Movie(title, year, rtAudienceScore, rtPictureURL, imdbPictureURL, tag_list);
			movieArray.add(movie);
		}
		con.close();
		return movieArray;
	}
	public ArrayList<Movie> query_3(String Genre_Type_Input) throws ClassNotFoundException, SQLException{
		Connection con = openConnection();
		Statement stmt = con.createStatement();
		String query = "SELECT distinct M.title, M.year, M.rtAudienceScore, M.rtPictureURL, M.imdbPictureURL "; 
		query += "FROM MOVIES M, MOVIE_GENRES MG ";
		query += "WHERE MG.genre like '%"+ Genre_Type_Input+"%' and MG.movieID=M.id ";
		query += "ORDER BY M.rtAudienceScore desc ";
		query += "limit 20;";
		
		ResultSet rs = stmt.executeQuery(query);
		ArrayList<Movie> movieArray = new ArrayList<Movie>();
		while(rs.next()){
			//get values
			String title = rs.getString("title");
			int year = rs.getInt("year");
			int rtAudienceScore = rs.getInt("rtAudienceScore");
			String rtPictureURL = rs.getString("rtPictureURL");
			String imdbPictureURL = rs.getString("imdbPictureURL");
			
			Movie movie = new Movie(title, year, rtAudienceScore, rtPictureURL, imdbPictureURL);
			movieArray.add(movie);
			}
		con.close();
		return movieArray;
	}
	public ArrayList<Movie> query_4(String Director_Name_Input) throws ClassNotFoundException, SQLException{
		Connection con = openConnection();
		Statement stmt = con.createStatement();
		String query = "SELECT distinct M.title, M.year, M.rtAudienceScore, M.rtPictureURL, M.imdbPictureURL "; 
		query += "FROM MOVIES M, MOVIE_DIRECTORS MD ";
		query += "WHERE MD.directorName like '%" + Director_Name_Input + "%' and MD.movieID=M.id;";
		
		ResultSet rs = stmt.executeQuery(query);
		ArrayList<Movie> movieArray = new ArrayList<Movie>();
		while(rs.next()){
			//get values
			String title = rs.getString("title");
			int year = rs.getInt("year");
			int rtAudienceScore = rs.getInt("rtAudienceScore");
			String rtPictureURL = rs.getString("rtPictureURL");
			String imdbPictureURL = rs.getString("imdbPictureURL");
			
			Movie movie = new Movie(title, year, rtAudienceScore, rtPictureURL, imdbPictureURL);
			movieArray.add(movie);
			}
		con.close();
		return movieArray;
	}
	public ArrayList<Movie> query_5(String Actor_Name_Input) throws ClassNotFoundException, SQLException{
		Connection con = openConnection();
		Statement stmt = con.createStatement();
		String query = "SELECT distinct M.title, M.year, M.rtAudienceScore, M.rtPictureURL, M.imdbPictureURL ";
		query += "FROM MOVIES M, MOVIE_ACTORS MA ";
		query += "WHERE MA.actorName like '%" + Actor_Name_Input + "%' and MA.movieID=M.id;";
		
		ResultSet rs = stmt.executeQuery(query);
		ArrayList<Movie> movieArray = new ArrayList<Movie>();
		while(rs.next()){
			//get values
			String title = rs.getString("title");
			int year = rs.getInt("year");
			int rtAudienceScore = rs.getInt("rtAudienceScore");
			String rtPictureURL = rs.getString("rtPictureURL");
			String imdbPictureURL = rs.getString("imdbPictureURL");
			
			Movie movie = new Movie(title, year, rtAudienceScore, rtPictureURL, imdbPictureURL);
			movieArray.add(movie);
			}
		con.close();
		return movieArray;
	}
	public ArrayList<Movie> query_6(String Tag_Value_Input) throws ClassNotFoundException, SQLException{
		Connection con = openConnection();
		Statement stmt = con.createStatement();
		String query = "SELECT distinct M.title, M.year, M.rtAudienceScore, M.rtPictureURL, M.imdbPictureURL ";
		query += "FROM MOVIES M, TAGS T, MOVIE_TAGS MT ";
		query += "WHERE T.value like '%" + Tag_Value_Input + "%' and T.id=MT.tagID and MT.movieID=M.id limit 100;";
		
		ResultSet rs = stmt.executeQuery(query);
		ArrayList<Movie> movieArray = new ArrayList<Movie>();
		while(rs.next()){
			//get values
			String title = rs.getString("title");
			int year = rs.getInt("year");
			int rtAudienceScore = rs.getInt("rtAudienceScore");
			String rtPictureURL = rs.getString("rtPictureURL");
			String imdbPictureURL = rs.getString("imdbPictureURL");
			
			Movie movie = new Movie(title, year, rtAudienceScore, rtPictureURL, imdbPictureURL);
			movieArray.add(movie);
			}
		con.close();
		return movieArray;
	}
	public ArrayList<Movie> query_7() throws ClassNotFoundException, SQLException{
		Connection con = openConnection();
		Statement stmt = con.createStatement();
		String query = "select MD.directorName ";
		query += "from MOVIE_DIRECTORS MD ";
		query += "where MD.directorName ";
		query += "in ( ";
		query += "select avg(M.rtAudienceScore) ";
		query += "from MOVIES M ";
		query += "where MD.movieID = M.id ";
		query += "order by M.rtAudienceScore desc) ";
		query += "limit 20;";
		
		ResultSet rs = stmt.executeQuery(query);
		ArrayList<Movie> movieArray = new ArrayList<Movie>();
		while(rs.next()){
			//get values
			String directorName = rs.getString("directorName");
			
			//display values
			//System.out.println("Director's Name: " + directorName);
			Movie movie = new Movie();
			movie.setDirectorName(directorName);
			movieArray.add(movie);
			
		}
		con.close();
		return movieArray;
	}
	public ArrayList<Movie> query_8() throws ClassNotFoundException, SQLException{
		Connection con = openConnection();
		Statement stmt = con.createStatement();
		String query = "select MA.actorName ";
		query += "from MOVIE_ACTORS MA ";
		query += "where MA.actorName ";
		query += "in ( ";
		query += "select avg(M.rtAudienceScore) ";
		query += "from MOVIES M ";
		query += "where MA.movieID = M.id ";
		query += "order by M.rtAudienceScore desc) ";
		query += "limit 20;";
		
		ResultSet rs = stmt.executeQuery(query);
		ArrayList<Movie> movieArray = new ArrayList<Movie>();
		while(rs.next()){
			//get values
			String actorName = rs.getString("actorName");
			
			Movie movie = new Movie();
			movie.setActorName(actorName);
			movieArray.add(movie);
			
		}
		con.close();
		return movieArray;
	}
	public ArrayList<Movie> query_9(String Country_Input) throws ClassNotFoundException, SQLException{
		Connection con = openConnection();
		Statement stmt = con.createStatement();
		String query = "SELECT distinct M.title, M.year, M.rtAudienceScore, M.rtPictureURL, M.imdbPictureURL ";
		query += "FROM MOVIES M, MOVIE_COUNTRIES MC ";
		query += "WHERE MC.country like '%" + Country_Input + "%' and MC.movieID=M.id limit 100;";
//		query += "WHERE MA.actorName like '%" + Actor_Name_Input + "%' and MA.movieID=M.id;";
		
		ResultSet rs = stmt.executeQuery(query);
		ArrayList<Movie> movieArray = new ArrayList<Movie>();
		while(rs.next()){
			//get values
			String title = rs.getString("title");
			int year = rs.getInt("year");
			int rtAudienceScore = rs.getInt("rtAudienceScore");
			String rtPictureURL = rs.getString("rtPictureURL");
			String imdbPictureURL = rs.getString("imdbPictureURL");
			
			//display values
			//System.out.println("Title: " + title);
			Movie movie = new Movie(title, year, rtAudienceScore, rtPictureURL, imdbPictureURL);
			movieArray.add(movie);
		}
		con.close();
		return movieArray;
	}
	public ArrayList<Movie> query_10(String Location_Input) throws ClassNotFoundException, SQLException{
		Connection con = openConnection();
		Statement stmt = con.createStatement();
		String query = "SELECT distinct M.title, M.year, M.rtAudienceScore, M.rtPictureURL, M.imdbPictureURL ";
		query += "FROM MOVIES M, MOVIE_LOCATIONS ML ";
		query += "WHERE ML.movieID=M.id and (ML.location1 like '%" + Location_Input + "%' or ML.location2 like '%" + Location_Input + "%' or ML.location3 like '%" + Location_Input + "%' or ML.location4 like '%" + Location_Input + "%') limit 100;";
		
		ResultSet rs = stmt.executeQuery(query);
		ArrayList<Movie> movieArray = new ArrayList<Movie>();
		while(rs.next()){
			//get values
			String title = rs.getString("title");
			int year = rs.getInt("year");
			int rtAudienceScore = rs.getInt("rtAudienceScore");
			String rtPictureURL = rs.getString("rtPictureURL");
			String imdbPictureURL = rs.getString("imdbPictureURL");
			
			Movie movie = new Movie(title, year, rtAudienceScore, rtPictureURL, imdbPictureURL);
			movieArray.add(movie);
		}
		con.close();
		return movieArray;
	}
	
}