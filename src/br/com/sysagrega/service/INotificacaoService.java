package br.com.sysagrega.service;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.INotificacao;
import br.com.sysagrega.model.imp.Notificacao;

public interface INotificacaoService extends Serializable{

	List<Notificacao> getAll();

	Notificacao getById(Long id);

	void remover(INotificacao item);

	INotificacao atualizar(INotificacao item);

	INotificacao salvar(INotificacao item);

}