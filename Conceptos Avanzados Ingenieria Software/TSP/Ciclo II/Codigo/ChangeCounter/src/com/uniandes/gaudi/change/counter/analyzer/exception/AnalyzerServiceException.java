package com.uniandes.gaudi.change.counter.analyzer.exception;

import com.uniandes.gaudi.change.counter.exception.ChangeCounterException;

/**
 * This class represents an exception for the analyzer module
 * 
 * @class AnalyzerServiceException.java
 * @author Felipe
 * @Date 6/04/2013
 * @since 1.0
 */
public class AnalyzerServiceException extends ChangeCounterException {

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This constructor creates an instance with a message
	 * 
	 * @param message for the exception
	 */
	public AnalyzerServiceException(String message) {
		super(message);
	}

	/**
	 * This constructor creates an instance with a message and a throwable cause
	 * 
	 * @param message for the exception
	 * @param throwable cause for this exception
	 */
	public AnalyzerServiceException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
