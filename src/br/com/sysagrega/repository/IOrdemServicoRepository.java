package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import br.com.sysagrega.model.IOrdemServico;
import br.com.sysagrega.model.imp.OrdemServico;

public interface IOrdemServicoRepository extends Serializable{

	List<OrdemServico> getAll();

	OrdemServico getById(Long id);
	
	IOrdemServico salvar(IOrdemServico item);

	void remover(IOrdemServico item);


	List<OrdemServico> getOrdemServicoByFilter(Long cliente, Long numeroOS);

	Integer obterUltimaNrOS(Long idContrato);
	
	OrdemServico obterByNumeroOs(String numeroOs, Long idContrato);

	OrdemServico obterMaiorID(Long idContrato);

	OrdemServico getPorID(Long id);


	List<OrdemServico> obterIN(String idsOs, String idsContrato, Date dataInicial, Date dataFinal);
	
	
	
}