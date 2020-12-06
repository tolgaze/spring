package com.token.demo.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.token.demo.dto.PaymentOutput;
import com.token.demo.service.ServiceInterface;

@WebMvcTest(controllers = PaymentController.class)
public class PaymentControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private ServiceInterface service;
	
	@Test
	public void payTest() throws Exception {
		PaymentOutput paymentOutput = new PaymentOutput();
		paymentOutput.setReturnCode(1000);
		paymentOutput.setReturnDesc("OK");
		
		Mockito.when(service.pay(Mockito.any())).thenReturn(paymentOutput);
		
		mvc.perform(MockMvcRequestBuilders.post("/pay")
				.param("qrData", "00020153039495403100800201810200821912-01-2018 12:43:24830481-48608800-100#8712AT00000000018901184034178844secureqrsigniturewillbehereinthenearfuture1=")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString(paymentOutput.getReturnDesc())))
				.andExpect(content().string(containsString(String.valueOf(paymentOutput.getReturnCode()))));
	}
}
