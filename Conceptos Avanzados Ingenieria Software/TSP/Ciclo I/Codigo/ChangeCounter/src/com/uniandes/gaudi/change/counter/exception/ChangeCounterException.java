package com.uniandes.gaudi.change.counter.exception;

/**
 * This class represents the parent system exception
 * 
 * @class ChangeCounterException.java
 * @author Felipe
 * @Date 6/04/2013
 * @since 1.0
 */
public class ChangeCounterException extends Exception {

	/**
	 * The serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This constructor creates an instance with a message
	 * 
	 * @param message for the exception
	 */
	public ChangeCounterException(String message) {
		super(message);
	}

	/**
	 * This constructor creates an instance with a message and a throwable cause
	 * 
	 * @param message for the exception
	 * @param throwable cause for this exception
	 */
	public ChangeCounterException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
