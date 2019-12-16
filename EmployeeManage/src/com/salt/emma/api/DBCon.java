package com.salt.emma.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBCon {
		
	public static Connection getCon() throws Exception {		
		Connection con = null;
		Class.forName("oracle.jdbc.driver.OracleDriver");		
		final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
		final String USER = "c##SALT";
		final String PW = "salt";		
		con = DriverManager.getConnection(URL, USER, PW);
		System.out.println("접속 성공!!!");
		return con;
	}
	
	public static void close(Connection con, PreparedStatement ps) {
		close(con, ps, null);
	}
	
	public static void close(Connection con, PreparedStatement ps, ResultSet rs) {	
		if(rs != null) {
			try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		
		if(ps != null) {
			try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		
		if(con != null) {
			try { con.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
	}
}
