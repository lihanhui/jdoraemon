package io.doraemon.uuid;

public class UUID {
	static public String uuid(){
		java.util.UUID uuid = java.util.UUID.randomUUID();
		String uuid2 = uuid.toString();
		return uuid2.replace("-","");
	}
	
}
