package br.facens.devops.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.facens.devops.entity.Matricula;
import br.facens.devops.entity.MatriculaStatus;

public class MatriculaResponseDTO {
	private int id;
	private double nota;
	private MatriculaStatus status;
	private CursoResponseDTO curso;
	private AlunoDTO aluno;
	
	public MatriculaResponseDTO(Matricula matricula) {
		super();
		this.id = matricula.getId();
		this.nota = matricula.getNota();
		this.status = matricula.getStatus();
		this.curso = new CursoResponseDTO(matricula.getCurso());
		this.aluno = new AlunoDTO(matricula.getAluno());
	}

	public int getId() {
		return id;
	}
	
	public double getNota() {
		return nota;
	}
	
	public MatriculaStatus getStatus() {
		return status;
	}
	
	public CursoResponseDTO getCurso() {
		return curso;
	}
	
	public AlunoDTO getAluno() {
		return aluno;
	}
	
	public static List<MatriculaResponseDTO> convert(List<Matricula> matriculas) {
		return matriculas.stream().map(MatriculaResponseDTO::new).collect(Collectors.toList());
	}
}
