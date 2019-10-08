package br.com.sysagrega.repository.imp;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.sysagrega.model.ITipoItemProposta;
import br.com.sysagrega.model.imp.TipoItemProposta;
import br.com.sysagrega.repository.ITipoItemPropostaRepository;
import br.com.sysagrega.service.imp.NegocioException;


public class TipoItemPropostaRepository implements ITipoItemPropostaRepository {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	@Inject
	public TipoItemPropostaRepository(EntityManager manager) {
		this.manager = manager;
		
	}

	@Override
	public List<TipoItemProposta> getAll() {
		TypedQuery<TipoItemProposta> query = manager.createQuery("from TipoItemProposta t where t.ativo='true' ", TipoItemProposta.class);
		return query.getResultList();
	}

	@Override
	public  TipoItemProposta getById(Long id) {
		return this.manager.find(TipoItemProposta.class, id);
	}

	@Override
	public TipoItemProposta salvar(TipoItemProposta tipo) {
		return this.manager.merge(tipo);
		 
	}
	
	@Override
	public  List<TipoItemProposta> getByPropostaId(Long idProposta, Long idTipoProposta) {
		try {
			
			return manager.createQuery("from TipoItemProposta t where  t.proposta.id = :idProposta and t.tipoProposta=:idTipoProposta", TipoItemProposta.class)
					.setParameter("idProposta", idProposta)
					.setParameter("idTipoProposta", idTipoProposta)
						  .getResultList();
			
		} catch (NoResultException e) {
			
			return null;
			
		}
		
	}

	@Override
	public void remover(ITipoItemProposta tipo) {
	
	try {
			
		ITipoItemProposta rec = getById(tipo.getId());
			this.manager.remove(rec);
			this.manager.flush();
			
		} catch (PersistenceException e) {
			
			throw new NegocioException("Tipo Item não pode ser excluído.");
			
		}
		
	}
 


}
