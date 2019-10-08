package br.com.sysagrega.service;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import br.com.sysagrega.model.ICustoOperacional;
import br.com.sysagrega.model.imp.CustoOperacional;
import br.com.sysagrega.model.imp.Proposta;

public interface ICustoOperacionalService extends Serializable{

	Set<CustoOperacional> getAll();
	CustoOperacional getById(Long id);
	void salvar(ICustoOperacional custo);
	void excluir(ICustoOperacional custo);
	public List<CustoOperacional> getByPropostaId(Long idProposta);
	public List<CustoOperacional> salvarCopiaCustoOperacional(List<CustoOperacional> lisCustoOperacioanl, Proposta proposta);

}