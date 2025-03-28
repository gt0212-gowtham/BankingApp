package com.bofa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.bofa.model.Customer;
import com.bofa.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository custRepo;

	public Customer saveCustomer(Customer customer) {
		return custRepo.save(customer);
	}

	@Override
	public Customer getCustomer(Long customerId) {
		// TODO Auto-generated method stub
		custRepo.findById(customerId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Custimer Not Found"));
		return custRepo.getById(customerId);
	}

	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub loops
		List<Customer> custList = new ArrayList(); // I created a object of loist

		custList = custRepo.findAll(); // get all customer from DB

		if (custList.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No customer found");
		}

		// using for each loop to process customer

		custList.forEach(customer -> {

			custRepo.findByFirstName(customer.getFirstName()); ///

			if (customer.getFirstName().equals("Alex")) {
				System.out.println(" The Alex is exisiting in database");
			}
		});

		return custList;
	}

	@Override
	public void deleteCustomer(Long customerId) {
		// TODO Auto-generated method stub

		custRepo.deleteById(customerId);

	}

	@Override
	public Customer updateCustomer(Customer customer) {
		// Save customer with control loop

		if (customer.getFirstName() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid first name");
		}

		switch (customer.getFirstName().toLowerCase()) {
		case "alex":
			System.out.println();
		case "sam":
			System.out.println();

		}

		return custRepo.save(customer);
	}

}
