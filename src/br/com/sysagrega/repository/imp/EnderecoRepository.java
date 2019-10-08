package br.com.sysagrega.repository.imp;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import br.com.sysagrega.model.IEndereco;
import br.com.sysagrega.model.imp.Endereco;
import br.com.sysagrega.repository.IEnderecoRepository;
import br.com.sysagrega.service.imp.NegocioException;

public class EnderecoRepository implements IEnderecoRepository {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	@Inject
	public EnderecoRepository(EntityManager manager) {
		
		this.manager = manager;
		
	}
	
	@Override
	public Endereco getById(Long id) {
		
		return this.manager.find(Endereco.class, id);
	}

	@Override
	public IEndereco salvar(IEndereco item) {
		return this.manager.merge(item);
		 
	}

	@Override
	public void excluir(IEndereco item) {
		try {
			
			IEndereco cli = getById(item.getId());
				this.manager.remove(cli);
				this.manager.flush();
				
			} catch (PersistenceException e) {
				
				throw new NegocioException("item não pode ser excluído.");
				
			}
		
	}

}
