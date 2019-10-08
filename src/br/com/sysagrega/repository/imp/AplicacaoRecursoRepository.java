package br.com.sysagrega.repository.imp;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;

import br.com.sysagrega.model.IAplicacaoRecurso;
import br.com.sysagrega.model.imp.AplicacaoRecurso;
import br.com.sysagrega.repository.IAplicacaoRecursoRepository;
import br.com.sysagrega.service.imp.NegocioException;

public class AplicacaoRecursoRepository implements IAplicacaoRecursoRepository {
	
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	@Inject
	public AplicacaoRecursoRepository(EntityManager manager) {
		
		this.manager = manager;
		
	}

	@Override
	public synchronized  List<AplicacaoRecurso> getAll() {
		UaiCriteria<AplicacaoRecurso> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, AplicacaoRecurso.class);
		
		return easyCriteria.getResultList();
	}

	@Override
	public synchronized  AplicacaoRecurso getById(Long id) {
		return this.manager.find(AplicacaoRecurso.class, id);
	}

	@Override
	public synchronized  IAplicacaoRecurso salvar(IAplicacaoRecurso recurso) {
		return this.manager.merge(recurso);
		 
	}

	@Override
	public synchronized  void remover(IAplicacaoRecurso recurso) {
	
	try {
			
		IAplicacaoRecurso rec = getById(recurso.getId());
			this.manager.remove(rec);
			this.manager.flush();
			
		} catch (PersistenceException e) {
			
			throw new NegocioException("Item não pode ser excluído.");
			
		}
		
	}

}
