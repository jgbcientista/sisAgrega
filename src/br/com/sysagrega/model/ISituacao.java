package br.com.sysagrega.model;

import java.io.Serializable;

public interface ISituacao extends Serializable{

	Long getId();

	void setId(Long id);
	
	String getNome();

	void setNome(String nome);
 
}