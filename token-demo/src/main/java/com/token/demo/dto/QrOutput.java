package com.token.demo.dto;

public class QrOutput extends CommonOutput {
    public String qRdata;
	
	public QrOutput(String qrData) {
		super();
		this.qRdata = qrData;
	}

	public String  getQrData() {
		return qRdata;
	}

	public void setQrData(String qrData) {
		this.qRdata = qrData;
	}

	@Override
	public String toString() {
		return "QrOutput [qRdata=" + qRdata + ", ReturnCode=" + getReturnCode() + ", ReturnDesc="
				+ getReturnDesc() + "]";
	}
	
	

}
