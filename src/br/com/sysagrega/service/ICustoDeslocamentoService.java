package br.com.sysagrega.service;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import br.com.sysagrega.model.ICustoDeslocamento;
import br.com.sysagrega.model.imp.CustoDeslocamento;
import br.com.sysagrega.model.imp.Proposta;

public interface ICustoDeslocamentoService extends Serializable{

	Set<CustoDeslocamento> getAll();
	CustoDeslocamento getById(Long id);
	void salvar(ICustoDeslocamento custo);
	void excluir(ICustoDeslocamento custo);
	public List<CustoDeslocamento> getByPropostaId(Long idProposta);
	public List<CustoDeslocamento> salvarCopiaCustoDeslocamento(List<CustoDeslocamento> lisCustoDeslocamento,
			Proposta proposta);

}