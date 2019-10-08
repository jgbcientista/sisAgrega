package br.com.sysagrega.repository.imp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.sysagrega.model.ICustoBdiComissao;
import br.com.sysagrega.model.imp.CustoBdiComissao;
import br.com.sysagrega.model.imp.CustoDeslocamento;
import br.com.sysagrega.repository.ICustoBDIComissaoRepository;
import br.com.sysagrega.service.imp.NegocioException;

public class CustoBDIComissaoRepository implements ICustoBDIComissaoRepository {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	@Inject
	public CustoBDIComissaoRepository(EntityManager manager) {
		
		this.manager = manager;
		
	}
 
	@Override
	public Set<CustoBdiComissao> getAll() {
		
		TypedQuery<CustoBdiComissao> query = manager.createQuery("from CustoBdiComissao", CustoBdiComissao.class);
		Set<CustoBdiComissao> set1 = new HashSet<>(query.getResultList());
		return set1;
		
	}
	
	@Override
	public CustoBdiComissao getById(Long id) {
		
		return this.manager.find(CustoBdiComissao.class, id);
	}
	@Override
	public ICustoBdiComissao saveOrMerge(ICustoBdiComissao custo) {
		return this.manager.merge(custo);
		 
	}
	
	@Override
	public  List<CustoBdiComissao> getByPropostaId(Long idProposta) {
		try {
			
			return manager.createQuery("from CustoBdiComissao t where t.proposta.id = :idProposta ", CustoBdiComissao.class)
					.setParameter("idProposta", idProposta)
					.getResultList();
			
		} catch (NoResultException e) {
			
			return null;
			
		}
	}
	
	@Override
	public void remover(ICustoBdiComissao custo) {
	
	try {
			
	ICustoBdiComissao cli = getById(custo.getId());
			this.manager.remove(cli);
			this.manager.flush();
			
		} catch (PersistenceException e) {
			
			throw new NegocioException("CustoExecucão não pode ser excluído.");
			
		}
		
	}

}
