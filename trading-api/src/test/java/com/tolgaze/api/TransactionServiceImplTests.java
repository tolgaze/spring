package com.tolgaze.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.tolgaze.tradingapp.model.Balance;
import com.tolgaze.tradingapp.model.CurrentPrice;
import com.tolgaze.tradingapp.model.TradingPair;
import com.tolgaze.tradingapp.model.Transaction;
import com.tolgaze.tradingapp.repository.BalanceReposity;
import com.tolgaze.tradingapp.repository.PairRepository;
import com.tolgaze.tradingapp.repository.TransactionRepository;
import com.tolgaze.tradingapp.service.TransactionService;
import com.tolgaze.tradingapp.service.TransactionServiceImpl;

@SpringBootTest(classes = TransactionServiceImpl.class)
class TransactionServiceImplTests {
	
	@MockBean
	PairRepository pairRepository;
	
	@MockBean
	TransactionRepository transactionRepository;
	
	@MockBean
	BalanceReposity balanceRepository;
	
	@MockBean
	RestTemplate restTemplate;
	
	@Autowired
	TransactionService transactionImpl;
	
	@BeforeEach
	void init() {
		TradingPair symbol = new TradingPair("btc");
		Mockito.when(pairRepository.findById(Mockito.anyString())).thenReturn(Optional.of(symbol));
		
		CurrentPrice currentPrice = new CurrentPrice();
		currentPrice.setSymbol("btc");
		currentPrice.setCurrentPrice(60000.00);
		CurrentPrice[] currentPrices = new CurrentPrice[1];
		currentPrices[0] = currentPrice;
		Mockito.when(restTemplate.getForObject("https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&ids=bitcoin,ethereum"
									, CurrentPrice[].class)).thenReturn(currentPrices);
		
		Optional<Balance> balance1 = Optional.of(new Balance("btc",10.00));
		Optional<Balance> balance2 = Optional.of(new Balance("usdt",1000.00));
		Mockito.when(balanceRepository.findById("btc")).thenReturn(balance1);
		Mockito.when(balanceRepository.findById("usdt")).thenReturn(balance2);
		
		Transaction transaction = new Transaction(
				"btc",
				"B",
				1.00,
				60000.00,
				new Date());
		Mockito.when(transactionRepository.save(transaction)).thenReturn(transaction);
		
		Mockito.when(balanceRepository.save(balance1.get())).thenReturn(balance1.get());
		Mockito.when(balanceRepository.save(balance2.get())).thenReturn(balance2.get());
	}
	
	@Test
	void buySellTest() {
		Transaction transaction1 = new Transaction("btc", "B", 0.01, 60000.00, new Date());
		ResponseEntity<String> response = transactionImpl.buySell(transaction1);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

}
