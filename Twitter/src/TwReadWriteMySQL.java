import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
//import java.util.HashMap;
import java.util.Map;

public class TwReadWriteMySQL {
	   // JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbcmysqldb4free.nethillel2014java1";

	   //  Database credentials
	   static final String USER = "hillel2014java1";
	   static final String PASS = "hillel2014java1";
	   
	
	 public static void main(String[] args) throws SQLException, FileNotFoundException, IOException {
	   Connection conn = null;
	   Statement stmt = null;
		//get params from ini file
		
//		ReadIniFile rf = new ReadIniFile();
		Map <String,String> Result = ReadIniFile.Read(); 

		try{
	      //STEP 2 Register JDBC driver
	      Class.forName("com.mysql.jdbc.Driver");

	      //STEP 3 Open a connection
	      System.out.println("Connecting to database...");
	      conn = DriverManager.getConnection(DB_URL, USER, PASS);

	      //STEP 4 Execute a query
	      System.out.println("Creating statement...");
	      
	      ResultSet rs = conn.getMetaData().getCatalogs();

	      while (rs.next()) {
	          System.out.println("Table name =  "+ rs.getString("TABLE_CAT") );
	      }
	      
	      stmt = conn.createStatement();
	      rs = stmt.executeQuery("SELECT * from TweeterUser;");
	      
	      int lastId = 0;
	      while (rs.next()) {
	    	  lastId = rs.getInt("id");
	          System.out.println("id =  "+ lastId);
	          System.out.println("name =  "+ rs.getString("name"));
	          System.out.println("phone =  "+ rs.getString("phone"));
	      }
	      lastId++; // counter 
//	      пример добавления новой записи в MYSQl
          String сommand = "INSERT INTO TweeterUser (id,name,email,phone)VALUES ("
			+" ' + lastId + ',"
			+" ' + Petrov+lastId+',"
			+"' + Petrov+lastId+@levix.net',"
			+" ' + lastId+0678925682 + ');";

          stmt.executeUpdate(сommand);

	      
	  //    STEP 6 Clean-up environment
	      rs.close();
	      stmt.close();
	      conn.close();
	   }catch(SQLException se){
	    //  Handle errors for JDBC
	      se.printStackTrace();
	   }catch(ClassNotFoundException e){
	  //    Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){
	      } //nothing we can do
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
	   System.out.println("Goodbye!");
	}
	}


