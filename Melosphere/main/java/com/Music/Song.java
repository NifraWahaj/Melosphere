package com.Music;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.dao.SongDao;

import jakarta.servlet.ServletException;

public class Song {
	private String SName;
	private String Genre;
	private String AName;
	private int streams;
	private SongDao SDB = new SongDao();
	private LocalDate UDate ;
	private LocalDate RDate ;

    private boolean isFavorite;


	public LocalDate getRDate() {
		return RDate;
	}

	public void setRDate(LocalDate rDate) {
		RDate = rDate;
	}

	public void setUDate() {
		UDate = LocalDate.now();
	}
	
	public LocalDate getUDate() {
		return UDate;
	}
	
	public String getAName() {
		return AName;
	}
	public void setAName(String aName) {
		AName = aName;
	}
	public String getSName() {
		return SName;
	}
	public void setSName(String sName) {
		SName = sName;
	}
	public String getGenre() {
		return Genre;
	}
	public void setGenre(String genre) {
		Genre = genre;
	}
	
	public int uploadSong(InputStream inputStream) throws ClassNotFoundException, ServletException, IOException, SQLException
	{
		return SDB.uploadSong(SName, Genre, AName, UDate, inputStream);
	}
	
	public InputStream listenSong(boolean flag) throws ClassNotFoundException, ServletException, IOException, SQLException 
	{
		InputStream inputStream = null;
		inputStream=SDB.playSong(SName,flag);
		return inputStream;
	}
	public int getStreams() {
		return streams;
	}
	public void setStreams(int streams) {
		this.streams = streams;
	}
	
	
	public boolean addSongToFav(String LName, String SName,String AName) throws Exception  {
		
		if(SDB.addSongToFav(LName, SName, AName))
	    {
	    	return true;
	    }
	    else
	    	return false;
	}

	//pubic boolean getFavPlyalit()
	
}