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
public class BranchServiceImpl implements BranchService{
	
	@Autowired
	BranchRepository branchRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Override
	public List<Branch> getBranchList() {
		return branchRepository.findAll();
	}

	@Override
	public Branch getBranchById(Long id) {
		Optional<Branch> branchOptional = branchRepository.findById(id);
		if(branchOptional.isPresent()) {
			return branchOptional.get();
		}
		else {
			return null;
		}
	}

	@Override
	public Branch createBranch(Branch branch) {
		return branchRepository.save(branch);
	}

	@Override
	public Branch updateBranch(Long id, Branch updatedBranch) {
		Optional<Branch> branch = branchRepository.findById(id);
		if(branch.isPresent()) {
			branch.get().setName(updatedBranch.getName());
			branchRepository.save(branch.get());
			return branch.get();
		}
		else {
			return null;
		}
	}

	@Override
	public Branch deleteBranch(Long id) {
		Optional<Branch> branchOptional = branchRepository.findById(id);
		if(branchOptional.isPresent()) {
			branchOptional.get().getBranchCustomers().forEach(customer -> {
				customer.getCustomerBranches().remove(branchOptional.get());
				customerRepository.save(customer);
			});
			branchRepository.deleteById(id);
			return branchOptional.get();
		}
		else {
			return null;
		}
	}
	
	@Override
	public Branch addBranchCustomer(Long customerId, Long branchId) {
		Optional<Customer> customer = customerRepository.findById(customerId);
		Optional<Branch> branch = branchRepository.findById(branchId);
		if(customer.isPresent() && branch.isPresent()) {
			customer.get().getCustomerBranches().add(branch.get());
			customerRepository.save(customer.get());
			branch.get().getBranchCustomers().add(customer.get());
			branchRepository.save(branch.get());
			return branch.get();
		}
		else {
			return null;
		}
	}

	@Override
	public Branch deleteBranchCustomer(Long customerId, Long branchId) {
		Optional<Customer> customer = customerRepository.findById(customerId);
		Optional<Branch> branch = branchRepository.findById(branchId);
		if(customer.isPresent() && branch.isPresent()) {
			customer.get().getCustomerBranches().remove(branch.get());
			customerRepository.save(customer.get());
			branch.get().getBranchCustomers().remove(customer.get());
			branchRepository.save(branch.get());
			return branch.get();
		}
		else {
			return null;
		}
	}

	@Override
	public List<Customer> getBranchCustomers(Long branchId) {
		Optional<Branch> branch = branchRepository.findById(branchId);
		if(branch.isPresent()) {
			return List.copyOf(branch.get().getBranchCustomers());
		}
		else {
			return null;
		}
	}

}
