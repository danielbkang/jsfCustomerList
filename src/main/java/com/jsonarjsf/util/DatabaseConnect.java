package com.jsonarjsf.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnect {
	public static Connection getConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/jsonarjsfdb", "root", "");
			return con;
		} catch (Exception ex) {
			System.out.println("Database.getConnection() Error:"
					+ " "+ ex.getClass().toString() + " " + ex.getMessage());
			return null;
		}
	}

	public static void close(Connection con) {
		try {
			con.close();
		} catch (Exception ex) {
		}
	}

}
