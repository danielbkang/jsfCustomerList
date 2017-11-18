package com.jsonarjsf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jsonarjsf.model.Customer;
import com.jsonarjsf.util.DatabaseConnect;
import com.jsonarjsf.util.FaceMessageHelper;

public class DataDAO { //TODO: need to be fixed (not correct code)
	public static List<Customer> getCustomers() {  //TODO: how will you check user is currently logged in.
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DatabaseConnect.getConnection();
			if(con == null) {
				FaceMessageHelper.fatal("Failed to connect to the database. Please try again or please contact to the developer.");
				return customerList;
			}
			ps = con.prepareStatement("Select * from customers");
//			ps.setString(1, username);
//			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				//result found, means valid inputs
				
				Customer customer = new Customer(3, "Mike");
				customerList.add(customer);
			}
		} catch (SQLException ex) { //TODO: Prepare to fix for xss and sqlinjection attack.
			System.out.println("Login error -->" + ex.getMessage());
			return customerList;
		} finally {
			DatabaseConnect.close(con);
		}
		return customerList;
	}
}
