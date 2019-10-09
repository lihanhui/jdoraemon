package io.doraemon.logging;

public class LoggerAdapter implements Logger {
	private org.slf4j.Logger logger;
	private LogLevel level;
	public LoggerAdapter(org.slf4j.Logger logger) {
		super();
		this.logger = logger;
		this.level = LogLevel.INFO;
	}

	@Override
	public String getName() {
		return logger.getName();
	}

	@Override
	public boolean isTraceEnabled() {
		return this.level.getLevel() <= LogLevel.TRACE.getLevel();
	}

	@Override
	public void trace(String msg) {
		logger.trace(msg);
	}

	@Override
	public void trace(String format, Object arg) {
		logger.trace(format, arg);
	}

	@Override
	public void trace(String format, Object arg1, Object arg2) {
		logger.trace(format, arg1, arg2);
	}

	@Override
	public void trace(String format, Object... arguments) {
		logger.trace(format, arguments);
	}

	@Override
	public void trace(String msg, Throwable t) {
		logger.trace(msg, t);
	}

	@Override
	public boolean isDebugEnabled() {
		return this.level.getLevel() <= LogLevel.DEBUG.getLevel();
	}

	@Override
	public void debug(String msg) {
		if(this.isDebugEnabled()) logger.debug(msg);
	}

	@Override
	public void debug(String format, Object arg) {
		if(this.isDebugEnabled()) logger.debug(format, arg);
	}

	@Override
	public void debug(String format, Object arg1, Object arg2) {
		if(this.isDebugEnabled()) logger.debug(format, arg1, arg2);
	}

	@Override
	public void debug(String format, Object... arguments) {
		if(this.isDebugEnabled()) logger.debug(format, arguments);
	}

	@Override
	public void debug(String msg, Throwable t) {
		if(this.isDebugEnabled()) logger.debug(msg, t);
	}

	@Override
	public boolean isInfoEnabled() {
		return this.level.getLevel() <= LogLevel.INFO.getLevel();
	}

	@Override
	public void info(String msg) {
		if(this.isInfoEnabled()) logger.info(msg);
	}

	@Override
	public void info(String format, Object arg) {
		if(this.isInfoEnabled()) logger.info(format, arg);
	}

	@Override
	public void info(String format, Object arg1, Object arg2) {
		if(this.isInfoEnabled()) logger.info(format, arg1, arg2);
	}

	@Override
	public void info(String format, Object... arguments) {
		if(this.isInfoEnabled()) logger.info(format, arguments);
	}

	@Override
	public void info(String msg, Throwable t) {
		if(this.isInfoEnabled()) logger.info(msg, t);
	}

	@Override
	public boolean isWarnEnabled() {
		return logger.isWarnEnabled();
	}

	@Override
	public void warn(String msg) {
		if(this.isWarnEnabled()) logger.warn(msg);
	}

	@Override
	public void warn(String format, Object arg) {
		if(this.isWarnEnabled()) logger.warn(format, arg);
	}

	@Override
	public void warn(String format, Object... arguments) {
		if(this.isWarnEnabled()) logger.warn(format, arguments);
	}

	@Override
	public void warn(String format, Object arg1, Object arg2) {
		if(this.isWarnEnabled()) logger.warn(format, arg1, arg2);
	}

	@Override
	public void warn(String msg, Throwable t) {
		if(this.isWarnEnabled()) logger.warn(msg, t);
	}

	@Override
	public boolean isErrorEnabled() {
		return this.level.getLevel() <= LogLevel.ERROR.getLevel();
	}

	@Override
	public void error(String msg) {
		if(this.isErrorEnabled()) logger.error(msg);
	}

	@Override
	public void error(String format, Object arg) {
		if(this.isErrorEnabled()) logger.error(format, arg);
	}

	@Override
	public void error(String format, Object arg1, Object arg2) {
		if(this.isErrorEnabled()) logger.error(format, arg1, arg2);
	}

	@Override
	public void error(String format, Object... arguments) {
		if(this.isErrorEnabled()) logger.error(format, arguments);
	}

	@Override
	public void error(String msg, Throwable t) {
		if(this.isErrorEnabled()) logger.error(msg, t);
	}

	@Override
	public void setLevel(LogLevel level) {
		this.level = level;
	}

	@Override
	public LogLevel getLevel() {
		return this.level;
	}

}
