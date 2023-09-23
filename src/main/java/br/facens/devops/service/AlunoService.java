package br.facens.devops.service;

import java.util.List;

import br.facens.devops.entity.Aluno;

public interface AlunoService {
	List<Aluno> findAll();
	Aluno findById(int id);
	Aluno save(Aluno aluno);
	void update(Aluno aluno);
}
