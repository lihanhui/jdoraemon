package io.doraemon.logging;

public enum LogLevel {
	TRACE	(0x01),
	INFO  	(0x02),
	DEBUG	(0x04),
	WARNNING(0x08),
	ERROR   (0x10);
	private int level;
	LogLevel(int level) {
		this.level = level;
	}
	public int getLevel() {
		return level;
	}
	
}
