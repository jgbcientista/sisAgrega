package br.com.sysagrega.repository.imp;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;

import br.com.sysagrega.model.IParcelaProjeto;
import br.com.sysagrega.model.imp.ParcelaProjeto;
import br.com.sysagrega.repository.IParcelaProjetoRepository;
import br.com.sysagrega.service.imp.NegocioException;


public class ParcelaProjetoRepository implements IParcelaProjetoRepository {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	@Inject
	public ParcelaProjetoRepository(EntityManager manager) {
		
		this.manager = manager;
		
	}

	@Override
	public List<ParcelaProjeto> getAll() {
		UaiCriteria<ParcelaProjeto> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, ParcelaProjeto.class);
		return easyCriteria.getResultList();
	}

	@Override
	public ParcelaProjeto getById(Long id) {
		return this.manager.find(ParcelaProjeto.class, id);
	}
	
	@Override
	public ParcelaProjeto getByProjeto(Long idProjeto){
		
		UaiCriteria<ParcelaProjeto> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, ParcelaProjeto.class);
				
		if(idProjeto != null) {
			easyCriteria.andEquals("projeto",idProjeto);
		}
		
		try {
			return easyCriteria.getSingleResult();
			
		} catch (NoResultException e) {
			return null;
		}
	}
	

	@Override
	public IParcelaProjeto salvar(IParcelaProjeto item) {
		return this.manager.merge(item);
		 
	}

	@Override
	public void remover(IParcelaProjeto item) {
	try {
			
		ParcelaProjeto rec = getById(item.getId());
			this.manager.remove(rec);
			this.manager.flush();
			
		} catch (PersistenceException e) {
			
			throw new NegocioException("Item não pode ser excluído.");
			
		}
		
	}
 


}
