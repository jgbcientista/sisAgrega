package br.com.sysagrega.model;

import java.io.Serializable;

public interface IArea extends Serializable{

	Long getId();

	void setId(Long id);

	String getDescricao();

	void setDescricao(String descricao);

	void setAtivo(boolean ativo);

	boolean isAtivo();

}