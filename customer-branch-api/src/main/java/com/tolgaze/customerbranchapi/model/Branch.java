package com.tolgaze.customerbranchapi.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "branch")
public class Branch {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@ManyToMany(mappedBy = "customerBranches", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JsonIgnore
	private Set<Customer> branchCustomers = new HashSet<>();
	
	public Branch() {}
	
	public Branch(String name) {
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

	public Set<Customer> getBranchCustomers() {
		return branchCustomers;
	}

	public void setBranchCustomers(Set<Customer> branchCustomers) {
		this.branchCustomers = branchCustomers;
	}
	
	
	

}
