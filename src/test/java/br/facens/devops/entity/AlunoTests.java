package br.facens.devops.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import br.facens.devops.exception.AlunoCourseLimitExceededException;

class AlunoTests {

	@Test
	public void deveSeMatricularSeForOPrimeiroCurso() {
		Aluno aluno = new Aluno("Thiago", 1);
		Curso curso = new Curso("Botanica");
		
		aluno.inscreverCurso(curso);
		
		assertEquals(aluno.getMatriculas().size(), 1);
	}
	
	@Test
	public void naoDeveSeMatricularSeNaoTiverCursoDisponivel() {
		Aluno aluno = new Aluno("Thiago", 1);
		Curso curso = new Curso("Botanica");
		Curso curso2 = new Curso("Ciencias Socias");
		
		aluno.inscreverCurso(curso);
		
		AlunoCourseLimitExceededException exc = assertThrows(AlunoCourseLimitExceededException.class, () -> aluno.inscreverCurso(curso2));
		
		assertEquals("Limite de cursos excedido. O aluno não pode se inscrever em mais cursos.", exc.getMessage());
	}
	
	@Test
	public void deveFinalizarCursoSeMediaForMaiorIgualASete() {
		Aluno aluno = new Aluno("Thiago", 1);
		Curso curso = new Curso("Botanica");
		
		aluno.inscreverCurso(curso);
		aluno.finalizarCurso(8, curso);
		
		assertEquals(aluno.getQtdCursosDisponivel(), 3);
		assertEquals(aluno.getMatriculas().get(0).getStatus(), MatriculaStatus.FINALIZADO);
	}
	
	@Test
	public void deveReprovarCursoSeMediaForMenorQueSete() {
		Aluno aluno = new Aluno("Thiago", 1);
		Curso curso = new Curso("Botanica");
		
		aluno.inscreverCurso(curso);
		aluno.finalizarCurso(3, curso);
		
		assertEquals(aluno.getQtdCursosDisponivel(), 1);
		assertEquals(aluno.getMatriculas().get(0).getStatus(), MatriculaStatus.REPROVADO);
		assertEquals(aluno.getMatriculas().get(0).isPodeFazerSub(), true);
	}
	
	@Test
	public void deveFazerNadaSePassarUmCursoInvalido() {
		Aluno aluno = new Aluno("Thiago", 1);
		Curso curso = new Curso("Botanica");
		Curso curso2 = new Curso("Botanica 2");
		
		aluno.inscreverCurso(curso);
		aluno.finalizarCurso(3, curso2);
		
		assertEquals(aluno.getQtdCursosDisponivel(), 0);
		assertEquals(aluno.getMatriculas().get(0).getStatus(), MatriculaStatus.CURSANDO);
	}

}
