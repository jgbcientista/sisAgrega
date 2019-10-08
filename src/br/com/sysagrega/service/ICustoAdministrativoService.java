package br.com.sysagrega.service;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import br.com.sysagrega.model.ICustoAdministrativo;
import br.com.sysagrega.model.imp.CustoAdministrativo;
import br.com.sysagrega.model.imp.Proposta;

public interface ICustoAdministrativoService extends Serializable{

	Set<CustoAdministrativo> getAll();
	CustoAdministrativo getById(Long id);
	void salvar(ICustoAdministrativo custo);
	void excluir(ICustoAdministrativo custo);
	public List<CustoAdministrativo> getByPropostaId(Long idProposta);
	public List<CustoAdministrativo> salvarCopiaCustoAdm(List<CustoAdministrativo> lisCustoAdministrativo, Proposta proposta);

}