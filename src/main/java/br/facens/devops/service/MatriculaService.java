package br.facens.devops.service;

import java.util.List;

import br.facens.devops.entity.Matricula;

public interface MatriculaService {
	List<Matricula> findAll();
	Matricula findById(int id);
}
