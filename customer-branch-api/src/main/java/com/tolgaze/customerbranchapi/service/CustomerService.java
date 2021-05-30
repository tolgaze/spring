package com.tolgaze.customerbranchapi.service;

import java.util.List;

import javax.transaction.Transactional;

import com.tolgaze.customerbranchapi.model.Branch;
import com.tolgaze.customerbranchapi.model.Customer;

public interface CustomerService {
	
	public List<Customer> getCustomerList();
	public Customer getCustomerById(Long id);
	public Customer createCustomer(Customer customer);
	public Customer updateCustomer(Long id, Customer customer);
	@Transactional
	public Customer deleteCustomer(Long id);
	@Transactional
	public Customer addCustomerBranch(Long customerId, Long branchId);
	@Transactional
	public Customer deleteCustomerBranch(Long customerId, Long branchId);
	public List<Branch> getCustomerBranches(Long customerId);

}
