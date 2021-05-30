package com.tolgaze.customerbranchapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tolgaze.customerbranchapi.model.Branch;

public interface BranchRepository extends JpaRepository<Branch, Long> {

}
