package com.bms.show.exception;

public class OperationFailedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OperationFailedException() {

	}

	public OperationFailedException(String message) {

		super(message);
	}

}
