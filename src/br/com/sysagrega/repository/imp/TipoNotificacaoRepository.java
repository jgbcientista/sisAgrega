package br.com.sysagrega.repository.imp;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;

import br.com.sysagrega.model.imp.TipoNotificacao;
import br.com.sysagrega.repository.ITipoNotificacaoRepository;


public class TipoNotificacaoRepository implements ITipoNotificacaoRepository {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	@Inject
	public TipoNotificacaoRepository(EntityManager manager) {
		
		this.manager = manager;
		
	}

	@Override
	public List<TipoNotificacao> getAll() {
		UaiCriteria<TipoNotificacao> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, TipoNotificacao.class);
		return easyCriteria.getResultList();
	}

	@Override
	public TipoNotificacao getById(Long id) {
		return this.manager.find(TipoNotificacao.class, id);
	}
}
