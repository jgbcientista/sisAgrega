package br.com.sysagrega.service;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import br.com.sysagrega.model.ICustoSegurancaHistorico;
import br.com.sysagrega.model.imp.CustoSegurancaHistorico;

public interface ICustoSegurancaHistoricoService extends Serializable{

	Set<CustoSegurancaHistorico> getAll();
	CustoSegurancaHistorico getById(Long id);
	void salvar(ICustoSegurancaHistorico custo);
	void excluir(ICustoSegurancaHistorico custo);
	public  List<CustoSegurancaHistorico> getByPropostaHistoricoId(Long idPropostaHistorico);

}