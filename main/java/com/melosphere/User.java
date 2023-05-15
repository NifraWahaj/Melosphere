package com.melosphere;
//import package Music; 
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.Music.*;
@SuppressWarnings("unused")
public interface User {
	boolean logIn() throws ClassNotFoundException, SQLException;
	boolean signUp() throws ClassNotFoundException, SQLException;
	boolean createPlaylist(String name);
	//boolean addSongtoplaylist(Song S);
	//boolean removesongfromPlaylist(Playlist P,Song S);
	boolean switchUser(String name, String email, String password);
	public int deleteUser() throws ClassNotFoundException;
	public String getName();
	public void setName(String name);
	public String getEmail();
	public void setEmail(String email);
	public String getPassword();
	public void setPassword(String password);
	//List<Playlist> displayPlaylists(String name);
	List<Playlist> displayPlaylists(String name);
	void getProfile();
	List<Song> getTopStreamsforArtist() throws Exception;
	List<Song> getSongsByArtist() throws Exception;
	String getBioFromDB() throws Exception;
	public String getBio();
	public boolean createPlaylist(String Name,String playlistName,String Description) throws Exception;
	public Playlist GetPlaylistSongs(String playlistName, String playlistDescription);
	public boolean editProfile(String newLPassword) throws Exception;

	
}
