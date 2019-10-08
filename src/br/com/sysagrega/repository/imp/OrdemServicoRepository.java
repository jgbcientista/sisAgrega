package br.com.sysagrega.repository.imp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;

import br.com.sysagrega.model.IOrdemServico;
import br.com.sysagrega.model.imp.OrdemServico;
import br.com.sysagrega.repository.IOrdemServicoRepository;
import br.com.sysagrega.service.imp.NegocioException;


public class OrdemServicoRepository implements IOrdemServicoRepository {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	@Inject
	public OrdemServicoRepository(EntityManager manager) {
		
		this.manager = manager;
		
	}

	@Override
	public synchronized List<OrdemServico> getAll() {
		UaiCriteria<OrdemServico> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, OrdemServico.class);
		easyCriteria.andEquals("ativo", true);
		easyCriteria.orderByAsc("numeroOS");
		return easyCriteria.getResultList();
	}

	@Override
	public synchronized  OrdemServico getById(Long id) {
		return this.manager.find(OrdemServico.class, id);
	}
	
	@Override
	public synchronized  OrdemServico getPorID(Long id) {
		if(id== null){
			return null;
		}
		return manager.createQuery("from OrdemServico p where p.id = :id", OrdemServico.class)
				  .setParameter("id", id)
				  .getSingleResult();
		
	}
	
	@Override
	public synchronized  List<OrdemServico> getOrdemServicoByFilter(Long cliente, Long numeroOS) {
		
		UaiCriteria<OrdemServico> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, OrdemServico.class);
		
		if(cliente != null) {
			easyCriteria.andEquals("cliente",cliente);
		}
		
		if(numeroOS != null) {
			easyCriteria.andEquals("numero", numeroOS);
		}
		
		easyCriteria.andEquals("ativo", true);
		easyCriteria.orderByAsc("numeroOS");
		try {
			
			return easyCriteria.getResultList();
			
		} catch (NoResultException e) {
			
			return null;
			
		}
		
	}
	
	
	
	@Override
	public synchronized  OrdemServico obterMaiorID(Long idContrato) {
		try {
			OrdemServico item = null;
			Object objeto = null;
			
			if(idContrato != null){

				String sql = "select max(a.id) from agrega.tb_ordem_servico a where a.contrato_id="+idContrato;
				Query nativeQuery = manager.createNativeQuery(sql);
			
				objeto = nativeQuery.getResultList();
		}
			if(objeto != null && !objeto.toString().equals("[null]".toString())){
				item = getById(Long.parseLong(objeto.toString().replace("]", "").replace("[", "")));	
			}
			return  item;
		 
		} catch (NoResultException e) {
			
			return null;
			
		}
		
	}
	
	@Override
	public synchronized  Integer obterUltimaNrOS(Long idContrato) {
		try {
			Integer item = null;
			String sql = "select max(a.contadorNrOS) from agrega.tb_ordem_servico a where a.contrato_id="+idContrato;
			
			Query nativeQuery = manager.createNativeQuery(sql);
			
			Object objeto = nativeQuery.getResultList();
			
			if(objeto != null && !objeto.toString().equals("[null]".toString())){
				item = Integer.parseInt(objeto.toString().replace("]", "").replace("[", ""));	
			}
			return  item;
		 
		} catch (NoResultException e) {
			
			return null;
			
		}
		
	}

	@Override
	public synchronized  IOrdemServico salvar(IOrdemServico item) {
		return this.manager.merge(item);
		 
	}

	@Override
	public void remover(IOrdemServico item) {
	try {
			
		IOrdemServico rec = getById(item.getId());
			this.manager.remove(rec);
			this.manager.flush();
			
		} catch (PersistenceException e) {
			
			throw new NegocioException("Itemo não pode ser excluído.");
			
		}
		
	}
 

public synchronized List<OrdemServico> getByFiltros(OrdemServico filtro){
		
		UaiCriteria<OrdemServico> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, OrdemServico.class);
		
		easyCriteria.andEquals("ativo", true);
			if(filtro != null){
				
				
				if(filtro.getId() !=null ) {
					easyCriteria.andEquals("id",filtro.getId());
				}
			
				if(filtro.getContrato()!= null && filtro.getContrato().getId()!= null) {
					easyCriteria.andEquals("contrato",filtro.getContrato());
				}
				
				if(filtro.getCliente()!= null && filtro.getCliente().getId()!= null) {
					easyCriteria.andEquals("cliente",filtro.getCliente());
				}
				
				if(filtro.getDataFiltroInicial()!= null && filtro.getDataFiltroFinal()!= null) {
					easyCriteria.andBetween("dataInclusao", filtro.getDataFiltroInicial(), filtro.getDataFiltroFinal());
				}
				
			}
			easyCriteria.orderByAsc("numeroOS");
		try {
			return easyCriteria.getResultList();
			
		} catch (NoResultException e) {
			return null;
		}
		
	}


public synchronized  OrdemServico obterByNumeroOs(String numeroOs, Long idContrato){
		
		UaiCriteria<OrdemServico> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, OrdemServico.class);
		
		easyCriteria.andEquals("ativo", true);
				
		if(numeroOs !=null ) {
			easyCriteria.andEquals("numeroOS",numeroOs);
		}
		
		if(idContrato!= null ) {
			easyCriteria.andEquals("contrato",idContrato);
		}
		
		try {
			return easyCriteria.getSingleResult();
			
		} catch (NoResultException e) {
			return null;
		}
		
	}

@Override
public synchronized  List<OrdemServico> obterIN(String idsOs, String idsContrato, Date dataInicial, Date dataFinal) {
	//SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	String sql = new String();
	//String sqlData = null;
	
	/*if(dataInicial != null){
		sqlData = " and (p.dataInclusao BETWEEN '" +dateFormat.format(dataInicial)+ "' and '"+dateFormat.format(dataFinal)+"') ";
	}*/
	
	if(idsContrato != null && !idsContrato.isEmpty()){
		sql = " from OrdemServico p where p.contrato.id in (" + idsContrato+ ")";
	}
	if(idsOs != null && !idsOs.isEmpty()){
		sql = sql + " and p.id in ("+idsOs+")";
	}
	/*if(sqlData != null){
		sql = sql + sqlData;
	}*/
	
	sql = sql +" order by p.numeroOS";
	return manager.createQuery(sql, OrdemServico.class).getResultList();
	
}



}
