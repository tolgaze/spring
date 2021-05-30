package com.tolgaze.customerbranchapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tolgaze.customerbranchapi.model.Branch;
import com.tolgaze.customerbranchapi.model.Customer;
import com.tolgaze.customerbranchapi.repository.BranchRepository;
import com.tolgaze.customerbranchapi.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	CustomerRepository customerReposity;
	@Autowired
	BranchRepository branchRepository;

	@Override
	public List<Customer> getCustomerList() {
		return customerReposity.findAll();
	}

	@Override
	public Customer getCustomerById(Long id) {
		Optional<Customer> customer = customerReposity.findById(id);
		if(customer.isPresent()) {
			return customer.get();
		}
		else {
			return null;
		}
	}

	@Override
	public Customer createCustomer(Customer customer) {
		return customerReposity.save(customer);
	}

	@Override
	public Customer updateCustomer(Long id, Customer updatedCustomer) {
		Optional<Customer> customer = customerReposity.findById(id);
		if(customer.isPresent()) {
			customer.get().setName(updatedCustomer.getName());
			customerReposity.save(customer.get());
			return customer.get();
		}
		else {
			return null;
		}
	}

	@Override
	public Customer deleteCustomer(Long id) {
		Optional<Customer> customer = customerReposity.findById(id);
		if(customer.isPresent()) {
			customer.get().getCustomerBranches().forEach(branch -> {
				branch.getBranchCustomers().remove(customer.get());
				branchRepository.save(branch);
			});
			customerReposity.deleteById(id);
			return customer.get();
		}
		else {
			return null;
		}
	}

	@Override
	public Customer addCustomerBranch(Long customerId, Long branchId) {
		Optional<Customer> customer = customerReposity.findById(customerId);
		Optional<Branch> branch = branchRepository.findById(branchId);
		if(customer.isPresent() && branch.isPresent()) {
			branch.get().getBranchCustomers().add(customer.get());
			branchRepository.save(branch.get());
			customer.get().getCustomerBranches().add(branch.get());
			customerReposity.save(customer.get());
			return customer.get();
		}
		else {
			return null;
		}
	}

	@Override
	public Customer deleteCustomerBranch(Long customerId, Long branchId) {
		Optional<Customer> customer = customerReposity.findById(customerId);
		Optional<Branch> branch = branchRepository.findById(branchId);
		if(customer.isPresent() && branch.isPresent()) {
			branch.get().getBranchCustomers().remove(customer.get());
			branchRepository.save(branch.get());
			customer.get().getCustomerBranches().remove(branch.get());
			customerReposity.save(customer.get());
			return customer.get();
		}
		else {
			return null;
		}
	}

	@Override
	public List<Branch> getCustomerBranches(Long customerId) {
		Optional<Customer> customer = customerReposity.findById(customerId);
		if(customer.isPresent()) {
			return List.copyOf(customer.get().getCustomerBranches());
		}
		else {
			return null;
		}
	}


}
