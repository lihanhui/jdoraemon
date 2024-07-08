package io.doraemon.util;

public class ByteUtil {
	
	public static int convert3Bytes(byte b1, byte b2, byte b3) {
		int val=((b1 & 0xFF) << 16) | ((b2 & 0xFF) << 8) |(b3 & 0xFF);
    	if((b1 & 0x80) != 0 ) { // negative
			val = (0xFF << 24) | val;
    	}
    	return val;
	}
    public static int convert3BytesLe(byte[] bytes) {
    	if(bytes == null || bytes.length < 3) return 0;
    	
    	byte b3 = bytes[0];
    	byte b2 = bytes[1];
    	byte b1 = bytes[2];
    	
    	return convert3Bytes(b1, b2, b3);
    }
    
    public static int convert3BytesBe(byte[] bytes) {
    	if(bytes == null || bytes.length < 3) return 0; 
    	
    	byte b1 = bytes[0];
    	byte b2 = bytes[1];
    	byte b3 = bytes[2];
    	
    	return convert3Bytes(b1, b2, b3);
    }
    
    public static byte[] convertTo4BytesLe(int value) {
    	byte[] bytes = new byte[4];
    	bytes[0] = (byte)(value & 0xFF);
    	bytes[1] = (byte)((value>>8) & 0xFF);
    	bytes[2] = (byte)((value>>16) & 0xFF);
    	bytes[3] = (byte)((value>>24) & 0xFF);
    	return bytes;
    }
    
    public static byte[] convertTo4BytesBe(int value) {
    	byte[] bytes = new byte[4];
    	bytes[3] = (byte)(value & 0xFF);
    	bytes[2] = (byte)((value>>8) & 0xFF);
    	bytes[1] = (byte)((value>>16) & 0xFF);
    	bytes[0] = (byte)((value>>24) & 0xFF);
    	return bytes;
    }
    
    public static byte[] convertTo2BytesLe(int value) {
    	byte[] bytes = new byte[4];
    	bytes[0] = (byte)(value & 0xFF);
    	bytes[1] = (byte)((value>>8) & 0xFF);
    	return bytes;
    }
    
    public static byte[] convertTo2BytesBe(int value) {
    	byte[] bytes = new byte[4];
    	bytes[1] = (byte)(value & 0xFF);
    	bytes[0] = (byte)((value>>8) & 0xFF);
    	return bytes;
    }
    
}
