package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import br.com.sysagrega.model.imp.CustoExecucao;

public interface ICustoExecucaoRepository extends Serializable{

	Set<CustoExecucao> getAllCustos();

	CustoExecucao getCustoExecucaoById(Long id);
	
	CustoExecucao saveOrMerge(CustoExecucao iCustoExecucao);

	CustoExecucao remover(CustoExecucao iCustoExecucao);

	List<CustoExecucao> getByPropostaId(Long idProposta);

}