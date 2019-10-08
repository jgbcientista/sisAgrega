package br.com.sysagrega.repository.imp;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.sysagrega.model.imp.Banco;
import br.com.sysagrega.repository.IBancoRepository;

public class BancoRepository implements IBancoRepository {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	@Inject
	public BancoRepository(EntityManager manager) {
		
		this.manager = manager;
		
	}
 
	@Override
	public List<Banco> getAllBancos() {
		
		TypedQuery<Banco> query = manager.createQuery("from Banco", Banco.class);
		return query.getResultList();
		
	}
 
	@Override
	public Banco getBancoById(Long id) {
		
		return this.manager.find(Banco.class, id);
	}

}
