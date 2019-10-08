package br.com.sysagrega.service;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import br.com.sysagrega.model.ICustoBdiComissao;
import br.com.sysagrega.model.imp.CustoBdiComissao;

public interface ICustoBDIComissaoService extends Serializable{

	Set<CustoBdiComissao> getAll();
	CustoBdiComissao getById(Long id);
	void salvar(ICustoBdiComissao custo);
	void excluir(ICustoBdiComissao custo);
	public List<CustoBdiComissao> getByPropostaId(Long idProposta);

}