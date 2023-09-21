package br.facens.devops.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.facens.devops.entity.Curso;
import br.facens.devops.exception.CursoNotFoundException;
import br.facens.devops.service.CursoService;

@RestController
@RequestMapping("/api")
public class CursoController {
	
	private CursoService cursoService;
	
	public CursoController(CursoService cursoService) {
		super();
		this.cursoService = cursoService;
	}

	@GetMapping("/cursos")
	public List<Curso> get() {
		return cursoService.findAll();
	}
	
	@GetMapping("/curso/{cursoId}")
	public Curso get(@PathVariable int cursoId) {
		Curso curso = cursoService.findById(cursoId);
		
		if (curso == null) {
			throw new CursoNotFoundException("id do curso não encontrado - " + cursoId);
		}
		
		return curso;
	}
	
	@PostMapping("/curso")
	public Curso add(@RequestBody Curso curso) {
		curso.setId(0);
		Curso cursoDB = cursoService.save(curso);
		return cursoDB;
	}
	
	@PutMapping("/curso")
	public Curso update(@RequestBody Curso curso) {
		Curso cursoDB = cursoService.save(curso);
		return cursoDB;
	}
	
	@DeleteMapping("/curso/{cursoId}")
	public String delete(@PathVariable int cursoId) {
		Curso curso = cursoService.findById(cursoId);
		
		if (curso == null) {
			throw new CursoNotFoundException("id do curso não encontrado - " + cursoId);
		}
		
		cursoService.delete(cursoId);
		
		return "Curso deletado com sucesso!";
	}
}
