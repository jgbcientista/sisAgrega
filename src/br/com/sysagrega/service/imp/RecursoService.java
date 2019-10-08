package br.com.sysagrega.service.imp;

import java.util.List;

import javax.inject.Inject;

import br.com.sysagrega.model.IRecurso;
import br.com.sysagrega.model.imp.Recurso;
import br.com.sysagrega.repository.imp.RecursoRepository;
import br.com.sysagrega.service.IRecursoService;
import br.com.sysagrega.util.cdi.Transactional;

public class RecursoService implements IRecursoService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private RecursoRepository recursoRepository;

	@Override
	@Transactional
	public void remover(IRecurso recurso) {
		if (recurso != null) {
			// Removendo
			this.recursoRepository.remover(recurso);

		} else {

			throw new NegocioException("Favor selecionar uma recurso!");

		}
		
	}

	@Override
	@Transactional
	public IRecurso atualizarRecurso(IRecurso recurso) {
		try {
			 
			recurso = this.recursoRepository.salvarRecurso(recurso);
			
		} catch (Exception e) {

			throw new NegocioException("Erro de processamento com banco de dados!");

		}
		return recurso;
	}

	@Override
	public Recurso getRecursoById(Long id) {
		return this.recursoRepository.getRecursoById(id);
	}

	@Override
	public List<Recurso> getAllRecursos() {
		return this.recursoRepository.getAllRecursos();
	}

	@Override
	public List<Recurso> getRecursoByFilter(Recurso filtro) {
		return this.recursoRepository.getRecursoByFilter(filtro);
	}

	@Override
	@Transactional
	public void salvarRecurso(IRecurso recurso) {
		try {
			 
			recurso = this.recursoRepository.salvarRecurso(recurso);
			
		} catch (Exception e) {
			throw new NegocioException("Erro de processamento com banco de dados!");

		}
		
	}

	 
} 
