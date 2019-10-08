package br.com.sysagrega.model;

import java.io.Serializable;

public interface ITipoDespesa extends Serializable{

	Long getId();

	void setId(Long id);

	String getDescricao();

	void setDescricao(String descricao);

 

}