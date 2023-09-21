package br.facens.devops.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.facens.devops.entity.Matricula;
import br.facens.devops.exception.MatriculaNotFoundException;
import br.facens.devops.repository.MatriculaRepository;

@Service
public class MatriculaServiceImpl implements MatriculaService {
	
	private MatriculaRepository repository;

	public MatriculaServiceImpl(MatriculaRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public List<Matricula> findAll() {
		return repository.findAll();
	}

	@Override
	public Matricula findById(int id) {
		Optional<Matricula> result = repository.findById(id);
		
		if(!result.isPresent()) {
			throw new MatriculaNotFoundException("id da matricula não encontrado - " + id);
		}
		
		return result.get();
	}
}
