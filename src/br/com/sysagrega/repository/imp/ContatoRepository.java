package br.com.sysagrega.repository.imp;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;

import br.com.sysagrega.model.IContato;
import br.com.sysagrega.model.imp.Contato;
import br.com.sysagrega.repository.IContatoRepository;
import br.com.sysagrega.service.imp.NegocioException;

public class ContatoRepository implements IContatoRepository {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;

	@Inject 
	public ContatoRepository(EntityManager manager) {
		
		this.manager = manager;
		
	}

	@Override
	public IContato getContatoById(Long id) {
		
		return this.manager.find(Contato.class, id);
		
	}
	
	@Override
	public  List<Contato> getByClienteId(Long idCliente) {
		try {
			
			return manager.createQuery("from Contato c where c.idCliente = :idCliente ", Contato.class)
					.setParameter("idCliente", idCliente)
					.getResultList();
			
		} catch (NoResultException e) {
			
			return null;
			
		}
	}
	
	@Override
	public  List<Contato> getByFornecedorId(Long idFornecedor) {
		try {
			return manager.createQuery("from Contato c where c.idFornecedor = :idFornecedor ", Contato.class)
					.setParameter("idFornecedor", idFornecedor)
					.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	
	@Override
	public List<Contato> getContatoByFilter(Long idCliente) {
		
		UaiCriteria<Contato> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Contato.class);
		
		if(idCliente != null && idCliente != null) {
			easyCriteria.andEquals("nome", idCliente);
		}
		
		easyCriteria.andEquals("ativo", true);
		try {
			
			return easyCriteria.getResultList();
			
		} catch (NoResultException e) {
			
			return null;
			
		}
		
	}
	
	@Override
	public IContato getContatoByCliente(Long idCliente) {
		
		try {
			
			return manager.createQuery("from Contato c where c.ativo='true' and c.idCliente = :idCliente", Contato.class)
						  .setParameter("idCliente", idCliente)
						  .getSingleResult();
			
		} catch (NoResultException e) {
			
			return null;
			
		}
		
	}
	
	@Override
	public List<Contato> getContatoByAtivo() {
		
		UaiCriteria<Contato> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Contato.class);
		
		easyCriteria.andEquals("ativo", true);
		try {
			
			return easyCriteria.getResultList();
			
		} catch (NoResultException e) {
			
			return null;
			
		}
		
	}
	
	@Override
	public IContato saveOrMerge(IContato contato) {

		return this.manager.merge(contato);

	}

	@Override
	public void remover(IContato contato) {

		try {
			
			IContato cli = getContatoById(contato.getId());
			this.manager.remove(cli);
			this.manager.flush();
			
		} catch (PersistenceException e) {
			
			throw new NegocioException("Contato não pode ser excluído.");
			
		}
		
	}

	@Override
	public List<Contato> getAllContatos() {
		
		TypedQuery<Contato> query = manager.createQuery("from Contato", Contato.class);
		return query.getResultList();
		
	}

	@Override
	public List<Contato> getAllContato() {
		// TODO Auto-generated method stub
		return null;
	}

}
