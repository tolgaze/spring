package com.tolgaze.customerbranchapi.service;

import java.util.List;

import javax.transaction.Transactional;

import com.tolgaze.customerbranchapi.model.Branch;
import com.tolgaze.customerbranchapi.model.Customer;

public interface BranchService {
	
	public List<Branch> getBranchList();
	public Branch getBranchById(Long id);
	public Branch createBranch(Branch branch);
	public Branch updateBranch(Long id, Branch branch);
	@Transactional
	public Branch deleteBranch(Long id);
	@Transactional
	public Branch addBranchCustomer(Long customerId, Long branchId);
	@Transactional
	public Branch deleteBranchCustomer(Long customerId, Long branchId);
	public List<Customer> getBranchCustomers(Long branchId);

}
