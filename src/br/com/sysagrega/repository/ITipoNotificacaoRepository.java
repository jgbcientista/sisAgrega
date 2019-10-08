package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.imp.TipoNotificacao;

public interface ITipoNotificacaoRepository extends Serializable{

	List<TipoNotificacao> getAll();
	
	TipoNotificacao getById(Long id);
	
	
}