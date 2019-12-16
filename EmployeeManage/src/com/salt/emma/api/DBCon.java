package com.salt.emma.api;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBCon {
	public static void main(String[] args) {
		try {
			getCon();
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
	
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
}
