package br.facens.devops.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.facens.devops.entity.Matricula;
import br.facens.devops.exception.MatriculaNotFoundException;
import br.facens.devops.service.MatriculaService;

@RestController
@RequestMapping("/api")
public class MatriculaController {

	private MatriculaService service;
	
	public MatriculaController(MatriculaService matriculaService) {
		super();
		this.service = matriculaService;
	}

	@GetMapping("/matriculas")
	public List<Matricula> get() {
		return service.findAll();
	}
	
	@GetMapping("/matricula/{matriculaId}")
	public Matricula get(@PathVariable int matriculaId) {
		Matricula matricula = service.findById(matriculaId);
		
		if (matricula == null) {
			throw new MatriculaNotFoundException("id da matricula não encontrado - " + matriculaId);
		}
		
		return matricula;
	}
}
