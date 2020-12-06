package com.token.demo.service;

import java.util.Collections;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.token.demo.dto.PaymentInput;
import com.token.demo.dto.PaymentOutput;
import com.token.demo.dto.QrInput;
import com.token.demo.dto.QrOutput;

@Service
public class ServiceImpl implements ServiceInterface {
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${token.endpoint.qr}")
	private String qrUrl;
	
	@Value("${token.endpoint.payment}")
	private String paymentUrl;
	
	@Value("${token.clientId}")
	private String clientId;
	
	@Value("${token.clientSecret}")
	private String clientSecret;
	
	@Override
	public QrOutput getQr(QrInput qrInput) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("x-ibm-client-id", clientId);
		headers.set("x-ibm-client-secret", clientSecret);
		
		HttpEntity<QrInput> request = new HttpEntity<>(qrInput, headers);
		String response = restTemplate.postForObject(qrUrl, request, String.class);
		JSONObject obj= new JSONObject(response);
		QrOutput qrOutput = new QrOutput(obj.getString("QRdata"));
		qrOutput.setReturnCode(obj.getInt("returnCode"));
		qrOutput.setReturnDesc(obj.getString("returnDesc"));
		
		return qrOutput;
	}

	@Override
	public PaymentOutput pay(PaymentInput paymentInput) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("x-ibm-client-id", clientId);
		headers.set("x-ibm-client-secret", clientSecret);
		
		HttpEntity<PaymentInput> request = new HttpEntity<>(paymentInput, headers);
		PaymentOutput paymentOutput = restTemplate.postForObject(paymentUrl, request, PaymentOutput.class);
		
		return paymentOutput;
	}

}
