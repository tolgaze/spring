package com.token.demo.service;

import com.token.demo.dto.PaymentInput;
import com.token.demo.dto.PaymentOutput;
import com.token.demo.dto.QrInput;
import com.token.demo.dto.QrOutput;

public interface ServiceInterface {
	
	public QrOutput getQr(QrInput qrInput);
	
	public PaymentOutput pay(PaymentInput paymentInput);
	
}
