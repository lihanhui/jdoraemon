package io.doraemon.logging;

public class LoggerFactory {
	public static <T> Logger getLogger(Class<T> clz) {
		org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(clz);
		return new LoggerAdapter(logger);
	}
}
