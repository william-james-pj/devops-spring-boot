package br.facens.devops.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.NoSuchElementException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.facens.devops.entity.Curso;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class CursoRepositoryTests {

	@Autowired
	CursoRepository repository;

	@BeforeEach
    public void setUp() {
		repository.save(new Curso("Matematica"));
		repository.save(new Curso("Historia"));
	}

    @AfterEach
    public void destroy() {
    	repository.deleteAll();
    }
    
    @Test
    public void testGetAllCursos() {
        List<Curso> cursos = repository.findAll();
        
        Assertions.assertThat(cursos.size()).isEqualTo(2);
        Assertions.assertThat(cursos.get(0).getId()).isNotNegative();
        Assertions.assertThat(cursos.get(0).getId()).isGreaterThan(0);
        Assertions.assertThat(cursos.get(0).getNome()).isEqualTo("Matematica");
    }
    
    @Test
    public void testGetInvalidCurso() {
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
        	repository.findById(15).get();
        });
        
        Assertions.assertThat(exception).isNotNull();
        Assertions.assertThat(exception.getClass()).isEqualTo(NoSuchElementException.class);
        Assertions.assertThat(exception.getMessage()).isEqualTo("No value present");
    }
    
    @Test
    public void testGetCreateCurso() {
    	Curso saved = new Curso("Calculo");
    	
    	Curso returned = repository.save(saved);
    	
        Assertions.assertThat(returned).isNotNull();
        Assertions.assertThat(returned.getId()).isNotNegative();
        Assertions.assertThat(returned.getId()).isGreaterThan(0);
        Assertions.assertThat(saved.getNome()).isEqualTo(returned.getNome());
    }

    @Test
    public void testDeleteCurso() {
    	Curso saved = new Curso("Calculo");
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
