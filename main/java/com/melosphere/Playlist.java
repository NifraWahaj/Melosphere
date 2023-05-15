package com.melosphere;
import com.Music.*;
import java.util.List;
import java.util.ArrayList;


public class Playlist {
    private String name;
    private String description;
    private List<Song> playist;
    
    public Playlist() {
    	name = "";
    	description = "";
    	playist = new ArrayList<>();
      
    }
    

    public Playlist(String name, String description, List<Song> playist) {
        this.name = name;
        this.description = description;
        this.playist = playist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Song> getPlaylist() {
    	if (playist.isEmpty())
    		return null;
        return playist;
    }

    public void setPlaylist(List<Song> playist) {
        this.playist = playist;
    }
    public void addSong(Song song) {
    	playist.add(song);
    }
    public void removeSong(Song song) {
    	//check
        if(playist.equals(null))
        	System.out.println("Your playlist is empty");
        else
        	playist.remove(song);
    }

 
}