package br.com.sysagrega.repository.imp;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;

import br.com.sysagrega.model.IVerificacaoProfissional;
import br.com.sysagrega.model.imp.CaracteristicaProjeto;
import br.com.sysagrega.model.imp.VerificacaoProfissional;
import br.com.sysagrega.repository.IVerificacaoProfissionalRepository;
import br.com.sysagrega.service.imp.NegocioException;


public class VerificacaoProfissionalRepository implements IVerificacaoProfissionalRepository {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	@Inject
	public VerificacaoProfissionalRepository(EntityManager manager) {
		
		this.manager = manager;
		
	}

	@Override
	public List<VerificacaoProfissional> getAll() {
		UaiCriteria<VerificacaoProfissional> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, VerificacaoProfissional.class);
	
		return easyCriteria.getResultList();
	}

	@Override
	public VerificacaoProfissional getById(Long id) {
		return this.manager.find(VerificacaoProfissional.class, id);
	}
	
	
	@Override
	public IVerificacaoProfissional salvar(IVerificacaoProfissional item) {
		return this.manager.merge(item);
		 
	}

	@Override
	public void remover(IVerificacaoProfissional item) {
	try {
			
		IVerificacaoProfissional rec = getById(item.getId());
			this.manager.remove(rec);
			this.manager.flush();
			
		} catch (PersistenceException e) {
			
			throw new NegocioException("Item não pode ser excluído.");
			
		}
		
	}
	@Override
	public VerificacaoProfissional getByProjeto(Long idProjeto){
		
		UaiCriteria<VerificacaoProfissional> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, VerificacaoProfissional.class);
				
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
	public List<VerificacaoProfissional>  getByFiltros(VerificacaoProfissional filtro){
		
		UaiCriteria<VerificacaoProfissional> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, VerificacaoProfissional.class);
				
		if(filtro != null && filtro.getProjeto() != null && filtro.getProjeto().getId() != null) {
			easyCriteria.andEquals("projeto",filtro.getProjeto());
		}
		 
		if(filtro != null && filtro.getProfissionalMapas() != null && filtro.getProfissionalMapas().getId() != null) {
			easyCriteria.andEquals("profissionalMapas",filtro.getProfissionalMapas());
		}
		if(filtro != null && filtro.getProfissionalEPI() != null && filtro.getProfissionalEPI().getId() != null) {
			easyCriteria.andEquals("profissionalEPI",filtro.getProfissionalEPI());
		}
		
		if(filtro != null && filtro.getProfissionalEVCTGAL() != null && filtro.getProfissionalEVCTGAL().getId() != null) {
			easyCriteria.andEquals("profissionalEVCTGAL",filtro.getProfissionalEVCTGAL());
		}
		
		
		try {
			return easyCriteria.getResultList();
			
		} catch (NoResultException e) {
			return null;
		}
	}
	


}
