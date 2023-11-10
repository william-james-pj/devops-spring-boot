package br.facens.devops.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.facens.devops.entity.Aluno;
import br.facens.devops.entity.Curso;
import br.facens.devops.entity.Matricula;
import br.facens.devops.entity.MatriculaStatus;
import br.facens.devops.service.MatriculaService;

@WebMvcTest(MatriculaController.class)
class MatriculaControllerTests {

	@Autowired
	private MockMvc mvcMock;

	@MockBean
    private MatriculaService service;
	
	private Matricula matricula;

	@BeforeEach
    public void setup() {
		Curso curso = new Curso("Matematica");
		Aluno aluno = new Aluno("Joao", 1);
    	matricula = new Matricula(8, false, MatriculaStatus.CURSANDO, curso, aluno);
    }
    
    @AfterEach
    public void destroy() {
    	matricula = null;
    }
    
    @Test
    public void getAllMatriculaAPI() throws Exception {
    	when(service.findAll()).thenReturn(Collections.singletonList(matricula));
    	mvcMock.perform(MockMvcRequestBuilders
      			.get("/api/matriculas")
      			.accept(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(MockMvcResultMatchers.jsonPath("$[*].id").exists())
          .andExpect(MockMvcResultMatchers.jsonPath("$[*].curso.id").isNotEmpty())
          .andExpect(MockMvcResultMatchers.jsonPath("$[*].aluno.ra").isNotEmpty());
    }
    
    @Test
    public void getByIdMatriculaAPIWhenIdIsValid() throws Exception {
    	when(service.findById(1)).thenReturn(matricula);
    	mvcMock.perform(MockMvcRequestBuilders
      			.get("/api/matricula/1")
      			.accept(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
          .andExpect(MockMvcResultMatchers.jsonPath("$.curso.id").isNotEmpty())
          .andExpect(MockMvcResultMatchers.jsonPath("$.aluno.ra").isNotEmpty());
    }
    
    @Test
    public void getByIdMatriculaAPIWhenIdIsInvalid() throws Exception {
    	mvcMock.perform(MockMvcRequestBuilders
      			.get("/api/matricula/1")
      			.accept(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isNotFound());
    }
}
