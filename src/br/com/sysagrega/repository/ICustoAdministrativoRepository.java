package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import br.com.sysagrega.model.ICustoAdministrativo;
import br.com.sysagrega.model.imp.CustoAdministrativo;

public interface ICustoAdministrativoRepository extends Serializable{

	Set<CustoAdministrativo> getAll();

	CustoAdministrativo getById(Long id);
	
	ICustoAdministrativo saveOrMerge(ICustoAdministrativo custo);

	void remover(ICustoAdministrativo custo);

	List<CustoAdministrativo> getByPropostaId(Long idProposta);

}