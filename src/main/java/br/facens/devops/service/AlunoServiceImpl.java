package br.facens.devops.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.facens.devops.entity.Aluno;
import br.facens.devops.exception.AlunoNotFoundException;
import br.facens.devops.repository.AlunoRepository;

@Service
public class AlunoServiceImpl implements AlunoService {
	
	private AlunoRepository repository;

	public AlunoServiceImpl(AlunoRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public List<Aluno> findAll() {
		return repository.findAll();
	}

	@Override
	public Aluno findById(int id) {
		Optional<Aluno> aluno = repository.findById(id);
		
		if(!aluno.isPresent()) {
			throw new AlunoNotFoundException("id do aluno não encontrado - " + id);
		}
		
		return aluno.get();
	}
	
	@Override
	public Aluno save(Aluno aluno) {
		return repository.save(aluno);
	}

	@Override
	public void update(Aluno aluno) {
		repository.save(aluno);
	}
}
