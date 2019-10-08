package br.com.sysagrega.repository.imp;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.sysagrega.model.imp.Estado;
import br.com.sysagrega.repository.IEstadoRepository;

public class EstadoRepository implements IEstadoRepository {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	@Inject
	public EstadoRepository(EntityManager manager) {
		
		this.manager = manager;
		
	}
	
	/* (non-Javadoc)
	 * @see br.com.sysagrega.repository.imp.IEstadoRepository#getAllEstados()
	 */
	@Override
	public List<Estado> getAllEstados() {
		
		TypedQuery<Estado> query = manager.createQuery("from Estado", Estado.class);
		return query.getResultList();
		
	}
	
	/* (non-Javadoc)
	 * @see br.com.sysagrega.repository.imp.IEstadoRepository#getEstadoById(java.lang.Long)
	 */
	@Override
	public Estado getEstadoById(Long id) {
		
		return this.manager.find(Estado.class, id);
	}

}
