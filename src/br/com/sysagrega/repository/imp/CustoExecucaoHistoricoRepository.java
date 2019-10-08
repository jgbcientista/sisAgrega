package br.com.sysagrega.repository.imp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.sysagrega.model.ICustoExecucaoHistorico;
import br.com.sysagrega.model.imp.CustoExecucaoHistorico;
import br.com.sysagrega.repository.ICustoExecucaoHistoricoRepository;
import br.com.sysagrega.service.imp.NegocioException;

public class CustoExecucaoHistoricoRepository implements ICustoExecucaoHistoricoRepository {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	@Inject
	public CustoExecucaoHistoricoRepository(EntityManager manager) {
		
		this.manager = manager;
		
	}
	/* (non-Javadoc)
	 * @see br.com.sysagrega.repository.imp.IBancoRepository#getAllBancos()
	 */
	@Override
	public Set<CustoExecucaoHistorico> getAllCustos() {
		
		TypedQuery<CustoExecucaoHistorico> query = manager.createQuery("from CustoExecucaoHistorico", CustoExecucaoHistorico.class);
		Set<CustoExecucaoHistorico> set1 = new HashSet<>(query.getResultList());
		return set1;
		
	}
	
	@Override
	public  List<CustoExecucaoHistorico> getByPropostaHistoricoId(Long idPropostaHistorico) {
		try {
			
			return manager.createQuery("from CustoExecucaoHistorico t where t.propostaHistorico.id = :idPropostaHistorico and t.ativo = 'true'", CustoExecucaoHistorico.class)
					.setParameter("idPropostaHistorico", idPropostaHistorico)
					
					.getResultList();
			
		} catch (NoResultException e) {
			
			return null;
			
		}
		
	}
 
	@Override
	public CustoExecucaoHistorico getCustoExecucaoHistoricoById(Long id) {
		
		return this.manager.find(CustoExecucaoHistorico.class, id);
	}
	@Override
	public CustoExecucaoHistorico saveOrMerge(CustoExecucaoHistorico iCustoExecucaoHistorico) {
		return this.manager.merge(iCustoExecucaoHistorico);
		 
	}
	@Override
	public CustoExecucaoHistorico remover(CustoExecucaoHistorico custoExecucaoHistorico) {
	
	try {
			
	ICustoExecucaoHistorico cli = getCustoExecucaoHistoricoById(custoExecucaoHistorico.getId());
			this.manager.remove(cli);
			this.manager.flush();
			
		} catch (PersistenceException e) {
			
			throw new NegocioException("Custo Execucão não pode ser excluído.");
			
		}
	return custoExecucaoHistorico;
		
	}

}
