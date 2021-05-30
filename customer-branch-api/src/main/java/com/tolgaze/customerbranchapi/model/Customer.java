package com.tolgaze.customerbranchapi.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "customer")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinTable(name = "customer_branch_relation", 
				joinColumns = @JoinColumn(name = "customer_id"), 
				inverseJoinColumns = @JoinColumn(name = "branch_id"))
	@JsonIgnore
	private Set<Branch> customerBranches = new HashSet<>();
	
	public Customer() {}
	
	public Customer(String name) {
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Set<Branch> getCustomerBranches() {
		return customerBranches;
	}

	public void setCustomerBranches(Set<Branch> customerBranches) {
		this.customerBranches = customerBranches;
	}
	
	

}
