package br.facens.devops.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.facens.devops.entity.Aluno;
import br.facens.devops.entity.Matricula;

public class AlunoResponseDTO {
	private int ra;
	private String nome;
	private List<Matricula> matriculas;
	
	public AlunoResponseDTO(Aluno aluno) {
		this.ra = aluno.getRA();
		this.nome = aluno.getNome();
		this.matriculas = aluno.getMatriculas();
	}

	public int getRa() {
		return ra;
	}

	public String getNome() {
		return nome;
	}

	public List<Matricula> getMatriculas() {
		return matriculas;
	}
	
	public static List<AlunoResponseDTO> convert(List<Aluno> alunos) {
		return alunos.stream().map(AlunoResponseDTO::new).collect(Collectors.toList());
	}
}