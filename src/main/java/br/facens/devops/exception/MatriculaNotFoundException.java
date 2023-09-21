package br.facens.devops.exception;

public class MatriculaNotFoundException extends RuntimeException {

	public MatriculaNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public MatriculaNotFoundException(String message) {
		super(message);
	}

	public MatriculaNotFoundException(Throwable cause) {
		super(cause);
	}
}
