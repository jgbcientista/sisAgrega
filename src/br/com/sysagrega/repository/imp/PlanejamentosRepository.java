package br.com.sysagrega.repository.imp;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;

import br.com.sysagrega.model.IPlanejamentos;
import br.com.sysagrega.model.IProjeto;
import br.com.sysagrega.model.imp.Planejamentos;
import br.com.sysagrega.model.imp.Projeto;
import br.com.sysagrega.repository.IPlanejamentosRepository;
import br.com.sysagrega.service.imp.NegocioException;


public class PlanejamentosRepository implements IPlanejamentosRepository {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	@Inject
	public PlanejamentosRepository(EntityManager manager) {
		
		this.manager = manager;
	}

	@Override
	public List<Planejamentos> getAll() {
		UaiCriteria<Planejamentos> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Planejamentos.class);
	
		return easyCriteria.getResultList();
	}
	
	
	@Override
	public Planejamentos getById(Long id) {
		return this.manager.find(Planejamentos.class, id);
	}
	
	
	@Override
	public IPlanejamentos salvar(IPlanejamentos item) {
		return this.manager.merge(item);
		 
	}

	@Override
	public void remover(IPlanejamentos item) {
	try {
			
		IPlanejamentos rec = getById(item.getId());
			this.manager.remove(rec);
			this.manager.flush();
			
		} catch (PersistenceException e) {
			
			throw new NegocioException("Item não pode ser excluído.");
			
		}
		
	}
	
	@Override
	public Integer obterMaiorID() {
		try {
			Integer item = null;
			Object objeto = null;
			String sql = "select max(a.nrplan) from agrega.tb_planejamentos a";
			Query nativeQuery = manager.createNativeQuery(sql);
			
			objeto = nativeQuery.getResultList();
		
			if(objeto != null && !objeto.toString().equals("[null]".toString())){
				item = Integer.parseInt(objeto.toString().replace("]", "").replace("[", ""));	
			}
			return  item;
		 
		} catch (NoResultException e) {
			throw new NegocioException("Item não pode ser excluído.");
		}
	}
	
public List<Planejamentos> getByFiltros(Planejamentos filtro){
		UaiCriteria<Planejamentos> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Planejamentos.class);
		
		if(filtro != null && filtro.getId() != null) {
			easyCriteria.andEquals("id",filtro.getId());
		}
		
		try {
			return easyCriteria.getResultList();
			
		} catch (NoResultException e) {
			return null;
		}
	}
	
	

}
