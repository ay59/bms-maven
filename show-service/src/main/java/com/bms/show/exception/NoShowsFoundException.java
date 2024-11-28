package com.bms.show.exception;

public class NoShowsFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoShowsFoundException() {

	}

	public NoShowsFoundException(String message) {

		super(message);
	}

}
