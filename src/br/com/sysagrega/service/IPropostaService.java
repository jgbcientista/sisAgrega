package br.com.sysagrega.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import br.com.sysagrega.controller.vo.RelatorioClienteVO;
import br.com.sysagrega.model.IProposta;
import br.com.sysagrega.model.imp.Proposta;
import br.com.sysagrega.model.imp.PropostaHistorico;

public interface IPropostaService extends Serializable{

	Proposta salvar(Proposta proposta);

	List<Proposta> getAllPropostas();

	IProposta atualizarProposta(Proposta proposta, String status);

	void excluirProposta(Proposta proposta);

	List<Proposta> getPropostaByFilter(String filtroNumeroProposta, Long filtroCliente, Character filtroStatus, Date filtroDataInicial, Date filtroDataFinal);

	PropostaHistorico salvarHistorico(PropostaHistorico propostaHistorico, String numeroProposta);

	List<PropostaHistorico> getPropostaHistoricoByFilter(IProposta propostaId);
	
	public Proposta preSalvar(Proposta proposta);

	public Proposta getPropostaById(Long id);

	public List<Proposta> getObterListPropostaRelatorioByFilter(RelatorioClienteVO filtro);

}