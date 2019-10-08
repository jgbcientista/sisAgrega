package br.com.sysagrega.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import br.com.sysagrega.model.IPlanejamentos;
import br.com.sysagrega.model.IProjeto;
import br.com.sysagrega.model.imp.Projeto;

public interface IProjetoService extends Serializable{

	public void remover(IProjeto item);
	
	IProjeto salvar(IProjeto item);
	
	public IProjeto atualizar(IProjeto item);

	IProjeto getById(Long id);
	
	Projeto getPorID(Long id);
	
	public IProjeto getByOrdemServico(Long idOrdemServico, Long idCliente, Long idContrato, Long idProfissional);

	public List<Projeto> getListaByOS(Long idOrdemServico, Long idCliente, Long idProjeto);
	
	List<Projeto> getAll();
	
	public List<Projeto> getByFiltros(Projeto filtro);
	
	public IProjeto obterMaiorID();

	public void salvarProjeto(IProjeto item);

	public List<Projeto> getByProjetosSemPlanejamento(Projeto filtro);
	
	public List<Projeto> obterProjetoIN(String idOss, String idsContrato, Date dataInicial, Date dataFinal, Projeto filtro);
	
	public List<Projeto> obterProjetoSemPlanejamento(String idPlanejamentos, Date dataInicial, Date dataFinal);
	
	public List<Projeto> obterProjetoByTipoByAnoReferencia(String subTipoProjeto, Long anoReferencia);
	
	IProjeto obterNumeracaoMaiorByTipoByAno(String subTipoProjeto, Long anoReferencia);

	IProjeto getByOSByNAgregaByNCliente(Long idOS, String nAgrega, String nCliente, String subTipoProjeto,Long anoReferencia);

	Boolean verificaPlanejamentoExistente(IPlanejamentos planejamento);

	public Integer obterProjetoPorOS(Long idOS);

	public Integer totalProjetos(Long idOrdemServico);

}