package br.com.sysagrega.repository.imp;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;

import br.com.sysagrega.model.IProjetoOrdemServico;
import br.com.sysagrega.model.imp.ProjetoOrdemServico;
import br.com.sysagrega.repository.IProjetoOrdemServicoRepository;
import br.com.sysagrega.service.imp.NegocioException;


public class ProjetoOrdemServicoRepository implements IProjetoOrdemServicoRepository {
	
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	@Inject
	public ProjetoOrdemServicoRepository(EntityManager manager) {
		this.manager = manager;
		
	}

	@Override
	public synchronized List<ProjetoOrdemServico> getAll() {
		UaiCriteria<ProjetoOrdemServico> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, ProjetoOrdemServico.class);
		easyCriteria.andEquals("ativo", true);
		return easyCriteria.getResultList();
	}
	
	@Override
	public synchronized List<ProjetoOrdemServico> getByProjetoByOS(Long idProjeto, Long idOrdemServico){
		UaiCriteria<ProjetoOrdemServico> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, ProjetoOrdemServico.class);
		easyCriteria.andEquals("ativo", true);
		
		if(idProjeto != null) {
			easyCriteria.andEquals("projeto",idProjeto);
		}
		
		if(idOrdemServico != null) {
			easyCriteria.andEquals("ordemServico",idOrdemServico);
		}
		
		return easyCriteria.getResultList();
	}

	@Override
	public ProjetoOrdemServico getById(Long id) {
		return this.manager.find(ProjetoOrdemServico.class, id);
	}

	@Override
	public  synchronized IProjetoOrdemServico salvar(IProjetoOrdemServico item) {
		return this.manager.merge(item);
		 
	}

	@Override
	public synchronized  void remover(IProjetoOrdemServico item) {
	try {
			
		IProjetoOrdemServico rec = getById(item.getId());
			this.manager.remove(rec);
			this.manager.flush();
			
		} catch (PersistenceException e) {
			
			throw new NegocioException("Item não pode ser excluído.");
			
		}
		
	}
 


}
