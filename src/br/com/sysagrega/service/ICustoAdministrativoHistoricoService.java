package br.com.sysagrega.service;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import br.com.sysagrega.model.ICustoAdministrativoHistorico;
import br.com.sysagrega.model.imp.CustoAdministrativoHistorico;

public interface ICustoAdministrativoHistoricoService extends Serializable{

	Set<CustoAdministrativoHistorico> getAll();
	CustoAdministrativoHistorico getById(Long id);
	void salvar(ICustoAdministrativoHistorico custoHistorico);
	void excluir(ICustoAdministrativoHistorico custoHistorico);
	public List<CustoAdministrativoHistorico> getByPropostaHistoricoId(Long idPropostaHistorico);

}