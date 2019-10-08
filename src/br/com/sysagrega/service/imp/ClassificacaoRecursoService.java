package br.com.sysagrega.service.imp;

import java.util.List;

import javax.inject.Inject;

import br.com.sysagrega.model.IClassificacaoRecurso;
import br.com.sysagrega.model.imp.ClassificacaoRecurso;
import br.com.sysagrega.repository.imp.ClassificacaoRecursoRepository;
import br.com.sysagrega.service.IClassificacaoRecursoService;
import br.com.sysagrega.util.cdi.Transactional;

public class ClassificacaoRecursoService implements IClassificacaoRecursoService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ClassificacaoRecursoRepository tipoRecursoRepository;

	@Override
	@Transactional
	public void remover(IClassificacaoRecurso recurso) {
		if (recurso != null) {
			// Removendo
			this.tipoRecursoRepository.remover(recurso);

		} else {

			throw new NegocioException("Favor selecionar uma recurso!");

		}
		
	}

	@Override
	@Transactional
	public IClassificacaoRecurso atualizar(IClassificacaoRecurso recurso) {
		try {
			 
			recurso = this.tipoRecursoRepository.salvar(recurso);
			
		} catch (Exception e) {

			throw new NegocioException("Erro de processamento com banco de dados!");

		}
		return recurso;
	}

	@Override
	public ClassificacaoRecurso getById(Long id) {
		return this.tipoRecursoRepository.getById(id);
	}

	@Override
	public List<ClassificacaoRecurso> getAll() {
		return this.tipoRecursoRepository.getAll();
	}


	@Override
	@Transactional
	public void salvar(IClassificacaoRecurso recurso) {
		try {
			 
			recurso = this.tipoRecursoRepository.salvar(recurso);
			
		} catch (Exception e) {
			throw new NegocioException("Erro de processamento com banco de dados!");

		}
		
	}

	 
} 
