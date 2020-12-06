package com.token.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.token.demo.dto.QrOutput;
import com.token.demo.service.ServiceInterface;


@WebMvcTest(controllers = QrController.class)
public class QrControllerTest {
	
	@Autowired
    private MockMvc mvc;

	@MockBean
	private ServiceInterface service;

	@Test
	public void getQrTest() throws Exception {
		
		QrOutput qrOutput = new QrOutput("00020153039495403100800201810200821912-01-2018 12:43:24830481-48608800-100#8712AT00000000018901184034178844secureqrsigniturewillbehereinthenearfuture1=");
		Mockito.when(service.getQr(Mockito.any())).thenReturn(qrOutput);
		
		mvc.perform(post("/getQr").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(model().attribute("qrData", "00020153039495403100800201810200821912-01-2018 12:43:24830481-48608800-100#8712AT00000000018901184034178844secureqrsigniturewillbehereinthenearfuture1="));
	}
}
