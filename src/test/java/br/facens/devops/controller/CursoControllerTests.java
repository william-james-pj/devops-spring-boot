package br.facens.devops.controller;

import static org.mockito.ArgumentMatchers.any;
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

import com.fasterxml.jackson.databind.ObjectMapper;

import br.facens.devops.dto.CursoForm;
import br.facens.devops.dto.CursoRequestDTO;
import br.facens.devops.entity.Curso;
import br.facens.devops.service.CursoService;

@WebMvcTest(CursoController.class)
class CursoControllerTests {

	@Autowired
	private MockMvc mvcMock;

	@MockBean
    private CursoService service;
	
	private Curso curso;
    private final ObjectMapper objectMapper = new ObjectMapper();

	@BeforeEach
    public void setup() {
		curso = new Curso("Matematica");
    }
    
    @AfterEach
    public void destroy() {
    	curso = null;
    }
    
    @Test
    public void getAlCursoAPI() throws Exception {
    	when(service.findAll()).thenReturn(Collections.singletonList(curso));
    	
    	mvcMock.perform(MockMvcRequestBuilders
      			.get("/api/cursos")
      			.accept(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(MockMvcResultMatchers.jsonPath("$[*].id").exists())
          .andExpect(MockMvcResultMatchers.jsonPath("$[*].nome").isNotEmpty());
    }
    
    @Test
    public void getByIdCursoAPIWhenIdIsValid() throws Exception {
    	when(service.findById(1)).thenReturn(curso);
    	
    	mvcMock.perform(MockMvcRequestBuilders
      			.get("/api/curso/1")
      			.accept(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
          .andExpect(MockMvcResultMatchers.jsonPath("$.nome").isNotEmpty());
    }
    
    @Test
    public void getByIdCursoAPIWhenIdIsInvalid() throws Exception {
    	mvcMock.perform(MockMvcRequestBuilders
      			.get("/api/curso/1")
      			.accept(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isNotFound());
    }
    
    @Test
    public void createCursoAPI() throws Exception {		
    	Curso cursoDTO = new Curso("Matematica");
		
    	when(service.save(any(Curso.class))).thenReturn(cursoDTO);
    	
    	mvcMock.perform(MockMvcRequestBuilders
    	      .post("/api/curso")
    	      .content(objectMapper.writeValueAsString(new CursoRequestDTO("Matematica")))
    	      .contentType(MediaType.APPLICATION_JSON)
    	      .accept(MediaType.APPLICATION_JSON))
          .andExpect(status().isCreated())
          .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }
    
    @Test
    public void updateCursoAPIWhenIdIsValid() throws Exception {
    	Curso cursoDTO = new Curso("Novo Nome");
    	
    	when(service.findById(1)).thenReturn(cursoDTO);
    	when(service.save(any(Curso.class))).thenReturn(cursoDTO);
    	
    	mvcMock.perform(MockMvcRequestBuilders
      	      .put("/api/curso")
      	      .content(objectMapper.writeValueAsString(new CursoForm(1, "Novo Nome")))
      	      .contentType(MediaType.APPLICATION_JSON)
      	      .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }
    
    @Test
    public void updateCursoAPIWhenIdIsInvalid() throws Exception {
    	Curso cursoDTO = new Curso("Novo Nome");
    	
    	when(service.save(any(Curso.class))).thenReturn(cursoDTO);
    	
    	mvcMock.perform(MockMvcRequestBuilders
      	      .put("/api/curso")
      	      .content(objectMapper.writeValueAsString(new CursoForm(1, "Novo Nome")))
      	      .contentType(MediaType.APPLICATION_JSON)
      	      .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }
    
    @Test
    public void deleteCursoAPIWhenIdIsValid() throws Exception {
    	when(service.findById(1)).thenReturn(curso);
    	when(service.save(any(Curso.class))).thenReturn(curso);
    	
    	mvcMock.perform(MockMvcRequestBuilders
    		  .delete("/api/curso/1"))
            .andExpect(status().isNoContent());
    }
    
    @Test
    public void deleteCursoAPIWhenIdIsInvalid() throws Exception {    	
    	when(service.save(any(Curso.class))).thenReturn(curso);
    	
    	mvcMock.perform(MockMvcRequestBuilders
      	      .delete("/api/curso/1"))
            .andExpect(status().isNotFound());
    }
}
