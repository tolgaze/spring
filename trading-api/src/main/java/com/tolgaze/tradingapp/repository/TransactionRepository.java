package com.tolgaze.tradingapp.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tolgaze.tradingapp.model.Transaction;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String>{
	List<Transaction> findByTransactionDateBetween(Date from, Date to);
}
