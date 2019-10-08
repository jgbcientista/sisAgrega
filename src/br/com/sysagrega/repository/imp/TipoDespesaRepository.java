package br.com.sysagrega.repository.imp;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.sysagrega.model.ITipoDespesa;
import br.com.sysagrega.model.imp.TipoDespesa;
import br.com.sysagrega.repository.ITipoDespesaRepository;
import br.com.sysagrega.service.imp.NegocioException;


public class TipoDespesaRepository implements ITipoDespesaRepository {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	@Inject
	public TipoDespesaRepository(EntityManager manager) {
		
		this.manager = manager;
		
	}

	@Override
	public List<TipoDespesa> getAllTipoDespesas() {
		TypedQuery<TipoDespesa> query = manager.createQuery("from TipoDespesa", TipoDespesa.class);
		return query.getResultList();
	}

	@Override
	public TipoDespesa getTipoDespesaById(Long id) {
		return this.manager.find(TipoDespesa.class, id);
	}

	@Override
	public ITipoDespesa salvar(ITipoDespesa tipo) {
		return this.manager.merge(tipo);
		 
	}

	@Override
	public void remover(ITipoDespesa tipo) {
	try {
			
		ITipoDespesa rec = getTipoDespesaById(tipo.getId());
			this.manager.remove(rec);
			this.manager.flush();
			
		} catch (PersistenceException e) {
			
			throw new NegocioException("tipo Servico não pode ser excluído.");
			
		}
		
	}
 


}
