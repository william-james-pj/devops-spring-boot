package br.facens.devops.exception;

public class AlunoNotFoundException extends RuntimeException {

	public AlunoNotFoundException(String message) {
		super(message);
	}
}
