package br.facens.devops.exception;

public class CursoNotFoundException extends RuntimeException {

	public CursoNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public CursoNotFoundException(String message) {
		super(message);
	}

	public CursoNotFoundException(Throwable cause) {
		super(cause);
	}

}
