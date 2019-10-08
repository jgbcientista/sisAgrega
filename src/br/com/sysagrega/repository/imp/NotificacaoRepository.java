package br.com.sysagrega.repository.imp;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;

import br.com.sysagrega.model.INotificacao;
import br.com.sysagrega.model.imp.Notificacao;
import br.com.sysagrega.repository.INotificacaoRepository;
import br.com.sysagrega.service.imp.NegocioException;


public class NotificacaoRepository implements INotificacaoRepository {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	@Inject
	public NotificacaoRepository(EntityManager manager) {
		
		this.manager = manager;
		
	}

	@Override
	public List<Notificacao> getAll() {
		UaiCriteria<Notificacao> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Notificacao.class);
		easyCriteria.andEquals("ativo", true);
		return easyCriteria.getResultList();
	}

	@Override
	public Notificacao getById(Long id) {
		return this.manager.find(Notificacao.class, id);
	}
	

	@Override
	public INotificacao salvar(INotificacao item) {
		return this.manager.merge(item);
		 
	}

	@Override
	public void remover(INotificacao item) {
	try {
			
		INotificacao rec = getById(item.getId());
			this.manager.remove(rec);
			this.manager.flush();
			
		} catch (PersistenceException e) {
			
			throw new NegocioException("Itemo não pode ser excluído.");
			
		}
		
	}
public List<Notificacao> getByFiltros(Notificacao filtro){
		
		UaiCriteria<Notificacao> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Notificacao.class);
		
			if(filtro != null){
			
				if(filtro.getProjeto()!= null) {
					easyCriteria.andEquals("projeto", filtro.getProjeto());
				} 
				if(filtro.getRegistro()!= null) {
					easyCriteria.andEquals("registro", filtro.getRegistro());
				}
		}
		try {
			return easyCriteria.getResultList();
			
		} catch (NoResultException e) {
			return null;
		}
		
	}

}
