package br.com.sysagrega.repository.imp;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.hibernate.criterion.Order;

import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;

import br.com.sysagrega.model.IFaturamento;
import br.com.sysagrega.model.imp.Faturamento;
import br.com.sysagrega.repository.IFaturamentoRepository;
import br.com.sysagrega.service.imp.NegocioException;


public class FaturamentoRepository implements IFaturamentoRepository {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	@Inject
	public FaturamentoRepository(EntityManager manager) {
		
		this.manager = manager;
		
	}

	@Override
	public List<Faturamento> getAll() {
		UaiCriteria<Faturamento> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Faturamento.class);
	
		return easyCriteria.getResultList();
	}

	@Override
	public Faturamento getById(Long id) {
		return this.manager.find(Faturamento.class, id);
	}
	
	
	@Override
	public IFaturamento salvar(IFaturamento item) {
		return this.manager.merge(item);
		 
	}

	@Override
	public void remover(IFaturamento item) {
	try {
			
		IFaturamento rec = getById(item.getId());
			this.manager.remove(rec);
			this.manager.flush();
			
		} catch (PersistenceException e) {
			
			throw new NegocioException("Item não pode ser excluído.");
			
		}
		
	}
	@Override
	public List<Faturamento> getByProjeto(Long idProjeto){
		
		UaiCriteria<Faturamento> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Faturamento.class);
				
		if(idProjeto != null) {
			easyCriteria.andEquals("projeto",idProjeto);
		}
		
		try {
			return easyCriteria.getResultList();
			
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@Override
	public  List<Faturamento> getByContrato(Long idContrato){
		
		UaiCriteria<Faturamento> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Faturamento.class);
				
		if(idContrato != null) {
			easyCriteria.andEquals("contrato",idContrato);
		}
		
		try {
			return easyCriteria.getResultList();
			
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@Override
	public  List<Faturamento> getByFiltros(Faturamento filtro){
		
		UaiCriteria<Faturamento> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Faturamento.class);
				
		if(filtro.getContrato() != null) {
			easyCriteria.andEquals("contrato",filtro.getContrato().getId());
		}
		
		if(filtro.getSituacao() != null) {
			easyCriteria.andEquals("situacao",filtro.getSituacao().getId());
		}
		
		try {
			return easyCriteria.getResultList();
			
		} catch (NoResultException e) {
			return null;
		}
	}
	
	
	@Override
	public Faturamento getByProjetoByParcela(Long idProjeto, Long parcela){
		
		UaiCriteria<Faturamento> criteria = UaiCriteriaFactory.createQueryCriteria(manager, Faturamento.class);
				
		if(idProjeto != null) {
			criteria.andEquals("projeto",idProjeto);
		}
		if(parcela != null) {
			criteria.andEquals("parcela",parcela);
		}
		criteria.orderByAsc("parcela");
		
		try {
			return criteria.getSingleResult();
			
		} catch (NoResultException e) {
			return null;
		}
	}



}
