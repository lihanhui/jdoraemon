package io.doraemon.crypto;

import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import io.doraemon.logging.Logger;
import io.doraemon.logging.LoggerFactory;

public class AESCipher {
	private static Logger logger = LoggerFactory.getLogger(RSACipher.class);
	private static final int KEY_LENGTH = 256;
	private static final int ITERATIONS = 65536;

	public static String encrypt(String toEncrypt, String secretKey, String salt) {
	    try {

	        SecureRandom secureRandom = new SecureRandom();
	        byte[] iv = new byte[16];
	        secureRandom.nextBytes(iv);
	        IvParameterSpec ivspec = new IvParameterSpec(iv);

	        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
	        KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), ITERATIONS, KEY_LENGTH);
	        SecretKey tmp = factory.generateSecret(spec);
	        SecretKeySpec secretKeySpec = new SecretKeySpec(tmp.getEncoded(), "AES");

	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivspec);

	        byte[] cipherText = cipher.doFinal(toEncrypt.getBytes("UTF-8"));
	        byte[] encrypted = new byte[iv.length + cipherText.length];
	        System.arraycopy(iv, 0, encrypted, 0, iv.length);
	        System.arraycopy(cipherText, 0, encrypted, iv.length, cipherText.length);

	        return Base64.getEncoder().encodeToString(encrypted);
	    } catch (Exception e) {
	    	logger.error("failed to encrypt ...", e);
	        return null;
	    }
	}
	
	public static String decrypt(String encoded, String secretKey, String salt) {

	    try {

	        byte[] encrypted = Base64.getDecoder().decode(encoded);
	        byte[] iv = new byte[16];
	        System.arraycopy(encrypted, 0, iv, 0, iv.length);
	        IvParameterSpec ivspec = new IvParameterSpec(iv);

	        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
	        KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), ITERATIONS, KEY_LENGTH);
	        SecretKey tmp = factory.generateSecret(spec);
	        SecretKeySpec secretKeySpec = new SecretKeySpec(tmp.getEncoded(), "AES");

	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivspec);

	        byte[] cipherText = new byte[encrypted.length - 16];
	        System.arraycopy(encrypted, 16, cipherText, 0, cipherText.length);

	        byte[] decrypted = cipher.doFinal(cipherText);
	        return new String(decrypted, "UTF-8");
	    } catch (Exception e) {
	    	logger.error("failed to decrypt ...", e);
	        return null;
	    }
	}


}
