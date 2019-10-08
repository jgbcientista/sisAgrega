package br.com.sysagrega.repository.imp;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.sysagrega.model.imp.Regiao;
import br.com.sysagrega.repository.IRegiaoRepository;

public class RegiaoRepository implements IRegiaoRepository {

	private static final long serialVersionUID = 1L;

	private EntityManager manager;

	@Inject
	public RegiaoRepository(EntityManager manager) {

		this.manager = manager;

	}

 
	@Override
	public List<Regiao> getAllRegiao() {

		TypedQuery<Regiao> query = manager.createQuery("from Regiao", Regiao.class);
		return query.getResultList();

	}

 
	@Override
	public Regiao getRegiaoById(Long id) {

		return this.manager.find(Regiao.class, id);
	}

	
}
