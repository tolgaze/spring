package com.tolgaze.customerbranchapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tolgaze.customerbranchapi.model.Branch;
import com.tolgaze.customerbranchapi.model.Customer;
import com.tolgaze.customerbranchapi.service.CustomerService;

@RestController
@RequestMapping("/customer-branch-api/customers")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping
	public ResponseEntity<List<Customer>> getCustomerList() {
		List<Customer> response = customerService.getCustomerList();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id) {
		Customer response = customerService.getCustomerById(id);
		
		if(response != null) {
			return new ResponseEntity<>(response, HttpStatus.OK); 
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); 
		}
	}
	
	@PostMapping
	public ResponseEntity<Customer> postCustomer(@RequestBody Customer customer) {
		Customer response = customerService.createCustomer(customer);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Customer> putCustomer(@PathVariable("id") Long id, @RequestBody Customer customer) {
		Customer response = customerService.updateCustomer(id, customer);
		
		if(response != null) {
			return new ResponseEntity<>(response, HttpStatus.OK); 
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); 
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") Long id) {
		Customer response = customerService.deleteCustomer(id);
		
		if(response != null) {
			return new ResponseEntity<>(response, HttpStatus.OK); 
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); 
		}
	}
	
	@GetMapping("/{customerId}/branches")
	public ResponseEntity<List<Branch>> getCustomerBranches(@PathVariable("customerId") Long customerId) {
		List<Branch> response = customerService.getCustomerBranches(customerId);
		
		if(response != null) {
			return new ResponseEntity<>(response, HttpStatus.OK); 
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); 
		}
	}
	
	@PostMapping("/{customerId}/branch/{branchId}")
	public ResponseEntity<Customer> addCustomerBranch(@PathVariable("customerId") Long customerId,
														@PathVariable("branchId") Long branchId) {
		Customer response = customerService.addCustomerBranch(customerId, branchId);
		
		if(response != null) {
			return new ResponseEntity<>(response, HttpStatus.OK); 
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); 
		}
	}
	
	@DeleteMapping("/{customerId}/branch/{branchId}")
	public ResponseEntity<Customer> deleteCustomerBranch(@PathVariable("customerId") Long customerId,
															@PathVariable("branchId") Long branchId) {
		Customer response = customerService.deleteCustomerBranch(customerId, branchId);
		
		if(response != null) {
			return new ResponseEntity<>(response, HttpStatus.OK); 
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); 
		}
	}

}
