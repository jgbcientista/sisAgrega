package br.com.sysagrega.repository.imp;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.sysagrega.model.ITipoServico;
import br.com.sysagrega.model.imp.TipoServico;
import br.com.sysagrega.repository.ITipoServicoRepository;
import br.com.sysagrega.service.imp.NegocioException;


public class TipoServicoRepository implements ITipoServicoRepository {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	@Inject
	public TipoServicoRepository(EntityManager manager) {
		
		this.manager = manager;
		
	}

	@Override
	public List<TipoServico> getAllTipoServicos() {
		TypedQuery<TipoServico> query = manager.createQuery("from TipoServico", TipoServico.class);
		return query.getResultList();
	}

	@Override
	public TipoServico getTipoServicoById(Long id) {
		return this.manager.find(TipoServico.class, id);
	}

	@Override
	public ITipoServico salvar(ITipoServico iTipoServico) {
		return this.manager.merge(iTipoServico);
		 
	}

	@Override
	public void remover(ITipoServico tipoServico) {
	
	try {
			
		ITipoServico rec = getTipoServicoById(tipoServico.getId());
			this.manager.remove(rec);
			this.manager.flush();
			
		} catch (PersistenceException e) {
			
			throw new NegocioException("tipo Servico não pode ser excluído.");
			
		}
		
	}
 


}
