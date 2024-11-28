package com.bms.show.exception;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bms.show.dto.BaseErrorResponse;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(OperationFailedException.class)
	public ResponseEntity<?> operationFailedException(Exception ex, WebRequest request) {

		BaseErrorResponse errors = new BaseErrorResponse();
		errors.setTimestamp(LocalDateTime.now());
		errors.setMessage(ex.getMessage());
		errors.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(ShowNotFoundException.class)
	public ResponseEntity<?> showNotFoundException(Exception ex, WebRequest request) {

		BaseErrorResponse errors = new BaseErrorResponse();
		errors.setTimestamp(LocalDateTime.now());
		errors.setMessage(ex.getMessage());
		errors.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(DuplicateMovieException.class)
	public ResponseEntity<?> duplicateMovieException(Exception ex, WebRequest request) {

		BaseErrorResponse errors = new BaseErrorResponse();
		errors.setTimestamp(LocalDateTime.now());
		errors.setMessage(ex.getMessage());
		errors.setStatusCode(HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(DuplicateShowException.class)
	public ResponseEntity<?> duplicateShowException(Exception ex, WebRequest request) {

		BaseErrorResponse errors = new BaseErrorResponse();
		errors.setTimestamp(LocalDateTime.now());
		errors.setMessage(ex.getMessage());
		errors.setStatusCode(HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(MovieNotFoundException.class)
	public ResponseEntity<?> movieNotFoundException(Exception ex, WebRequest request) {

		BaseErrorResponse errors = new BaseErrorResponse();
		errors.setTimestamp(LocalDateTime.now());
		errors.setMessage(ex.getMessage());
		errors.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(NoShowsFoundException.class)
	public ResponseEntity<?> NoShowsFoundException(Exception ex, WebRequest request) {

		BaseErrorResponse errors = new BaseErrorResponse();
		errors.setTimestamp(LocalDateTime.now());
		errors.setMessage(ex.getMessage());
		errors.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> customHandleForServerError(Exception ex, WebRequest request) {

		BaseErrorResponse errors = new BaseErrorResponse();
		errors.setTimestamp(LocalDateTime.now());
		errors.setMessage(ex.getMessage());
		errors.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
