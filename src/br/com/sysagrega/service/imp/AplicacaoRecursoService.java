package br.com.sysagrega.service.imp;

import java.util.List;

import javax.inject.Inject;

import br.com.sysagrega.model.IAplicacaoRecurso;
import br.com.sysagrega.model.imp.AplicacaoRecurso;
import br.com.sysagrega.repository.imp.AplicacaoRecursoRepository;
import br.com.sysagrega.service.IAplicacaoRecursoService;
import br.com.sysagrega.util.cdi.Transactional;

public class AplicacaoRecursoService implements IAplicacaoRecursoService{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private AplicacaoRecursoRepository aplicacaoRecursoRepository;

	@Override
	@Transactional
	public void remover(IAplicacaoRecurso recurso) {
		if (recurso != null) {
			// Removendo
			this.aplicacaoRecursoRepository.remover(recurso);

		} else {

			throw new NegocioException("Favor selecionar uma recurso!");

		}
		
	}

	@Override
	@Transactional
	public IAplicacaoRecurso atualizar(IAplicacaoRecurso recurso) {
		try {
			 
			recurso = this.aplicacaoRecursoRepository.salvar(recurso);
			
		} catch (Exception e) {

			throw new NegocioException("Erro de processamento com banco de dados!");

		}
		return recurso;
	}

	@Override
	public AplicacaoRecurso getById(Long id) {
		return this.aplicacaoRecursoRepository.getById(id);
	}

	@Override
	public List<AplicacaoRecurso> getAll() {
		return this.aplicacaoRecursoRepository.getAll();
	}


	@Override
	@Transactional
	public void salvar(IAplicacaoRecurso recurso) {
		try {
			 
			recurso = this.aplicacaoRecursoRepository.salvar(recurso);
			
		} catch (Exception e) {
			throw new NegocioException("Erro de processamento com banco de dados!");

		}
		
	}

	 
} 
