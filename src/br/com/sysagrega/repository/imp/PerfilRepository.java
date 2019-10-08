package br.com.sysagrega.repository.imp;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;

import br.com.sysagrega.model.IPerfil;
import br.com.sysagrega.model.imp.Perfil;
import br.com.sysagrega.repository.IPerfilRepository;
import br.com.sysagrega.service.imp.NegocioException;

public class PerfilRepository implements IPerfilRepository{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;

	@Inject 
	public PerfilRepository(EntityManager manager) {
		
		this.manager = manager;
		
	}

	@Override
	public IPerfil getById(Long id) {
		return this.manager.find(Perfil.class, id);
		
	}
	
	@Override
	public List<Perfil> getByFilter(String nome){
		
		UaiCriteria<Perfil> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Perfil.class);
		
		if(nome != null && !nome.isEmpty() && nome != null) {
			easyCriteria.andStringLike("nome", nome+"%");
			
		}
		easyCriteria.andEquals("ativo", true);
		try {
			
			return easyCriteria.getResultList();
			
		} catch (NoResultException e) {
			
			return null;
			
		}
		
	}
	public IPerfil saveOrMerge(IPerfil perfil) {
		return this.manager.merge(perfil);

	}
 
	@Override
	public void remover(IPerfil perfil) {

		try {
			
			IPerfil item = getById(perfil.getId());
			this.manager.remove(item);
			this.manager.flush();
			
		} catch (PersistenceException e) {
			
			throw new NegocioException("Item não pode ser excluído.");
			
		}
		
	}
 
	@Override
	public List<Perfil> getAll() {
		
		UaiCriteria<Perfil> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Perfil.class);
		easyCriteria.andEquals("ativo", true);
		
		return easyCriteria.getResultList();
		
	}
}
