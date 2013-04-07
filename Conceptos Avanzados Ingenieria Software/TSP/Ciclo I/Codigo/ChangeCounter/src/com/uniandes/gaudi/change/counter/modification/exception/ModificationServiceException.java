package com.uniandes.gaudi.change.counter.modification.exception;

import com.uniandes.gaudi.change.counter.exception.ChangeCounterException;

/**
 * This class represents an exception for the modification module
 * 
 * @class ModificationServiceException.java
 * @author Felipe
 * @Date 7/04/2013
 * @since 1.0
 */
public class ModificationServiceException extends ChangeCounterException {

	/**
	 * The serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This constructor creates an instance with a message
	 * 
	 * @param message for the exception
	 */
	public ModificationServiceException(String message) {
		super(message);
	}

	/**
	 * This constructor creates an instance with a message and a throwable cause
	 * 
	 * @param message for the exception
	 * @param throwable cause for this exception
	 */
	public ModificationServiceException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
