package br.facens.devops.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.facens.devops.dto.CursoForm;
import br.facens.devops.dto.CursoRequestDTO;
import br.facens.devops.dto.CursoResponseDTO;
import br.facens.devops.entity.Curso;
import br.facens.devops.exception.CursoNotFoundException;
import br.facens.devops.service.CursoService;

@RestController
@RequestMapping("/api")
public class CursoController {
	
	@Autowired
	private CursoService cursoService;
	
	public CursoController(CursoService cursoService) {
		super();
		this.cursoService = cursoService;
	}

	@GetMapping("/cursos")
	public List<CursoResponseDTO> get() {
		List<Curso> cursos = cursoService.findAll();
		return CursoResponseDTO.convert(cursos);
	}
	
	@GetMapping("/curso/{cursoId}")
	public CursoResponseDTO get(@PathVariable int cursoId) {
		Curso curso = cursoService.findById(cursoId);
		
		if (curso == null) {
			throw new CursoNotFoundException("id do curso não encontrado - " + cursoId);
		}
		
		return new CursoResponseDTO(curso);
	}
	
	@PostMapping("/curso")
	public ResponseEntity<CursoResponseDTO> add(@RequestBody CursoRequestDTO cursoDTO) {
		Curso curso = CursoRequestDTO.convert(cursoDTO);
		Curso cursoDB = cursoService.save(curso);
		return ResponseEntity.status(HttpStatus.CREATED).body(new CursoResponseDTO(cursoDB));
	}
	
	@PutMapping("/curso")
	public CursoResponseDTO update(@RequestBody CursoForm cursoForm) {
		Curso hasCurso = cursoService.findById(cursoForm.getId());
		
		if (hasCurso == null) {
			throw new CursoNotFoundException("id do curso não encontrado - " + cursoForm.getId());
		}
		
		Curso curso = CursoForm.convert(cursoForm);
		Curso cursoDB = cursoService.save(curso);
		return new CursoResponseDTO(cursoDB);
	}
	
	@DeleteMapping("/curso/{cursoId}")
	public ResponseEntity<Void> delete(@PathVariable int cursoId) {
		Curso curso = cursoService.findById(cursoId);
		
		if (curso == null) {
			throw new CursoNotFoundException("id do curso não encontrado - " + cursoId);
		}
		
		cursoService.delete(cursoId);
		
		return ResponseEntity.noContent().build();
	}
}
