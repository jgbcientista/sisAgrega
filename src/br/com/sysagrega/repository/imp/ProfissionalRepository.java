package br.com.sysagrega.repository.imp;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;

import br.com.sysagrega.model.IProfissional;
import br.com.sysagrega.model.imp.Profissional;
import br.com.sysagrega.model.imp.Usuario;
import br.com.sysagrega.repository.IProfissionalRepository;
import br.com.sysagrega.service.imp.NegocioException;

public class ProfissionalRepository implements IProfissionalRepository {

	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;

	@Inject 
	public ProfissionalRepository(EntityManager manager) {
		
		this.manager = manager;
		
	}

	 
	@Override
	public Profissional getById(Long id) {
		if(id== null){
			return null;
		}
		return manager.createQuery("from Profissional p where p.id = :id", Profissional.class)
				  .setParameter("id", id)
				  .getSingleResult();
		}
	
	@Override
	public IProfissional getProfissionalByCpf(String cpf) {
		
		try {
			
			return manager.createQuery("from Profissional p where p.ativo='true' and p.cpf = :cpf", Profissional.class)
						  .setParameter("cpf", cpf)
						  .getSingleResult();
			
		} catch (NoResultException e) {
			
			return null;
			
		}
		
	}
	
	@Override
	public Profissional obterProfissionalByLogin(Usuario usuarioAcesso) {
			
			UaiCriteria<Profissional> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Profissional.class);
			easyCriteria.andEquals("ativo", true);
				
					if(usuarioAcesso!= null) {
						easyCriteria.andEquals("usuarioAcesso",usuarioAcesso.getId());
					}
					 
			try {
				return easyCriteria.getSingleResult();
				
			} catch (NoResultException e) {
				return null;
			}
					
		}
	
	@Override
	public List<Profissional> getProfissionalByFilter(String nome, String cpf, String tipoColaborador) {
		
		UaiCriteria<Profissional> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Profissional.class);
		
		if(nome != null && !nome.isEmpty()) {
			easyCriteria.andStringLike(true, "nome", nome+"%");
			
		}
		
		if(cpf != null && !cpf.isEmpty()) {
			easyCriteria.andEquals("cpf", cpf);
		}
		
		if(tipoColaborador != null && !tipoColaborador.isEmpty()) {
			easyCriteria.andEquals("tipoColaborador", tipoColaborador);
		}
		easyCriteria.andEquals("ativo", true);
		easyCriteria.orderByAsc("nome");
		try {
			
			return easyCriteria.getResultList();
			
		} catch (NoResultException e) {
			
			return null;
			
		}
		
	}
	

	@Override
	public IProfissional saveOrMerge(IProfissional profissional) {
		
		return this.manager.merge(profissional);

	}
	
	@Override
	public void remover(IProfissional profissional) {

		try {
			
			IProfissional prof = getById(profissional.getId());
			this.manager.remove(prof);
			this.manager.flush();
			
		} catch (PersistenceException e) {
			
			throw new NegocioException("Profissional não pode ser excluído.");
			
		}
		
	}

	@Override
	public List<Profissional> getAllProfissionals() {
		TypedQuery<Profissional> query = manager.createQuery("from Profissional p where p.ativo ='true' order by p.nome asc", Profissional.class);
		
		return query.getResultList();
		
	}
}
