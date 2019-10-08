package br.com.sysagrega.repository.imp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.sysagrega.model.ICustoDeslocamentoHistorico;
import br.com.sysagrega.model.imp.CustoDeslocamentoHistorico;
import br.com.sysagrega.repository.ICustoDeslocamentoHistoricoRepository;
import br.com.sysagrega.service.imp.NegocioException;

public class CustoDeslocamentoHistoricoRepository implements ICustoDeslocamentoHistoricoRepository {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	@Inject
	public CustoDeslocamentoHistoricoRepository(EntityManager manager) {
		
		this.manager = manager;
		
	}
 
	@Override
	public Set<CustoDeslocamentoHistorico> getAll() {
		
		TypedQuery<CustoDeslocamentoHistorico> query = manager.createQuery("from CustoDeslocamentoHistorico", CustoDeslocamentoHistorico.class);
		Set<CustoDeslocamentoHistorico> set1 = new HashSet<>(query.getResultList());
		return set1;
		
	}
	
	@Override
	public CustoDeslocamentoHistorico  getById(Long id) {
		
		return this.manager.find(CustoDeslocamentoHistorico.class, id);
	}
	@Override
	public  List<CustoDeslocamentoHistorico> getByPropostaHistoricoId(Long idPropostaHistorico) {
		try {
			
			return manager.createQuery("from CustoDeslocamentoHistorico t where t.propostaHistorico.id = :idPropostaHistorico ", CustoDeslocamentoHistorico.class)
					.setParameter("idPropostaHistorico", idPropostaHistorico)
					.getResultList();
			
		} catch (NoResultException e) {
			
			return null;
			
		}
	}	
	
	@Override
	public ICustoDeslocamentoHistorico saveOrMerge(ICustoDeslocamentoHistorico custo) {
		return this.manager.merge(custo);
		 
	}
	@Override
	public void remover(ICustoDeslocamentoHistorico custo) {
	
	try {
			
		ICustoDeslocamentoHistorico cli = getById(custo.getId());
			this.manager.remove(cli);
			this.manager.flush();
			
		} catch (PersistenceException e) {
			
			throw new NegocioException("CustoDeslocamento não pode ser excluído.");
			
		}
		
	}

}
