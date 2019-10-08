package br.com.sysagrega.repository.imp;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;

import br.com.sysagrega.model.IDistancia;
import br.com.sysagrega.model.imp.Distancia;
import br.com.sysagrega.repository.IDistanciaRepository;
import br.com.sysagrega.service.imp.NegocioException;


public class DistanciaRepository implements IDistanciaRepository {
	
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	@Inject
	public DistanciaRepository(EntityManager manager) {
		
		this.manager = manager;
		
	}

	@Override
	public List<Distancia> getAll() {
		UaiCriteria<Distancia> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Distancia.class);
		easyCriteria.andEquals("ativo", true);
		return easyCriteria.getResultList();
	}
	
	@Override
	public List<Distancia> getByPlanilhao(Long id) {
		UaiCriteria<Distancia> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Distancia.class);
		easyCriteria.andEquals("ativo", true);
		
		if(id != null) {
			easyCriteria.andEquals("projeto",id);
		}
		return easyCriteria.getResultList();
	}

	@Override
	public Distancia getById(Long id) {
		return this.manager.find(Distancia.class, id);
	}

	@Override
	public IDistancia salvar(IDistancia item) {
		return this.manager.merge(item);
		 
	}

	@Override
	public void remover(IDistancia item) {
	try {
			
		IDistancia rec = getById(item.getId());
			this.manager.remove(rec);
			this.manager.flush();
			
		} catch (PersistenceException e) {
			
			throw new NegocioException("Itemo não pode ser excluído.");
			
		}
		
	}
 


}
