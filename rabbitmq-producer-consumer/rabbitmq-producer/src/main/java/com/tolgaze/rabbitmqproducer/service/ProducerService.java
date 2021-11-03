package com.tolgaze.rabbitmqproducer.service;

import org.springframework.stereotype.Service;

import com.tolgaze.rabbitmqproducer.model.Message;

@Service
public interface ProducerService {
	
	public String sendMessage(Message message);

}
