package com.tolgaze.rabbitmqproducer.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tolgaze.rabbitmqproducer.model.Message;

@Service
public class ProducerServiceImpl implements ProducerService {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Override
	public String sendMessage(Message message) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			String messageJsonString = objectMapper.writeValueAsString(message);
			rabbitTemplate.convertAndSend(messageJsonString);
			return "Message sent successfully.";
		} catch (Exception e) {
			return "Message could not sent!\n" + e.getMessage();
		}
	}

}
