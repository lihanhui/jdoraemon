package io.doraemon.restful;

public class ResultMsg<T> {
	private Integer retCode;
	private String errMsg;
	private Long timestamp;
	private T result;
	public Integer getRetCode() {
		return retCode;
	}
	public void setRetCode(Integer retCode) {
		this.retCode = retCode;
	}
	
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	public T getResult() {
		return result;
	}
	public void setResult(T result) {
		this.result = result;
	}
	public ResultMsg(Integer retCode, String errMsg, Long timestamp, T result) {
		super();
		this.retCode = retCode;
		this.errMsg = errMsg;
		this.timestamp = timestamp;
		this.result = result;
	}
	public ResultMsg(Integer retCode, String errMsg) {
		super();
		this.retCode = retCode;
		this.errMsg = errMsg;
		this.timestamp = System.currentTimeMillis();
		this.result = null;
	}
	public ResultMsg() {
		super();
		this.retCode = 0;
		this.errMsg = "";
		this.timestamp = System.currentTimeMillis();
		this.result = null;
	}
	public ResultMsg(T result) {
		super();
		this.retCode = 0;
		this.errMsg = "";
		this.timestamp = System.currentTimeMillis();
		this.result = result;
	}
	@Override
	public String toString() {
		return "ResultMsg [retCode=" + retCode + ", errMsg=" + errMsg + ", timestamp=" + timestamp + ", result="
				+ result + "]";
	}
	
}
