package br.facens.devops.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.facens.devops.entity.Curso;
import br.facens.devops.exception.CursoNotFoundException;
import br.facens.devops.repository.CursoRepository;

@Service
public class CursoServiceImpl implements CursoService {
	
	private CursoRepository repository;

	public CursoServiceImpl(CursoRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public List<Curso> findAll() {
		return repository.findAll();
	}

	@Override
	public Curso findById(int id) {
		Optional<Curso> result = repository.findById(id);
		
		if(!result.isPresent()) {
			throw new CursoNotFoundException("id do curso não encontrado - " + id);
		}
		
		return result.get();
	}

	@Override
	public Curso save(Curso curso) {
		return repository.save(curso);
	}

	@Override
	public void delete(int id) {
		repository.deleteById(id);
	}
}
