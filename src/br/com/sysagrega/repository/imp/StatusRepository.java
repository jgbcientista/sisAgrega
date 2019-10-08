package br.com.sysagrega.repository.imp;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;

import br.com.sysagrega.model.IStatus;
import br.com.sysagrega.model.imp.Status;
import br.com.sysagrega.model.imp.TrajetoRede;
import br.com.sysagrega.repository.IStatusRepository;
import br.com.sysagrega.service.imp.NegocioException;


public class StatusRepository implements IStatusRepository {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	@Inject
	public StatusRepository(EntityManager manager) {
		
		this.manager = manager;
		
	}

	@Override
	public List<Status> getAll() {
		UaiCriteria<Status> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Status.class);
	
		return easyCriteria.getResultList();
	}

	@Override
	public Status getById(Long id) {
		return this.manager.find(Status.class, id);
	}
	
	
	@Override
	public IStatus salvar(IStatus item) {
		return this.manager.merge(item);
	}
	
	@Override
	public synchronized List<Status> obterByTipo(Long tipo){
		
		UaiCriteria<Status> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Status.class);
				
		if(tipo != null) {
			easyCriteria.andEquals("tipo",tipo);
		}
		
		try {
			easyCriteria.orderByAsc("nome");
			return easyCriteria.getResultList();
			
		} catch (NoResultException e) {
			return null;
		}
	}
	

	@Override
	public void remover(IStatus item) {
	try {
			
		IStatus rec = getById(item.getId());
			this.manager.remove(rec);
			this.manager.flush();
			
		} catch (PersistenceException e) {
			
			throw new NegocioException("Item não pode ser excluído.");
			
		}
		
	}

}
