package br.com.sysagrega.repository.imp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.sysagrega.model.ICustoOperacional;
import br.com.sysagrega.model.imp.CustoExecucao;
import br.com.sysagrega.model.imp.CustoOperacional;
import br.com.sysagrega.repository.ICustoOperacionalRepository;
import br.com.sysagrega.service.imp.NegocioException;

public class CustoDespesaOperacionalRepository implements ICustoOperacionalRepository {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	@Inject
	public CustoDespesaOperacionalRepository(EntityManager manager) {
		
		this.manager = manager;
		
	}
 
	@Override
	public Set<CustoOperacional> getAll() {
		
		TypedQuery<CustoOperacional> query = manager.createQuery("from CustoOperacional", CustoOperacional.class);
		Set<CustoOperacional> set1 = new HashSet<>(query.getResultList());
		return set1;
		
	}
	
	@Override
	public CustoOperacional getById(Long id) {
		return this.manager.find(CustoOperacional.class, id);
	}
	
	@Override
	public  List<CustoOperacional> getByPropostaId(Long idProposta) {
		try {
			
			return manager.createQuery("from CustoOperacional t where t.proposta.id = :idProposta ", CustoOperacional.class)
					.setParameter("idProposta", idProposta)
					.getResultList();
			
		} catch (NoResultException e) {
			e.getMessage();
			return null;
			
		}
		
	}
	
	@Override
	public ICustoOperacional saveOrMerge(ICustoOperacional custo) {
		return this.manager.merge(custo);
		 
	}
	@Override
	public void remover(ICustoOperacional custo) {
	
	try {
			
		ICustoOperacional cli = getById(custo.getId());
			this.manager.remove(cli);
			this.manager.flush();
			
		} catch (PersistenceException e) {
			
			throw new NegocioException("ICustoOperacional não pode ser excluído.");
			
		}
		
	}

}
