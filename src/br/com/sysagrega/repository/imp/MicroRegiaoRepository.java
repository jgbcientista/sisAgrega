package br.com.sysagrega.repository.imp;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.sysagrega.model.imp.MicroRegiao;
import br.com.sysagrega.repository.IMicroRegiaoRepository;

public class MicroRegiaoRepository implements IMicroRegiaoRepository {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	@Inject
	public MicroRegiaoRepository(EntityManager manager) {
		
		this.manager = manager;
		
	}
	
	
	@Override
	public List<MicroRegiao> getAllMicroRegiao() {
		
		TypedQuery<MicroRegiao> query = manager.createQuery("from MicroRegiao", MicroRegiao.class);
		return query.getResultList();
		
	}
	

	@Override
	public MicroRegiao getMicroRegiaoById(Long id) {
		
		return this.manager.find(MicroRegiao.class, id);
	}
	
	@Override
	public List<MicroRegiao> getMicroRegiaoByMacroRegiaoId(Long id) {

		try {

			return manager.createQuery("from MicroRegiao c where c.macroRegiao.id = :id", MicroRegiao.class).setParameter("id", id)
					.getResultList();

		} catch (NoResultException e) {

			return null;

		}

	}

}
