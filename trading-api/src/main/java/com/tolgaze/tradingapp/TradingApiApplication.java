package com.tolgaze.tradingapp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.tolgaze.tradingapp.model.Balance;
import com.tolgaze.tradingapp.model.TradingPair;
import com.tolgaze.tradingapp.repository.BalanceReposity;
import com.tolgaze.tradingapp.repository.PairRepository;

@SpringBootApplication
public class TradingApiApplication {
	
	
	public static void main(String[] args) {
		SpringApplication.run(TradingApiApplication.class, args);
	}
	
    @Bean
    CommandLineRunner init(PairRepository pairRepository, BalanceReposity balanceReposity) {

        return args -> {
        	// Inserts pairs
        	List<TradingPair> tradingPairs = new ArrayList<>();
    		tradingPairs.add(new TradingPair("btc"));
    		tradingPairs.add(new TradingPair("eth"));
    		if(pairRepository.findAll().isEmpty()) {
    			pairRepository.saveAll(tradingPairs);
    			System.out.println("Symbols saved");
    		}
    		else {
    			System.out.println("Symbols exist");
    		}
            
            // Inserts balances
            List<Balance> balances = new ArrayList<>();
            balances.add(new Balance("btc", Double.valueOf(0)));
            balances.add(new Balance("eth", Double.valueOf(0)));
            balances.add(new Balance("usdt", Double.valueOf(1000)));
            if(balanceReposity.findAll().isEmpty()) {
            	balanceReposity.saveAll(balances);
            	System.out.println("Balances saved");
            }
            else {
            	System.out.println("Balances exist");
            }

        };

    }

}
