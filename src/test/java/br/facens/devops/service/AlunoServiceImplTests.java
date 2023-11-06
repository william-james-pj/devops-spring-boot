package br.facens.devops.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.facens.devops.entity.Aluno;
import br.facens.devops.entity.Matricula;
import br.facens.devops.entity.MatriculaStatus;
import br.facens.devops.exception.AlunoNotFoundException;
import br.facens.devops.repository.AlunoRepository;

@ExtendWith(SpringExtension.class)
class AlunoServiceImplTests {
	@Mock 
	private AlunoRepository repositoryMock;
	
	@InjectMocks
	AlunoServiceImpl service;
	
	@Test
	public void testFindAll() {
		Matricula matricula1 = new Matricula(8, false, MatriculaStatus.CURSANDO);
		List<Matricula> matriculas = new ArrayList<>(Arrays.asList(matricula1));
		Aluno aluno1 = new Aluno("Joao", 1, matriculas);
		List<Aluno> alunosMock = new ArrayList<>(Arrays.asList(aluno1));
		
	    when(repositoryMock.findAll()).thenReturn(alunosMock);
	    List<Aluno> alunos = service.findAll();
	    
	    assertEquals(alunos.size(), 1);
	    assertEquals(alunos.get(0).getNome(), "Joao");
	    assertEquals(alunos.get(0).getRA(), 0);
        assertThat(alunos.get(0).getRA()).isNotNegative();
	}
	
	@Test
	public void testfindById() {
		Matricula matricula1 = new Matricula(8, false, MatriculaStatus.CURSANDO);
		List<Matricula> matriculas = new ArrayList<>(Arrays.asList(matricula1));
		Aluno alunoMock = new Aluno("Joao", 1, matriculas);
		
	    when(repositoryMock.findById(1)).thenReturn(Optional.of(alunoMock));
	    Aluno aluno = service.findById(1);
	    
	    assertEquals(aluno.getNome(), "Joao");
	    assertEquals(aluno.getRA(), 0);
	    assertEquals(aluno.getMatriculas().size(), 1);
	}
	
	@Test
	public void testfindByInvalidId() {	
	    when(repositoryMock.findById(1)).thenThrow(new AlunoNotFoundException("id do aluno não encontrado"));
	    Exception exception = assertThrows(AlunoNotFoundException.class, () -> {
	    	service.findById(1);
	    });
	    assertTrue(exception.getMessage().contains("id do aluno não encontrado"));
	}
	
	@Test
	public void testSave() {
		Matricula matricula1 = new Matricula(8, false, MatriculaStatus.CURSANDO);
		List<Matricula> matriculas = new ArrayList<>(Arrays.asList(matricula1));
		Aluno alunoMock = new Aluno("Joao", 1, matriculas);
		
		service.save(alunoMock);
		
	    verify(repositoryMock, times(1)).save(alunoMock);
	    ArgumentCaptor<Aluno> orderArgumentCaptor = ArgumentCaptor.forClass(Aluno.class);
	    verify(repositoryMock).save(orderArgumentCaptor.capture());
	    Aluno alunoCreated = orderArgumentCaptor.getValue();
	    
	    assertNotNull(alunoCreated.getRA());
	    assertEquals("Joao", alunoCreated.getNome());
	}
	
	@Test
	public void testUpdate() {
		Matricula matricula1 = new Matricula(8, false, MatriculaStatus.CURSANDO);
		List<Matricula> matriculas = new ArrayList<>(Arrays.asList(matricula1));
		Aluno alunoMock = new Aluno("Joao", 1, matriculas);

		service.update(alunoMock);
		
	    verify(repositoryMock, times(1)).save(alunoMock);
	    ArgumentCaptor<Aluno> orderArgumentCaptor = ArgumentCaptor.forClass(Aluno.class);
	    verify(repositoryMock).save(orderArgumentCaptor.capture());
	    Aluno alunoCreated = orderArgumentCaptor.getValue();
	    
	    assertNotNull(alunoCreated.getRA());
	    assertEquals("Joao", alunoCreated.getNome());
	}
}
