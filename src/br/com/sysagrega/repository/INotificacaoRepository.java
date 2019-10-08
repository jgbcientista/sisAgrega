package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.INotificacao;
import br.com.sysagrega.model.imp.Notificacao;

public interface INotificacaoRepository extends Serializable{

	List<Notificacao> getAll();

	Notificacao getById(Long id);
	
	void remover(INotificacao item);

	INotificacao salvar(INotificacao item);
	
	 
	
}