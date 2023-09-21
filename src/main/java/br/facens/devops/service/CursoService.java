package br.facens.devops.service;

import java.util.List;

import br.facens.devops.entity.Curso;

public interface CursoService {
	List<Curso> findAll();
	Curso findById(int id);
	Curso save(Curso curso);
	void delete(int id);
}
