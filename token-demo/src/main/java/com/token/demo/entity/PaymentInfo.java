package com.token.demo.entity;

import java.util.List;

public class PaymentInfo {
	
	private int paymentProcessorID;
	private List<PaymentAction> paymentActionList;
	
		public PaymentInfo(int paymentProcessorID, List<PaymentAction> paymentActionList) {
		super();
		this.paymentProcessorID = paymentProcessorID;
		this.paymentActionList = paymentActionList;
	}
		
	public int getPaymentProcessorID() {
		return paymentProcessorID;
	}
	public void setPaymentProcessorID(int paymentProcessorID) {
		this.paymentProcessorID = paymentProcessorID;
	}
	public List<PaymentAction> getPaymentActionList() {
		return paymentActionList;
	}
	public void setPaymentActionList(List<PaymentAction> paymentActionList) {
		this.paymentActionList = paymentActionList;
	}
	
	

}
