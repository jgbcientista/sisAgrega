package br.com.sysagrega.repository.imp;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.sysagrega.model.IServico;
import br.com.sysagrega.model.imp.Servico;
import br.com.sysagrega.repository.IServicoRepository;
import br.com.sysagrega.service.imp.NegocioException;
import br.com.sysagrega.util.cdi.Transactional;

import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;

public class ServicoRepository implements IServicoRepository {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Transactional
	private EntityManager manager;
	
	@Inject
	public ServicoRepository(EntityManager manager) {
		
		this.manager = manager;
		
	}
	@Override
	public List<Servico> getAllServicos() {
		
		TypedQuery<Servico> query = manager.createQuery("from Servico s where s.ativo='true' ", Servico.class);
		return query.getResultList();
		
	}
	
	/* (non-Javadoc)
	 * @see br.com.sysagrega.repository.imp.IBancoRepository#getBancoById(java.lang.Long)
	 */
	@Override
	public Servico getServicoById(Long id) {
		
		return this.manager.find(Servico.class, id);
	}
	@Override
	public IServico saveOrMerge(IServico iServico) {
		return this.manager.merge(iServico);
		 
	}
	@Override
	public void remover(IServico servico) {
	
	try {
			
	IServico cli = getServicoById(servico.getId());
			this.manager.remove(cli);
			this.manager.flush();
			
		} catch (PersistenceException e) {
			
			throw new NegocioException("Servico não pode ser excluído: "+e.getMessage());
			
		}
		
	}
	@Override
	public List<Servico> getServicoByFilter(Long tipo, Long area, String descricao) {

		UaiCriteria<Servico> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Servico.class);
		
		
		if(tipo  != null ) {
			easyCriteria.andEquals("tipoServico", tipo);
		}
		
		if(area  != null ) {
			easyCriteria.andEquals("area", area);
		}
		
		easyCriteria.andEquals("ativo", true);
		
		try {
			
			return easyCriteria.getResultList();
			
		} catch (NoResultException e) {
			
			return null;
			
		}
	}

}
