package com.example.workshopmongo.resource.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.workshopmongo.service.exception.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> handleObjectNotFound(
			ObjectNotFoundException e, HttpServletRequest request) {
		StandardError error = new StandardError(
				System.currentTimeMillis(), 
				HttpStatus.NOT_FOUND.value(), 
				HttpStatus.NOT_FOUND.getReasonPhrase(), 
				e.getMessage(), 
				request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
}
