package br.facens.devops.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.facens.devops.entity.Matricula;

public interface MatriculaRepository extends JpaRepository<Matricula, Integer> {

}
