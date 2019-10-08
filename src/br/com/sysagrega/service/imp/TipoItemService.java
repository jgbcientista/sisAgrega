package br.com.sysagrega.service.imp;

import java.util.List;

import javax.inject.Inject;

import br.com.sysagrega.model.ITipoItem;
import br.com.sysagrega.model.imp.TipoItem;
import br.com.sysagrega.repository.imp.TipoItemRepository;
import br.com.sysagrega.service.ITipoItemService;
import br.com.sysagrega.util.cdi.Transactional;

public class TipoItemService implements ITipoItemService{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private TipoItemRepository  tipoItemRepository;

	@Override
	@Transactional
	public void remover(ITipoItem tipo)  {
		if (tipo != null) {
			
			this.tipoItemRepository.remover(tipo);

		} else {

			throw new NegocioException("Favor selecionar uma recurso!");

		}
		
	}

	@Override
	@Transactional
	public ITipoItem atualizar(ITipoItem tipo) {
		try {
			 
			tipo = this.tipoItemRepository.salvar(tipo);
			
		} catch (Exception e) {

			throw new NegocioException("Erro de processamento com banco de dados!");

		}
		return tipo;
	}

	@Override
	public ITipoItem getById(Long id){
		return this.tipoItemRepository.getById(id);
	}

	@Override
	public List<TipoItem> getAll(){
		return this.tipoItemRepository.getAll();
	}


	@Override
	@Transactional
	public void salvar(ITipoItem tipo) {
		try {
			tipo = this.tipoItemRepository.salvar(tipo);
			
		} catch (Exception e) {
			throw new NegocioException("Erro de processamento com banco de dados!");

		}
		
	}
	 
} 
