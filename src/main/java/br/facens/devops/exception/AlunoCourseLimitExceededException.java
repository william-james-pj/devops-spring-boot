package br.facens.devops.exception;

public class AlunoCourseLimitExceededException extends RuntimeException {

	public AlunoCourseLimitExceededException(String message, Throwable cause) {
		super(message, cause);
	}

	public AlunoCourseLimitExceededException(String message) {
		super(message);
	}

	public AlunoCourseLimitExceededException(Throwable cause) {
		super(cause);
	}
}
