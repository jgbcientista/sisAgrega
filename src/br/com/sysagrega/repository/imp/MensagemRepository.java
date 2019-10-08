package br.com.sysagrega.repository.imp;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;

import br.com.sysagrega.model.IMensagem;
import br.com.sysagrega.model.imp.Mensagem;
import br.com.sysagrega.repository.IMensagemRepository;
import br.com.sysagrega.service.imp.NegocioException;

public class MensagemRepository implements IMensagemRepository {
	
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	@Inject
	public MensagemRepository(EntityManager manager) {
		this.manager = manager;
		
	}

	@Override
	public List<Mensagem> getAll() {
		UaiCriteria<Mensagem> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Mensagem.class);
		return easyCriteria.getResultList();
	}

	@Override
	public Mensagem getById(Long id) {
		return this.manager.find(Mensagem.class, id);
	}

	@Override
	public IMensagem salvar(IMensagem item) {
		return this.manager.merge(item);
		 
	}

	@Override
	public void remover(IMensagem recurso) {
	
	try {
			
		IMensagem rec = getById(recurso.getId());
			this.manager.remove(rec);
			this.manager.flush();
			
		} catch (PersistenceException e) {
			
			throw new NegocioException("Mensagem não pode ser excluído.");
			
		}
		
	}

	@Override
	public List<Mensagem> getByFilter(Mensagem filtro) {
		String sql = new String();
		
		if(filtro != null && filtro.getProfissional() !=null){
			sql = "from Mensagem m where m.profissional.id in ('" + filtro.getProfissional().getId()+ "') and lida='false' ";
			sql = sql +" order by m.titulo ";
		try {
			
			return manager.createQuery(sql, Mensagem.class).getResultList();
			
		} catch (NoResultException e) {
			e.getMessage();
		}
		}
		return null;
	}
	
	@Override
	public List<Mensagem> obterByProjetoByProfissional(Long idProjeto, Long idProfissional, Long idTipo){
		
		String sql = new String();
		String sqlProfissional = new String();
		try {
		sql = "from Mensagem m where m.projeto.id  in (" + idProjeto+ ") and tipo="+idTipo;
	 
		sqlProfissional = " and m.profissional.id =" +idProfissional;
		sql = sql + sqlProfissional;
		sql = sql +" order by m.titulo ";
		
		return manager.createQuery(sql, Mensagem.class).getResultList();
			
		} catch (NoResultException e) {
			return null;
		}

		
	}
	
}
