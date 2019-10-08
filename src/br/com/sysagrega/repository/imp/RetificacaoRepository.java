package br.com.sysagrega.repository.imp;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;

import br.com.sysagrega.model.IRetificacao;
import br.com.sysagrega.model.imp.Retificacao;
import br.com.sysagrega.repository.IRetificacaoRepository;
import br.com.sysagrega.service.imp.NegocioException;


public class RetificacaoRepository implements IRetificacaoRepository {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	@Inject
	public RetificacaoRepository(EntityManager manager) {
		
		this.manager = manager;
		
	}

	@Override
	public List<Retificacao> getAll() {
		UaiCriteria<Retificacao> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Retificacao.class);
		return easyCriteria.getResultList();
	}

	@Override
	public Retificacao getById(Long id) {
		return this.manager.find(Retificacao.class, id);
	}
	
	@Override
	public List<Retificacao> getByProjeto(Long idProjeto){
		
		UaiCriteria<Retificacao> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Retificacao.class);
				
		if(idProjeto != null) {
			easyCriteria.andEquals("projeto",idProjeto);
		}
		
		try {
			return easyCriteria.getResultList();
			
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@Override
	public IRetificacao salvar(IRetificacao item) {
		return this.manager.merge(item);
		 
	}

	@Override
	public void remover(IRetificacao item) {
	try {
			
		IRetificacao rec = getById(item.getId());
			this.manager.remove(rec);
			this.manager.flush();
			
		} catch (PersistenceException e) {
			
			throw new NegocioException("Item não pode ser excluído.");
			
		}
		
	}
 


}
