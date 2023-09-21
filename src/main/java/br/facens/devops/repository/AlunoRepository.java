package br.facens.devops.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.facens.devops.entity.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

}
