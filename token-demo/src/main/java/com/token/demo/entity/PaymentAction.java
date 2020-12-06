package com.token.demo.entity;

import java.math.BigDecimal;

public class PaymentAction {
	public int paymentType;
    public BigDecimal amount;
    public int currencyID;
    public int vatRate;
    
	public PaymentAction(int paymentType, BigDecimal amount, int currencyID, int vatRate) {
		super();
		this.paymentType = paymentType;
		this.amount = amount;
		this.currencyID = currencyID;
		this.vatRate = vatRate;
	}
	
	public int getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(int paymentType) {
		this.paymentType = paymentType;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public int getCurrencyID() {
		return currencyID;
	}
	public void setCurrencyID(int currencyID) {
		this.currencyID = currencyID;
	}
	public int getVatRate() {
		return vatRate;
	}
	public void setVatRate(int vatRate) {
		this.vatRate = vatRate;
	}
	

	
}
