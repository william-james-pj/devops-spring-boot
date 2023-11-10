package br.facens.devops.exception;

public class AlunoCourseLimitExceededException extends RuntimeException {

	public AlunoCourseLimitExceededException(String message) {
		super(message);
	}
}
