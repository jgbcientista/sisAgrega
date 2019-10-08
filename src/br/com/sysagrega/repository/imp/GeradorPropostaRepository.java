package br.com.sysagrega.repository.imp;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import br.com.sysagrega.model.IGeradorProposta;
import br.com.sysagrega.model.imp.GeradorProposta;
import br.com.sysagrega.repository.IGeradorPropostaRepository;
import br.com.sysagrega.service.imp.NegocioException;

public class GeradorPropostaRepository implements IGeradorPropostaRepository {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;

	@Inject 
	public GeradorPropostaRepository(EntityManager manager) {
		this.manager = manager;
	}

	@Override
	public synchronized GeradorProposta getGeradorPropostaById(Long id) {
		return this.manager.find(GeradorProposta.class, id);
	}
	
	
	@Override
	public IGeradorProposta saveOrMerge(IGeradorProposta geradorProposta) {

		return this.manager.merge(geradorProposta);

	}

	@Override
	public void remover(IGeradorProposta geradorProposta) {

		try {
			
			IGeradorProposta cli = getGeradorPropostaById(geradorProposta.getId());
			this.manager.remove(cli);
			this.manager.flush();
			
		} catch (PersistenceException e) {
			
			throw new NegocioException("numero não pode ser excluído.");
			
		}
		
	}
	
	@Override
	public synchronized GeradorProposta obterMaiorID() {
		try {
			GeradorProposta item = null;
			String sql = "select max(a.id) from agrega.tb_gerador_proposta a";
			
			Query nativeQuery = manager.createNativeQuery(sql);
			
			Object objeto = nativeQuery.getResultList();
			
			if(objeto != null && !objeto.toString().equals("[null]".toString())){
				item = getGeradorPropostaById(Long.parseLong(objeto.toString().replace("]", "").replace("[", "")));	
			}
			return  item;
		 
		} catch (NoResultException e) {
			
			return null;
			
		}
	}
}

/*	@Override
	public List<GeradorProposta> getAllGeradorProposta() {
		
		TypedQuery<GeradorProposta> query = manager.createQuery("from GeradorProposta", GeradorProposta.class);
		query.
		return query.getResultList();
		
	}*/

	


