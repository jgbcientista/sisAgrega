package br.com.sysagrega.repository.imp;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;

import br.com.sysagrega.model.IArea;
import br.com.sysagrega.model.imp.Area;
import br.com.sysagrega.repository.IAreaRepository;
import br.com.sysagrega.service.imp.NegocioException;


public class AreaRepository implements IAreaRepository {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	@Inject
	public AreaRepository(EntityManager manager) {
		
		this.manager = manager;
		
	}

	@Override
	public List<Area> getAllAreas() {
		UaiCriteria<Area> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Area.class);
		easyCriteria.andEquals("ativo", true);
		
		return easyCriteria.getResultList();
	}

	@Override
	public Area getAreaById(Long id) {
		return this.manager.find(Area.class, id);
	}

	@Override
	public IArea salvar(IArea iTipoServico) {
		return this.manager.merge(iTipoServico);
		 
	}

	@Override
	public void remover(IArea area) {
	try {
			
		IArea rec = getAreaById(area.getId());
			this.manager.remove(rec);
			this.manager.flush();
			
		} catch (PersistenceException e) {
			
			throw new NegocioException("tipo Servico não pode ser excluído.");
			
		}
		
	}
 


}
