package com.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.CallableStatement;
import com.Music.Song;
import com.melosphere.*;
public class ArtistDao {

	public ArtistDao() {
		// TODO Auto-generated constructor stub
	}
	public boolean artistLogin(String Name, String Password, String Email) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SDAProjectP1","root","Arfasara1624928");
        CallableStatement cs = (CallableStatement)con.prepareCall("{call loginArtist(?, ?, ?, ?)}");
        cs.setString(1, Name);
        cs.setString(2, Password);
        cs.setString(3, Email);
        cs.registerOutParameter(4, -7);
        cs.execute();
        boolean status = cs.getBoolean(4);
        con.close();
        return status;
    }
	
    public boolean artistSignup(String Name, String Password, String Email, String Bio) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SDAProjectP1","root","Arfasara1624928");
        CallableStatement cs = (CallableStatement)con.prepareCall("{call signupArtist(?, ?, ?, ?, ?)}");
        cs.setString(1, Name);
        cs.setString(2, Password);
        cs.setString(3, Email);
        cs.setString(4, Bio);
        cs.registerOutParameter(5, 16);
        cs.execute();
        boolean status = cs.getBoolean(5);
        con.close();
        return status;
    }


public static List<Artist> getRandomArtists() throws Exception {
	Connection conn = null;
	CallableStatement stmt = null;
    ResultSet rs = null;
    List<Artist> artists = new ArrayList<>();
    try {
    	Class.forName("com.mysql.jdbc.Driver");
    	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SDAProjectP1","root","Arfasara1624928");
    	 stmt = (CallableStatement) conn.prepareCall("{ call getRandomArtists() }");
         rs = stmt.executeQuery();
    	while (rs.next()) 
    	{
    		Artist artist = new Artist();
    		artist.setName(rs.getString("AName"));
    		artist.setBio(rs.getString("ABio"));
    		artists.add(artist);
    	
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
    return artists;
}


public Artist getProfile(String Name) throws ClassNotFoundException, SQLException
{
	Connection conn = null;
	CallableStatement stmt = null;
    ResultSet rs = null;
    Artist ArtistData = new Artist();
      
	  try {
		  Class.forName("com.mysql.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SDAProjectP1","root","Arfasara1624928");
	    	stmt = (CallableStatement) conn.prepareCall("{CALL getArtistProfile(?)}");
	    	stmt.setString(1, Name);
	    	rs = stmt.executeQuery();
		
	    	while (rs.next()) 
	    	{
	    		ArtistData.setName(rs.getString("AName"));
	    		ArtistData.setPassword(rs.getString("APassword"));
	    		ArtistData.setEmail(rs.getString("AEmail"));
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

	  return ArtistData;
}

    public List<Song> getTopStreamsforArtist(String AName) throws Exception {
	Connection conn = null;
	CallableStatement stmt = null;
    ResultSet rs = null;
    List<Song> songs = new ArrayList<>();
    try {
    	Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SDAProjectP1","root","Arfasara1624928");
    	stmt = (CallableStatement)conn.prepareCall("{CALL getTopSongs(?)}");
    	stmt.setString(1, AName);
    	rs = stmt.executeQuery();
    	while (rs.next()) 
    	{
    		Song song = new Song();
    		song.setSName(rs.getString("SName"));
    		song.setGenre(rs.getString("Genre"));
    		song.setAName(AName);
    		java.sql.Date sqlDate = rs.getDate("ReleaseDate");
    		if (sqlDate != null) {
    		    song.setRDate(sqlDate.toLocalDate());
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
    
    public List<Song> getSongsByArtist(String AName) throws Exception {
		Connection conn = null;
		CallableStatement stmt = null;
	    ResultSet rs = null;
	    List<Song> songs = new ArrayList<Song>();
	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SDAProjectP1","root","Arfasara1624928");
	        stmt = (CallableStatement)conn.prepareCall("{CALL getArtistSongs(?)}");
	        stmt.setString(1, AName);
	        rs = stmt.executeQuery();
	        while(rs.next()) {
	        	Song song = new Song();
	        	song.setSName(rs.getString("SName"));
	        	song.setAName(AName);
	        	song.setGenre(rs.getString("Genre"));
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
    
    public String getBio(String Name) throws Exception
    {
    	Connection conn = null;
		PreparedStatement stmt = null;
	    ResultSet rs = null;
	    String Bio = "";
	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SDAProjectP1","root","Arfasara1624928");
	        stmt = conn.prepareStatement("SELECT ABio From Artist WHERE AName = (?)");
	        stmt.setString(1, Name);
	        rs = stmt.executeQuery();
	        if(rs.next()) 
	        {
	        	Bio = rs.getString("ABio");
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
	    return Bio;
    	
    }
    public  int deleteArtist(String ArtistName) throws ClassNotFoundException 
	{
		  int ArtistData = 0;
		  Connection conn = null;
		  CallableStatement stmt = null;
		  ResultSet rs = null;

		  try {
		    Class.forName("com.mysql.jdbc.Driver");
		    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SDAProjectP1","root","Arfasara1624928");
		    stmt = (CallableStatement) conn.prepareCall("{CALL deleteArtist(?,?)}");
            stmt.setString(1, ArtistName);
            stmt.registerOutParameter(2, java.sql.Types.INTEGER);
            stmt.execute();
            ArtistData = stmt.getInt(2);

            return ArtistData;
        } catch(SQLException e) {
            e.printStackTrace();
            return -1; 
       }
    }
    public boolean editProfile(String name, String newLPassword) throws Exception {
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    int updatedRows = 0;

	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SDAProjectP1","root","Arfasara1624928");
	        stmt = conn.prepareStatement("UPDATE Artist SET LPassword = ? WHERE AName = ?");
	        stmt.setString(1, newLPassword);
	        stmt.setString(2, name);
	        updatedRows = stmt.executeUpdate();
	        if(updatedRows > 0)
	        {
	        	return true;
	        }
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	        throw e;
	    } finally {
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

	    return false;
	}
    
	
	public boolean deleteSong(String AName, String SName) {
	    boolean success = false;
	    Connection conn = null;
	    CallableStatement stmt = null;

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SDAProjectP1", "root", "Arfasara1624928");
	        stmt = (CallableStatement) conn.prepareCall("{CALL callDeleteSong(?, ?, ?)}");
	        stmt.setString(1, AName);
	        stmt.setString(2, SName);
	        stmt.registerOutParameter(3, Types.BOOLEAN);
	        stmt.execute();
	        success = stmt.getBoolean(3);
	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	    } finally {
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

	    return success;
	}

}