package twitter;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.List;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class DBClient {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://db4free.net/hilleljavaelem1";
	
	static final String USER = "hilleljavaelem1";
	static final String PASS = "hilleljavaelem1";

	Connection conn=null;
	Statement stmt = null;
	
	public DBClient() {
			
		try {
			Class.forName(JDBC_DRIVER);
			
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			
			System.out.println("Creating statement...");
			stmt = conn.createStatement(); 
			
			conn.setAutoCommit(false);
				
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
	}
	
	public void insertData(Status status) {
		
		System.out.println("Inserting row....");
		
		java.util.Date statusDate = status.getStatusDate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(statusDate);
		String statusId = Long.toString(status.getStatusId());
										
		String SQL = "INSERT INTO status (statusid, text, statusdate, username) VALUES (" + 
						statusId + "," +
						"'" + status.getText() + "'," +
						"'" + date + "'," +
						"'" + status.getUserName() + "')";
		
		try {
			stmt.executeUpdate(SQL);
			
			System.out.println("Commiting data here....");
			conn.commit();
					
			String sql = "SELECT statusid FROM status ORDER BY idDB DESC LIMIT 1";
			ResultSet rs = stmt.executeQuery(sql);
			
			String id = null;
			while(rs.next()){
			id = rs.getString("statusid");}
			
			if (statusId.equals(id)) {
				System.out.println("Status was sucssesfully added to database. Status ID = " + statusId);
			} else {
				System.out.println("Rolling back data here....");
				conn.rollback();
			}
		
		} catch (MySQLIntegrityConstraintViolationException e) {
			System.out.println("Status (id = " + statusId + ")"+ " is already in the database");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Rolling back data here....");
			try{
				if(conn!=null)
					conn.rollback();
		     }catch(SQLException e2){
		         e2.printStackTrace();
		     }
		}
				
	}
	
public void insertData(List<Status> statuses) {
		
		for (Status status: statuses) {
			
			insertData(status);
		
		   }
	}
public void close(){
	try {
		stmt.close();
		conn.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	}

}
