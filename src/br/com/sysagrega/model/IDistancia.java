package br.com.sysagrega.model;

import java.io.Serializable;


public interface IDistancia extends Serializable{

	Long getId();

	void setId(Long id);

	void setAtivo(boolean ativo);

	boolean isAtivo();

	void setProjeto(IProjeto projeto);

	IProjeto getProjeto();

	void setPavimentada(Boolean pavimentada);

	void setDistancia(Double distancia);

	Double getDistancia();
	
	void setTpUnidadeMedida(String tpUnidadeMedida);

	String getTpUnidadeMedida();

}