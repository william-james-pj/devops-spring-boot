package br.facens.devops.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

import br.facens.devops.dto.AlunoRequestDTO;
import br.facens.devops.entity.Aluno;
import br.facens.devops.entity.Curso;
import br.facens.devops.entity.Matricula;
import br.facens.devops.entity.MatriculaStatus;
import br.facens.devops.service.AlunoService;
import br.facens.devops.service.CursoService;


@WebMvcTest(AlunoController.class)
class AlunoControllerTests {
	
	@Autowired
	private MockMvc mvcMock;

	@MockBean
    private AlunoService alunoService;
	
	@MockBean
	private CursoService cursoService;
	
	private Aluno aluno;
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    @BeforeEach
    public void setup() {
    	Matricula matricula1 = new Matricula(8, false, MatriculaStatus.CURSANDO);
		List<Matricula> matriculas = new ArrayList<>(Arrays.asList(matricula1));
		aluno = new Aluno("Joao", 1, matriculas);
    }
    
    @AfterEach
    public void destroy() {
    	aluno = null;
    }
    
    @Test
    public void getAllAlunoAPI() throws Exception {
    	when(alunoService.findAll()).thenReturn(Collections.singletonList(aluno));
    	
    	mvcMock.perform(MockMvcRequestBuilders
      			.get("/api/alunos")
      			.accept(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(MockMvcResultMatchers.jsonPath("$[*].ra").exists())
          .andExpect(MockMvcResultMatchers.jsonPath("$[*].matriculas[*].id").isNotEmpty());
    }
    
    @Test
    public void getByIdAlunoAPI() throws Exception {
    	when(alunoService.findById(1)).thenReturn(aluno);
    	
    	mvcMock.perform(MockMvcRequestBuilders
      			.get("/api/aluno/1")
      			.accept(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(MockMvcResultMatchers.jsonPath("$.ra").exists())
          .andExpect(MockMvcResultMatchers.jsonPath("$.matriculas[*].id").isNotEmpty());
    }
    
    @Test
    public void getByIdMatriculaAPIWhenIdIsInvalid() throws Exception {
    	mvcMock.perform(MockMvcRequestBuilders
      			.get("/api/aluno/1")
      			.accept(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isNotFound());
    }
    
    @Test
    public void createAlunoAPI() throws Exception {		
		Aluno alunoDTO = new Aluno("Maria", 1);
		
    	when(alunoService.save(any(Aluno.class))).thenReturn(alunoDTO);
    	
    	mvcMock.perform(MockMvcRequestBuilders
    	      .post("/api/aluno")
    	      .content(objectMapper.writeValueAsString(new AlunoRequestDTO("Maria")))
    	      .contentType(MediaType.APPLICATION_JSON)
    	      .accept(MediaType.APPLICATION_JSON))
    	  .andDo(print())
          .andExpect(status().isCreated())
          .andExpect(MockMvcResultMatchers.jsonPath("$.ra").exists());
    }
    
    @Test
    public void inscreverCursoAPIWhenCursoIdIsInvalid() throws Exception {
    	mvcMock.perform(MockMvcRequestBuilders
    			.post("/api/aluno/inscrever")
    			.param("alunoId", "1")
    			.param("cursoId", "1"))
    	  .andDo(print())
          .andExpect(status().isNotFound());
    }
    
    @Test
    public void inscreverCursoAPIWhenAlunoIdIsInvalid() throws Exception {
    	Curso cursoDTO = new Curso("Calculo");
    	
    	when(cursoService.findById(1)).thenReturn(cursoDTO);
    	
    	mvcMock.perform(MockMvcRequestBuilders
    			.post("/api/aluno/inscrever")
    			.param("alunoId", "1")
    			.param("cursoId", "1"))
    	  .andDo(print())
          .andExpect(status().isNotFound());
    }
    
    @Test
    public void inscreverCursoAPIWhenIdsAreValid() throws Exception {
    	Curso cursoDTO = new Curso("Calculo");
    	Aluno alunoDTO = new Aluno("Maria", 1);
    	
    	when(cursoService.findById(1)).thenReturn(cursoDTO);
    	when(alunoService.findById(1)).thenReturn(alunoDTO);
    	
    	mvcMock.perform(MockMvcRequestBuilders
    			.post("/api/aluno/inscrever")
    			.param("alunoId", "1")
    			.param("cursoId", "1"))
    	  .andDo(print())
          .andExpect(status().isOk());
    }
    
    @Test
    public void inscreverCursoAPIWhenMaxLimitRaising() throws Exception {
    	Curso cursoDTO = new Curso("Calculo");
    	Aluno alunoDTO = new Aluno("Maria", 1);
    	
    	when(cursoService.findById(1)).thenReturn(cursoDTO);
    	when(alunoService.findById(1)).thenReturn(alunoDTO);
    	
    	mvcMock.perform(MockMvcRequestBuilders
    			.post("/api/aluno/inscrever")
    			.param("alunoId", "1")
    			.param("cursoId", "1"))
    	  .andDo(print())
          .andExpect(status().isOk());
    	
    	mvcMock.perform(MockMvcRequestBuilders
    			.post("/api/aluno/inscrever")
    			.param("alunoId", "1")
    			.param("cursoId", "1"))
    	  .andDo(print())
          .andExpect(status().isBadRequest());
    }
    
    @Test
    public void finalizarCursoAPIWhenCursoIdIsInvalid() throws Exception {
    	mvcMock.perform(MockMvcRequestBuilders
    			.post("/api/aluno/finalizar")
    			.param("alunoId", "1")
    			.param("cursoId", "1")
    			.param("nota", "8"))
    	  .andDo(print())
          .andExpect(status().isNotFound());
    }
    
    @Test
    public void finalizarCursoAPIWhenAlunoIdIsInvalid() throws Exception {
    	Curso cursoDTO = new Curso("Calculo");
    	
    	when(cursoService.findById(1)).thenReturn(cursoDTO);
    	
    	mvcMock.perform(MockMvcRequestBuilders
    			.post("/api/aluno/finalizar")
    			.param("alunoId", "1")
    			.param("cursoId", "1")
    			.param("nota", "8"))
    	  .andDo(print())
          .andExpect(status().isNotFound());
    }
    
    @Test
    public void finalizarCursoAPIWhenIdsAreValid() throws Exception {
    	Matricula matriculaDTO = new Matricula(0, false, MatriculaStatus.CURSANDO);
    	Curso cursoDTO = new Curso("Calculo");
    	Aluno alunoDTO = new Aluno("Maria", 1, Arrays.asList(matriculaDTO));
    	matriculaDTO.setCurso(cursoDTO);
    	
    	when(cursoService.findById(1)).thenReturn(cursoDTO);
    	when(alunoService.findById(1)).thenReturn(alunoDTO);
    	
    	mvcMock.perform(MockMvcRequestBuilders
    			.post("/api/aluno/finalizar")
    			.param("alunoId", "1")
    			.param("cursoId", "1")
    			.param("nota", "8"))
    	  .andDo(print())
          .andExpect(status().isOk());
    }
}
