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
	private Customer selectedCustomer;
	private List<Customer> filteredCustomers;
	private String filterValue;
	
	@PostConstruct
    public void init() {
        //Customers
        customers = DataDAO.getCustomers();
        filteredCustomers = customers;
        DataDAO.getOrderDetail(customers.get(0));
//        System.out.println("Printing customer size to check the result:" + customers.size());
    }
	
	public List<Customer> getCustomers(){
		return customers;
	}
	
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	
	public Customer getSelectedCustomer() {
		return selectedCustomer;
	}

	public void setSelectedCustomer(Customer selectedCustomer) {
		this.selectedCustomer = selectedCustomer;
	}

	public List<Customer> getFilteredCustomers() {
		return filteredCustomers;
	}

	public void setFilteredCustomers(List<Customer> filteredCustomers) {
		this.filteredCustomers = filteredCustomers;
	}
	
	public String getFilterValue() {
		return filterValue;
	}

	public void setFilterValue(String filterValue) {
		this.filterValue = filterValue;
	}

	public void filterList() {
	    List<Customer> filteredList = new ArrayList<Customer>();
	    for(Customer customer: customers) {
	    		if(customer.contains(filterValue))
	    			filteredList.add(customer);
	    }
	    if(filterValue.equals(null) || filterValue.equals(""))
	    		setFilteredCustomers(customers);
	    else
	    		setFilteredCustomers(filteredList);
	}
	
	public void nothing() {
		System.out.println("Hello");
	}
}
