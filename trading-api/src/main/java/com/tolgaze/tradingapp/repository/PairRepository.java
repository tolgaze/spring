package com.tolgaze.tradingapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tolgaze.tradingapp.model.TradingPair;

@Repository
public interface PairRepository extends MongoRepository<TradingPair, String>{

}
