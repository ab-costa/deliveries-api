package br.com.abcosta.deliveries.security;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class DeliveriesCrypto {

	public static String encrypt(String password) throws Exception {
		
		String secretKey = "66a024a649814cdd92f93fa39075cf20";
		Key mySecretKey = new SecretKeySpec(secretKey.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, mySecretKey);
		cipher.update(password.getBytes());
		String passwordHash = new String(cipher.doFinal(), "UTF-8");
		
		byte[] passwordHashBytes = passwordHash.getBytes();		
		StringBuilder passwordHashHex = new StringBuilder();
		
		for(byte b : passwordHashBytes) {
			passwordHashHex.append(Integer.toHexString(b));
		}
		return passwordHashHex.toString();
	}
}
