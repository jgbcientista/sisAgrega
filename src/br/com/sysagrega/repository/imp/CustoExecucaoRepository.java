package br.com.sysagrega.repository.imp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.sysagrega.model.ICustoExecucao;
import br.com.sysagrega.model.imp.CustoExecucao;
import br.com.sysagrega.model.imp.CustoSeguranca;
import br.com.sysagrega.model.imp.TipoItemProposta;
import br.com.sysagrega.repository.ICustoExecucaoRepository;
import br.com.sysagrega.service.imp.NegocioException;

public class CustoExecucaoRepository implements ICustoExecucaoRepository {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	@Inject
	public CustoExecucaoRepository(EntityManager manager) {
		
		this.manager = manager;
		
	}
	/* (non-Javadoc)
	 * @see br.com.sysagrega.repository.imp.IBancoRepository#getAllBancos()
	 */
	@Override
	public Set<CustoExecucao> getAllCustos() {
		
		TypedQuery<CustoExecucao> query = manager.createQuery("from CustoExecucao", CustoExecucao.class);
		Set<CustoExecucao> set1 = new HashSet<>(query.getResultList());
		return set1;
		
	}
	
	@Override
	public  List<CustoExecucao> getByPropostaId(Long idProposta) {
		try {
			
			return manager.createQuery("from CustoExecucao t where t.proposta.id = :idProposta and t.ativo = 'true'", CustoExecucao.class)
					.setParameter("idProposta", idProposta)
					
					.getResultList();
			
		} catch (NoResultException e) {
			
			return null;
			
		}
		
	}
 
	@Override
	public CustoExecucao getCustoExecucaoById(Long id) {
		
		return this.manager.find(CustoExecucao.class, id);
	}
	@Override
	public CustoExecucao saveOrMerge(CustoExecucao iCustoExecucao) {
		return this.manager.merge(iCustoExecucao);
		 
	}
	@Override
	public CustoExecucao remover(CustoExecucao custoExecucao) {
	
	try {
			
	ICustoExecucao cli = getCustoExecucaoById(custoExecucao.getId());
			this.manager.remove(cli);
			this.manager.flush();
			
		} catch (PersistenceException e) {
			
			throw new NegocioException("Custo Execucão não pode ser excluído.");
			
		}
	return custoExecucao;
		
	}

}
