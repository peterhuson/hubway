package edu.brown.cs.db_lab.query;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Fill in the method stubs using your knowledge of SQL statements!
 *
 */
public class HubwayQuery {

  private Connection conn;
  /**
   * This constructor takes in a path to the db file
   * @param db Path to the db file
   * @throws ClassNotFoundException 
   * @throws SQLException 
   */
  public HubwayQuery(String db) throws ClassNotFoundException, SQLException {
    // Set up a connection and store it in a field
    Class.forName("org.sqlite.JDBC");
    String url = "jdbc:sqlite:" + db;
    conn = DriverManager.getConnection(url);
    Statement stat = conn.createStatement();
    stat.executeUpdate("PRAGMA foreign_keys = ON;");
    stat.close();
  }

  /**
   * Return all of the station names whose status correspond to 'Removed'. Make
   * sure the names are sorted in ascending order (ie. 'a' should come before 
   * 'b')
   * @param args Empty array
   * @return A List of the station names, sorted in ascending order
   * @throws SQLException 
   */
  public List<String> query1(String args[]) throws SQLException{
    //TODO: Write the query as a string
    String query = "";
    
    // Create a PreparedStatement
    PreparedStatement prep;
    prep = conn.prepareStatement(query);
    
    // Execute the query and retrieve a ResultStatement
    ResultSet rs = prep.executeQuery();
    
    // Add the results to a list
    List<String> toReturn = new ArrayList<String>();
    while (rs.next()) {
      toReturn.add(rs.getString(1));
    }

    // Close the ResultSet and the PreparedStatement
    rs.close();
    prep.close();

    return toReturn;
  }
  
  /**
   * Return all of the station names that are located inside the box formed by 
   * two given points. The points are stored in the args parameter with latitude
   * and longitude coordinates. As with query1, the names should be sorted in
   * ascending order
   * 
   * @param args An array containing the lower left and upper right corners of 
   * the bounding box. Indices 0 and 1 contain the latitude and longitude of
   * the lower left corner and indices 2 and 3 contain the latitude and
   * longitude of the upper right corner. The coordinates should be doubles.
   * 
   * @return A ResultSet with all of the station names inside of the bounding
   * box
   * 
   * @throws SQLException 
   */
  public List<String> query2(String args[]) throws SQLException {
    double lowerLeftLat = Double.parseDouble(args[0]);
    double lowerLeftLng = Double.parseDouble(args[1]);
    double upperRightLat = Double.parseDouble(args[2]);
    double upperRightLng = Double.parseDouble(args[3]);

    //TODO: Write the query as a string
    String query = "";

    PreparedStatement prep;
    prep = conn.prepareStatement(query);
    
    //TODO: Fill in the PreparedStatement
    //HINT: Use PreparedStatment's method 'setDouble'
    
    // Add the results to a list
    ResultSet rs = prep.executeQuery();
    List<String> toReturn = new ArrayList<String>();
    while (rs.next()) {
      toReturn.add(rs.getString(1));
    }
    
    // Close the ResultSet and the PreparedStatement
    rs.close();
    prep.close();
    
    return toReturn;
  }
  
  /**
   * Return the top 5 trips' ids (hubway_id) that started or ended at stations
   * within a bounding boxed formed by two given points. The ids should be
   * sorted in ascending order.
   * @param args An array containing the lower left and upper right corners of 
   * the bounding box. Indices 0 and 1 contain the latitude and longitude of
   * the lower left corner and indices 2 and 3 contain the latitude and
   * longitude of the upper right corner
   * @return A List of all the trips ids (hubway_id)
   * @throws SQLException 
   */
  public List<String> query3(String args[]) throws SQLException {
    double lowerLeftLat = Double.parseDouble(args[0]);
    double lowerLeftLng = Double.parseDouble(args[1]);
    double upperRightLat = Double.parseDouble(args[2]);
    double upperRightLng = Double.parseDouble(args[3]);
    
    //TODO: Write the query as a string
    // At least one of the starting or ending stations must be 
    // in the bounding box
    
    String query = "";

    PreparedStatement prep;
    prep = conn.prepareStatement(query);
    
    //TODO: Fill in the PreparedStatement
      
    // Add the results to a list
    ResultSet rs = prep.executeQuery();
    List<String> toReturn = new ArrayList<String>();
    while (rs.next()) {
      toReturn.add(Integer.toString(rs.getInt(1)));
    }
    
    // Close the ResultSet and the PreparedStatement
    rs.close();
    prep.close();

    return toReturn;
  }
  
  /**
   * Closes and cleans up any resources.
   * @throws SQLException
   */
  public void close() throws SQLException {
    // Close the connection
    conn.close();
  }
}
