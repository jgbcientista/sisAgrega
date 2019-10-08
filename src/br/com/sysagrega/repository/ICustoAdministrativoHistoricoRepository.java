package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import br.com.sysagrega.model.ICustoAdministrativoHistorico;
import br.com.sysagrega.model.imp.CustoAdministrativoHistorico;

public interface ICustoAdministrativoHistoricoRepository extends Serializable{

	Set<CustoAdministrativoHistorico> getAll();

	CustoAdministrativoHistorico getById(Long id);
	
	ICustoAdministrativoHistorico saveOrMerge(ICustoAdministrativoHistorico custoHistorico);

	void remover(ICustoAdministrativoHistorico custoHistorico);

	List<CustoAdministrativoHistorico> getByPropostaId(Long idPropostaHistorico);

}