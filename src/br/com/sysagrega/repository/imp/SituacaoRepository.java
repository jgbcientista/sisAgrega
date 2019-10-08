package br.com.sysagrega.repository.imp;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;

import br.com.sysagrega.model.ISituacao;
import br.com.sysagrega.model.imp.Situacao;
import br.com.sysagrega.repository.ISituacaoRepository;


public class SituacaoRepository implements ISituacaoRepository {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	@Inject
	public SituacaoRepository(EntityManager manager) {
		
		this.manager = manager;
		
	}

	@Override
	public List<Situacao> getAll() {
		UaiCriteria<Situacao> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Situacao.class);
	
		return easyCriteria.getResultList();
	}

	@Override
	public Situacao getById(Long id) {
		return this.manager.find(Situacao.class, id);
	}

}
