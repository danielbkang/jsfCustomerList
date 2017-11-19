package com.jsonarjsf.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.jsonarjsf.dao.DataDAO;
import com.jsonarjsf.model.Customer;

@ManagedBean
@SessionScoped
public class DataBean implements Serializable {

	private static final long serialVersionUID = 5432101282834859663L;
	
	private List<Customer> customers;
	
	@PostConstruct
    public void init() {
        //Customers
        customers = DataDAO.getCustomers();
        System.out.println("Printing customer size to check the result:" + customers.size());
    }
	
	public List<Customer> getCustomers(){
		return customers;
	}
	
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	
	public void nothing() {
		
	}
}
