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

public class DataDAO { // TODO: need to be fixed (not correct code)
	
	// Returns empty list if:
	// 			database connection fails.
	//			sqlexception happens.
	// Returns list of customers if query was successful, and information was retrieved successfully.
	public static List<Customer> getCustomers() { // TODO: how will you check user is currently logged in.
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DatabaseConnect.getConnection("classicmodels");
			if (con == null) {
				FaceMessageHelper.fatal(
						"Failed to connect to the database. Please try again or please contact to the developer.");
				return customerList;
			}
			ps = con.prepareStatement("Select * from customers");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				// result found, means valid inputs
				int customerNumber = rs.getInt("customerNumber");
				String customerName = rs.getString("customerName");
				String contactLastName = rs.getString("contactLastName");
				String contactFirstName = rs.getString("contactFirstName");
				String phone = rs.getString("phone");
				String addressLine1 = rs.getString("addressLine1");
				String addressLine2 = rs.getString("addressLine2");
				String city = rs.getString("city");
				String state = rs.getString("state");
				String postalCode = rs.getString("postalCode");
				String country = rs.getString("country");
				int salesRepEmployeeNumber = rs.getInt("salesRepEmployeeNumber");
				double creditLimit = rs.getDouble("creditLimit");
				Customer customer = new Customer(customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, addressLine2, city, state, postalCode, country, salesRepEmployeeNumber, creditLimit);
				customerList.add(customer);
			}
			return customerList;
		} catch (SQLException ex) { // TODO: Prepare to fix for xss and sqlinjection attack.
			System.out.println("Login error -->" + ex.getMessage());
			customerList.clear();
			return customerList;
		} finally {
			DatabaseConnect.close(con);
		}
	}
	
	public static void getOrderDetail(Customer customer) { // TODO: how will you check user is currently logged in.
//		OrderDetail orderDetail = null;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DatabaseConnect.getConnection("classicmodels");
			if (con == null) {
				FaceMessageHelper.fatal(
						"Failed to connect to the database. Please try again or please contact to the developer.");
//				return orderDetail;
			}
			ps = con.prepareStatement("Select products.*, orders.*, orderdetails.quantityOrdered, orderdetails.priceEach, orderDetails.orderLineNumber " + 
					"from customers, orders, orderdetails, products " + 
					"where customers.customerNumber = orders.customerNumber AND orders.orderNumber = orderdetails.orderNumber AND orderdetails.productCode = products.productCode AND customers.customerNumber = ? " + 
					"order by orders.orderDate;");
			ps.setInt(1, customer.getCustomerNumber());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				System.out.println("FIND: productName: " + rs.getString("productName")); //working upto this point.
//				// result found, means valid inputs
//				int customerNumber = rs.getInt("customerNumber");
//				String customerName = rs.getString("customerName");
//				String contactLastName = rs.getString("contactLastName");
//				String contactFirstName = rs.getString("contactFirstName");
//				String phone = rs.getString("phone");
//				String addressLine1 = rs.getString("addressLine1");
//				String addressLine2 = rs.getString("addressLine2");
//				String city = rs.getString("city");
//				String state = rs.getString("state");
//				String postalCode = rs.getString("postalCode");
//				String country = rs.getString("country");
//				int salesRepEmployeeNumber = rs.getInt("salesRepEmployeeNumber");
//				double creditLimit = rs.getDouble("creditLimit");
//				Customer customer = new Customer(customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, addressLine2, city, state, postalCode, country, salesRepEmployeeNumber, creditLimit);
//				customerList.add(customer);
			}
//			return customerList;
		} catch (SQLException ex) { // TODO: Prepare to fix for xss and sqlinjection attack.
			System.out.println("Login error -->" + ex.getMessage());
//			customerList.clear();
//			return customerList;
		} finally {
			DatabaseConnect.close(con);
		}
	}
}
