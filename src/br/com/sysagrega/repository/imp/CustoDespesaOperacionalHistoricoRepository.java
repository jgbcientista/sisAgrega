package br.com.sysagrega.repository.imp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.sysagrega.model.ICustoOperacionalHistorico;
import br.com.sysagrega.model.imp.CustoOperacionalHistorico;
import br.com.sysagrega.repository.ICustoOperacionalHistoricoRepository;
import br.com.sysagrega.service.imp.NegocioException;

public class CustoDespesaOperacionalHistoricoRepository implements ICustoOperacionalHistoricoRepository {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	@Inject
	public CustoDespesaOperacionalHistoricoRepository(EntityManager manager) {
		
		this.manager = manager;
		
	}
 
	@Override
	public Set<CustoOperacionalHistorico> getAll() {
		
		TypedQuery<CustoOperacionalHistorico> query = manager.createQuery("from CustoOperacionalHistorico", CustoOperacionalHistorico.class);
		Set<CustoOperacionalHistorico> set1 = new HashSet<>(query.getResultList());
		return set1;
		
	}
	
	@Override
	public CustoOperacionalHistorico getById(Long id) {
		return this.manager.find(CustoOperacionalHistorico.class, id);
	}
	
	@Override
	public  List<CustoOperacionalHistorico> getByPropostaHistoricoId(Long idPropostaHistorico) {
		try {
			
			return manager.createQuery("from CustoOperacionalHistorico t where t.propostaHistorico.id = :idPropostaHistorico ", CustoOperacionalHistorico.class)
					.setParameter("idPropostaHistorico", idPropostaHistorico)
					.getResultList();
			
		} catch (NoResultException e) {
			e.getMessage();
			return null;
			
		}
		
	}
	
	@Override
	public ICustoOperacionalHistorico saveOrMerge(ICustoOperacionalHistorico custo) {
		return this.manager.merge(custo);
		 
	}
	@Override
	public void remover(ICustoOperacionalHistorico custoHistorico) {
	
	try {
			
		CustoOperacionalHistorico cli = getById(custoHistorico.getId());
			this.manager.remove(cli);
			this.manager.flush();
			
		} catch (PersistenceException e) {
			
			throw new NegocioException("ICustoOperacional não pode ser excluído.");
			
		}
		
	}

}
