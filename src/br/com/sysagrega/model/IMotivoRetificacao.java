package br.com.sysagrega.model;

import java.io.Serializable;


public interface IMotivoRetificacao extends Serializable{

	Long getId();

	void setId(Long id);

	String getMotivoRetificacao();

	void setMotivoRetificacao(String motivoRetificacao);
	
	
}