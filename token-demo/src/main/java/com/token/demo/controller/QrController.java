package com.token.demo.controller;

import java.io.IOException;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.zxing.WriterException;
import com.token.demo.dto.QrInput;
import com.token.demo.dto.QrOutput;
import com.token.demo.service.ServiceInterface;
import com.token.demo.util.CommonUtil;

@Controller
public class QrController {
	@Value("${spring.application.name}")
    String appName;
	
	@Autowired
	ServiceInterface qrInterface;
	
	@RequestMapping("/getQr")
	public String getQr(Model model) throws WriterException, IOException {
		
		QrInput qrInput = new QrInput(new BigDecimal(12345));
		QrOutput qrOutput = qrInterface.getQr(qrInput);
		model.addAttribute("qrDataImage", CommonUtil.getBase64StringFromQrCode(qrOutput.getQrData()));
	    model.addAttribute("qrData", qrOutput.getQrData());
		return "qrCode";
	}

}

