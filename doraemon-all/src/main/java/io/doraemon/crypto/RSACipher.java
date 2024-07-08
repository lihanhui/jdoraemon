package io.doraemon.crypto;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

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
			Encoder encoder = java.util.Base64.getEncoder();
			byte[] bytes = cipher.doFinal(toencrypt.getBytes("UTF-8"));
			return encoder.encodeToString(bytes);
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
		    Decoder decoder = java.util.Base64.getDecoder();
		    ;
			return new String(cipher.doFinal(decoder.decode(encrypted)));
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |IllegalBlockSizeException | BadPaddingException e) {
			logger.warn("failed to decrypt ", e);
			return null;
		}
	}
	
	public static RSACipher newCipher(String rsaPublicKeyFile, String rsaPrivateKeyFile ) {
		RSAKey key = RSAKey.createRSAKey(rsaPublicKeyFile, rsaPrivateKeyFile);
		return new RSACipher(key.getPublicKey(), key.getPrivateKey());
	}
	
	public static void main(String[] args){
		RSACipher newCipher = RSACipher.newCipher(
				"/Users/lihanhui/work/etc/key/public_test.key", 
				"/Users/lihanhui/work/etc/key/pkcs8_rsa_private_test.key");
		String encrypted = newCipher.encrypt("127.0.0.1");
		System.out.println(encrypted);
		System.out.println(newCipher.decrypt(encrypted));
	}
}
