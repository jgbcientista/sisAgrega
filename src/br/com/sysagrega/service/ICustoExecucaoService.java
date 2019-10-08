package br.com.sysagrega.service;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import br.com.sysagrega.model.imp.CustoExecucao;
import br.com.sysagrega.model.imp.Proposta;

public interface ICustoExecucaoService extends Serializable{

	Set<CustoExecucao> getAllCustos();

	CustoExecucao getCustoExecucaoById(Long id);
	CustoExecucao salvar(CustoExecucao custoExecucao);
	CustoExecucao excluir(CustoExecucao custoExecucao);
	public List<CustoExecucao> getByPropostaId(Long idProposta);

	public List<CustoExecucao> salvarCopiaExecucao(List<CustoExecucao> lisCustoExecucao, Proposta proposta);



}