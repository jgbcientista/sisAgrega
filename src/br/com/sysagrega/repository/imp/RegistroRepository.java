package br.com.sysagrega.repository.imp;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;

import br.com.sysagrega.model.IRegistro;
import br.com.sysagrega.model.imp.Registro;
import br.com.sysagrega.repository.IRegistroRepository;
import br.com.sysagrega.service.imp.NegocioException;


public class RegistroRepository implements IRegistroRepository {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	@Inject
	public RegistroRepository(EntityManager manager) {
		
		this.manager = manager;
		
	}

	@Override
	public List<Registro> getAll() {
		UaiCriteria<Registro> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Registro.class);
		easyCriteria.andEquals("ativo", true);
		return easyCriteria.getResultList();
	}

	@Override
	public Registro getById(Long id) {
		return this.manager.find(Registro.class, id);
	}
	

	@Override
	public IRegistro salvar(IRegistro item) {
		return this.manager.merge(item);
		 
	}

	@Override
	public void remover(IRegistro item) {
	try {
			
		IRegistro rec = getById(item.getId());
			this.manager.remove(rec);
			this.manager.flush();
			
		} catch (PersistenceException e) {
			
			throw new NegocioException("Itemo não pode ser excluído.");
			
		}
		
	}
	public List<Registro> getByFiltros(Registro filtro){
		
		UaiCriteria<Registro> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Registro.class);
		
			if(filtro != null){
			
				if(filtro.getProjeto()!= null) {
					easyCriteria.andEquals("projeto", filtro.getProjeto());
				} 
		}
		try {
			return easyCriteria.getResultList();
			
		} catch (NoResultException e) {
			return null;
		}
		
	}

}
