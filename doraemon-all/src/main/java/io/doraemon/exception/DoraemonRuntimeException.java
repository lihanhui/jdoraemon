package io.doraemon.exception;

public class DoraemonRuntimeException extends java.lang.RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public DoraemonRuntimeException(String message){
		super(message);
	}
	public DoraemonRuntimeException(String message, Throwable cause){
		super(message, cause);
	}
	public DoraemonRuntimeException(Throwable cause){
		super(cause);
	}

}
