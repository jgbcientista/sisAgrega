package br.com.sysagrega.service;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import br.com.sysagrega.model.imp.CustoExecucaoHistorico;

public interface ICustoExecucaoHistoricoService extends Serializable{

	Set<CustoExecucaoHistorico> getAllCustos();

	CustoExecucaoHistorico getCustoExecucaoHistoricoById(Long id);
	CustoExecucaoHistorico salvar(CustoExecucaoHistorico custoExecucaoHistorico);
	CustoExecucaoHistorico excluir(CustoExecucaoHistorico custoExecucaoHistorico);
	public List<CustoExecucaoHistorico> getByPropostaHistoricoId(Long idPropostaHistorico);

}