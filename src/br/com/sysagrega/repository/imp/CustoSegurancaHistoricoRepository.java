package br.com.sysagrega.repository.imp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.sysagrega.model.ICustoSegurancaHistorico;
import br.com.sysagrega.model.imp.CustoSegurancaHistorico;
import br.com.sysagrega.repository.ICustoSegurancaHistoricoRepository;
import br.com.sysagrega.service.imp.NegocioException;

public class CustoSegurancaHistoricoRepository implements ICustoSegurancaHistoricoRepository {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	@Inject
	public CustoSegurancaHistoricoRepository(EntityManager manager) {
		
		this.manager = manager;
		
	}
 
	@Override
	public Set<CustoSegurancaHistorico> getAll() {
		
		TypedQuery<CustoSegurancaHistorico> query = manager.createQuery("from CustoSegurancaHistorico", CustoSegurancaHistorico.class);
		 Set<CustoSegurancaHistorico> set1 = new HashSet<>(query.getResultList());
		return set1;
		
	}
	
	@Override
	public CustoSegurancaHistorico  getById(Long id) {
		return this.manager.find(CustoSegurancaHistorico.class, id);
	}
	@Override
	public ICustoSegurancaHistorico saveOrMerge(ICustoSegurancaHistorico custo) {
		return this.manager.merge(custo);
	}
	@Override
	public  List<CustoSegurancaHistorico> getByPropostaHistoricoId(Long idPropostaHistorico) {
		try {
			
			return manager.createQuery("from CustoSegurancaHistorico t where t.propostaHistorico.id = :idPropostaHistorico ", CustoSegurancaHistorico.class)
					.setParameter("idPropostaHistorico", idPropostaHistorico)
					.getResultList();
			
		} catch (NoResultException e) {
			
			return null;
			
		}
		
	}
	
	@Override
	public void remover(ICustoSegurancaHistorico custo) {
	
	try {
			
		ICustoSegurancaHistorico cli = getById(custo.getId());
			this.manager.remove(cli);
			this.manager.flush();
			
		} catch (PersistenceException e) {
			
			throw new NegocioException(" Custo Seguranca não pode ser excluído.");
			
		}
		
	}

}
