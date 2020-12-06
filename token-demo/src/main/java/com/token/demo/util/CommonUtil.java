package com.token.demo.util;

import javax.imageio.ImageIO;

import org.springframework.util.Base64Utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public final class CommonUtil{

    public static String getBase64StringFromQrCode(String qrCodeString) throws WriterException, IOException{
    	QRCodeWriter barcodeWriter = new QRCodeWriter();
	    BitMatrix bitMatrix = barcodeWriter.encode(qrCodeString, BarcodeFormat.QR_CODE, 200, 200);
	    BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    ImageIO.write(image, "jpeg", baos);
	    byte[] bytes = baos.toByteArray();
	    return "data:image/jpeg;base64," +  Base64Utils.encodeToString(bytes);
    }
}