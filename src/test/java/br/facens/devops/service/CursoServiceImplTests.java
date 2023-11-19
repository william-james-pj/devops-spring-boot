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

import br.facens.devops.entity.Curso;
import br.facens.devops.exception.CursoNotFoundException;
import br.facens.devops.repository.CursoRepository;

@ExtendWith(SpringExtension.class)
class CursoServiceImplTests {

	@Mock 
	private CursoRepository repositoryMock;
	
	@InjectMocks
	CursoServiceImpl service;

	@Test
	public void testCursoFindAll() {
		Curso curso1 = new Curso("Fisica");
		Curso curso2 = new Curso("Matematica");
		List<Curso> alunosMock = new ArrayList<>(Arrays.asList(curso1, curso2));
		
	    when(repositoryMock.findAll()).thenReturn(alunosMock);
	    List<Curso> alunos = service.findAll();
	    
	    assertEquals(alunos.size(), 2);
	    assertEquals(alunos.get(0).getNome(), "Fisica");
	    assertEquals(alunos.get(1).getNome(), "Matematica");
	}
	
	@Test
	public void testCursoFindByIdWhenIdIsValid() { 
		Curso cursoMock = new Curso("Fisica");
		
		when(repositoryMock.findById(1)).thenReturn(Optional.of(cursoMock));
		Curso curso = service.findById(1);
		
		assertEquals(curso.getNome(), "Fisica");
	}
	
	@Test
	public void testCursoFindByIdWhenIdIsInvalid() {
		Exception exception = assertThrows(CursoNotFoundException.class, () -> {
			service.findById(1);
        });
	    
		assertThat(exception).isNotNull();
		assertThat(exception.getClass()).isEqualTo(CursoNotFoundException.class);
      	assertThat(exception.getMessage()).isEqualTo("id do curso não encontrado - 1");
	}
	
	@Test
	public void testCursoSave() {
		Curso cursoMock = new Curso("Fisica");
		
		service.save(cursoMock);
		
	    verify(repositoryMock, times(1)).save(cursoMock);
	    ArgumentCaptor<Curso> orderArgumentCaptor = ArgumentCaptor.forClass(Curso.class);
	    verify(repositoryMock).save(orderArgumentCaptor.capture());
	    Curso cursoCreated = orderArgumentCaptor.getValue();
	    
	    assertNotNull(cursoCreated.getNome());
	    assertEquals("Fisica", cursoCreated.getNome());
	}
	
	@Test
	public void testCursoDelete() {
		Curso cursoMock = new Curso("Fisica");
		
	    when(repositoryMock.findById(1)).thenReturn(Optional.of(cursoMock));
	    
	    service.delete(cursoMock.getId());
	    verify(repositoryMock, times(1)).deleteById(cursoMock.getId());
	}
}
