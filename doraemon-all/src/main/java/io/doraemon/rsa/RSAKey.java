package io.doraemon.rsa;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import io.doraemon.conf.Config;
import io.doraemon.exception.DoraemonRuntimeException;
import io.doraemon.logging.Logger;
import io.doraemon.logging.LoggerFactory;
import sun.misc.BASE64Decoder;

public class RSAKey {
	private static Logger logger = LoggerFactory.getLogger(RSAKey.class);
	private RSAPublicKey publicKey;
	private RSAPrivateKey privateKey;
	
	public RSAPublicKey getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(RSAPublicKey publicKey) {
		this.publicKey = publicKey;
	}
	public RSAPrivateKey getPrivateKey() {
		return privateKey;
	}
	public void setPrivateKey(RSAPrivateKey privateKey) {
		this.privateKey = privateKey;
	}
	private RSAKey(String rsaPublicKeyFile, String rsaPrivateKeyFile) {
		super();
		loadPublicKey(rsaPublicKeyFile);
		loadPrivateKey(rsaPrivateKeyFile);
	}
	
	private void loadPublicKey(String publicKeyFile) {
		BufferedReader br = null;
		try {
			br= new BufferedReader(
					new InputStreamReader(new FileInputStream(publicKeyFile)));
			String readLine= null;
			StringBuilder sb= new StringBuilder();
			while((readLine= br.readLine())!=null){
				if(readLine.charAt(0)=='-'){
					continue;
				}else{
					sb.append(readLine);
					sb.append('\r');
				}
			}
			publicKey = toPublicKey(sb.toString());
		} catch (IOException |NullPointerException e) {
			logger.info("can not load public key", e);
			throw new DoraemonRuntimeException("can not load public key",e);
		} finally {
			if(br != null)
				try {
					br.close();
				} catch (IOException e) {
					throw new DoraemonRuntimeException("can not close BufferedReader",e);
				}
		}
	}
 
 
	/**
	 * 从字符串中加载公钥
	 * @param publicKeyStr 公钥数据字符串
	 * @throws Exception 加载公钥时产生的异常
	 */
	private RSAPublicKey toPublicKey(String publicKeyStr){
		try {
			BASE64Decoder base64Decoder= new BASE64Decoder();
			byte[] buffer= base64Decoder.decodeBuffer(publicKeyStr);
			KeyFactory keyFactory= KeyFactory.getInstance("RSA");
			X509EncodedKeySpec keySpec= new X509EncodedKeySpec(buffer);
			return (RSAPublicKey) keyFactory.generatePublic(keySpec);
		} catch (NoSuchAlgorithmException|InvalidKeySpecException|IOException|NullPointerException e) {
			logger.info("can not generate public key", e);
			throw new DoraemonRuntimeException("can not generate public key",e);
		} 
	}
 
	/**
	 * 从文件中加载私钥
	 * @param keyFileName 私钥文件名
	 * @return 是否成功
	 * @throws Exception 
	 */
	private void loadPrivateKey(String privateKeyFile){
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(privateKeyFile)));
			String readLine= null;
			StringBuilder sb= new StringBuilder();
			while((readLine= br.readLine())!=null){
				if(readLine.charAt(0)=='-'){
					continue;
				}else{
					sb.append(readLine);
					sb.append('\r');
				}
			}
			privateKey = toPrivateKey(sb.toString());
		} catch (IOException |NullPointerException e) {
			logger.info("can not load private key", e);
			throw new DoraemonRuntimeException("can not load private key",e);
		} finally {
			if(br != null)
				try {
					br.close();
				} catch (IOException e) {
					throw new DoraemonRuntimeException("can not close BufferedReader",e);
				}
		}
	}
 
	private RSAPrivateKey toPrivateKey(String privateKeyStr) {
		try {
			BASE64Decoder base64Decoder= new BASE64Decoder();
			byte[] buffer= base64Decoder.decodeBuffer(privateKeyStr);
			PKCS8EncodedKeySpec keySpec= new PKCS8EncodedKeySpec(buffer);
			KeyFactory keyFactory= KeyFactory.getInstance("RSA");
			return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
		} catch (NoSuchAlgorithmException|InvalidKeySpecException|IOException|NullPointerException e) {
			logger.info("can not generatre private key", e);
			throw new DoraemonRuntimeException("can not generatre private key",e);
		}
	}
	public static RSAKey createRSAKey(String rsaPublicKeyFile, String rsaPrivateKeyFile) {
		return new RSAKey(rsaPublicKeyFile, rsaPrivateKeyFile);
	}
}
