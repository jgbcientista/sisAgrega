package br.com.sysagrega.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import br.com.sysagrega.model.IOrdemServico;
import br.com.sysagrega.model.imp.OrdemServico;

public interface IOrdemServicoService extends Serializable{

	public void remover(IOrdemServico item);
	
	IOrdemServico salvar(IOrdemServico item);
	
	public IOrdemServico atualizar(IOrdemServico item);

	IOrdemServico getById(Long id);
	
	OrdemServico getPorID(Long id);
	
	List<OrdemServico> getAll();
	
	List<OrdemServico> getOrdemServicoByFilter(Long cliente, Long numeroOS);
	
	List<OrdemServico> getByFiltros(OrdemServico filtro);

	Integer obterUltimaNrOS(Long idContrato);
	
	OrdemServico obterByNumeroOs(String numeroOs,Long idContrato);

	OrdemServico obterMaiorID(Long idContrato);
	
	List<OrdemServico> obterIN(String idsOs, String idsContrato, Date dataInicial, Date dataFinal);

}