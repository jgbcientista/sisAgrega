package br.com.sysagrega.repository.imp;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;

import br.com.sysagrega.model.IAnexo;
import br.com.sysagrega.model.imp.Anexo;
import br.com.sysagrega.repository.IAnexoRepository;
import br.com.sysagrega.service.imp.NegocioException;

public class AnexoRepository implements IAnexoRepository {
	
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	@Inject
	public AnexoRepository(EntityManager manager) {
		this.manager = manager;
	}

	@Override
	public List<Anexo> getAll() {
		UaiCriteria<Anexo> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Anexo.class);
		return easyCriteria.getResultList();
	}

	@Override
	public Anexo getById(Long id) {
		return this.manager.find(Anexo.class, id);
	}

	@Override
	public IAnexo saveOrMerge(IAnexo item) {
		return this.manager.merge(item);
		 
	}

	@Override
	public void remover(IAnexo item) {
	try {
			
		IAnexo rec = getById(item.getId());
			this.manager.remove(rec);
			this.manager.flush();
			
		} catch (PersistenceException e) {
			throw new NegocioException("Item não pode ser excluído.");
			
		}
		
	}

	@Override
	public List<Anexo> getByIdByTipo(Long tipo, Long idEntidade, String descricao) {
		UaiCriteria<Anexo> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Anexo.class);
		
		if(tipo != null) {
			easyCriteria.andEquals("tipo",tipo);
		}
		
		if(idEntidade != null) {
			easyCriteria.andEquals("idEntidade",idEntidade);
		}
		
		if(descricao != null) {
			easyCriteria.andEquals("descricao",descricao);
		}
		
		try {
			return easyCriteria.getResultList();
	
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@Override
	public Boolean verificaSeAnexoJaExite(Long tipo, Long idEntidade, String descricao) {
		UaiCriteria<Anexo> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Anexo.class);
		
		easyCriteria.andEquals("tipo",tipo);
		easyCriteria.andEquals("idEntidade",idEntidade);
		easyCriteria.andEquals("descricao",descricao);
		
		List<Anexo> lisAnexo = easyCriteria.getResultList();
		if(lisAnexo != null && !lisAnexo.isEmpty()){
			return true;
		}
		return false;
	}
}
