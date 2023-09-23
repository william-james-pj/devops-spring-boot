package br.facens.devops.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.facens.devops.dto.AlunoRequestDTO;
import br.facens.devops.exception.AlunoCourseLimitExceededException;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="aluno")
public class Aluno {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ra")
	private int ra;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="qtd_cursos_disponivel")
	private int qtdCursosDisponivel;
	
	@OneToMany(mappedBy = "aluno", 
			   fetch = FetchType.LAZY,
			   cascade = {CascadeType.PERSIST, CascadeType.MERGE, 
						  CascadeType.DETACH, CascadeType.REFRESH})
	@JsonIgnoreProperties("aluno")
	private List<Matricula> matriculas;
	
	public Aluno() {
		super();
		this.qtdCursosDisponivel = 1;
	}
	
	public Aluno(AlunoRequestDTO alunoDTO) {
		super();
		this.nome = alunoDTO.nome();
		this.qtdCursosDisponivel = 1;
	}

	public int getRA() {
		return ra;
	}

	public void setRa(int ra) {
		this.ra = ra;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQtdCursosDisponivel() {
		return qtdCursosDisponivel;
	}

	public void setQtdCursosDisponivel(int qtdCursosDisponivel) {
		this.qtdCursosDisponivel = qtdCursosDisponivel;
	}

	public List<Matricula> getMatriculas() {
		return matriculas;
	}
	
	@Override
	public String toString() {
		return "Aluno [ra=" + ra + ", nome=" + nome + ", qtdCursosDisponivel=" + qtdCursosDisponivel + "]";
	}

	public void inscreverCurso(Curso curso) throws AlunoCourseLimitExceededException {
		if(this.qtdCursosDisponivel < 1) {
			throw new AlunoCourseLimitExceededException("Limite de cursos excedido. O aluno não pode se inscrever em mais cursos.");
		}
		
		if(this.matriculas == null) {
			this.matriculas = new ArrayList<Matricula>();
		}
		
		Matricula matricula = new Matricula();
		this.matriculas.add(matricula);
		matricula.setAluno(this);
		matricula.setCurso(curso);
		this.qtdCursosDisponivel -= 1;
	}
	
	public void finalizarCurso(double nota, Curso curso) {	
		for (Matricula matricula : matriculas) {
			if(matricula.getCurso().equals(curso)) {
				matricula.setNota(nota);
				if(nota >= 7) {
					matricula.setStatus(MatriculaStatus.FINALIZADO);
					this.qtdCursosDisponivel += 3;
				} else {
					matricula.setStatus(MatriculaStatus.REPROVADO);
					matricula.setPodeFazerSub(true);
					this.qtdCursosDisponivel += 1;
				}
			}
		}
	}
}
