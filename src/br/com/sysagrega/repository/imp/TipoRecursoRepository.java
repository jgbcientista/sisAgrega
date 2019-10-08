package br.com.sysagrega.repository.imp;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;

import br.com.sysagrega.model.ITipoRecurso;
import br.com.sysagrega.model.imp.TipoRecurso;
import br.com.sysagrega.repository.ITipoRecursoRepository;
import br.com.sysagrega.service.imp.NegocioException;

public class TipoRecursoRepository implements ITipoRecursoRepository {
	
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	@Inject
	public TipoRecursoRepository(EntityManager manager) {
		
		this.manager = manager;
		
	}

	@Override
	public List<TipoRecurso> getAll() {
		UaiCriteria<TipoRecurso> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, TipoRecurso.class);
		
		return easyCriteria.getResultList();
	}

	@Override
	public TipoRecurso getById(Long id) {
		return this.manager.find(TipoRecurso.class, id);
	}

	@Override
	public ITipoRecurso salvar(ITipoRecurso recurso) {
		return this.manager.merge(recurso);
		 
	}

	@Override
	public void remover(ITipoRecurso recurso) {
	
	try {
			
		ITipoRecurso rec = getById(recurso.getId());
			this.manager.remove(rec);
			this.manager.flush();
			
		} catch (PersistenceException e) {
			
			throw new NegocioException("Item não pode ser excluído.");
			
		}
		
	}

}
