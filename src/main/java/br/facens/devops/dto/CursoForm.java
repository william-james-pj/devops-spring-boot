package br.facens.devops.dto;

import br.facens.devops.entity.Curso;

public class CursoForm {
	private int id;
	private String nome;
	
	public CursoForm() {}

	public CursoForm(int id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public static Curso convert(CursoForm form) {
		return new Curso(form);
	}
}
