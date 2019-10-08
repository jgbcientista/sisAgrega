package br.com.sysagrega.repository.imp;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;

import br.com.sysagrega.model.IClassificacaoRecurso;
import br.com.sysagrega.model.imp.ClassificacaoRecurso;
import br.com.sysagrega.repository.IClassificacaoRecursoRepository;
import br.com.sysagrega.service.imp.NegocioException;

public class ClassificacaoRecursoRepository implements IClassificacaoRecursoRepository {
	
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	@Inject
	public ClassificacaoRecursoRepository(EntityManager manager) {
		
		this.manager = manager;
		
	}

	@Override
	public List<ClassificacaoRecurso> getAll() {
		UaiCriteria<ClassificacaoRecurso> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, ClassificacaoRecurso.class);
		
		return easyCriteria.getResultList();
	}

	@Override
	public ClassificacaoRecurso getById(Long id) {
		return this.manager.find(ClassificacaoRecurso.class, id);
	}

	@Override
	public IClassificacaoRecurso salvar(IClassificacaoRecurso recurso) {
		return this.manager.merge(recurso);
		 
	}

	@Override
	public void remover(IClassificacaoRecurso recurso) {
	
	try {
			
		IClassificacaoRecurso rec = getById(recurso.getId());
			this.manager.remove(rec);
			this.manager.flush();
			
		} catch (PersistenceException e) {
			
			throw new NegocioException("Item não pode ser excluído.");
			
		}
		
	}

}
