package com.token.demo.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.token.demo.dto.PaymentInput;
import com.token.demo.dto.PaymentOutput;
import com.token.demo.entity.PaymentAction;
import com.token.demo.entity.PaymentInfo;
import com.token.demo.service.ServiceInterface;

@Controller
public class PaymentController {
	
	@Autowired
	ServiceInterface serviceInterface;
	
	@RequestMapping("/pay")
	public String payment(Model model, @RequestParam String qrData) {
		
		PaymentInput paymentInput = new PaymentInput();
		
		PaymentAction paymentAction = new PaymentAction(3, BigDecimal.valueOf(12345), 949, 800);
		List<PaymentAction> paymentActionList = new ArrayList<>();
		paymentActionList.add(paymentAction);
		
		PaymentInfo paymentInfo = new PaymentInfo(67, paymentActionList);
		List<PaymentInfo> paymentInfoList = new ArrayList<>();
		paymentInfoList.add(paymentInfo);
		
		paymentInput.setReturnCode(1000);
		paymentInput.setReturnDesc("success");
		paymentInput.setReceiptMsgCustomer("beko Campaign/n2018");
		paymentInput.setReceiptMsgMerchant("beko Campaign Merchant/n2018");
		paymentInput.setPaymentInfoList(paymentInfoList);
		paymentInput.setqRdata(qrData);
		
		PaymentOutput paymentOutput = serviceInterface.pay(paymentInput);
		model.addAttribute("paymentOutput",paymentOutput);
		
		return "paymentResult";
	}

}

