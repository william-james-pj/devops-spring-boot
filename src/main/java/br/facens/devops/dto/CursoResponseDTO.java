package br.facens.devops.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.facens.devops.entity.Curso;

public class CursoResponseDTO {
	private int id;
	private String nome;
	
	public CursoResponseDTO(Curso curso) {
		super();
		this.id = curso.getId();
		this.nome = curso.getNome();
	}

	public int getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public static List<CursoResponseDTO> convert(List<Curso> cursos) {
		return cursos.stream().map(CursoResponseDTO::new).collect(Collectors.toList());
	}
}
