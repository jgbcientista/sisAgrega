package br.com.sysagrega.repository.imp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.sysagrega.model.ICustoAdministrativo;
import br.com.sysagrega.model.imp.CustoAdministrativo;
import br.com.sysagrega.model.imp.CustoBdiComissao;
import br.com.sysagrega.repository.ICustoAdministrativoRepository;
import br.com.sysagrega.service.imp.NegocioException;

public class CustoAdministrativoRepository implements ICustoAdministrativoRepository {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	@Inject
	public CustoAdministrativoRepository(EntityManager manager) {
		
		this.manager = manager;
		
	}
 
	@Override
	public Set<CustoAdministrativo> getAll() {
		
		TypedQuery<CustoAdministrativo> query = manager.createQuery("from CustoAdministrativo", CustoAdministrativo.class);
		Set<CustoAdministrativo> set1 = new HashSet<>(query.getResultList());
		return set1;
		
	}
	
	@Override
	public CustoAdministrativo  getById(Long id) {
		
		return this.manager.find(CustoAdministrativo.class, id);
	}
	
	@Override
	public  List<CustoAdministrativo> getByPropostaId(Long idProposta) {
		try {
			
			return manager.createQuery("from CustoAdministrativo t where t.proposta.id = :idProposta ", CustoAdministrativo.class)
					.setParameter("idProposta", idProposta)
					.getResultList();
			
		} catch (NoResultException e) {
			
			return null;
			
		}
	}
	@Override
	public ICustoAdministrativo saveOrMerge(ICustoAdministrativo custo) {
		return this.manager.merge(custo);
		 
	}
	@Override
	public void remover(ICustoAdministrativo custo) {
	
	try {
			
		ICustoAdministrativo cli = getById(custo.getId());
			this.manager.remove(cli);
			this.manager.flush();
			
		} catch (PersistenceException e) {
			
			throw new NegocioException("CustoDeslocamento não pode ser excluído.");
			
		}
		
	}

}
