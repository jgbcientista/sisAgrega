package br.com.sysagrega.repository.imp;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;

import br.com.sysagrega.model.IContrato;
import br.com.sysagrega.model.imp.Contrato;
import br.com.sysagrega.repository.IContratoRepository;
import br.com.sysagrega.service.imp.NegocioException;

public class ContratoRepository implements IContratoRepository {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;

	@Inject 
	public ContratoRepository(EntityManager manager) {
		
		this.manager = manager;
		
	}
 
	@Override
	public Contrato getContratoById(Long id) {
		
		return this.manager.find(Contrato.class, id);
		
	}
	
	 
	@Override
	public IContrato getPropostaByNumero(String nrContrato) {
		
		try {
			
			return manager.createQuery("from Contrato c where c.ativo='true' and c.nrContrato = :nrContrato", Contrato.class)
						  .setParameter("nrContrato", nrContrato)
						  .getSingleResult();
			
		} catch (NoResultException e) {
			
			return null;
			
		}
	}
	
 
	@Override
	public List<Contrato> getContratoByFilter(String nrContrato, String filtroCliente, String filtroStatus,
			Date filtroDataInicial, Date filtroDataFinal) {
		UaiCriteria<Contrato> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Contrato.class);
		if(!filtroCliente.isEmpty() && filtroCliente != null) {
			easyCriteria.andStringLike(true,"nomeCliente", filtroCliente.toUpperCase()+"%");
		}
		if(!nrContrato.isEmpty() && nrContrato != null) {
			easyCriteria.andEquals("nrContrato", nrContrato);
		}
		
		if(!filtroStatus.isEmpty() && filtroStatus != null) {
			easyCriteria.andEquals("statusContrato", filtroStatus);
		}
		
		if(filtroDataInicial != null) {
			easyCriteria.andGreaterOrEqualTo("vigenciaDataInicial", filtroDataInicial);
		}
		
		if(filtroDataFinal != null) {
			easyCriteria.andLessOrEqualTo("vigenciaDataFinal", filtroDataFinal);
		}
		easyCriteria.orderByDesc("dataAssinatura");
		try {
			return easyCriteria.getResultList();
		} catch (NoResultException e) {
			throw new NegocioException("Erro '"+e+"' ao tentar pesquisar. Entre em contato com administrador");
		}
	}
	
 
	@Override
	public IContrato saveOrMerge(IContrato contrato) {

		return this.manager.merge(contrato);

	}
	
 
	@Override
	public void remover(Contrato contrato) {

		try {
			
			Contrato ctr = getContratoById(contrato.getId());
			this.manager.remove(ctr);
			this.manager.flush();
			
		} catch (PersistenceException e) {
			
			throw new NegocioException("O contrato não pode ser excluído.");
			
		}
		
	}

 
	@Override
	public List<Contrato> getAllContratos() {
		
		TypedQuery<Contrato> query = manager.createQuery("from Contrato", Contrato.class);
		return query.getResultList();
		
	}
}
