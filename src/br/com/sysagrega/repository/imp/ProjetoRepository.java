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

import br.com.sysagrega.model.IPlanejamentos;
import br.com.sysagrega.model.IProjeto;
import br.com.sysagrega.model.imp.Projeto;
import br.com.sysagrega.repository.IProjetoRepository;
import br.com.sysagrega.service.imp.NegocioException;


public class ProjetoRepository implements IProjetoRepository {
	
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	@Inject
	public ProjetoRepository(EntityManager manager) {
		
		this.manager = manager;
		
	}

	@Override
	public List<Projeto> getAll() {
		UaiCriteria<Projeto> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Projeto.class);
		easyCriteria.andEquals("ativo", true);
		easyCriteria.orderByAsc("ordemServico");
		return easyCriteria.getResultList();
	}

	@Override
	public Projeto getById(Long id) {
		return this.manager.find(Projeto.class, id);
	}
	
	
	public IProjeto getByOrdemServico(Long idOrdemServico, Long idCliente, Long idContrato, Long idProfissional){
		
		UaiCriteria<Projeto> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Projeto.class);
		easyCriteria.andEquals("ativo", true);
		
		if(idOrdemServico != null) {
			easyCriteria.andEquals("ordemServico",idOrdemServico);
		}
		
		if(idCliente != null) {
			easyCriteria.andEquals("cliente",idCliente);
		}
		
		if(idContrato != null) {
			easyCriteria.andEquals("contrato",idContrato);
		}
		
		if(idProfissional != null) {
			easyCriteria.andEquals("profissional",idProfissional);
		}
		
		
		try {
			return easyCriteria.getSingleResult();
			
		} catch (NoResultException e) {
			return null;
		}
	}
	
	
	@Override
	public IProjeto obterMaiorID() {
		try {
			IProjeto item = null;
			String sql = "select max(p.id) From agrega.tb_projeto p";
			
			Query nativeQuery = manager.createNativeQuery(sql);
			
			Object objeto = nativeQuery.getResultList();
			
			if(objeto != null && !objeto.toString().equals("[null]".toString())){
				item = getById(Long.parseLong(objeto.toString().replace("]", "").replace("[", "")));	
			}
			return  item;
		 
		} catch (NoResultException e) {
			
			return null;
		}
		
	}
	
	@Override
	public Projeto getPorID(Long id) {
		if(id== null){
			return null;
		}
		return manager.createQuery("from Projeto p where p.id = :id", Projeto.class)
				  .setParameter("id", id)
				  .getSingleResult();
	}
	
	@Override
	public List<Projeto> obterProjetoByTipoByAnoReferencia(String subTipoProjeto, Long anoReferencia) {
		if(anoReferencia== null || subTipoProjeto == null){
			return null;
		}
		return manager.createQuery("from Projeto p where p.subTipoProjeto = :subTipoProjeto and anoReferencia =:anoReferencia", Projeto.class)
			.setParameter("subTipoProjeto", subTipoProjeto)
			.setParameter("anoReferencia", anoReferencia)
			.getResultList();
	}
	
	@Override
	public Integer obterProjetoPorOS(Long idOS) {
		UaiCriteria<Projeto> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Projeto.class);
		
		if(idOS != null) {
			easyCriteria.andEquals("ordemServico",idOS);
		}
		List<Projeto> resultList = easyCriteria.getResultList();
		if(resultList != null && !resultList.isEmpty()){
			return resultList.size();
		}
		return 0;
		/*return easyCriteria.getSingleResult();
		resultList = manager.createQuery("From Projeto p where p.idOS = :idOS", Projeto.class)
				.setParameter("idOS", idOS)
				.getResultList();
		if(resultList != null && !resultList.isEmpty()){
			return resultList.size();
		}
		return 0;*/
	}
				
		
	@Override
	public IProjeto obterNumeracaoMaiorByTipoByAno(String subTipoProjeto, Long anoReferencia) {
		try {
			IProjeto item = null;
			String sql = "select max(id) From agrega.tb_projeto p where subTipoProjeto='"+subTipoProjeto + "' and anoReferencia ="+anoReferencia;
			
			Query nativeQuery = manager.createNativeQuery(sql);
			
			Object objeto = nativeQuery.getSingleResult();
			
			if(objeto != null && !objeto.toString().equals("[null]".toString())){
				item = getById(Long.parseLong(objeto.toString().replace("]", "").replace("[", "")));	
			}
			return  item;
		 
		} catch (NoResultException e) {
			
			return null;
		}
		
	}
	
	@Override
	public IProjeto getByOSByNAgregaByNCliente(Long idOS, String nAgrega, String nCliente, String subTipoProjeto, Long anoReferencia){
		
		UaiCriteria<Projeto> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Projeto.class);
		easyCriteria.andEquals("ativo", true);
		
		if(idOS != null) {
			easyCriteria.andEquals("ordemServico",idOS);
		}
		
		if(nAgrega != null) {
			easyCriteria.andEquals("numeroProjetoAgrega",nAgrega);
			
		}
		
		if(nCliente != null) {
			easyCriteria.andEquals("numeroProjetoCliente",nCliente);
		}
		
		if(subTipoProjeto != null) {
			easyCriteria.andEquals("subTipoProjeto",subTipoProjeto);
		}
		
		if(anoReferencia != null) {
			easyCriteria.andEquals("anoReferencia",anoReferencia);
		}
		
		try {
			return easyCriteria.getSingleResult();
			
		} catch (NoResultException e) {
			return null;
		}
		
	}
	@Override
	public List<Projeto> getListaByOS(Long idOrdemServico, Long idCliente, Long idProjeto){
		
		
		UaiCriteria<Projeto> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Projeto.class);
		
		easyCriteria.andEquals("ativo", true);
	
		if(idOrdemServico != null) {
			easyCriteria.andEquals("ordemServico",idOrdemServico);
		}
		
		if(idCliente != null) {
			easyCriteria.andEquals("cliente",idCliente);
		}
		
		if(idProjeto != null) {
			easyCriteria.andEquals("id",idProjeto);
		}
		
		easyCriteria.orderByAsc("ordemServico");
		List<Projeto> i = easyCriteria.getResultList();
		try {
			return i;
			
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@Override
	public Integer totalProjetos(Long idOrdemServico){
		try {
			UaiCriteria<Projeto> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Projeto.class);
			easyCriteria.andEquals("ativo", true);
		
			if(idOrdemServico != null) {
				easyCriteria.andEquals("ordemServico",idOrdemServico);
			}
			List<Projeto> result = easyCriteria.getResultList();
			if(result!= null && !result.isEmpty()){
				return result.size();
			}
			
		} catch (NoResultException e) {
			return null;
		}
		return 0;
	}

	@Override
	public IProjeto salvar(IProjeto item) {
		return this.manager.merge(item);
		 
	}

	@Override
	public void remover(IProjeto item) {
	try {
			
		IProjeto rec = getById(item.getId());
			this.manager.remove(rec);
			this.manager.flush();
			
		} catch (PersistenceException e) {
			
			throw new NegocioException("Item não pode ser excluído.");
			
		}
		
	}
	
	@Override
	public Boolean verificaPlanejamentoExistente(IPlanejamentos planejamento){
		UaiCriteria<Projeto> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Projeto.class);
		
		easyCriteria.andEquals("ativo", true);
		if(planejamento != null){
			easyCriteria.andEquals("planejamentos", planejamento);
		}
		
		List<Projeto> listProjeto = easyCriteria.getResultList();
		if(listProjeto != null && !listProjeto.isEmpty()){
			return true;
		}
		
		return false;
	}
	
	public List<Projeto> getByFiltros(Projeto filtro){
		
		UaiCriteria<Projeto> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Projeto.class);
	
		
		easyCriteria.andEquals("ativo", true);
			if(filtro != null){
			
				if(filtro.getContrato()!= null && filtro.getContrato().getId()!= null) {
					easyCriteria.andEquals("contrato",filtro.getContrato());
				}
				if(filtro.getId()!= null ) {
					easyCriteria.andEquals("id",filtro.getId());
				}
				
				if(filtro.getStatus()!= null && filtro.getStatus().getId()!= null) {
					easyCriteria.andEquals("status",filtro.getStatus());
				}
				if(filtro.getCliente()!= null) {
					easyCriteria.andEquals("cliente",filtro.getCliente());
				}
				
				if(filtro.getOrdemServico()!= null && filtro.getOrdemServico().getId()!= null) {
					easyCriteria.andEquals("ordemServico",filtro.getOrdemServico());
				}
				
				if(filtro.getSituacao()!= null && filtro.getSituacao().getId()!= null) {
					easyCriteria.andEquals("situacao",filtro.getSituacao());
				}
			 	
				if(filtro.getTipoProjeto()!= null && !filtro.getTipoProjeto().isEmpty()) {
					easyCriteria.andEquals("tipoProjeto",filtro.getTipoProjeto());
				}
				if(filtro.getSubTipoProjeto() != null && !filtro.getSubTipoProjeto().isEmpty()) {
					easyCriteria.andEquals("subTipoProjeto",filtro.getSubTipoProjeto());
				}
				
				if(filtro.getNumeroProjetoAgrega()!= null && !filtro.getNumeroProjetoAgrega().isEmpty()) {
					easyCriteria.andEquals("numeroProjetoAgrega",filtro.getNumeroProjetoAgrega());
				}
				if(filtro.getAnoReferencia()!= null && filtro.getAnoReferencia()!= 0) {
					easyCriteria.andEquals("anoReferencia",filtro.getAnoReferencia());
				}
				if(filtro.getNumeroProjetoCliente()!= null && !filtro.getNumeroProjetoCliente().isEmpty()) {
					easyCriteria.andEquals("numeroProjetoCliente",filtro.getNumeroProjetoCliente());
				}
				if(filtro.getNomeProjeto() != null && !filtro.getNomeProjeto().isEmpty()) {
					easyCriteria.andEquals("nomeProjeto",filtro.getNomeProjeto());
				}
				
				if(filtro.getDtIniPlanj() != null && filtro.getDtFimPlanj() != null) {
					easyCriteria.andBetween("dtIniPlanj", filtro.getDtIniPlanj(), filtro.getDtFimPlanj());
					//easyCriteria.andBetween("dtFimPlanj", filtro.getDtIniPlanj(), filtro.getDtFimPlanj());
				}
				
				if(filtro.getFiltroDataEntradaoInicial() != null && filtro.getFiltroDataEntradaoFinal() != null) {
					easyCriteria.andBetween("dataEntrada", filtro.getFiltroDataEntradaoInicial(), filtro.getFiltroDataEntradaoFinal());
				}
				
				if(filtro.getMunicipio()!= null && filtro.getMunicipio().getId()!= null) {
					easyCriteria.andEquals("municipio",filtro.getMunicipio());
				}
				if(filtro.getProfissionalGestor()!= null && filtro.getProfissionalGestor().getId()!= null) {
					easyCriteria.andEquals("profissionalGestor",filtro.getProfissionalGestor());
				}
				
				if(filtro.getPlanejamentos()!= null && filtro.getPlanejamentos().getId()!= null) {
					easyCriteria.andEquals("planejamentos",filtro.getPlanejamentos());
				}
				
				if(filtro.getSituacaoPlanejamento()!= null) {
					easyCriteria.andEquals("situacaoPlanejamento",filtro.getSituacaoPlanejamento());
				}
				
		}
		try {
			
			easyCriteria.orderByAsc("ordemServico");
			return easyCriteria.getResultList();
			
		} catch (NoResultException e) {
			return null;
		}
		
		
		
		
	}

	@Override
	public List<Projeto> getByProjetosSemPlanejamento(Projeto filtro){
		
		UaiCriteria<Projeto> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Projeto.class);
	
		
		easyCriteria.andEquals("ativo", true);
			if(filtro != null){
			
				if(filtro.getContrato()!= null && filtro.getContrato().getId()!= null) {
					easyCriteria.andEquals("contrato",filtro.getContrato());
				}
				if(filtro.getId()!= null ) {
					easyCriteria.andEquals("id",filtro.getId());
				}
				
				if(filtro.getStatus()!= null && filtro.getStatus().getId()!= null) {
					easyCriteria.andEquals("status",filtro.getStatus());
				}
				if(filtro.getCliente()!= null) {
					easyCriteria.andEquals("cliente",filtro.getCliente());
				}
				
				if(filtro.getOrdemServico()!= null && filtro.getOrdemServico().getId()!= null) {
					easyCriteria.andEquals("ordemServico",filtro.getOrdemServico());
				}
				
				if(filtro.getSituacao()!= null && filtro.getSituacao().getId()!= null) {
					easyCriteria.andEquals("situacao",filtro.getSituacao());
				}
			 	
				if(filtro.getTipoProjeto()!= null && !filtro.getTipoProjeto().isEmpty()) {
					easyCriteria.andEquals("tipoProjeto",filtro.getTipoProjeto());
				}
				
				if(filtro.getNumeroProjetoAgrega()!= null && !filtro.getNumeroProjetoAgrega().isEmpty()) {
					easyCriteria.andEquals("numeroProjetoAgrega",filtro.getNumeroProjetoAgrega());
				}
				if(filtro.getNumeroProjetoCliente()!= null && !filtro.getNumeroProjetoCliente().isEmpty()) {
					easyCriteria.andEquals("numeroProjetoCliente",filtro.getNumeroProjetoCliente());
				}
				
				if(filtro.getDtIniPlanj() != null && filtro.getDtFimPlanj() != null) {
					easyCriteria.andBetween("dtIniPlanj", filtro.getDtIniPlanj(), filtro.getDtFimPlanj());
					//easyCriteria.andBetween("dtFimPlanj", filtro.getDtIniPlanj(), filtro.getDtFimPlanj());
				}
				
				if(filtro.getFiltroDataEntradaoInicial() != null && filtro.getFiltroDataEntradaoFinal() != null) {
					easyCriteria.andBetween("dataEntrada", filtro.getFiltroDataEntradaoInicial(), filtro.getFiltroDataEntradaoFinal());
				}
				
				if(filtro.getMunicipio()!= null && filtro.getMunicipio().getId()!= null) {
					easyCriteria.andEquals("municipio",filtro.getMunicipio());
				}
				if(filtro.getProfissionalGestor()!= null && filtro.getProfissionalGestor().getId()!= null) {
					easyCriteria.andEquals("profissionalGestor",filtro.getProfissionalGestor());
				}
				
				easyCriteria.andIsNull("planejamentos");
		}
		try {
			easyCriteria.orderByAsc("ordemServico");
			return easyCriteria.getResultList();
			
		} catch (NoResultException e) {
			return null;
		}
				
	}
	
	@Override
	public synchronized List<Projeto> obterProjetoIN(String idOss, String idsContrato, Date dataInicial, Date dataFinal, Projeto filtro) {
		try {
			boolean selecionarContratoObrigatorio = false;
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			String sql = "from Projeto p where p.ativo='true' ";
			String orderBy = "";
			
			if(dataInicial != null && dataInicial!=null){
				sql = sql + " and (p.dataEntrada BETWEEN '" +dateFormat.format(dataInicial).toString()+ "' and '"+dateFormat.format(dataFinal).toString()+"')";
				selecionarContratoObrigatorio = true;
			}
		
			if(filtro.getNomeProjeto() != null && !filtro.getNomeProjeto().isEmpty()){
				sql = sql + " and p.nomeProjeto like '" + filtro.getNomeProjeto().toUpperCase()+"%' ";;
				selecionarContratoObrigatorio = true;
			}
			
			if(filtro.getNumeroProjetoCliente() != null && !filtro.getNumeroProjetoCliente().isEmpty()){
				sql = sql + " and p.numeroProjetoCliente like '" + filtro.getNumeroProjetoCliente().toUpperCase()+"%' ";
				selecionarContratoObrigatorio = true;
			}
			
			if(filtro.getSubTipoProjeto() != null && !filtro.getSubTipoProjeto().isEmpty()){
				sql = sql + " and p.subTipoProjeto like '" + filtro.getSubTipoProjeto().toUpperCase()+"%' ";
				selecionarContratoObrigatorio = true;
			}
			
			if(filtro.getAnoReferencia() != null){
				sql = sql + " and p.anoReferencia="+filtro.getAnoReferencia();
				selecionarContratoObrigatorio = true;
			}
			
			if(filtro.getNumeroProjetoAgrega() != null && !filtro.getNumeroProjetoAgrega().isEmpty()){
				sql = sql + " and p.numeroProjetoAgrega like '" + filtro.getNumeroProjetoAgrega().toUpperCase()+"%' ";;
				selecionarContratoObrigatorio = true;
			}
			
			if(filtro.getSituacaoPlanejamento()!= null){
				sql = sql + " and p.situacaoPlanejamento = " +filtro.getSituacaoPlanejamento().toString();
				selecionarContratoObrigatorio = true;
			}
			
			if(idsContrato!=null && !idsContrato.isEmpty()){
				sql = sql + " and p.ordemServico.contrato.id  in (" + idsContrato+ ") ";
				orderBy = " order by p.ordemServico.numeroOS, numeroProjetoAgrega ";
			}
				
			if(idOss != null && !idOss.isEmpty()){
				sql = sql + " and p.ordemServico.id in (" + idOss+ ") ";
			}
			
			if(selecionarContratoObrigatorio || (idsContrato!=null && !idsContrato.isEmpty())){
				sql = sql + orderBy;
				return manager.createQuery(sql, Projeto.class).getResultList();
			}else{
				throw new NegocioException("Preencha um filtro ou selecione um contrato.");
			}
		} catch (NoResultException e) {
			throw new NegocioException("Erro ao pesquisar projeto. Contacte o administrador.");
		}
	}
	
	@Override
	public List<Projeto> obterProjetoSemPlanejamento(String idPlanejamentos, Date dataInicial, Date dataFinal){
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String sql = new String();
		String sqlData = null;
		
		if(dataInicial != null){
			sqlData = "and (p.dtIniPlanj BETWEEN '" +dateFormat.format(dataInicial)+ "' and '"+dateFormat.format(dataFinal)+"' or p.dtFimPlanj BETWEEN '" +dateFormat.format(dataInicial)+ "' and '"+dateFormat.format(dataFinal)+"')";
		}
			
		if(idPlanejamentos != null && !idPlanejamentos.isEmpty()){
			sql = "from Projeto p where p.ativo='true' and p.planejamentos.id  in (" + idPlanejamentos+ ") ";
		}else{
			sql = "from Projeto p where p.ativo='true'";
		}
		
		if(sqlData != null){
			sql = sql + sqlData;
		} 
		 
		sql = sql +" order by planejamentos, ordemServico.numeroOS, numeroProjetoAgrega";
		return manager.createQuery(sql, Projeto.class).getResultList();
	}

}
