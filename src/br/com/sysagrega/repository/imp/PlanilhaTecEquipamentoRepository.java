package br.com.sysagrega.repository.imp;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.sysagrega.model.IPlanilhaTecEquipamento;
import br.com.sysagrega.model.imp.PlanilhaTecEquipamento;
import br.com.sysagrega.repository.IPlanilhaTecEquipamentoRepository;
import br.com.sysagrega.service.imp.NegocioException;


public class PlanilhaTecEquipamentoRepository implements IPlanilhaTecEquipamentoRepository {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	@Inject
	public PlanilhaTecEquipamentoRepository(EntityManager manager) {
		this.manager = manager;
		
	}

	@Override
	public List<PlanilhaTecEquipamento> getAll() {
		TypedQuery<PlanilhaTecEquipamento> query = manager.createQuery("from PlanilhaTecEquipamento", PlanilhaTecEquipamento.class);
		return query.getResultList();
	}

	@Override
	public PlanilhaTecEquipamento getById(Long id) {
		return this.manager.find(PlanilhaTecEquipamento.class, id);
	}

	@Override
	public PlanilhaTecEquipamento salvar(PlanilhaTecEquipamento item) {
		return this.manager.merge(item);
		 
	}
	
	@Override
	public  List<PlanilhaTecEquipamento> getByPropostaId(Long idProposta) {
		try {
			
			return manager.createQuery("from PlanilhaTecEquipamento t where t.proposta.id = :idProposta", PlanilhaTecEquipamento.class)
						  .setParameter("idProposta", idProposta)
						  .getResultList();
			
		} catch (NoResultException e) {
			
			return null;
			
		}
		
	}

	@Override
	public void remover(PlanilhaTecEquipamento item) {
	
	try {
			
		IPlanilhaTecEquipamento rec = getById(item.getId());
			this.manager.remove(rec);
			this.manager.flush();
			
		} catch (PersistenceException e) {
			
			throw new NegocioException("O Item do equipamento não pode ser excluído.");
			
		}
		
	}
 


}
