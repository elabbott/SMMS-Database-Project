package SMMS;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/SMMSDB2";
	private File actors = new File("/Rotten Tomatos Dataset/movie_actors.dat");
	private File countries = new File(
			"/Rotten Tomatos Dataset/movie_countries.dat");
	private File directors = new File(
			"/Rotten Tomatos Dataset/movie_directors.dat");
	private File genres = new File("/Rotten Tomatos Dataset/movie_genres.dat");
	private File locations = new File(
			"/Rotten Tomatos Dataset/movie_locations.dat");
	private File movietags = new File("/Rotten Tomatos Dataset/movie_tags.dat");
	private File movies = new File("/Rotten Tomatos Dataset/movies.dat");
	private File tags = new File("/Rotten Tomatos Dataset/tags.dat");
	private File userratestime = new File(
			"/Rotten Tomatos Dataset/user_ratedmovies-timestamps.dat");
	private File userratedmov = new File(
			"/Rotten Tomatos Dataset/user_ratedmovies.dat");
	private File usertaggedmoviestime = new File(
			"/Rotten Tomatos Dataset/user_taggedmovies-timestamps.dat");
	private File usertaggedmovies = new File(
			"/Rotten Tomatos Dataset/user_taggedmovies.dat");

	public Connect() {

	}

	// so every time we open a connection all we have to do is 'Connection con =
	// openConnection();'
	public Connection openConnection() throws SQLException,
			ClassNotFoundException {
		Class.forName(JDBC_DRIVER);
		Connection con = DriverManager.getConnection(DB_URL, "root", "");
		System.out.println("Connected");
		return con;
	}

	public void populateDBTables() throws ClassNotFoundException, SQLException,
			IOException {
		Statement stmt = null;
		Connection con = openConnection();

					String insert = "LOAD DATA INFILE '/Rotten Tomatos Dataset/movies.dat' INTO TABLE MOVIES;";
					stmt = con.createStatement();
					stmt.executeQuery(insert);
					
			

		
		// System.out.println(input);

	}
}