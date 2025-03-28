package com.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bofa.model.Customer;
import com.bofa.service.CustomerServiceImpl;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("gmail")
public class CustomerController {
	
	
	
	/*
	 * 
	 *    http://google.com/gmail/signup/saveCustomer
	 *    {
	 *     firstname
	 *     lastname
	 *     
	 *    }
	 *    hit create/submit button    -- POST 
	 *    
	 *    
	 *    http verbs we have 4   -- 
	 *    
	 *    POST   -- always create new object 
	 *    GET
	 *    UPDATE   -- put is used for updating the existing record
	 *    DELETE 
	 *    
	 *    
	 *    Controller   -- the entity/object (the controller will send data to service class )
	 *    Service   -- service class modifies or verfies the data and send back to repository
	 *    Repository  -- repo will save your data in DB
	 *    Entity   -- all the atttributes associated to request object
	 *    
	 *    Exception handling   --- 
	 *    
	 *    
	 *    we create objects using autowired annotoation
	 *    
	 *    
	 *    @RequestBody
	 *    @ResponseBody
	 *    @PathParam   --- sending individual attributes 
	 *    
	 *    http://google.com/gmail/getCustomerById/1
	 * 
	 * 
	 *    {
	 *    
	 *     }
	 * 
	 * 
	 */
	
	@Autowired
	CustomerServiceImpl customerService;
	
	@PostMapping("/saveCustomer") 
	public Customer saveCustomer(@RequestBody Customer customer) {
		return customerService.saveCustomer(customer);
	}
	
	
	@GetMapping("/getCustomerById/{customerId}")
	public Customer getCustomer(@PathParam(value = "customerId") Long customerId) {
		return customerService.getCustomer(customerId);
	}
	
	
}
