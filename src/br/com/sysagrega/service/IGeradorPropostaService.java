package br.com.sysagrega.service;

import java.io.Serializable;

import br.com.sysagrega.model.IGeradorProposta;
import br.com.sysagrega.model.imp.GeradorProposta;

public interface IGeradorPropostaService extends Serializable{

	void salvar(IGeradorProposta geradorProposta);

	GeradorProposta obterMaiorID();

	void remover(IGeradorProposta geradorProposta);

	
}