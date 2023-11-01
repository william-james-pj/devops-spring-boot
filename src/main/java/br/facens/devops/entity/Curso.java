package br.facens.devops.entity;

import br.facens.devops.dto.CursoForm;
import br.facens.devops.dto.CursoRequestDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="curso")
public class Curso {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="nome")
	private String nome;
	
	public Curso() {
		super();
	}

	public Curso(String nome) {
		super();
		this.nome = nome;
	}
	
	public Curso(CursoRequestDTO dto) {
		super();
		this.id = 0;
		this.nome = dto.getNome();
	}
	
	public Curso(CursoForm form) {
		super();
		this.id = form.getId();
		this.nome = form.getNome();
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
	
}
