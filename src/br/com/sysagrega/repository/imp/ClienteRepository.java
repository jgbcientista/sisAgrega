package br.com.sysagrega.repository.imp;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;

import br.com.sysagrega.model.ICliente;
import br.com.sysagrega.model.imp.Cliente;
import br.com.sysagrega.repository.IClienteRepository;
import br.com.sysagrega.service.imp.NegocioException;

public class ClienteRepository implements IClienteRepository {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;

	@Inject 
	public ClienteRepository(EntityManager manager) {
		
		this.manager = manager;
		
	}

 
	@Override
	public ICliente getClienteById(Long id) {
		
		return this.manager.find(Cliente.class, id);
		
	}
	
 
	@Override
	public ICliente getClienteByCnpj(String cnpjCPF, Boolean situacao) {
		
		try {
			
			return manager.createQuery("from Cliente c where c.ativo=" +situacao+" and c.cnpjCPF = :cnpjCPF", Cliente.class)
						  .setParameter("cnpjCPF", cnpjCPF)
						  .getSingleResult();
			
		} catch (NoResultException e) {
			
			return null;
			
		}
		
	}
	
	@Override
	public List<Cliente> getClienteByAtivo() {
		
		UaiCriteria<Cliente> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Cliente.class);
		
		easyCriteria.andEquals("ativo", true);
		try {
			
			return easyCriteria.getResultList();
			
		} catch (NoResultException e) {
			
			return null;
			
		}
		
	}
	
	/* (non-Javadoc)
	 * @see br.com.sysagrega.repository.imp.IClienteRepository#getClienteByFilter(java.lang.String, br.com.sysagrega.model.Enums.TipoSituacaoCliente, br.com.sysagrega.model.Enums.TipoSeguimentoCliente, br.com.sysagrega.model.Enums.TipoCliente)
	 */
	@Override
	public List<Cliente> getClienteByFilter(String nome, String seguimento, String tipoCliente) {
		
		UaiCriteria<Cliente> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Cliente.class);
		
		if(nome != null && !nome.isEmpty() && nome != null) {
			easyCriteria.andStringLike(true,"nome", nome+"%");
		}
		
		if(seguimento != null && !seguimento.isEmpty() && seguimento != null) {
			easyCriteria.andEquals("seguimento", seguimento);
		}
		
		if(tipoCliente != null && !tipoCliente.isEmpty() && tipoCliente != null) {
			easyCriteria.andEquals("tipoCliente", tipoCliente);
		}
		easyCriteria.andEquals("ativo", true);
		try {
			
			return easyCriteria.getResultList();
			
		} catch (NoResultException e) {
			
			return null;
			
		}
		
	}
	
	@Override
	public List<Cliente> getClienteByFilterNome(String nome) {
		
		UaiCriteria<Cliente> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Cliente.class);
		
		if(nome != null && !nome.isEmpty() && nome != null) {
			easyCriteria.andStringLike("nome", nome);
		}
		easyCriteria.andEquals("ativo", true);			
		try {
			
			return easyCriteria.getResultList();
			
		} catch (NoResultException e) {
			
			return null;
			
		}
		
	}

 
	@Override
	public ICliente saveOrMerge(ICliente cliente) {

		return this.manager.merge(cliente);

	}

 
	@Override
	public void remover(ICliente cliente) {

		try {
			
			ICliente cli = getClienteById(cliente.getId());
			this.manager.remove(cli);
			this.manager.flush();
			
		} catch (PersistenceException e) {
			
			throw new NegocioException("Cliente não pode ser excluído.");
			
		}
		
	}

	 
	@Override
	public List<Cliente> getAllClientes() {
		
		TypedQuery<Cliente> query = manager.createQuery("from Cliente", Cliente.class);
		return query.getResultList();
		
	}
}
