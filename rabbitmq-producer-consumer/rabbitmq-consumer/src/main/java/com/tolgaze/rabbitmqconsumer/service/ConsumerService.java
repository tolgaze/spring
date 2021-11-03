package com.tolgaze.rabbitmqconsumer.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tolgaze.rabbitmqconsumer.model.Message;

@Service
public class ConsumerService{
	
    @RabbitListener(queues = "${rabbitmq.message-queue}")
	public void receiveMessage(String messageJsonString) throws JsonMappingException, JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
		Message message = mapper.readValue(messageJsonString, Message.class);
		System.out.println("Message received ->" + messageJsonString );
		System.out.println("messageText ->"+message.getMessageText());
		System.out.println("messageLength ->"+message.getMessageLength());
		System.out.println("------------------------------------------");
	}

}
