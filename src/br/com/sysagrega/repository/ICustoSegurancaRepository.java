package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import br.com.sysagrega.model.ICustoSeguranca;
import br.com.sysagrega.model.imp.CustoSeguranca;

public interface ICustoSegurancaRepository extends Serializable{

	Set<CustoSeguranca> getAll();

	CustoSeguranca getById(Long id);
	
	ICustoSeguranca saveOrMerge(ICustoSeguranca custo);

	void remover(ICustoSeguranca custo);
	
	public  List<CustoSeguranca> getByPropostaId(Long idProposta);

}