package br.com.sysagrega.repository.imp;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.sysagrega.model.imp.Municipio;
import br.com.sysagrega.repository.IMunicipioRepository;

public class MunicipioRepository implements IMunicipioRepository {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	@Inject
	public MunicipioRepository(EntityManager manager) {
		
		this.manager = manager;
		
	}
	
	
	@Override
	public List<Municipio> getAllMunicipio() {
		
		TypedQuery<Municipio> query = manager.createQuery("from Municipio", Municipio.class);
		return query.getResultList();
		
	}
	

	@Override
	public Municipio getMunicipioById(Long id) {
		
		return this.manager.find(Municipio.class, id);
	}
	
	@Override
	public List<Municipio> getMunicipioByMicroRegiaoId(Long id) {

		try {

			return manager.createQuery("from Municipio c where c.microRegiao.id = :id", Municipio.class).setParameter("id", id)
					.getResultList();

		} catch (NoResultException e) {

			return null;

		}

	}
	
	@Override
	public List<Municipio> getMunicipioByMacroRegiaoId(Long id) {

		try {

			return manager.createQuery("from Municipio c where c.macroRegiao.id = :id", Municipio.class).setParameter("id", id)
					.getResultList();

		} catch (NoResultException e) {

			return null;

		}

	}
	
	@Override
	public List<Municipio> getMunicipioByEstado(Long id) {

		try {

			return manager.createQuery("from Municipio c where c.estado.id = :id", Municipio.class).setParameter("id", id)
					.getResultList();

		} catch (NoResultException e) {

			return null;

		}

	}

}
