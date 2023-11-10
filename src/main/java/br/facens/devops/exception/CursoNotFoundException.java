package br.facens.devops.exception;

public class CursoNotFoundException extends RuntimeException {

	public CursoNotFoundException(String message) {
		super(message);
	}
}
