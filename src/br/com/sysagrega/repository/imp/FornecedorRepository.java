package br.com.sysagrega.repository.imp;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;

import br.com.sysagrega.model.IFornecedor;
import br.com.sysagrega.model.imp.Fornecedor;
import br.com.sysagrega.repository.IFornecedorRepository;
import br.com.sysagrega.service.imp.NegocioException;

public class FornecedorRepository implements IFornecedorRepository{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;

	@Inject 
	public FornecedorRepository(EntityManager manager) {
		
		this.manager = manager;
		
	}
	
	@Override
	public IFornecedor getFornecedorById(Long id) {
		
		return this.manager.find(Fornecedor.class, id);
	}
	
	@Override
	public IFornecedor getFornecedorByCnpj(String cnpjCPF) {

		try {
			
			return manager.createQuery("from Fornecedor f where f.ativo='true'  and f.cnpjCPF = :cnpjCPF", Fornecedor.class)
						  .setParameter("cnpjCPF", cnpjCPF)
						  .getSingleResult();
			
		} catch (NoResultException e) {
			
			return null;
			
		}
		
	}
	
	@Override
	public List<Fornecedor> getFornecedorByFilter(String nomeFantasia, String ramoAtividade, String principalAtividade) {

		UaiCriteria<Fornecedor> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Fornecedor.class);
		
		if(!nomeFantasia.isEmpty() && nomeFantasia != null) {
			easyCriteria.andStringLike(true,"nomeFantasia", nomeFantasia+"%");
		
		}
		
		if(!ramoAtividade.isEmpty() && ramoAtividade != null ) {
			easyCriteria.andEquals("ramoAtividade", ramoAtividade);
		}
		
		if(!principalAtividade.isEmpty() && principalAtividade != null ) {
			easyCriteria.andEquals("principalAtividade", principalAtividade);
		}
		
		easyCriteria.andEquals("ativo", true);
		
		try {
			
			return easyCriteria.getResultList();
			
		} catch (NoResultException e) {
			
			return null;
			
		}
		
	}

	@Override
	public IFornecedor saveOrMerge(IFornecedor fornecedor) {

		return this.manager.merge(fornecedor);
		
	}
	
	/* (non-Javadoc)
	 * @see br.com.sysagrega.repository.imp.IFornecedorRepository#remover(br.com.sysagrega.model.imp.Fornecedores)
	 */

	@Override
	public void remover(IFornecedor fornecedor) {
		
		try {
			
			IFornecedor forn = getFornecedorById(fornecedor.getId());
			this.manager.remove(forn);
			this.manager.flush();
			
		} catch (PersistenceException e) {
			
			throw new NegocioException("Fornecedor não pode ser excluído.");
			
		}
		
	}
	

	@Override
	public List<Fornecedor> getAllFornecedor() {
		
		TypedQuery<Fornecedor> query = manager.createQuery("from Fornecedor", Fornecedor.class);
		return query.getResultList();
		
	}

	

}
