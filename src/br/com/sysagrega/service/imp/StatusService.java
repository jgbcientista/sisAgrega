package br.com.sysagrega.service.imp;

import java.util.List;

import javax.inject.Inject;

import br.com.sysagrega.model.IStatus;
import br.com.sysagrega.model.imp.Status;
import br.com.sysagrega.repository.imp.StatusRepository;
import br.com.sysagrega.service.IStatusService;
import br.com.sysagrega.util.cdi.Transactional;

public class StatusService implements IStatusService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private StatusRepository statusRepository;

	@Override
	@Transactional
	public void remover(IStatus item) {
		if (item != null) {
			// Removendo
			this.statusRepository.remover(item);

		} else {

			throw new NegocioException("Favor selecionar uma recurso!");

		}
		
	}

	@Override
	public Status getById(Long id) {
		return this.statusRepository.getById(id);
	}
	
	@Override
	public List<Status> getAll() {
		return this.statusRepository.getAll();
	}
	
	@Override
	public synchronized List<Status> obterByTipo(Long tipo){
		return this.statusRepository.obterByTipo(tipo);
	}
	
	@Override
	@Transactional
	public IStatus salvar(IStatus item) {
		try {
			 
			item = this.statusRepository.salvar(item);
			
		} catch (Exception e) {
			throw new NegocioException("Erro de processamento com banco de dados: " +  e.getMessage());

		}
		return item; 
	}

	 
} 
