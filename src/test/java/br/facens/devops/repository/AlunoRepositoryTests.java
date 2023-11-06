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

import br.facens.devops.entity.Aluno;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class AlunoRepositoryTests {

	@Autowired
	AlunoRepository repository;

	@BeforeEach
    public void setUp() {
		repository.save(new Aluno("Joao", 1));
		repository.save(new Aluno("Maria", 1));
	}

    @AfterEach
    public void destroy() {
    	repository.deleteAll();
    }
    
    @Test
    public void testGetAllAlunos() {
        List<Aluno> alunos = repository.findAll();
        
        Assertions.assertThat(alunos.size()).isEqualTo(2);
        Assertions.assertThat(alunos.get(0).getRA()).isNotNegative();
        Assertions.assertThat(alunos.get(0).getRA()).isGreaterThan(0);
        Assertions.assertThat(alunos.get(0).getNome()).isEqualTo("Joao");
    }
    
    @Test
    public void testGetInvalidAluno() {
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
        	repository.findById(15).get();
        });
        
        Assertions.assertThat(exception).isNotNull();
        Assertions.assertThat(exception.getClass()).isEqualTo(NoSuchElementException.class);
        Assertions.assertThat(exception.getMessage()).isEqualTo("No value present");
    }
    
    @Test
    public void testGetCreateAluno() {
    	Aluno saved = new Aluno("Pedro", 1);
    	
    	Aluno returned = repository.save(saved);
    	
        Assertions.assertThat(returned).isNotNull();
        Assertions.assertThat(returned.getRA()).isNotNegative();
        Assertions.assertThat(returned.getRA()).isGreaterThan(0);
        Assertions.assertThat(saved.getNome()).isEqualTo(returned.getNome());
    }

    @Test
    public void testDeleteAluno() {
    	Aluno saved = new Aluno("Pedro", 1);
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
