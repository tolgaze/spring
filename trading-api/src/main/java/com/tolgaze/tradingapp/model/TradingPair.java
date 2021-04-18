package com.tolgaze.tradingapp.model;

public class TradingPair {
	String id;
	
	public TradingPair(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "TradingPair [id=" + id + "]";
	}
}
