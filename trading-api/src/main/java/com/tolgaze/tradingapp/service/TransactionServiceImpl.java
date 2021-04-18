package com.tolgaze.tradingapp.service;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.net.ssl.SSLContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tolgaze.tradingapp.model.Balance;
import com.tolgaze.tradingapp.model.CurrentPrice;
import com.tolgaze.tradingapp.model.TradingPair;
import com.tolgaze.tradingapp.model.Transaction;
import com.tolgaze.tradingapp.repository.BalanceReposity;
import com.tolgaze.tradingapp.repository.PairRepository;
import com.tolgaze.tradingapp.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	BalanceReposity balanceReposity;
	
	@Autowired
	TransactionRepository transactionReposity;
	
	@Autowired
	PairRepository pairRepository;
	
	@Autowired
	RestTemplate restTemplate;

	@Override
	public ResponseEntity<String> buySell(Transaction transaction) {
		transaction.setSymbol(transaction.getSymbol().toLowerCase());
		Optional<TradingPair> symbol = pairRepository.findById(transaction.getSymbol());
		if (!symbol.isPresent()) {
			return new ResponseEntity<>("Symbol not found!", HttpStatus.NOT_FOUND);
		}
			
		// Get current prices from coingecko
		SSLContext ctx;
		try {
			ctx = SSLContext.getInstance("TLSv1.2");
			ctx.init(null, null, null);
			SSLContext.setDefault(ctx);
		} catch (NoSuchAlgorithmException e1) {
			return new ResponseEntity<>(e1.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (KeyManagementException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		CurrentPrice[] currentPrices = restTemplate.getForObject(
				"https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&ids=bitcoin,ethereum", 
				CurrentPrice[].class);
		Optional<CurrentPrice> symbolPrice = Arrays.asList(currentPrices).stream().filter(i -> 
								i.getSymbol().equals(transaction.getSymbol())).findFirst();
		transaction.setPrice(symbolPrice.get().getCurrentPrice());
		
		// Get current balances from db
		Balance pair1Balance = balanceReposity.findById(transaction.getSymbol()).get();
		Balance pair2Balance = balanceReposity.findById("usdt").get();
		if(transaction.getTransactionType().equals("B")) {
			if(pair2Balance.getAmount() < transaction.getAmount() * transaction.getPrice()) {
				return new ResponseEntity<>("Insufficient funds!", HttpStatus.BAD_REQUEST);
			}
		}
		else {
			if (pair1Balance.getAmount() < transaction.getAmount()) {
				return new ResponseEntity<>("Insufficient funds!", HttpStatus.BAD_REQUEST);
			}
		}
		
		try {
			// insert transaction record
			transactionReposity.save(new Transaction(
					transaction.getSymbol(),
					transaction.getTransactionType(),
					transaction.getAmount(),
					transaction.getPrice(),
					new Date()));
			
			// update balances
			if(transaction.getTransactionType().equals("B")) {
				pair1Balance.setAmount(pair1Balance.getAmount() + transaction.getAmount());
				pair2Balance.setAmount(pair2Balance.getAmount() - (transaction.getAmount() * transaction.getPrice()));
			}
			else {
				pair1Balance.setAmount(pair1Balance.getAmount() - transaction.getAmount());
				pair2Balance.setAmount(pair2Balance.getAmount() + (transaction.getAmount() * transaction.getPrice()));
			}
			balanceReposity.save(pair1Balance);
			balanceReposity.save(pair2Balance);
			
			return new ResponseEntity<>("transaction done", HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@Override
	public ResponseEntity<List<Transaction>> getHistory(Date from, Date to) {
		if(from.after(to)) {
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
		try {
			List<Transaction> transactions = transactionReposity.findByTransactionDateBetween(from, to);
			if(transactions.isEmpty()) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
			else {
				return new ResponseEntity<>(transactions, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@Override
	public ResponseEntity<List<Balance>> getBalances() {
		try {
			List<Balance> balances = balanceReposity.findAll();
			return new ResponseEntity<>(balances, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
