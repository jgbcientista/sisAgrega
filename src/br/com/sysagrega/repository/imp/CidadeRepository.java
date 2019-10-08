package br.com.sysagrega.repository.imp;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.sysagrega.model.imp.Cidade;
import br.com.sysagrega.repository.ICidadeRepository;

public class CidadeRepository implements ICidadeRepository {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	private EntityManager manager;

	@Inject
	public CidadeRepository(EntityManager manager) {

		this.manager = manager;

	}

	@Override
	public List<Cidade> getAllCidades() {

		TypedQuery<Cidade> query = manager.createQuery("from Cidade", Cidade.class);
		return query.getResultList();

	}

 
	@Override
	public Cidade getCidadeById(Long id) {

		return this.manager.find(Cidade.class, id);
	}

 
	@Override
	public List<Cidade> getCidadesByEstadoId(Long id) {

		try {

			return manager.createQuery("from Cidade c where c.estado.id = :id", Cidade.class).setParameter("id", id)
					.getResultList();

		} catch (NoResultException e) {

			return null;

		}

	}
	
	@Override
	public List<Cidade> getCidadesByEstado(String uf) {

		try {

			return manager.createQuery("from Cidade c where c.estado.uf = :uf", Cidade.class).setParameter("uf", uf)
					.getResultList();

		} catch (NoResultException e) {

			return null;

		}

	}

}
