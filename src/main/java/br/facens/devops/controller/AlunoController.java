package br.facens.devops.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.facens.devops.dto.AlunoRequestDTO;
import br.facens.devops.dto.AlunoResponseDTO;
import br.facens.devops.entity.Aluno;
import br.facens.devops.entity.Curso;
import br.facens.devops.exception.AlunoNotFoundException;
import br.facens.devops.service.AlunoService;
import br.facens.devops.service.CursoService;

@RestController
@RequestMapping("/api")
public class AlunoController {
	
	@Autowired
	private AlunoService alunoService;
	
	@Autowired
	private CursoService cursoService;
	
	public AlunoController(AlunoService alunoService, CursoService cursoService) {
		super();
		this.alunoService = alunoService;
		this.cursoService = cursoService;
	}

	@GetMapping("/alunos")
	public List<AlunoResponseDTO> get() {
		List<Aluno> alunos = alunoService.findAll();
		return AlunoResponseDTO.convert(alunos);
	}
	

	@GetMapping("/aluno/{alunoId}")
	public AlunoResponseDTO get(@PathVariable int alunoId) {
		Aluno aluno = alunoService.findById(alunoId);
		
		if (aluno == null) {
			throw new AlunoNotFoundException("id do aluno não encontrado - " + alunoId);
		}
		
		return new AlunoResponseDTO(aluno);
	}
	
	@PostMapping("/aluno/inscrever")
	public String inscreverCurso(@RequestParam int alunoId, @RequestParam int cursoId) {
		Aluno aluno = alunoService.findById(alunoId);
		Curso curso = cursoService.findById(cursoId);
		
		if (curso == null) {
			throw new AlunoNotFoundException("id do curso não encontrado - " + cursoId);
		}
		
		if (aluno == null) {
			throw new AlunoNotFoundException("id do aluno não encontrado - " + alunoId);
		}
		
		aluno.inscreverCurso(curso);
		alunoService.update(aluno);
		
		return "Aluno inscrito com sucesso no curso.";
	}
	
	@PostMapping("/aluno/finalizar")
	public String finalizarCurso(@RequestParam int alunoId, @RequestParam int cursoId, @RequestParam float nota) {
		Aluno aluno = alunoService.findById(alunoId);
		Curso curso = cursoService.findById(cursoId);
		
		if (curso == null) {
			throw new AlunoNotFoundException("id do curso não encontrado - " + cursoId);
		}
		
		if (aluno == null) {
			throw new AlunoNotFoundException("id do aluno não encontrado - " + alunoId);
		}
		
		aluno.finalizarCurso(nota, curso);
		alunoService.update(aluno);
		
		return "Aluno finalizou com sucesso o curso.";
	}
	
	@PostMapping("/aluno")
	public AlunoResponseDTO add(@RequestBody AlunoRequestDTO alunoDTO) {
		Aluno aluno = AlunoRequestDTO.convert(alunoDTO);
		Aluno alunoDB = alunoService.save(aluno);
		return new AlunoResponseDTO(alunoDB);
	}
}
