package br.com.sysagrega.repository.imp;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;

import br.com.sysagrega.model.IPlanejamento;
import br.com.sysagrega.model.imp.Planejamento;
import br.com.sysagrega.model.imp.Projeto;
import br.com.sysagrega.repository.IPlanejamentoRepository;
import br.com.sysagrega.service.imp.NegocioException;


public class PlanejamentoRepository implements IPlanejamentoRepository {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	@Inject
	public PlanejamentoRepository(EntityManager manager) {
		
		this.manager = manager;
	}

	@Override
	public List<Planejamento> getAll() {
		UaiCriteria<Planejamento> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Planejamento.class);
	
		return easyCriteria.getResultList();
	}
	
	@Override
	public List<Planejamento> getByFiltros(Planejamento filtro){
		UaiCriteria<Planejamento> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Planejamento.class);
		
			if(filtro != null){
			
				if(filtro.getProjeto()!= null && filtro.getProjeto().getId()!= null) {
					easyCriteria.andEquals("projeto",filtro.getProjeto());
				}
				if(filtro.getId()!= null ) {
					easyCriteria.andEquals("id",filtro.getId());
				}
				if(filtro.getDtInicioPlanejamento() != null && filtro.getDtfimPlanejamento() != null) {
					easyCriteria.andBetween("dtInicioPlanejamento", filtro.getDtInicioPlanejamento(), filtro.getDtfimPlanejamento());
					easyCriteria.andBetween("dtfimPlanejamento", filtro.getDtInicioPlanejamento(), filtro.getDtfimPlanejamento());
				}
				if(filtro.getProfissionalEPI()!= null && filtro.getProfissionalEPI().getId()!= null) {
					easyCriteria.andEquals("profissionalEPI",filtro.getProfissionalEPI());
				}
				if(filtro.getProfissionalEVCTGAL()!= null && filtro.getProfissionalEVCTGAL().getId()!= null) {
					easyCriteria.andEquals("profissionalEVCTGAL",filtro.getProfissionalEVCTGAL());
				}
				if(filtro.getProfissionalMapas()!= null && filtro.getProfissionalMapas().getId()!= null) {
					easyCriteria.andEquals("profissionalMapas",filtro.getProfissionalMapas());
				}
				if(filtro.getProfissionalVerificacao()!= null && filtro.getProfissionalVerificacao().getId()!= null) {
					easyCriteria.andEquals("profissionalVerificacao",filtro.getProfissionalVerificacao());
				}
				
			}
			try {
				
				easyCriteria.orderByAsc("dtInicioPlanejamento");
				return easyCriteria.getResultList();
				
			} catch (NoResultException e) {
				return null;
			}
	}
	

	@Override
	public Planejamento getById(Long id) {
		return this.manager.find(Planejamento.class, id);
	}
	
	public Planejamento getByProjeto(Long idProjeto){
		
		UaiCriteria<Planejamento> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Planejamento.class);
		
		if(idProjeto != null) {
			easyCriteria.andEquals("projeto",idProjeto);
		}
		try {
			return easyCriteria.getSingleResult();
			
		} catch (NoResultException e) {
			return null;
		}
		
	}
	
	@Override
	public IPlanejamento salvar(IPlanejamento item) {
		return this.manager.merge(item);
		 
	}

	@Override
	public void remover(IPlanejamento item) {
	try {
			
		IPlanejamento rec = getById(item.getId());
			this.manager.remove(rec);
			this.manager.flush();
			
		} catch (PersistenceException e) {
			
			throw new NegocioException("Item não pode ser excluído.");
			
		}
		
	}
 


}
