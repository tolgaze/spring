package com.tolgaze.tradingapp.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Document(collection = "transactions")
public class Transaction {
	private String symbol;
	private String transactionType;
	private Double amount;
	private Double price;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date transactionDate;
	
	
	
	public Transaction(String symbol , String transactionType, Double amount, Double price, Date transactionDate) {
		this.symbol = symbol;
		this.transactionType = transactionType;
		this.amount = amount;
		this.price = price;
		this.transactionDate = transactionDate;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	@Override
	public String toString() {
		return "Transaction [symbol=" + symbol + ", transactionType=" + transactionType + ", "
	    		+ "amount=" + amount + ", transactionDate=" + transactionDate + "]";
	}

}
