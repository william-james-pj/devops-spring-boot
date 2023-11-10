package br.facens.devops.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AlunoExceptionHandler {
	
	@ExceptionHandler(AlunoNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<String> handleException(AlunoNotFoundException exc) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exc.getMessage());
	}
	
	@ExceptionHandler(AlunoCourseLimitExceededException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<String> handleException(AlunoCourseLimitExceededException exc) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exc.getMessage());
	}
}
