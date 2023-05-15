package com.dao;
import com.Music.Song;
import com.melosphere.Playlist;
import com.mysql.cj.jdbc.CallableStatement;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@SuppressWarnings("unused")
public class SongDao {
	
	public InputStream playSong(String songName, boolean flag) throws ServletException, IOException, SQLException, ClassNotFoundException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        InputStream inputStream = null;
        int count = 0; // initialize a count variable to zero

        // Connect to the database
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/SDAProjectP1","root","Arfasara1624928");
        if(flag) 
        {
        	// Retrieve the current count from the database
        	statement = connection.prepareStatement("SELECT streams FROM Song WHERE SName = ?");
        	statement.setString(1, songName);
        	resultSet = statement.executeQuery();
        	if (resultSet.next()) {
        		count = resultSet.getInt("streams");
        	}

        	// Increment the count by one
        	count++;

        	// Update the count in the database
        	statement = connection.prepareStatement("UPDATE Song SET streams = ? WHERE SName = ?");
        	statement.setInt(1, count);
        	statement.setString(2, songName);
        	statement.executeUpdate();
        }
        // Prepare the SQL statement to retrieve the BLOB data
        statement = connection.prepareStatement("SELECT SongFile FROM Song WHERE SName = ?");
        statement.setString(1, songName);

        // Execute the SQL statement and get the result set
        resultSet = statement.executeQuery();

        if (resultSet.next()) {
            inputStream = resultSet.getBinaryStream("SongFile");
        }

        if (resultSet != null) {
            resultSet.close();
        }

        if (statement != null) {
            statement.close();
        }

        if (connection != null) {
            connection.close();
        }

