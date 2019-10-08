package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.IRetificacao;
import br.com.sysagrega.model.imp.Retificacao;

public interface IRetificacaoRepository extends Serializable{

	List<Retificacao> getAll();

	Retificacao getById(Long id);

	List<Retificacao> getByProjeto(Long idProjeto);

	IRetificacao salvar(IRetificacao item);

	void remover(IRetificacao item);


	
}