package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import br.com.sysagrega.model.ICustoOperacional;
import br.com.sysagrega.model.imp.CustoOperacional;

public interface ICustoOperacionalRepository extends Serializable{

	Set<CustoOperacional> getAll();

	CustoOperacional getById(Long id);
	
	 ICustoOperacional saveOrMerge(ICustoOperacional custo);

	void remover(ICustoOperacional custo);

	List<CustoOperacional> getByPropostaId(Long idProposta);

}