        return inputStream;
    }

	public int uploadSong(String SName, String Genre, String Name, LocalDate UDate, InputStream inputStream) throws ServletException, IOException, SQLException, ClassNotFoundException {
	    Connection conn = null;
	    CallableStatement cstmt = null;
	    
	    int result = 0;

	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SDAProjectP1", "root", "Arfasara1624928");
	        cstmt = (CallableStatement) conn.prepareCall("{CALL uploadSong(?, ?, ?, ?, ?, ?)}");

	        cstmt.setString(1, SName);
	        cstmt.setString(2, Name);
	        cstmt.setBlob(3, inputStream);
	        cstmt.setString(4, Genre);
	        cstmt.setDate(5, java.sql.Date.valueOf(UDate));
	        cstmt.registerOutParameter(6, Types.INTEGER);

	        cstmt.execute();

	        result = cstmt.getInt(6);

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        if (cstmt != null) {
	            cstmt.close();
	        }
	        if (conn != null) {
	            conn.close();
	        }
	    }
	    return result;

	}

	
	
	
		
	public static List<Song> getRandomSongs() throws Exception {
		Connection conn = null;
		CallableStatement stmt = null;
	    ResultSet rs = null;
	    List<Song> songs = new ArrayList<>();
	    try {
	    	Class.forName("com.mysql.jdbc.Driver");
	    	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SDAProjectP1","root","Arfasara1624928");
	    	stmt = (CallableStatement) conn.prepareCall("{ call getRandomSongs() }");
	    	rs = stmt.executeQuery();
	    	while (rs.next()) 
	    	{
	    	    Song song = new Song();
	    	    song.setSName(rs.getString("SName"));
	    	    song.setGenre(rs.getString("Genre"));
	    	    song.setAName(rs.getString("AName"));
	    	    java.sql.Date sqlDate = rs.getDate("ReleaseDate");
	    	    if (sqlDate != null) {
	    	        song.setRDate(sqlDate.toLocalDate());
	    	        // Do something with the LocalDate object
	    	    }
	    	    song.setStreams(rs.getInt("streams"));
	    	    songs.add(song);
	    	}
	      
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	        throw e; 
	    } finally {
	        if (rs != null) {
	            try {
	                rs.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (stmt != null) {
	            try {
	                stmt.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    return songs;
	}


	
  
	public boolean addSongToFav(String LName, String SName, String AName) throws Exception {
	    Class.forName("com.mysql.cj.jdbc.Driver");
	    Connection con = null;
	    CallableStatement cs = null;
	    try {
	    	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SDAProjectP1","root","Arfasara1624928");
	    	cs = (CallableStatement) con.prepareCall("{CALL add_favorite_song(?, ?, ?, ?)}");
	    	cs.setString(1, SName);
	    	cs.setString(2, LName);
	    	cs.setString(3, AName);
	    	cs.registerOutParameter(4, Types.BOOLEAN);
	    	cs.execute();
	    	boolean success = cs.getBoolean(4);
	    	return success;
	    } catch (SQLException e) {
	        // handle the error appropriately
	        e.printStackTrace();
	        return false;
	    } finally {
	        if (cs != null) {
	            cs.close();
	        }
	        if (con != null) {
	            con.close();
	        }
	    }
	}
	
	public boolean ListenercreatePlaylist(String Name,String playlistName,String Description) throws Exception {
	Connection conn = null;
	CallableStatement cs = null;
    try {
    	Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SDAProjectP1","root","Arfasara1624928");
    	cs =(CallableStatement) conn.prepareCall("{CALL ListenerCreatePlaylist(?,?,?,?)}");
    	cs.setString(1, Name);
    	cs.setString(2,playlistName);
    	cs.setString(3, Description);
    	
    	
    	cs.registerOutParameter(4, Types.INTEGER);
    	cs.executeQuery();
        int result = cs.getInt(4);
        if(result == 1)
        {
        	return true;
        }
        else
        {
        	return false;
        }
      
    } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
        throw e; 
    } finally {
        if (cs != null) {
            try {
                cs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
}
	public List<Song> DisplayFavSongs(String Name) throws ClassNotFoundException, SQLException
	{
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		List<Song> favoriteSongs = new ArrayList<>();

		try {
		    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SDAProjectP1", "root", "Arfasara1624928");
		    stmt = (CallableStatement) conn.prepareCall("{CALL getFavSongs(?)}");
		    stmt.setString(1, Name);
		    rs = stmt.executeQuery();
		    while (rs.next()) {
		        Song song = new Song();
		        song.setSName(rs.getString("SName"));
		        song.setAName(rs.getString("AName"));
		        song.setGenre(rs.getString("Genre"));
		        java.sql.Date sqlDate = rs.getDate("ReleaseDate");
	    		if (sqlDate != null) {
	    		    song.setRDate(sqlDate.toLocalDate());
	    		}
		        song.setStreams(rs.getInt("streams"));
		        favoriteSongs.add(song);
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		} finally {
		    try {
		        if (rs != null) {
		            rs.close();
		        }
		        if (stmt != null) {
		            stmt.close();
		        }
		        if (conn != null) {
		            conn.close();
		        }
		    } catch (SQLException ex) {
		        ex.printStackTrace();
		    }
		}
		return favoriteSongs;
	}
	public Playlist GetPlaylistSongsListener(String LName,String PName,String PDes)
	{
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		Playlist P = new Playlist();
		List<Song> songs = new ArrayList<>();
        P.setName(PName);
        P.setDescription(PDes);
		try {
		    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SDAProjectP1", "root", "Arfasara1624928");
		    stmt = (CallableStatement) conn.prepareCall("{CALL getPlaylistSongs(?,?)}");
		    stmt.setString(1, LName);
		    stmt.setString(2, PName);
		    rs = stmt.executeQuery();
		    while (rs.next()) 
		    {
		    	Song song = new Song();
		        song.setSName(rs.getString("SName"));
		        song.setAName(rs.getString("AName"));
		        song.setGenre(rs.getString("Genre"));
		        java.sql.Date sqlDate = rs.getDate("ReleaseDate");
	    		if (sqlDate != null) {
	    		    song.setRDate(sqlDate.toLocalDate());
	    		}
		        song.setStreams(rs.getInt("streams"));	 
		        songs.add(song);
		    	
		    }
	       P.setPlaylist(songs);
		} catch (SQLException e) {
		    e.printStackTrace();
		} finally {
		    try {
		        if (rs != null) {
		            rs.close();
		        }
		        if (stmt != null) {
		            stmt.close();
		        }
		        if (conn != null) {
		            conn.close();
		        }
		    } catch (SQLException ex) {
		        ex.printStackTrace();
		    }
		}
		return P;
	}
	
	public boolean addSongToPlaylist(String LName, String PName, String SName, String AName) throws Exception {
	    Class.forName("com.mysql.cj.jdbc.Driver");
	    Connection con = null;
	    CallableStatement cs = null;
	    try {
	    	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SDAProjectP1","root","Arfasara1624928");
	    	cs = (CallableStatement) con.prepareCall("{CALL insertSongIntoPlaylist(?, ?, ?, ?, ?)}");
	    	cs.setString(1, LName);
	    	cs.setString(2, PName);
	    	cs.setString(3, SName);
	    	cs.setString(4, AName);
	    	cs.registerOutParameter(5, Types.BOOLEAN);
	    	cs.execute();
	    	boolean success = cs.getBoolean(5);
	    	return success;
	    } catch (SQLException e) {
	        // handle the error appropriately
	        e.printStackTrace();
	        return false;
	    } finally {
	        if (cs != null) {
	            cs.close();
	        }
	        if (con != null) {
	            con.close();
	        }
	    }
	}

}

