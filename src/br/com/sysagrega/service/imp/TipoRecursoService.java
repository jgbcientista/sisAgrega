package br.com.sysagrega.service.imp;

import java.util.List;

import javax.inject.Inject;

import br.com.sysagrega.model.ITipoRecurso;
import br.com.sysagrega.model.imp.TipoRecurso;
import br.com.sysagrega.repository.imp.TipoRecursoRepository;
import br.com.sysagrega.service.ITipoRecursoService;
import br.com.sysagrega.util.cdi.Transactional;

public class TipoRecursoService implements ITipoRecursoService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private TipoRecursoRepository tipoRecursoRepository;

	@Override
	@Transactional
	public void remover(ITipoRecurso recurso) {
		if (recurso != null) {
			// Removendo
			this.tipoRecursoRepository.remover(recurso);

		} else {

			throw new NegocioException("Favor selecionar uma recurso!");

		}
		
	}

	@Override
	@Transactional
	public ITipoRecurso atualizar(ITipoRecurso recurso) {
		try {
			 
			recurso = this.tipoRecursoRepository.salvar(recurso);
			
		} catch (Exception e) {

			throw new NegocioException("Erro de processamento com banco de dados!");

		}
		return recurso;
	}

	@Override
	public TipoRecurso getById(Long id) {
		return this.tipoRecursoRepository.getById(id);
	}

	@Override
	public List<TipoRecurso> getAll() {
		return this.tipoRecursoRepository.getAll();
	}


	@Override
	@Transactional
	public void salvar(ITipoRecurso recurso) {
		try {
			 
			recurso = this.tipoRecursoRepository.salvar(recurso);
			
		} catch (Exception e) {
			throw new NegocioException("Erro de processamento com banco de dados!");

		}
		
	}

	 
} 
