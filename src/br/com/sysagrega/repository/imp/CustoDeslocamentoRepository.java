package br.com.sysagrega.repository.imp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.sysagrega.model.ICustoDeslocamento;
import br.com.sysagrega.model.imp.CustoDeslocamento;
import br.com.sysagrega.model.imp.CustoOperacional;
import br.com.sysagrega.repository.ICustoDeslocamentoRepository;
import br.com.sysagrega.service.imp.NegocioException;

public class CustoDeslocamentoRepository implements ICustoDeslocamentoRepository {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	@Inject
	public CustoDeslocamentoRepository(EntityManager manager) {
		
		this.manager = manager;
		
	}
 
	@Override
	public Set<CustoDeslocamento> getAll() {
		
		TypedQuery<CustoDeslocamento> query = manager.createQuery("from CustoDeslocamento", CustoDeslocamento.class);
		Set<CustoDeslocamento> set1 = new HashSet<>(query.getResultList());
		return set1;
		
	}
	
	@Override
	public CustoDeslocamento  getById(Long id) {
		
		return this.manager.find(CustoDeslocamento.class, id);
	}
	@Override
	public  List<CustoDeslocamento> getByPropostaId(Long idProposta) {
		try {
			
			return manager.createQuery("from CustoDeslocamento t where t.proposta.id = :idProposta ", CustoDeslocamento.class)
					.setParameter("idProposta", idProposta)
					.getResultList();
			
		} catch (NoResultException e) {
			
			return null;
			
		}
	}	
	
	@Override
	public ICustoDeslocamento saveOrMerge(ICustoDeslocamento custo) {
		return this.manager.merge(custo);
		 
	}
	@Override
	public void remover(ICustoDeslocamento custo) {
	
	try {
			
		ICustoDeslocamento cli = getById(custo.getId());
			this.manager.remove(cli);
			this.manager.flush();
			
		} catch (PersistenceException e) {
			
			throw new NegocioException("CustoDeslocamento não pode ser excluído.");
			
		}
		
	}

}
