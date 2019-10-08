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

import br.com.sysagrega.controller.vo.RelatorioClienteVO;
import br.com.sysagrega.model.IProposta;
import br.com.sysagrega.model.imp.Proposta;
import br.com.sysagrega.repository.IPropostaRepository;
import br.com.sysagrega.service.imp.NegocioException;

public class PropostaRepository implements IPropostaRepository  {

	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;

	@Inject 
	public PropostaRepository(EntityManager manager) {
		
		this.manager = manager;
		
	}

	@Override
	public synchronized  Proposta getPropostaById(Long id) {
		
		return this.manager.find(Proposta.class, id);
		
	}
	
 
	@Override
	public synchronized  IProposta getPropostaByPrecificacao(Long idPrecificacao) {
		
		try {
			
			return manager.createQuery("from Proposta p where p.precificacao.id = :idPrecificacao", Proposta.class)
					  .setParameter("idPrecificacao", idPrecificacao)
					  .setParameter("ativo", true)
						  
						  .getSingleResult();
			
		} catch (NoResultException e) {
			
			return null;
			
		}
		
	}
 
	@Override
	public synchronized  List<Proposta> getPropostaByFilter(String filtroNumeroProposta, Long cliente,
			Character filtroStatus, Date filtroDataInicial, Date filtroDataFinal) {
		
		UaiCriteria<Proposta> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Proposta.class);
		
		
		if(!filtroNumeroProposta.isEmpty() && filtroNumeroProposta != null ) {
			easyCriteria.andStringLike("numeroProposta", "%" + filtroNumeroProposta + "%");
		}
		
		if(cliente != null) {
			easyCriteria.andEquals("cliente",cliente);
		}
		
		if(filtroStatus != null) {
			easyCriteria.andEquals("status", filtroStatus);
		}
		
		if(filtroDataInicial != null && filtroDataFinal != null) {
			easyCriteria.andBetween("dataInclusao", filtroDataInicial, filtroDataFinal);
		}
		easyCriteria.andEquals("ativo", true);
		
		easyCriteria.orderByAsc("ordProposta");
		try {
			
			return easyCriteria.getResultList();
			
		} catch (NoResultException e) {
			
			return null;
			
		}
		
	}
	
	 
	@Override
	public synchronized  List<IProposta> getPropostasByPeriodo(Date dataInicial, Date dataFinal) {
		//TODO Implementar
		return null;
	}
	

 
	@Override
	public synchronized  Proposta saveOrMerge(Proposta proposta) {

		return this.manager.merge(proposta);

	}
	
	@Override
	public synchronized  void remover(Proposta proposta) {

		try {
			
			Proposta prop = getPropostaById(proposta.getId());
			this.manager.remove(prop);
			this.manager.flush();
			
		} catch (PersistenceException e) {
			
			throw new NegocioException("A Proposta não pode ser excluído.");
			
		}
		
	}

 
	@Override
	public synchronized  List<Proposta> getAllPropostas() {
		
		TypedQuery<Proposta> query = manager.createQuery("from Proposta p where p.ativo='1' order by p.ordProposta desc", Proposta.class);
		return query.getResultList();
		
	}

	@Override
	public synchronized  Proposta preSalvar(Proposta proposta) {
		return this.manager.merge(proposta);
	}
	
	@Override
	public synchronized  List<Proposta> getObterListPropostaRelatorioByFilter(RelatorioClienteVO filtro) {
		
		UaiCriteria<Proposta> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Proposta.class);
				
		if(filtro != null && filtro.getIdCliente() != null) {
			easyCriteria.andEquals("cliente",filtro.getIdCliente());
		}
		
		if(filtro != null && filtro.getDataInicial() != null) {
			easyCriteria.andGreaterOrEqualTo("dataInclusao", filtro.getDataInicial());
		}
		
		if(filtro != null && filtro.getDataFinal() != null) {
			easyCriteria.andLessOrEqualTo("dataInclusao", filtro.getDataFinal());
		}
		
		if(filtro != null && filtro.getIdServicoNegocio() != null) {
			easyCriteria.andEquals("idServicoNegocio", filtro.getIdServicoNegocio());
		}
		
		if(filtro != null && filtro.getIdArea() != null) {
			easyCriteria.andEquals("idArea", filtro.getIdArea());
		}
		
		easyCriteria.andEquals("ativo", true);
		easyCriteria.orderByAsc("ordProposta");
		
		try {
			return easyCriteria.getResultList();

		} catch (NoResultException e) {
		
			return null;
		}
	}
}
