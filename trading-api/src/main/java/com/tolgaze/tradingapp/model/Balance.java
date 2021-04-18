package com.tolgaze.tradingapp.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "balances")
public class Balance {
	private String id;
	private Double amount;
	
	public Balance(String id, Double amount) {
		this.id = id;
		this.amount = amount;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Balance [symbol=" + id + ", amount=" + amount + "]";
	}

}
