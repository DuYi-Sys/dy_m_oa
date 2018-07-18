/**
 * 
 */
package com.duyi.commons.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author wangyan
 *
 */
public class Trace implements Log{
	private  Log log;
	private Trace(Class<?> clazz) {
		log=LogFactory.getLog(clazz);
	}

	private Trace(String name) {
		log=LogFactory.getLog(name);
	}
	public static Trace register(Class<?> clazz) {
		return new Trace(clazz);
	}
	public static Trace register(String name) {
		return new Trace(name);
	}
	@Override
	public boolean isDebugEnabled() {
		return log.isDebugEnabled();
	}

	@Override
	public boolean isErrorEnabled() {
		return log.isErrorEnabled();
	}

	@Override
	public boolean isFatalEnabled() {
		return log.isFatalEnabled();
	}

	@Override
	public boolean isInfoEnabled() {
		return log.isInfoEnabled();
	}

	@Override
	public boolean isTraceEnabled() {
		return log.isTraceEnabled();
	}

	@Override
	public boolean isWarnEnabled() {
		return log.isWarnEnabled();
	}

	@Override
	public void trace(Object message) {
		log.trace(message);
	}

	@Override
	public void trace(Object message, Throwable t) {
		log.trace(message, t);
	}

	@Override
	public void debug(Object message) {
		if(isDebugEnabled()) {
			log.debug(message);
		}
		
	}

	@Override
	public void debug(Object message, Throwable t) {
		if(isDebugEnabled()) {
			log.debug(message, t);
		}
	}

	@Override
	public void info(Object message) {
		log.info(message);
	}

	@Override
	public void info(Object message, Throwable t) {
		log.info(message, t);
	}

	@Override
	public void warn(Object message) {
		log.warn(message);
	}

	@Override
	public void warn(Object message, Throwable t) {
		log.warn(message, t);
	}

	@Override
	public void error(Object message) {
		log.error(message);
	}

	@Override
	public void error(Object message, Throwable t) {
		log.error(message, t);
	}

	@Override
	public void fatal(Object message) {
		log.fatal(message);
	}

	@Override
	public void fatal(Object message, Throwable t) {
		log.fatal(message, t);
	}
	
}
