package br.com.sysagrega.model;

import java.io.Serializable;

public interface IStatus extends Serializable{

	Long getId();

	void setId(Long id);
	
	String getNome();

	void setNome(String nome);

	void setTipo(Integer tipo);

	Integer getTipo();
 
}