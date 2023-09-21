package br.facens.devops.exception;

public class AlunoNotFoundException extends RuntimeException {

	public AlunoNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public AlunoNotFoundException(String message) {
		super(message);
	}

	public AlunoNotFoundException(Throwable cause) {
		super(cause);
	}

}
