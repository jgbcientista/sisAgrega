package br.com.sysagrega.repository.imp;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.sysagrega.model.ICltMensal;
import br.com.sysagrega.model.imp.CltMensal;
import br.com.sysagrega.repository.ICltMensalRepository;
import br.com.sysagrega.service.imp.NegocioException;

public class CltMensalRepository implements ICltMensalRepository {

	private static final long serialVersionUID = 1L;
	private EntityManager manager;

	@Inject 
	public CltMensalRepository(EntityManager manager) {
		this.manager = manager;
	}
 
	@Override
	public CltMensal getCltMensalById(Long cod) {
		return this.manager.find(CltMensal.class, cod);
	}
	
	@Override
	public ICltMensal saveOrMerge(ICltMensal cltMensal) {
		return this.manager.merge(cltMensal);
	}
	
	@Override
	public void remover(CltMensal cltMensal) {
		try {
			CltMensal clt = getCltMensalById(cltMensal.getCod());
			this.manager.remove(clt);
			this.manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("O parametro não pode ser excluído.");
		}
	}
 
	@Override
	public List<CltMensal> getAllCltMensal() {
		TypedQuery<CltMensal> query = manager.createQuery("from CltMensal", CltMensal.class);
		return query.getResultList();
	}
}
