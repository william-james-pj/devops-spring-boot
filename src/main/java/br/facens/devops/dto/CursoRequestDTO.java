package br.facens.devops.dto;

import br.facens.devops.entity.Curso;

public class CursoRequestDTO {
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public static Curso convert(CursoRequestDTO dto) {
		return new Curso(dto);
	}
}