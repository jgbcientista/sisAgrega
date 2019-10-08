package br.com.sysagrega.repository.imp;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.sysagrega.model.ITipoItem;
import br.com.sysagrega.model.imp.TipoItem;
import br.com.sysagrega.repository.ITipoItemRepository;
import br.com.sysagrega.service.imp.NegocioException;


public class TipoItemRepository implements ITipoItemRepository {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	@Inject
	public TipoItemRepository(EntityManager manager) {
		
		this.manager = manager;
		
	}

	@Override
	public List<TipoItem> getAll() {
		TypedQuery<TipoItem> query = manager.createQuery("from TipoItem ", TipoItem.class);
		return query.getResultList();
	}

	@Override
	public TipoItem getById(Long id) {
		return this.manager.find(TipoItem.class, id);
	}

	@Override
	public ITipoItem salvar(ITipoItem tipo) {
		return this.manager.merge(tipo);
		 
	}

	@Override
	public void remover(ITipoItem tipo) {
	
	try {
			
		ITipoItem rec = getById(tipo.getId());
			this.manager.remove(rec);
			this.manager.flush();
			
		} catch (PersistenceException e) {
			
			throw new NegocioException("Tipo Item não pode ser excluído.");
			
		}
		
	}
 


}
