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
import com.tolgaze.customerbranchapi.service.BranchService;

@RestController
@RequestMapping("/customer-branch-api/branches")
public class BranchController {
	
	@Autowired
	BranchService branchService;
	
	@GetMapping
	public ResponseEntity<List<Branch>> getBranchList() {
		List<Branch> response = branchService.getBranchList();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Branch> getBranch(@PathVariable("id") Long id) {
		Branch response = branchService.getBranchById(id);
		
		if(response != null) {
			return new ResponseEntity<>(response, HttpStatus.OK); 
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); 
		}
	}
	
	@PostMapping
	public ResponseEntity<Branch> postBranch(@RequestBody Branch customer) {
		Branch response = branchService.createBranch(customer);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Branch> putBranch(@PathVariable("id") Long id, @RequestBody Branch customer) {
		Branch response = branchService.updateBranch(id, customer);
		
		if(response != null) {
			return new ResponseEntity<>(response, HttpStatus.OK); 
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); 
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Branch> deleteBranch(@PathVariable("id") Long id) {
		Branch response = branchService.deleteBranch(id);
		
		if(response != null) {
			return new ResponseEntity<>(response, HttpStatus.OK); 
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); 
		}
	}
	
	@GetMapping("/{branchId}/customers")
	public ResponseEntity<List<Customer>> getBranchCustomers(@PathVariable("branchId") Long branchId) {
		List<Customer> response = branchService.getBranchCustomers(branchId);
		
		if(response != null) {
			return new ResponseEntity<>(response, HttpStatus.OK); 
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); 
		}
	}
	
	@PostMapping("/{branchId}/customer/{customerId}")
	public ResponseEntity<Branch> addCustomerBranch(@PathVariable("customerId") Long customerId,
														@PathVariable("branchId") Long branchId) {
		Branch response = branchService.addBranchCustomer(customerId, branchId);
		
		if(response != null) {
			return new ResponseEntity<>(response, HttpStatus.OK); 
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); 
		}
	}
	
	@DeleteMapping("/{branchId}/customer/{customerId}")
	public ResponseEntity<Branch> deleteCustomerBranch(@PathVariable("customerId") Long customerId,
															@PathVariable("branchId") Long branchId) {
		Branch response = branchService.deleteBranchCustomer(customerId, branchId);
		
		if(response != null) {
			return new ResponseEntity<>(response, HttpStatus.OK); 
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); 
		}
	}

}
