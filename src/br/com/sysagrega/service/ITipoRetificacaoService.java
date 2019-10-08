package br.com.sysagrega.service;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.imp.TipoRetificacao;

public interface ITipoRetificacaoService extends Serializable{

	List<TipoRetificacao> getAll();

	TipoRetificacao getById(Long id);




}