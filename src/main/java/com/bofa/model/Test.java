package com.bofa.model;

public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		Customer cust = new Customer("1"); // to create an object 
		cust.calc(0, 0);
	}
	
	public Customer addCustomer() {
		
		
		return new Customer();
	}

}
