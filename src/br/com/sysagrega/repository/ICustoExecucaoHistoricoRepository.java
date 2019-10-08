package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import br.com.sysagrega.model.imp.CustoExecucaoHistorico;

public interface ICustoExecucaoHistoricoRepository extends Serializable{

	Set<CustoExecucaoHistorico> getAllCustos();

	CustoExecucaoHistorico getCustoExecucaoHistoricoById(Long id);
	
	CustoExecucaoHistorico saveOrMerge(CustoExecucaoHistorico iCustoExecucaoHistorico);

	CustoExecucaoHistorico remover(CustoExecucaoHistorico iCustoExecucaoHistorico);

	List<CustoExecucaoHistorico> getByPropostaHistoricoId(Long idPropostaHistorico);

}