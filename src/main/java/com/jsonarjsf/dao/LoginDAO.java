package com.jsonarjsf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jsonarjsf.util.DatabaseConnect;

public class LoginDAO {
	
	public static boolean validate(String username, String password) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DatabaseConnect.getConnection();
			ps = con.prepareStatement("Select uname, password from Users where uname = ? and password = ?");
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				//result found, means valid inputs
				return true;
			}
		} catch (SQLException ex) {
			System.out.println("Login error -->" + ex.getMessage());
			return false;
		} finally {
			DatabaseConnect.close(con);
		}
		return false;
	}
}

