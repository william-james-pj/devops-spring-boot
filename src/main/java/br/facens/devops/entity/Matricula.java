package br.facens.devops.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="matricula")
public class Matricula {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="nota")
	private double nota;
	
	@Column(name="pode_fazer_sub")
	private boolean podeFazerSub;
	
	@Column(name="status")
	@Enumerated(EnumType.STRING)
	private MatriculaStatus status;
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, 
		                 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name = "curso_id")
	private Curso curso;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, 
            			  CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name = "aluno_ra")
	private Aluno aluno;
	
	public Matricula() {
		super();
		this.status = MatriculaStatus.CURSANDO;
		this.podeFazerSub = false;
	}

	public Matricula(double nota, boolean podeFazerSub, MatriculaStatus status) {
		super();
		this.id = 0;
		this.nota = nota;
		this.podeFazerSub = podeFazerSub;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

	public boolean isPodeFazerSub() {
		return podeFazerSub;
	}

	public void setPodeFazerSub(boolean podeFazerSub) {
		this.podeFazerSub = podeFazerSub;
	}

	public MatriculaStatus getStatus() {
		return status;
	}

	public void setStatus(MatriculaStatus status) {
		this.status = status;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	@Override
	public String toString() {
		return "Matricula [id=" + id + ", nota=" + nota + ", podeFazerSub=" + podeFazerSub + ", status=" + status
				+ ", curso=" + curso + "]";
	}
}
