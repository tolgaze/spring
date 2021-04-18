package com.tolgaze.tradingapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tolgaze.tradingapp.model.Balance;

@Repository
public interface BalanceReposity extends MongoRepository<Balance, String> {

}
