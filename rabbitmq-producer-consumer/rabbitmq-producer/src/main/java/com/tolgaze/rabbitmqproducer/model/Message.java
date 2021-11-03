package com.tolgaze.rabbitmqproducer.model;

public class Message  {
	
	private int messageLength;
	private String messageText;
	
	public int getMessageLength() {
		return messageLength;
	}
	public void setMessageLength(int messageLength) {
		this.messageLength = messageLength;
	}
	public String getMessageText() {
		return messageText;
	}
	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}
	
	

}
