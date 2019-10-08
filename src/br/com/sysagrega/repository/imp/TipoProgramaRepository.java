package br.com.sysagrega.repository.imp;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;

import br.com.sysagrega.model.ITipoPrograma;
import br.com.sysagrega.model.imp.TipoPrograma;
import br.com.sysagrega.repository.ITipoProgramaRepository;
import br.com.sysagrega.service.imp.NegocioException;


public class TipoProgramaRepository implements ITipoProgramaRepository {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	@Inject
	public TipoProgramaRepository(EntityManager manager) {
		this.manager = manager;
		
	}

	@Override
	public List<TipoPrograma> getAll() {
		UaiCriteria<TipoPrograma> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, TipoPrograma.class);
		return easyCriteria.getResultList();
	}

	@Override
	public TipoPrograma getById(Long id) {
		return this.manager.find(TipoPrograma.class, id);
	}
	
	@Override
	public ITipoPrograma salvar(ITipoPrograma item) {
		return this.manager.merge(item);
	}
	

	@Override
	public void remover(ITipoPrograma item) {
	try {
			
		ITipoPrograma rec = getById(item.getId());
			this.manager.remove(rec);
			this.manager.flush();
			
		} catch (PersistenceException e) {
			
			throw new NegocioException("Item não pode ser excluído.");
			
		}
		
	}

}
