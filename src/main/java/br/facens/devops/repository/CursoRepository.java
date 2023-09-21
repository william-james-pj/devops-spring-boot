package br.facens.devops.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.facens.devops.entity.Curso;

public interface CursoRepository extends JpaRepository<Curso, Integer> {
	
}
