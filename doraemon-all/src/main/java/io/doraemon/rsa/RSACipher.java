package io.doraemon.rsa;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import io.doraemon.logging.Logger;
import io.doraemon.logging.LoggerFactory;

public class RSACipher {
	private static Logger logger = LoggerFactory.getLogger(RSACipher.class);
	private RSAPublicKey rsaPublicKey;
	private RSAPrivateKey rsaPrivateKey;
	
	private RSACipher(RSAPublicKey rsaPublicKey, RSAPrivateKey rsaPrivateKey) {
		this.rsaPublicKey = rsaPublicKey;
		this.rsaPrivateKey = rsaPrivateKey;
	}
	public String encrypt(String toencrypt) {
	    Cipher cipher;
		try {
			cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, rsaPublicKey);
		    sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
			return encoder.encode(cipher.doFinal(toencrypt.getBytes("UTF-8")));
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException e) {
			logger.warn("failed to encrypt ", e);
			return null;
		}
	} 
	public String decrypt(String encrypted) {
	    Cipher cipher;
		try {
			cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, rsaPrivateKey);
		    sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
			return encoder.encode(cipher.doFinal(encrypted.getBytes("UTF-8")));
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException e) {
			logger.warn("failed to decrypt ", e);
			return null;
		}
	}
	public static RSACipher newCipher(String rsaPublicKeyFile, String rsaPrivateKeyFile ) {
		RSAKey key = RSAKey.createRSAKey(rsaPublicKeyFile, rsaPrivateKeyFile);
		return new RSACipher(key.getPublicKey(), key.getPrivateKey());
	}
}
