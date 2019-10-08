package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.imp.TipoRetificacao;

public interface ITipoRetificacaoRepository extends Serializable{

	List<TipoRetificacao> getAll();
	
	TipoRetificacao getById(Long id);
	
	
}