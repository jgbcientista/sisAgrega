package br.com.sysagrega.repository.imp;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;

import br.com.sysagrega.model.IUsuario;
import br.com.sysagrega.model.imp.Usuario;
import br.com.sysagrega.repository.IUsuarioRepository;
import br.com.sysagrega.service.imp.NegocioException;

public class UsuarioRepository implements IUsuarioRepository {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;

	@Inject 
	public UsuarioRepository(EntityManager manager) {
		
		this.manager = manager;
		
	}

	@Override
	public IUsuario getById(Long id) {
		return this.manager.find(Usuario.class, id);
		
	}
	
	@Override
	public List<Usuario> getByFilter(String login, String nome, String perfil){
		
		UaiCriteria<Usuario> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Usuario.class);
		
		
		if(login != null && !login.isEmpty() && login != null) {
			easyCriteria.andStringLike("login", login+"%");
		}
		
		if(nome != null && !nome.isEmpty() && nome != null) {
			easyCriteria.andStringLike("nome", nome+"%");
		}
		
		if(perfil != null && !perfil.isEmpty() && perfil != null) {
			easyCriteria.andStringLike("perfil", perfil+"%");
		}
		easyCriteria.andEquals("ativo", true);
		
		try {
			
			return easyCriteria.getResultList();
			
		} catch (NoResultException e) {
			
			return null;
			
		}
		
	}
	
	public Usuario getUserPerfilByLogin(String login) {
		UaiCriteria<Usuario> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Usuario.class);
		

		if(login != null && !login.isEmpty() && login != null) {
			easyCriteria.andStringLike("login", login);
		}
		
		easyCriteria.andEquals("ativo", true);
		
		return easyCriteria.getSingleResult();
	}
	
	public IUsuario saveOrMerge(IUsuario usuario) {
		return this.manager.merge(usuario);

	}
 
	@Override
	public void remover(IUsuario usuario) {

		try {
			
			IUsuario item = getById(usuario.getId());
			this.manager.remove(item);
			this.manager.flush();
			
		} catch (PersistenceException e) {
			
			throw new NegocioException("Usuario não pode ser excluído.");
			
		}
		
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.repository.imp.IClienteRepository#getAllClientes()
	 */
	@Override
	public List<Usuario> getAll() {
		UaiCriteria<Usuario> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Usuario.class);
		easyCriteria.andEquals("ativo", true);
		
		return easyCriteria.getResultList();
		
	}
}
