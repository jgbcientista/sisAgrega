package br.com.sysagrega.service;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.imp.Notificacao;
import br.com.sysagrega.model.imp.TipoNotificacao;

public interface ITipoNotificacaoService extends Serializable{

	List<TipoNotificacao> getAll();

	TipoNotificacao getById(Long id);




}