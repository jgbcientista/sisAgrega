package br.com.sysagrega.repository.imp;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.sysagrega.model.ICustoPlanilhaTecnica;
import br.com.sysagrega.model.imp.CustoPlanilhaTecnica;
import br.com.sysagrega.repository.ICustoPlanilhaTecnicaRepository;
import br.com.sysagrega.service.imp.NegocioException;


public class CustoPlanilhaTecnicaRepository implements ICustoPlanilhaTecnicaRepository {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	@Inject
	public CustoPlanilhaTecnicaRepository(EntityManager manager) {
		this.manager = manager;
		
	}

	@Override
	public List<CustoPlanilhaTecnica> getAll() {
		TypedQuery<CustoPlanilhaTecnica> query = manager.createQuery("from CustoPlanilhaTecnica", CustoPlanilhaTecnica.class);
		return query.getResultList();
	}

	@Override
	public CustoPlanilhaTecnica getById(Long id) {
		return this.manager.find(CustoPlanilhaTecnica.class, id);
	}

	@Override
	public CustoPlanilhaTecnica salvar(CustoPlanilhaTecnica item) {
		return this.manager.merge(item);
		 
	}
	
	@Override
	public  List<CustoPlanilhaTecnica> getByPropostaId(Long idProposta) {
		try {
			
			return manager.createQuery("from CustoPlanilhaTecnica t where t.proposta.id = :idProposta", CustoPlanilhaTecnica.class)
						  .setParameter("idProposta", idProposta)
						  .getResultList();
			
		} catch (NoResultException e) {
			
			return null;
			
		}
		
	}

	@Override
	public void remover(CustoPlanilhaTecnica item) {
	
	try {
			
		ICustoPlanilhaTecnica rec = getById(item.getId());
			this.manager.remove(rec);
			this.manager.flush();
			
		} catch (PersistenceException e) {
			
			throw new NegocioException("CustoPlanilhaTecnica Item não pode ser excluído.");
			
		}
		
	}
 


}
