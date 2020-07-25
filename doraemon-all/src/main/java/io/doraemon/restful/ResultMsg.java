package io.doraemon.restful;

import java.util.List;

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
	public static ResultMsg<Void> notFound() {
		return notFound;
	}
	public static ResultMsg<Void> invalidParameter() {
		return invalidParameter;
	}
	public static ResultMsg<Void> internalError() {
		return internalError;
	}
	public static ResultMsg<Void> ok() {
		return ok;
	}
	public static ResultMsg<List<Void>> okList() {
		return okList;
	}
	public static ResultMsg<Void> timeout() {
		return timeout;
	}
	public static ResultMsg<Void> duplicated() {
		return duplicated;
	}
	public static ResultMsg<Void> paymentRequired() {
		return paymentRequired;
	}
	public static ResultMsg<Void> inProgress() {
		return inProgress;
	}
	private static ResultMsg<Void> notFound = new ResultMsg<Void>(-1404, "not Found");
	private static ResultMsg<Void> invalidParameter = new ResultMsg<Void>(-1696, "Invalid parameter");
	private static ResultMsg<Void> internalError = new ResultMsg<Void>(-1500, "internal error");
	private static ResultMsg<Void> ok = new ResultMsg<Void>();
	private static ResultMsg<List<Void>> okList = new ResultMsg<List<Void>>();
	private static ResultMsg<Void> timeout = new ResultMsg<Void>(-1406, "time out");
	private static ResultMsg<Void> duplicated = new ResultMsg<Void>(-1409, "duplicated request");
	private static ResultMsg<Void> paymentRequired = new ResultMsg<Void>(-1402, "payment Required");
	private static ResultMsg<Void> inProgress = new ResultMsg<Void>(-110, "In Progress");
}
