package com.token.demo.dto;

import java.math.BigDecimal;

public class QrInput {
	BigDecimal totalReceiptAmount;
	
	public QrInput(BigDecimal totalReceiptAmount) {
		super();
		this.totalReceiptAmount = totalReceiptAmount;
	}

	public BigDecimal getTotalReceiptAmount() {
		return totalReceiptAmount;
	}

	public void setTotalReceiptAmount(BigDecimal totalReceiptAmount) {
		this.totalReceiptAmount = totalReceiptAmount;
	}

}
