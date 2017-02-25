package edu.brown.cs.db_lab.query;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import edu.brown.cs.db_lab.query.HubwayQuery;

public class Main {

  public static void main(String[] args) {
    if (args.length != 0) {
      query(args[0]);
    } else {
      System.err
          .println("You must specify the location of the hubway.sqlite3 database.");
    }

  }

  private static void query(String databaseFile) {
    try {
      HubwayQuery hq = new HubwayQuery(databaseFile);
      
      Scanner in = new Scanner(System.in);
      
      String line;
      String[] lineArray;
      String[] params;
      Method queryMethod;
      
      while(in.hasNextLine()) {
        
        try {
          line = in.nextLine();
          lineArray = line.split("\\s+");
          
          if(line.equals("")) {
            return;
          }
          
          params = Arrays.copyOfRange(lineArray, 1, lineArray.length);
          queryMethod = hq.getClass().getDeclaredMethod("query" + lineArray[0],
              params.getClass());
          
          @SuppressWarnings("unchecked")
          List<String> results = (List<String>) queryMethod.invoke(hq, 
              new Object[]{params});
          
          for(String result : results) {
            System.out.println(result);
          }
        } catch(NoSuchMethodException|IllegalAccessException|
          IllegalArgumentException|InvocationTargetException e) {
          e.printStackTrace();
        }
      }
      
      hq.close();
      in.close();
    } catch (ClassNotFoundException|SQLException e) {
      e.printStackTrace();
    }
  }
}
