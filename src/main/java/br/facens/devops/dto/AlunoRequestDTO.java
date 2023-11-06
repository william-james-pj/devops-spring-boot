package br.facens.devops.dto;

import br.facens.devops.entity.Aluno;

public class AlunoRequestDTO {
	private String nome;
	
	public AlunoRequestDTO() {}
	
	public AlunoRequestDTO(String nome) {
		super();
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public static Aluno convert(AlunoRequestDTO dto) {
		return new Aluno(dto);
	}
}
