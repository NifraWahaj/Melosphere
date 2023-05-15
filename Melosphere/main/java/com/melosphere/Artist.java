package com.melosphere;
import com.Music.Song;
import com.dao.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

@SuppressWarnings("unused")
@MultipartConfig
(
	fileSizeThreshold = 1024 * 1024 * 10, // 2MB
	maxFileSize = 1024 * 1024 * 20, // 10MB
	maxRequestSize = 1024 * 1024 * 50 // 50MB
)

public class Artist implements User{
	protected String Name;
	protected String Email;
	protected String Password;
	protected String Bio;
	protected ArtistDao ADB = new ArtistDao();
	private static final long serialVersionUID = 1L;
	@Override
	public boolean logIn() throws ClassNotFoundException, SQLException
	{
			if(ADB.artistLogin(Name, Password, Email)) 
			{
				return true;
			}
			else 
			{
				return false;
			}		
	}
	@Override
	public boolean signUp()throws ClassNotFoundException, SQLException 
	{
			if(ADB.artistSignup(Name, Password, Email, Bio)) 
			{
					return true;
			}
			else 
			{
					return false;
			}		
	}
	@Override
	public boolean createPlaylist(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean switchUser(String name, String email, String password) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public String getName() {
		return Name;
	}
	@Override
	public void setName(String name) {
		Name = name;
	}
	@Override
	public String getEmail() {
		return Email;
	}
	@Override
	public void setEmail(String email) {
		Email = email;
	}
	@Override
	public String getPassword() {
		return Password;
	}
	@Override
	public void setPassword(String password) {
		Password = password;
	}
	
	public String getBio() {
		return Bio;
	}
	
	public void setBio(String bio) {
		Bio = bio;
	}
	@Override
	public List<Playlist> displayPlaylists(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void getProfile() {
		Artist A;
		try {
			A = ADB.getProfile(Name);
			this.setName(A.getName());
			this.setEmail(A.getEmail());
			this.setPassword(A.getPassword());
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public List<Song> getTopStreamsforArtist() throws Exception
	{
		return ADB.getTopStreamsforArtist(Name);
	}
	public List<Song> getSongsByArtist()throws Exception
	{
		return ADB.getSongsByArtist(Name);
	}
	public String getBioFromDB() throws Exception
	{
		return ADB.getBio(Name);
	}
	@Override
	public boolean createPlaylist(String Name, String playlistName, String Description) throws Exception {
		return false;
	}
	@Override
	public Playlist GetPlaylistSongs(String playlistName, String playlistDescription) {
		return null;
	}
	@Override
	public boolean editProfile(String newLPassword) throws Exception
	{
		return ADB.editProfile(Name,newLPassword);	
	}
	public int deleteUser() throws ClassNotFoundException
	{
		return ADB.deleteArtist(Name); 
	}
	public boolean deleteSong(String SName)
	{
		return ADB.deleteSong(Name, SName);
	}
}
