package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import br.com.sysagrega.model.ICustoDeslocamento;
import br.com.sysagrega.model.imp.CustoDeslocamento;

public interface ICustoDeslocamentoRepository extends Serializable{

	Set<CustoDeslocamento> getAll();

	CustoDeslocamento getById(Long id);
	
	ICustoDeslocamento saveOrMerge(ICustoDeslocamento custo);

	void remover(ICustoDeslocamento custo);

	List<CustoDeslocamento> getByPropostaId(Long idProposta);

}