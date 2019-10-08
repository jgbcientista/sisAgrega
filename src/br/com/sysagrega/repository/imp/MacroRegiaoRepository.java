package br.com.sysagrega.repository.imp;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.sysagrega.model.imp.MacroRegiao;
import br.com.sysagrega.repository.IMacroRegiaoRepository;

public class MacroRegiaoRepository implements IMacroRegiaoRepository {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	@Inject
	public MacroRegiaoRepository(EntityManager manager) {
		
		this.manager = manager;
		
	}
	
	
	@Override
	public List<MacroRegiao> getAllMacroRegiao() {
		
		TypedQuery<MacroRegiao> query = manager.createQuery("from MacroRegiao", MacroRegiao.class);
		return query.getResultList();
		
	}
	

	@Override
	public MacroRegiao getMacroRegiaoById(Long id) {
		
		return this.manager.find(MacroRegiao.class, id);
	}
	
	
	@Override
	public List<MacroRegiao> getMacroRegiaoByEstadoId(Long id) {

		try {

			return manager.createQuery("from MacroRegiao c where c.estado.id = :id", MacroRegiao.class).setParameter("id", id)
					.getResultList();

		} catch (NoResultException e) {

			return null;

		}

	}
	
	@Override
	public List<MacroRegiao> getMacroRegiaoByCidade(Long id) {
		try {
			if(id != null){
			return manager.createQuery("from MacroRegiao c where c.cidade.id = :id", MacroRegiao.class).setParameter("id", id)
					.getResultList();
			}
			return null;
		} catch (NoResultException e) {

			return null;

		}

	}
	
	

}
