package twitter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class DBClient {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://db4free.net/hilleljavaelem1";
	
	static final String USER = "hilleljavaelem1";
	static final String PASS = "hilleljavaelem1";
	
		
	public DBClient() {
			
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
	}
	
	public void insertData(Status status) {
		
		System.out.println("Connecting to database...");
		
		try(Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);)
		{
			conn.setAutoCommit(false);
			
			try (Statement stmt = conn.createStatement();)
			{
				System.out.println("Inserting row....");
				
				String statusId = Long.toString(status.getStatusId());
								
				String sqlINS = "INSERT INTO status (statusid, text, statusdate, username) VALUES (" + 
						statusId + "," +
						"'" + status.getText() + "'," +
						"'" + status.getStatusDate() + "'," +
						"'" + status.getUserName() + "')";
				
				stmt.executeUpdate(sqlINS);
				
				System.out.println("Commiting data here....");
				
				conn.commit();
				
				String sqlSEL = "SELECT statusid FROM status ORDER BY idDB DESC LIMIT 1";
				try (ResultSet rs = stmt.executeQuery(sqlSEL);)
				{
					String id = null;
					while(rs.next()){
					id = rs.getString("statusid");}
					
					if (statusId.equals(id)) {
						System.out.println("Status was sucssesfully added to database");
					} else {
						System.out.println("Rolling back data here....");
						conn.rollback();
					}
				}
			}
			catch (MySQLIntegrityConstraintViolationException e) {
				System.out.println("Status (id = " + status.getStatusId() + ")"+ " is already in the database");
			} 
			catch (SQLException e) {
				System.out.println("Rolling back data here....");
				conn.rollback();
				conn.setAutoCommit(true);
				throw e;
			}
			conn.setAutoCommit(true);
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
		
		
	public void insertData(List<Status> statuses) {
		
		List<String> allIds = new ArrayList<String>();
		List<String> duplIds = new ArrayList<String>();
		List<String> addedIds = new ArrayList<String>();
		
		System.out.println("Connecting to database...");
		try(Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);)
		{
			conn.setAutoCommit(false);
			try (Statement stmt = conn.createStatement();)
			{
				System.out.println("Inserting rows....");
				for (Status status: statuses){
					
					String statusId = Long.toString(status.getStatusId());
					
					allIds.add(statusId);
					
					String sqlINS = "INSERT INTO status (statusid, text, statusdate, username) VALUES (" + 
							statusId + "," +
							"'" + status.getText() + "'," +
							"'" + status.getStatusDate() + "'," +
							"'" + status.getUserName() + "')";
					try
					{
						stmt.executeUpdate(sqlINS);
						}
					catch (MySQLIntegrityConstraintViolationException e) {
						duplIds.add(statusId);
					}
				}
				conn.commit();
				allIds.removeAll(duplIds);
				String sqlSEL = "SELECT statusid FROM status ORDER BY idDB DESC LIMIT " + allIds.size();
				try (ResultSet rs = stmt.executeQuery(sqlSEL);)
				{
					String id = null;
					while(rs.next()){
					id = rs.getString("statusid");
					addedIds.add(id);
					}
					if (addedIds.containsAll(allIds)) {
						System.out.println("Statuses with ids: " + addedIds + " were sucssesfully added to database");
						if (duplIds.size()>0) {
							System.out.println("Statuses with ids: " + duplIds + " are already in the database");
						}	else {
							System.out.println("Rolling back data here....");
							conn.rollback();}
					}
				}
			}
			catch (SQLException e) {
				System.out.println("Rolling back data here....");
				conn.rollback();
				conn.setAutoCommit(true);
				throw e;
			}
			conn.setAutoCommit(true);
		}
		
		catch (SQLException e){
			e.printStackTrace();
		}	
	}
}
