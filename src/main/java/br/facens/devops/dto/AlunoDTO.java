package br.facens.devops.dto;

import br.facens.devops.entity.Aluno;

public class AlunoDTO {
	private int ra;
	private String nome;
	
	public AlunoDTO(Aluno aluno) {
		this.ra = aluno.getRA();
		this.nome = aluno.getNome();
	}
	
	public int getRa() {
		return ra;
	}

	public String getNome() {
		return nome;
	}
}
