package br.com.sysagrega.repository.imp;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;

import br.com.sysagrega.model.imp.TipoRetificacao;
import br.com.sysagrega.repository.ITipoRetificacaoRepository;


public class TipoRetificacaoRepository implements ITipoRetificacaoRepository {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	@Inject
	public TipoRetificacaoRepository(EntityManager manager) {
		
		this.manager = manager;
		
	}

	@Override
	public List<TipoRetificacao> getAll() {
		UaiCriteria<TipoRetificacao> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, TipoRetificacao.class);
		return easyCriteria.getResultList();
	}

	@Override
	public TipoRetificacao getById(Long id) {
		return this.manager.find(TipoRetificacao.class, id);
	}
}
