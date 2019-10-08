package br.com.sysagrega.service;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import br.com.sysagrega.model.ICustoOperacionalHistorico;
import br.com.sysagrega.model.imp.CustoOperacionalHistorico;

public interface ICustoOperacionalHistoricoService extends Serializable{

	Set<CustoOperacionalHistorico> getAll();
	CustoOperacionalHistorico getById(Long id);
	void salvar(ICustoOperacionalHistorico custo);
	void excluir(ICustoOperacionalHistorico custo);
	public List<CustoOperacionalHistorico> getByPropostaHistoricoId(Long idPropostaHistorico);

}