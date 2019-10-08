package br.com.sysagrega.model;

import java.io.Serializable;

public interface IGeradorProposta extends Serializable{

	Long getId();

	void setId(Long id);

	void setNumero(Integer numero);

	Integer getNumero();

	Integer getAno();

	void setAno(Integer ano);

	


	

}