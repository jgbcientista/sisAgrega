package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import br.com.sysagrega.model.ICustoBdiComissao;
import br.com.sysagrega.model.imp.CustoBdiComissao;

public interface ICustoBDIComissaoRepository extends Serializable{

	Set<CustoBdiComissao> getAll();

	CustoBdiComissao getById(Long id);
	
	ICustoBdiComissao saveOrMerge(ICustoBdiComissao custo);

	void remover(ICustoBdiComissao custo);

	List<CustoBdiComissao> getByPropostaId(Long idProposta);

}