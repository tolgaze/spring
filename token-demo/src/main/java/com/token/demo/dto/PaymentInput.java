package com.token.demo.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.token.demo.entity.PaymentInfo;

public class PaymentInput {
	private int returnCode;
	private String returnDesc;
	private String receiptMsgCustomer;
	private String receiptMsgMerchant;
	private List<PaymentInfo> paymentInfoList;
    @JsonProperty("QRdata") 
    private String qRdata;
    
    
	public int getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}
	public String getReturnDesc() {
		return returnDesc;
	}
	public void setReturnDesc(String returnDesc) {
		this.returnDesc = returnDesc;
	}
	public String getReceiptMsgCustomer() {
		return receiptMsgCustomer;
	}
	public void setReceiptMsgCustomer(String receiptMsgCustomer) {
		this.receiptMsgCustomer = receiptMsgCustomer;
	}
	public String getReceiptMsgMerchant() {
		return receiptMsgMerchant;
	}
	public void setReceiptMsgMerchant(String receiptMsgMerchant) {
		this.receiptMsgMerchant = receiptMsgMerchant;
	}
	public List<PaymentInfo> getPaymentInfoList() {
		return paymentInfoList;
	}
	public void setPaymentInfoList(List<PaymentInfo> paymentInfoList) {
		this.paymentInfoList = paymentInfoList;
	}
	public String getqRdata() {
		return qRdata;
	}
	public void setqRdata(String qRdata) {
		this.qRdata = qRdata;
	}
	



}
