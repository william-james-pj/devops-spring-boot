package br.facens.devops.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class MatriculaExceptionHandler {
	
	@ExceptionHandler(MatriculaNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<String> handleException(MatriculaNotFoundException exc) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exc.getMessage());
	}
}
