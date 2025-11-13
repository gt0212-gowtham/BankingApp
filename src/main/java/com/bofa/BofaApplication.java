package com.bofa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bofa.model.Customer;

@SpringBootApplication
public class BofaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BofaApplication.class, args);
		
		
	}
	
	
	
	
	/*
	 *   Banking sector  
	 *    
	 *    
	 *  Customer -- Raj
	 *    customerid
	 *    first name
	 *    last name
	 *    address
	 *    email
	 *    mobile number
	 *    gender
	 *  
	 *  Transaction -- Srijan 
	 *    transactionid
	 *    transactiontype
	 *    datetime
	 *    amount
	 *    branchCode
	 *  
	 *  Accounts  -- Alekhya
	 *    accountid
	 *    accountnumber
	 *    accounttype
	 *    balance   -- 
	 *    createdDate  -- date
	 *    status
	 *    
	 *  Employees -- Gowtham
	 *   employeeid 
	 *   first name
	 *   last name
	 *   address
	 *   email 
	 *   mobile number
	 *   gender
	 *   pay/salary
	 *   
	 *  Branches  -- Harsha
	 *   branchid
	 *   branch name
	 *   branch address
	 *   branch manager
	 *   branch code
	 *   Timings
	 *  
	 *  Services   -- Vishnu 
	 *   ServiceId    long
	 *   Lending Services -- auto , personal,list
	 *   Retail Services --  List
	 *   Digital Services  -- String
	 *   Customer Service -- String
	 *   status -- boolean
	 *   
	 *   
	 *   
	 *   Support  -- Jagadeesh
	 *    caseid -- int/Long   -- private
	 *    caseDescription  -- char[]/String -- public
	 *    caseOwner -- string   -- public
	 *    employeeName  -- string -- public
	 *    attachments  -- string  -- protected
	 *    status  -- boolean
	 *    
	 *    
	 *   
	 *   
	 *   
	 *   
	 *  
	 *  Model // POJO
	 *    
	 *  object ]
	 *  attributes
	 *  data types
	 *  access specifiers --- public, private, default, protected
	 *  constructor   -- default , parameterised contrsutors
	 *  
	 *  
	 *  
	 *  
	 *  
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

}
