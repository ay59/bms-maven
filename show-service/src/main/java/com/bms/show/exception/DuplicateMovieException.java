package com.bms.show.exception;

public class DuplicateMovieException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DuplicateMovieException() {

	}

	public DuplicateMovieException(String message) {

		super(message);
	}

}
