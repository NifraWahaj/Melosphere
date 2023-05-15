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

public class ListenerDao {

	public ListenerDao() {
		// TODO Auto-generated constructor stub
	}
	public boolean listenerLogin(String Name, String Password, String Email) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SDAProjectP1","root","Arfasara1624928");
        CallableStatement cs = (CallableStatement)con.prepareCall("{call loginListener(?, ?, ?, ?)}");
        cs.setString(1, Name);
        cs.setString(2, Password);
        cs.setString(3, Email);
        cs.registerOutParameter(4, -7);
        cs.execute();
        boolean status = cs.getBoolean(4);
        con.close();
        return status;
    }

    public boolean listenerSignup(String Name, String Password, String Email) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SDAProjectP1","root","Arfasara1624928");
        CallableStatement cs = (CallableStatement)con.prepareCall("{call signupListener(?, ?, ?, ?)}");
        cs.setString(1, Name);
        cs.setString(2, Password);
        cs.setString(3, Email);
        cs.registerOutParameter(4, 16);
        cs.execute();
        boolean status = cs.getBoolean(4);
        con.close();
        return status;
    }

    public boolean checkfollowArtist(String LName, String AName) throws ClassNotFoundException, SQLException {
        boolean recordExists = false;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SDAProjectP1","root","Arfasara1624928");
        
        try {
            CallableStatement cstmt = (CallableStatement) con.prepareCall("{call check_following_artist(?, ?, ?)}");
            cstmt.setString(1, LName);
            cstmt.setString(2, AName);
            cstmt.registerOutParameter(3, Types.INTEGER);
            cstmt.execute();
            int count = cstmt.getInt(3);
            
            if (count > 0) {
                recordExists = true;
            }
            
            cstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        }

        return recordExists;
    }

    
    public boolean followArtist(String LName, String AName) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        CallableStatement stmt = null;
        boolean success = false;
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SDAProjectP1", "root", "Arfasara1624928");
        stmt = (CallableStatement) conn.prepareCall("{call InsertFollowArtist(?, ?, ?)}");
        stmt.setString(1, LName);
        stmt.setString(2, AName);
        stmt.registerOutParameter(3, Types.INTEGER);
        stmt.execute();
        int result = stmt.getInt(3);
        if (result == 1) {
            success = true;
        }
        stmt.close();
        conn.close();
        return success;
    }
	
	public boolean unfollowArtist(String LName, String AName) throws ClassNotFoundException, SQLException {
	    Connection conn = null;
	    CallableStatement stmt = null;
	    boolean success = false;
	    Class.forName("com.mysql.jdbc.Driver");
	    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SDAProjectP1", "root", "Arfasara1624928");
	    stmt = (CallableStatement) conn.prepareCall("{call UnFollowArtist(?, ?, ?)}");
	    stmt.setString(1, LName);
	    stmt.setString(2, AName);
	    stmt.registerOutParameter(3, Types.INTEGER);
	    stmt.execute();
	    int result = stmt.getInt(3);
	    if (result == 1) {
	        success = true;
	    }
	    stmt.close();
	    conn.close();
	    return success;
	}


	public Listener getProfile(String Name) throws ClassNotFoundException, SQLException
	{
		Connection conn = null;
		CallableStatement stmt = null;
	    ResultSet rs = null;
	    Listener listenerData = new Listener();
	  
    	  try {
    		  Class.forName("com.mysql.jdbc.Driver");
    	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SDAProjectP1","root","Arfasara1624928");
    	    	stmt = (CallableStatement) conn.prepareCall("{CALL getListenerProfile(?)}");
    	    	stmt.setString(1, Name);
    	    	rs = stmt.executeQuery();
    		
    	    	while (rs.next()) 
    	    	{
    	        listenerData.setName(rs.getString("LName"));
    	        listenerData.setPassword(rs.getString("LPassword"));
    	    	listenerData.setEmail(rs.getString("LEmail"));
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

    	  return listenerData;
    }

	public List<Playlist> getPlaylists(String Name) throws Exception
	{
		Connection conn = null;
		CallableStatement stmt = null;
	    ResultSet rs = null;
	    List<Playlist> playlist = new ArrayList<>();
	    try {
	    	Class.forName("com.mysql.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SDAProjectP1","root","Arfasara1624928");
	    	stmt = (CallableStatement) conn.prepareCall("{CALL getArtistPlaylist(?)}");
	    	stmt.setString(1, Name);
	    	rs = stmt.executeQuery();
	    	while (rs.next()) 
	    	{
	    		Playlist playlist2 = new Playlist();
	    		playlist2.setName(rs.getString("PName"));
	    		playlist2.setDescription(rs.getString("PDescription"));
	    		playlist.add(playlist2);
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
	    return playlist;

	}
	
	public List<Artist> GetFollowingArtist(String Name) throws Exception
	{
		
		Connection conn = null;
		CallableStatement stmt = null;
	    ResultSet rs = null;
	    List<Artist> Artists = new ArrayList<>();
	    try {
	    	Class.forName("com.mysql.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SDAProjectP1","root","Arfasara1624928");
	    	stmt = (CallableStatement) conn.prepareCall("{CALL GetFollowedArtists(?)}");
	    	stmt.setString(1, Name);
	    	rs = stmt.executeQuery();
	    	while (rs.next()) 
	    	{
	    		Artist A = new Artist();
	    		A.setName(rs.getString("AName"));
	    		A.setBio(rs.getString("ABio"));
	    		Artists.add(A);
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
	    return Artists;
	}
	
	public boolean editProfile(String name, String newLPassword) throws Exception {
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    int updatedRows = 0;

	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SDAProjectP1","root","Arfasara1624928");
	        stmt = conn.prepareStatement("UPDATE Listener SET LPassword = ? WHERE Lname = ?");
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


	public  int deleteListener(String listenerName) throws ClassNotFoundException 
	{
		  int listenerData = 0;
		  Connection conn = null;
		  CallableStatement stmt = null;
		  ResultSet rs = null;

		  try {
		    Class.forName("com.mysql.jdbc.Driver");
		    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SDAProjectP1","root","Arfasara1624928");
		    stmt = (CallableStatement) conn.prepareCall("{CALL deleteListener(?,?)}");
            stmt.setString(1, listenerName);
            stmt.registerOutParameter(2, java.sql.Types.INTEGER);
            stmt.execute();
            listenerData = stmt.getInt(2);

            return listenerData;
        } catch(SQLException e) {
            e.printStackTrace();
            return -1; 
            }
    }
	public boolean deletePlaylist(String Name, String PName) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SDAProjectP1","root","Arfasara1624928");
		CallableStatement cs = (CallableStatement)con.prepareCall("{call delete_playlist(?, ?, ?)}");
		cs.setString(1, Name);
		cs.setString(2, PName);
		cs.registerOutParameter(3, Types.BOOLEAN);
		cs.execute();
		boolean status = cs.getBoolean(3);
		con.close();
		return status;
	 }
	public boolean DeleteSongFromFav(String Name,String AName,String SName) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SDAProjectP1","root","Arfasara1624928");
		CallableStatement cs = (CallableStatement) con.prepareCall("{call remove_song_from_favorites(?, ?, ?, ?)}");
		cs.setString(1, Name);
		cs.setString(2, AName);
		cs.setString(3, SName);
		cs.registerOutParameter(4, Types.BOOLEAN);
		cs.execute();
		boolean success = cs.getBoolean(4);
		con.close();
		return success;
		
	}
	public boolean DeleteSongFromPlaylist(String Name,String decodedAName,String decodedSName,String decodedPName) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SDAProjectP1","root","Arfasara1624928");
		CallableStatement cs = (CallableStatement) con.prepareCall("{call remove_song_from_playlist(?, ?, ?, ?)}");
		cs.setString(1, Name);
		cs.setString(2, decodedAName);
		cs.setString(3, decodedSName);
		cs.setString(4, decodedPName);
		cs.registerOutParameter(5, Types.BOOLEAN);
		cs.execute();
		boolean success = cs.getBoolean(4);
		con.close();
		return success;
	}
}