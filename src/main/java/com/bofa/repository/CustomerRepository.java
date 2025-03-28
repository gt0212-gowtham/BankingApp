package com.bofa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bofa.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	

	
	Customer findByFirstName(String firstName);
	
	Customer findByAddress(String address);
	
	

}
