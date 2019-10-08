package br.com.sysagrega.service;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.imp.Situacao;

public interface ISituacaoService extends Serializable{

	List<Situacao> getAll();

	Situacao getById(Long id);

}