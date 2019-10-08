package br.com.sysagrega.repository.imp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.sysagrega.model.ICustoSeguranca;
import br.com.sysagrega.model.imp.CustoSeguranca;
import br.com.sysagrega.repository.ICustoSegurancaRepository;
import br.com.sysagrega.service.imp.NegocioException;

public class CustoSegurancaRepository implements ICustoSegurancaRepository {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	@Inject
	public CustoSegurancaRepository(EntityManager manager) {
		
		this.manager = manager;
		
	}
 
	@Override
	public Set<CustoSeguranca> getAll() {
		
		TypedQuery<CustoSeguranca> query = manager.createQuery("from CustoSeguranca", CustoSeguranca.class);
		 Set<CustoSeguranca> set1 = new HashSet<>(query.getResultList());
		return set1;
		
	}
	
	@Override
	public CustoSeguranca  getById(Long id) {
		return this.manager.find(CustoSeguranca.class, id);
	}
	@Override
	public ICustoSeguranca saveOrMerge(ICustoSeguranca custo) {
		return this.manager.merge(custo);
	}
	@Override
	public  List<CustoSeguranca> getByPropostaId(Long idProposta) {
		try {
			
			return manager.createQuery("from CustoSeguranca t where t.proposta.id = :idProposta ", CustoSeguranca.class)
					.setParameter("idProposta", idProposta)
					.getResultList();
			
		} catch (NoResultException e) {
			
			return null;
			
		}
		
	}
	
	@Override
	public void remover(ICustoSeguranca custo) {
	
	try {
			
		ICustoSeguranca cli = getById(custo.getId());
			this.manager.remove(cli);
			this.manager.flush();
			
		} catch (PersistenceException e) {
			
			throw new NegocioException(" Custo Seguranca não pode ser excluído.");
			
		}
		
	}

}
