package com.jsonarjsf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jsonarjsf.util.DatabaseConnect;
import com.jsonarjsf.util.FaceMessageHelper;

public class LoginDAO {
	public static boolean validate(String username, String password) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DatabaseConnect.getConnection("jsonarjsfdb");
			if(con == null) {
				FaceMessageHelper.fatal("Failed to connect to the database. Please contact a developer or please try again. Connection to the database was unsuccessful.");
				return false;
			}
			ps = con.prepareStatement("Select uname, password from Users where uname = ? and password = ?");
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				//result found, means valid inputs
				return true;
			}
		} catch (SQLException ex) { //TODO: Prepare to fix for xss and sqlinjection attack.
			System.out.println("Login error -->" + ex.getMessage());
			return false;
		} finally {
			DatabaseConnect.close(con);
		}
		return false;
	}
}

