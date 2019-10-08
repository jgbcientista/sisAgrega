package br.com.sysagrega.repository.imp;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;

import br.com.sysagrega.model.imp.Aditivo;
import br.com.sysagrega.repository.IAditivo;
import br.com.sysagrega.repository.IAditivoRepository;
import br.com.sysagrega.service.imp.NegocioException;


public class AditivoRepository implements IAditivoRepository {
	
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	@Inject
	public AditivoRepository(EntityManager manager) {
		
		this.manager = manager;
		
	}

	@Override
	public List<Aditivo> getAll() {
		UaiCriteria<Aditivo> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Aditivo.class);
		easyCriteria.andEquals("ativo", true);
		easyCriteria.orderByAsc("dataAditivo");
		return easyCriteria.getResultList();
	}

	@Override
	public Aditivo getById(Long id) {
		return this.manager.find(Aditivo.class, id);
	}
	
	
	public List<Aditivo> getByContrato(Aditivo filtro){
		
		UaiCriteria<Aditivo> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Aditivo.class);
		easyCriteria.andEquals("ativo", true);
		
		if(filtro != null) {
			easyCriteria.andEquals("contrato",filtro.getContrato());
		}
		
		try {
			return easyCriteria.getResultList();
			
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@Override
	public IAditivo salvar(IAditivo item) {
		return this.manager.merge(item);
		 
	}

	@Override
	public void remover(IAditivo item) {
	try {
			
		IAditivo rec = getById(item.getId());
			this.manager.remove(rec);
			this.manager.flush();
			
		} catch (PersistenceException e) {
			
			throw new NegocioException("Itemo não pode ser excluído.");
			
		}
		
	} 


}
