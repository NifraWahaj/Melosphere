package com.melosphere;
import com.Music.Song;
import com.dao.*;

import java.sql.SQLException;
import java.util.List;



public class Listener implements User{

	
	protected String Name;
	protected String Email;
	protected String Password;
	protected ListenerDao LDB = new ListenerDao();
	protected SongDao SD = new SongDao();
	protected int songID;
	private static final long serialVersionUID = 1L;

	protected List<Playlist> L_playlists;
	//protected List<Song> uploadedSongs;
	//protected List<Playlist> favList ; 
	//protected List<Artist> favArtist ;
	//protected List<Artist> followingArtists;
	//protected List<Song> blockedSongs;
	
	public Listener() {
		L_playlists = null;
	}
	@Override
	public boolean logIn() throws ClassNotFoundException, SQLException
	{
			if(LDB.listenerLogin(Name, Password, Email)) 
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
			if(LDB.listenerSignup(Name, Password, Email)) 
			{
					return true;
			}
			else 
			{
					return false;
			}		
	}
	
	
	@Override
	public List<Playlist> displayPlaylists(String name) {
		
		try 
		{
		  L_playlists = LDB.getPlaylists(name);
		} 
		 catch (Exception e) {
			e.printStackTrace();
		 }
		return L_playlists;
	}
	
	@Override
	public boolean switchUser(String name, String email, String password) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean checkfollowArtist(String AName)throws ClassNotFoundException, SQLException {
	    if(LDB.checkfollowArtist(Name, AName))
	    {
	    	return true;
	    }
	    else
	    	return false;
	}
	
	public boolean followArtist(String AName) throws ClassNotFoundException, SQLException {
	    if(LDB.followArtist(Name, AName))
	    {
	    	return true;
	    }
	    else
	    	return false;
	}
	
	public boolean unfollowArtist(String AName) throws ClassNotFoundException, SQLException {
		if(LDB.unfollowArtist(Name, AName))
	    {
	    	return true;
	    }
	    else
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
	
	@Override
	public boolean createPlaylist(String name) {
		// TODO Auto-generated method stub
		return false;
	}
	public List<Song> DisplayFavSongs() throws ClassNotFoundException, SQLException
	{
		return SD.DisplayFavSongs(Name);
	}
	public void getProfile()
	{
		Listener L;
		try {
			L = LDB.getProfile(Name);
			this.setName(L.getName());
			this.setEmail(L.getEmail());
			this.setPassword(L.getPassword());
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	@Override
	public List<Song> getTopStreamsforArtist() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Song> getSongsByArtist() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getBioFromDB() throws Exception{
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getBio() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean createPlaylist(String Name,String playlistName,String Description) throws Exception
	{
	    if(SD.ListenercreatePlaylist(Name,playlistName,Description))
	    {
	    	return true;
	    }
	    else
	    {
	    	return false;
	    }
	}
	
	public Playlist GetPlaylistSongs(String PName, String PDes)
	{
		return SD.GetPlaylistSongsListener(Name,PName,PDes);
	}
	public List<Artist> getFollowingArtists() throws Exception
	{
		return LDB.GetFollowingArtist(Name);
	}
	public boolean addSongToPlaylist(String PName, String SName, String AName) throws Exception
	{
		if(SD.addSongToPlaylist(Name,PName,SName,AName))
		{
			return true;
		}
		return false;
	}
	public boolean editProfile(String newLPassword) throws Exception
	{
		return LDB.editProfile(Name,newLPassword);	
	}
	public int deleteUser() throws ClassNotFoundException
	{
		return LDB.deleteListener(Name); 
	}
	public boolean deletePlaylist(String PName) throws ClassNotFoundException, SQLException
	{
		return LDB.deletePlaylist(Name, PName);
	}
	public boolean DeleteSongFromFav(String decodedAName,String decodedSName) throws ClassNotFoundException, SQLException
	{
		return LDB.DeleteSongFromFav(Name,decodedAName,decodedSName);
	}
	public boolean DeleteSongFromPlaylist(String decodedAName,String decodedSName, String decodedPName) throws ClassNotFoundException, SQLException
	{
		return LDB.DeleteSongFromPlaylist(Name,decodedAName,decodedSName,decodedPName);
	}
	
	
}