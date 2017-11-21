package com.jsonarjsf.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jsonarjsf.hibernate.model.Order;
import com.jsonarjsf.hibernate.model.OrderDetail;
import com.jsonarjsf.hibernate.model.Product;
import com.jsonarjsf.model.Customer;
import com.jsonarjsf.model.OrderSummary;
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
	
	public static List<OrderSummary> getOrderSummary(Customer customer) { // TODO: how will you check user is currently logged in.
		List<OrderSummary> orderSummarys = new ArrayList<OrderSummary>();
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DatabaseConnect.getConnection("classicmodels");
			if (con == null) {
				FaceMessageHelper.fatal(
						"Failed to connect to the database. Please try again or please contact to the developer.");
//				return orderDetail;
			}
			ps = con.prepareStatement("Select products.*, orders.*, orderDetails.* " + //orderDetails.quantityOrdered, orderDetails.priceEach, orderDetails.orderLineNumber 
					"from customers, orders, orderdetails, products " + 
					"where customers.customerNumber = orders.customerNumber AND orders.orderNumber = orderdetails.orderNumber AND orderdetails.productCode = products.productCode AND customers.customerNumber = ? " + 
					"order by orders.orderDate;");
			ps.setInt(1, customer.getCustomerNumber());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				System.out.println("FIND: productName: " + rs.getString("productName")); //working upto this point.
				Product product = new Product(rs.getString("productCode"), rs.getString("productName"), rs.getString("productLine"), rs.getString("productScale"),
						rs.getString("productVendor"), rs.getString("productDescription"), rs.getInt("quantityInStock"), rs.getDouble("buyPrice"), rs.getDouble("mSRP"));
				Order order = new Order(rs.getInt("orderNumber"), rs.getDate("orderDate"), rs.getDate("requiredDate"), rs.getDate("shippedDate"), rs.getString("status"), rs.getString("comments"),
						rs.getInt("customerNumber"));
				OrderDetail orderDetail = new OrderDetail(rs.getInt("orderNumber"), rs.getString("productCode"), rs.getInt("quantityOrdered"), rs.getDouble("priceEach"),
						rs.getInt("orderLineNumber"));
				OrderSummary orderSummary = new OrderSummary(product, order, orderDetail);
				orderSummarys.add(orderSummary);				
			}
			return orderSummarys;
		} catch (SQLException ex) { // TODO: Prepare to fix for xss and sqlinjection attack.
			System.out.println("Login error -->" + ex.getMessage());
			orderSummarys.clear();
			return orderSummarys;
		} finally {
			DatabaseConnect.close(con);
		}
	}
}
