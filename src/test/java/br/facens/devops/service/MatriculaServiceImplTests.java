package br.facens.devops.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.facens.devops.entity.Matricula;
import br.facens.devops.entity.MatriculaStatus;
import br.facens.devops.exception.MatriculaNotFoundException;
import br.facens.devops.repository.MatriculaRepository;

@ExtendWith(SpringExtension.class)
class MatriculaServiceImplTests {

	@Mock 
	private MatriculaRepository repositoryMock;
	
	@InjectMocks
	MatriculaServiceImpl service;

	@Test
	public void testFindAll() {
		Matricula matricula1 = new Matricula(8, false, MatriculaStatus.CURSANDO);
		List<Matricula> matriculasMock = new ArrayList<>(Arrays.asList(matricula1));
		
		when(repositoryMock.findAll()).thenReturn(matriculasMock);
	    List<Matricula> matriculas = service.findAll();
	    
	    assertEquals(matriculas.size(), 1);
	    assertEquals(matriculas.get(0).getNota(), 8);
	}
	
	@Test
	public void testfindByIdWhenIdIsValid() {
		Matricula matriculaMock = new Matricula(8, false, MatriculaStatus.CURSANDO);
		
	    when(repositoryMock.findById(1)).thenReturn(Optional.of(matriculaMock));
	    Matricula matricula = service.findById(1);
	    
	    assertEquals(matricula.getNota(), 8);
	    assertEquals(matricula.isPodeFazerSub(), false);
	}
	
	@Test
	public void testFindByIdWhenIdIsInvalid() {
		Exception exception = assertThrows(MatriculaNotFoundException.class, () -> {
			service.findById(1);
        });
	    
		assertThat(exception).isNotNull();
		assertThat(exception.getClass()).isEqualTo(MatriculaNotFoundException.class);
      	assertThat(exception.getMessage()).isEqualTo("id da matricula não encontrado - 1");
	}
}
