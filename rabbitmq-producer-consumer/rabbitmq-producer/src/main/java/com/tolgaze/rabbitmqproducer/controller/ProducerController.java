package com.tolgaze.rabbitmqproducer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tolgaze.rabbitmqproducer.model.Message;
import com.tolgaze.rabbitmqproducer.service.ProducerService;

@RestController
@RequestMapping(value = "/producer/v1")
public class ProducerController {
	
	@Autowired
	ProducerService producerService;

	@PostMapping(value = "message")
	public String sendMessage(@RequestBody Message message) {
		return producerService.sendMessage(message);
	}
}
