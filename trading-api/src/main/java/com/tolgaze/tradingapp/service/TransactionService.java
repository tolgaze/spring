package com.tolgaze.tradingapp.service;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tolgaze.tradingapp.model.Balance;
import com.tolgaze.tradingapp.model.Transaction;

@Service
public interface TransactionService {
	ResponseEntity<String> buySell(Transaction transaction);
	ResponseEntity<List<Transaction>> getHistory(@DateTimeFormat(pattern = "dd.MM.yyyy") Date from,
												 @DateTimeFormat(pattern = "dd.MM.yyyy") Date to);
	ResponseEntity<List<Balance>> getBalances();
}
