package br.com.sysagrega.repository;

import java.io.Serializable;

import br.com.sysagrega.model.IGeradorProposta;
import br.com.sysagrega.model.imp.GeradorProposta;

public interface IGeradorPropostaRepository extends Serializable{

	IGeradorProposta getGeradorPropostaById(Long id);

	void remover(IGeradorProposta geradorProposta);

	IGeradorProposta saveOrMerge(IGeradorProposta contato);

	GeradorProposta obterMaiorID();





}