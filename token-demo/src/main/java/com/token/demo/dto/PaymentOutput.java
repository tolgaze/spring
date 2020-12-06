package com.token.demo.dto;

public class PaymentOutput extends CommonOutput {
	private String applicationID;
	private String sessionID;
	private String posID;
	
	
	public String getApplicationID() {
		return applicationID;
	}
	public void setApplicationID(String applicationID) {
		this.applicationID = applicationID;
	}
	public String getSessionID() {
		return sessionID;
	}
	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}
	public String getPosID() {
		return posID;
	}
	public void setPosID(String posID) {
		this.posID = posID;
	}


	
	

	
}
