package br.com.sysagrega.repository.imp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.sysagrega.model.ICustoAdministrativoHistorico;
import br.com.sysagrega.model.imp.CustoAdministrativoHistorico;
import br.com.sysagrega.repository.ICustoAdministrativoHistoricoRepository;
import br.com.sysagrega.service.imp.NegocioException;

public class CustoAdministrativoHistoricoRepository implements ICustoAdministrativoHistoricoRepository {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	@Inject
	public CustoAdministrativoHistoricoRepository(EntityManager manager) {
		
		this.manager = manager;
		
	}
 
	@Override
	public Set<CustoAdministrativoHistorico> getAll() {
		
		TypedQuery<CustoAdministrativoHistorico> query = manager.createQuery("from CustoAdministrativoHistorico", CustoAdministrativoHistorico.class);
		Set<CustoAdministrativoHistorico> set1 = new HashSet<>(query.getResultList());
		return set1;
		
	}
	
	@Override
	public CustoAdministrativoHistorico getById(Long id) {
		
		return this.manager.find(CustoAdministrativoHistorico.class, id);
	}
	
	@Override
	public  List<CustoAdministrativoHistorico> getByPropostaId(Long idPropostaHistorico) {
		try {
			
			return manager.createQuery("from CustoAdministrativoHistorico t where t.propostaHistorico.id = :idPropostaHistorico ", CustoAdministrativoHistorico.class)
					.setParameter("idPropostaHistorico", idPropostaHistorico)
					.getResultList();
			
		} catch (NoResultException e) {
			
			return null;
			
		}
	}
	@Override
	public ICustoAdministrativoHistorico saveOrMerge(ICustoAdministrativoHistorico custoHistorico) {
		return this.manager.merge(custoHistorico);
		 
	}
	@Override
	public void remover(ICustoAdministrativoHistorico custoHistorico) {
	
	try {
			
		ICustoAdministrativoHistorico cli = getById(custoHistorico.getId());
			this.manager.remove(cli);
			this.manager.flush();
			
		} catch (PersistenceException e) {
			
			throw new NegocioException("CustoDeslocamento não pode ser excluído.");
			
		}
		
	}

}
