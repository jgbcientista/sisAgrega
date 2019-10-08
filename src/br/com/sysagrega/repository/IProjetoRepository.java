package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import br.com.sysagrega.model.IPlanejamentos;
import br.com.sysagrega.model.IProjeto;
import br.com.sysagrega.model.imp.Projeto;

public interface IProjetoRepository extends Serializable{

	List<Projeto> getAll();

	Projeto getById(Long id);
	
	IProjeto salvar(IProjeto item);

	void remover(IProjeto item);
	
	public IProjeto getByOrdemServico(Long idOrdemServico, Long idCliente, Long idContrato, Long idProfissional);
	
	public List<Projeto> getListaByOS(Long idOrdemServico, Long idCliente, Long idProjeto);
	
	public IProjeto obterMaiorID();

	Projeto getPorID(Long id);

	public List<Projeto> getByProjetosSemPlanejamento(Projeto filtro);

	public List<Projeto> obterProjetoSemPlanejamento(String idPlanejamentos, Date dataInicial, Date dataFinal);

	List<Projeto> obterProjetoByTipoByAnoReferencia(String subTipoProjeto, Long anoReferencia);

	IProjeto obterNumeracaoMaiorByTipoByAno(String subTipoProjeto, Long anoReferencia);

	IProjeto getByOSByNAgregaByNCliente(Long idOS, String nAgrega, String nCliente, String subTipoProjeto,Long anoReferencia);

	Boolean verificaPlanejamentoExistente(IPlanejamentos planejamento);

	List<Projeto> obterProjetoIN(String idOss, String idsContrato, Date dataInicial, Date dataFinal, Projeto filtro);

	public Integer obterProjetoPorOS(Long idOS);

	public Integer totalProjetos(Long idOrdemServico);
	
}