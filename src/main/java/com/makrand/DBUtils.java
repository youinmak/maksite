package com.makrand;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBUtils {

	public static void main(String[] args) {
		
		getConnection();
	}
	
	public static void getConnection(){
		
		String dbURL1 = "jdbc:derby:codejava/webdb1;create=true";
        
		try(Connection conn1 = DriverManager.getConnection(dbURL1);) {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");			
			if (conn1 != null) {
	            System.out.println("Connected to database #1");
	        }
		} catch (SQLException | ClassNotFoundException e) {
			
			e.printStackTrace();
		}	
	}
	
}
