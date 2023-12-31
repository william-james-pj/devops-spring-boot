package br.facens.devops.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.NoSuchElementException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.facens.devops.entity.Matricula;
import br.facens.devops.entity.MatriculaStatus;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class MatriculaRepositoryTests {

	@Autowired
	MatriculaRepository repository;

    @Test
    public void testGetAllMatriculas() {
        List<Matricula> matriculas = repository.findAll();
        
        Assertions.assertThat(matriculas.size()).isEqualTo(3);
        Assertions.assertThat(matriculas.get(0).getId()).isNotNegative();
        Assertions.assertThat(matriculas.get(0).getId()).isGreaterThan(0);
    }
    
    @Test
    public void testGetInvalidMatricula() {
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
        	repository.findById(15).get();
        });
        
        Assertions.assertThat(exception).isNotNull();
        Assertions.assertThat(exception.getClass()).isEqualTo(NoSuchElementException.class);
        Assertions.assertThat(exception.getMessage()).isEqualTo("No value present");
    }
    
    @Test
    public void testGetCreateMatricula() {
    	Matricula saved = new Matricula(9, false, MatriculaStatus.CURSANDO);
    	
    	Matricula returned = repository.save(saved);
    	
        Assertions.assertThat(returned).isNotNull();
        Assertions.assertThat(returned.getId()).isNotNegative();
        Assertions.assertThat(returned.getId()).isGreaterThan(0);
        Assertions.assertThat(saved.getNota()).isEqualTo(returned.getNota());
    }

    @Test
    public void testDeleteMatricula() {
    	Matricula saved = new Matricula(9, false, MatriculaStatus.CURSANDO);
        repository.save(saved);
        
        repository.delete(saved);
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
        	repository.findById(13).get();
        });
        
        Assertions.assertThat(exception).isNotNull();
        Assertions.assertThat(exception.getClass()).isEqualTo(NoSuchElementException.class);
        Assertions.assertThat(exception.getMessage()).isEqualTo("No value present");
    }
}
