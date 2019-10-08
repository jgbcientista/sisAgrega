package br.com.sysagrega.repository.imp;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;

import br.com.sysagrega.model.ITrajetoRede;
import br.com.sysagrega.model.imp.TrajetoRede;
import br.com.sysagrega.repository.ITrajetoRedeRepository;
import br.com.sysagrega.service.imp.NegocioException;


public class TrajetoRedeRepository implements ITrajetoRedeRepository {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	@Inject
	public TrajetoRedeRepository(EntityManager manager) {
		
		this.manager = manager;
		
	}

	@Override
	public synchronized List<TrajetoRede> getAll() {
		UaiCriteria<TrajetoRede> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, TrajetoRede.class);
		return easyCriteria.getResultList();
	}

	@Override
	public synchronized TrajetoRede getById(Long id) {
		return this.manager.find(TrajetoRede.class, id);
	}
	
	@Override
	public synchronized List<TrajetoRede> getByProjeto(Long idProjeto){
		
		UaiCriteria<TrajetoRede> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, TrajetoRede.class);
				
		if(idProjeto != null) {
			easyCriteria.andEquals("projeto",idProjeto);
		}
		
		try {
			easyCriteria.orderByAsc("id");
			return easyCriteria.getResultList();
			
		} catch (NoResultException e) {
			return null;
		}
	}
	

	@Override
	public synchronized TrajetoRede salvar(TrajetoRede item) {
		return this.manager.merge(item);
		 
	}

	@Override
	public synchronized void remover(ITrajetoRede item) {
	try {
			
		ITrajetoRede rec = getById(item.getId());
			this.manager.remove(rec);
			this.manager.flush();
			
		} catch (PersistenceException e) {
			
			throw new NegocioException("Item não pode ser excluído.");
			
		}
		
	}
	@Override
	public synchronized TrajetoRede obterMaiorTrajeto(Long idProjeto) {
		try {
			TrajetoRede item = null;
			String sql = "select max(c.ordem) from agrega.tb_trajeto_rede c";
			
			Query nativeQuery = manager.createNativeQuery(sql);
			
			Object objeto = nativeQuery.getResultList();
			
			if(objeto != null && !objeto.toString().equals("[null]".toString())){
				item = new TrajetoRede();
				//item.setOrdem(Long.parseLong(objeto.toString().replace("]", "").replace("[", "")));	
			}
			return  item;
		 
		} catch (NoResultException e) {
			
			return null;
			
		}
	}

	 


}
