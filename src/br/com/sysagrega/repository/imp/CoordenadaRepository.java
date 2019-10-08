package br.com.sysagrega.repository.imp;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;

import br.com.sysagrega.model.ICoordenada;
import br.com.sysagrega.model.imp.Coordenada;
import br.com.sysagrega.model.imp.OrdemServico;
import br.com.sysagrega.model.imp.TrajetoRede;
import br.com.sysagrega.repository.ICoordenadaRepository;
import br.com.sysagrega.service.imp.NegocioException;


public class CoordenadaRepository implements ICoordenadaRepository {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	@Inject
	public CoordenadaRepository(EntityManager manager) {
		
		this.manager = manager;
		
	}

	@Override
	public synchronized List<Coordenada> getAll() {
		UaiCriteria<Coordenada> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Coordenada.class);
		return easyCriteria.getResultList();
	}
	

	@Override
	public synchronized Coordenada obterMaiorOrdenacao(Long projeto) {
		try {
			Coordenada item = null;
			String sql = "select max(c.ordem) from agrega.tb_coordenada c";
			
			Query nativeQuery = manager.createNativeQuery(sql);
			
			Object objeto = nativeQuery.getResultList();
			
			/*if(objeto != null && !objeto.toString().equals("[null]".toString())){
				item = new Coordenada();
				item.setOrdem(Long.parseLong(objeto.toString().replace("]", "").replace("[", "")));		
			}*/
			return  item;
		 
		} catch (NoResultException e) {
			
			return null;
			
		}
	}

	@Override
	public synchronized Coordenada getById(Long id) {
		return this.manager.find(Coordenada.class, id);
	}
	
	@Override
	public List<Coordenada> getByProjeto(Long idProjeto){
		
		UaiCriteria<Coordenada> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Coordenada.class);
				
		if(idProjeto != null) {
			easyCriteria.andEquals("projeto",idProjeto);
		}
		
		try {
			easyCriteria.orderByAsc("ponto");
			return easyCriteria.getResultList();
			
		} catch (NoResultException e) {
			return null;
		}
	}
	
 
	

	@Override
	public synchronized Coordenada salvar(Coordenada item) {
		return this.manager.merge(item);
		 
	}

	@Override
	public synchronized void remover(ICoordenada item) {
	try {
			
		ICoordenada rec = getById(item.getId());
			this.manager.remove(rec);
			this.manager.flush();
			
		} catch (PersistenceException e) {
			
			throw new NegocioException("Item não pode ser excluído.");
			
		}
		
	}
 


}
