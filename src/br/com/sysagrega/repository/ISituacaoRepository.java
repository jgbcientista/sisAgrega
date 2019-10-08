package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.imp.Situacao;


public interface ISituacaoRepository extends Serializable{

	List<Situacao> getAll();

	Situacao getById(Long id);
}