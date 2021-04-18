package com.tolgaze.tradingapp.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tolgaze.tradingapp.model.Balance;
import com.tolgaze.tradingapp.model.Transaction;
import com.tolgaze.tradingapp.service.TransactionService;

@RestController
@RequestMapping("/tradingapp")
public class TransactionController {
	
	@Autowired
	TransactionService transactionService;
	
	@PostMapping("/buy")
	public ResponseEntity<String> buy(@RequestBody Transaction transaction) {
		transaction.setTransactionType("B");
		ResponseEntity<String> response = transactionService.buySell(transaction);
		return response;
	}
	
	@PostMapping("/sell")
	public ResponseEntity<String> sell(@RequestBody Transaction transaction) {
		transaction.setTransactionType("S");
		ResponseEntity<String> response = transactionService.buySell(transaction);
		return response;
	}
	
	@GetMapping("/history")
	public ResponseEntity<List<Transaction>> getHistory(
					@RequestParam("from") 	@DateTimeFormat(pattern = "dd.MM.yyyy") Date from,
					@RequestParam("to") 	@DateTimeFormat(pattern = "dd.MM.yyyy") Date to) {
		ResponseEntity<List<Transaction>> response = transactionService.getHistory(from, to);
		return response;
	}
	
	@GetMapping("/balance")
	public  ResponseEntity<List<Balance>> getBalance() {
		ResponseEntity<List<Balance>> response = transactionService.getBalances();
		return response;
	}

}
