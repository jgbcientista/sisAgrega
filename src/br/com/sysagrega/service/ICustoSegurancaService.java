package br.com.sysagrega.service;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import br.com.sysagrega.model.ICustoSeguranca;
import br.com.sysagrega.model.imp.CustoSeguranca;
import br.com.sysagrega.model.imp.Proposta;

public interface ICustoSegurancaService extends Serializable{

	Set<CustoSeguranca> getAll();
	CustoSeguranca getById(Long id);
	void salvar(ICustoSeguranca custo);
	void excluir(ICustoSeguranca custo);
	public  List<CustoSeguranca> getByPropostaId(Long idProposta);
	List<CustoSeguranca> salvarCopiaCustoSeguranca(List<CustoSeguranca> lisCustoSeguranca, Proposta proposta);

}