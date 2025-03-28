package com.bofa.service;

import java.util.List;

import com.bofa.model.Customer;

public interface CustomerService {
	
	// I have to perform 4 operations for customer add delete get update
	
	/*
	 *  I want to create a saveCustomer method which has return tupe of customer and method should abke to pass customer object as param and save in DB
	 *  
	 *  
	 * 
	 *  
	 */
     public Customer saveCustomer(Customer customer);
	
	public Customer getCustomer(Long customerId);
	
	public List<Customer> getAllCustomers();
	
	public void deleteCustomer(Long customerId);
	
	public Customer updateCustomer(Customer customer);
	
	

}
