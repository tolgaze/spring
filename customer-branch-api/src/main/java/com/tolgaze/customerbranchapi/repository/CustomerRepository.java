package com.tolgaze.customerbranchapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tolgaze.customerbranchapi.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
