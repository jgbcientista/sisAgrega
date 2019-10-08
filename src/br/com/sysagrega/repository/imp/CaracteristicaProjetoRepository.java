package br.com.sysagrega.repository.imp;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;

import br.com.sysagrega.model.ICaracteristicaProjeto;
import br.com.sysagrega.model.imp.CaracteristicaProjeto;
import br.com.sysagrega.repository.ICaracteristicaProjetoRepository;
import br.com.sysagrega.service.imp.NegocioException;

public class CaracteristicaProjetoRepository implements ICaracteristicaProjetoRepository {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;

	@Inject 
	public CaracteristicaProjetoRepository(EntityManager manager) {
		
		this.manager = manager;
		
	}

	 
	@Override
	public CaracteristicaProjeto getById(Long id) {
		if(id== null){
			return null;
		}
		return this.manager.find(CaracteristicaProjeto.class, id);
		
	}
	
	
	
	@Override
	public ICaracteristicaProjeto salvar(ICaracteristicaProjeto item) {
		return this.manager.merge(item);
		 
	}

	
	@Override
	public void remover(ICaracteristicaProjeto item) {

		try {
			
			ICaracteristicaProjeto caract = getById(item.getId());
			this.manager.remove(caract);
			this.manager.flush();
			
		} catch (PersistenceException e) {
			
			throw new NegocioException("Caracteristica não pode ser excluído.");
			
		}
		
	}
	
	@Override
	public List<CaracteristicaProjeto> getAllCaracteristicaProjeto() {
		TypedQuery<CaracteristicaProjeto> query = manager.createQuery("from CaracteristicaProjeto", CaracteristicaProjeto.class);
		
		return query.getResultList();
		
	}

	@Override
	public CaracteristicaProjeto getByProjeto(Long idProjeto){
		
		UaiCriteria<CaracteristicaProjeto> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, CaracteristicaProjeto.class);
				
		if(idProjeto != null) {
			easyCriteria.andEquals("projeto",idProjeto);
		}
		
		try {
			return easyCriteria.getSingleResult();
			
		} catch (NoResultException e) {
			return null;
		}
	}
	

}
