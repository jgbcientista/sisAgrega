package br.com.sysagrega.service;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import br.com.sysagrega.model.ICustoDeslocamentoHistorico;
import br.com.sysagrega.model.imp.CustoDeslocamentoHistorico;

public interface ICustoDeslocamentoHistoricoService extends Serializable{

	Set<CustoDeslocamentoHistorico> getAll();
	CustoDeslocamentoHistorico getById(Long id);
	void salvar(ICustoDeslocamentoHistorico custo);
	void excluir(ICustoDeslocamentoHistorico custo);
	public List<CustoDeslocamentoHistorico> getByPropostaHistoricoId(Long idPropostaHistorico);

}