package br.com.sysagrega.repository.imp;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.sysagrega.model.ICustoPlanilhaFinanceira;
import br.com.sysagrega.model.imp.CustoPlanilhaFinanceira;
import br.com.sysagrega.repository.ICustoPlanilhaFinanceiraRepository;
import br.com.sysagrega.service.imp.NegocioException;


public class CustoPlanilhaFinanceiraRepository implements ICustoPlanilhaFinanceiraRepository {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	@Inject
	public CustoPlanilhaFinanceiraRepository(EntityManager manager) {
		this.manager = manager;
		
	}

	@Override
	public List<CustoPlanilhaFinanceira> getAll() {
		TypedQuery<CustoPlanilhaFinanceira> query = manager.createQuery("from CustoPlanilhaFinanceira", CustoPlanilhaFinanceira.class);
		return query.getResultList();
	}

	@Override
	public  CustoPlanilhaFinanceira getById(Long id) {
		return this.manager.find(CustoPlanilhaFinanceira.class, id);
	}

	@Override
	public CustoPlanilhaFinanceira salvar(CustoPlanilhaFinanceira item) {
		return this.manager.merge(item);
		 
	}
	
	@Override
	public  List<CustoPlanilhaFinanceira> getByPropostaId(Long idProposta) {
		try {
			
			return manager.createQuery("from CustoPlanilhaFinanceira t where t.proposta.id = :idProposta", CustoPlanilhaFinanceira.class)
						  .setParameter("idProposta", idProposta)
						  .getResultList();
			
		} catch (NoResultException e) {
			
			return null;
			
		}
		
	}

	@Override
	public void remover(CustoPlanilhaFinanceira item) {
	
	try {
			
		ICustoPlanilhaFinanceira rec = getById(item.getId());
			this.manager.remove(rec);
			this.manager.flush();
			
		} catch (PersistenceException e) {
			
			throw new NegocioException("CustoPlanilhaFinanceira Item não pode ser excluído.");
			
		}
		
	}
 


}
