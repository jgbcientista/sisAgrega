package br.com.sysagrega.service;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.IRetificacao;
import br.com.sysagrega.model.imp.Retificacao;

public interface IRetificacaoService extends Serializable{

	List<Retificacao> getAll();

	Retificacao getById(Long id);

	List<Retificacao> getByProjeto(Long idProjeto);

	IRetificacao salvar(IRetificacao item);

	public void remover(IRetificacao item);
	

}