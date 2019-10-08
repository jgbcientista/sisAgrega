package br.com.sysagrega.model;

import java.io.Serializable;
import java.util.Date;


public interface ICoordenada extends Serializable{

	Long getId();

	void setId(Long id);

	Long getCoordenadaX();

	void setCoordenadaX(Long coordenadaX);

	Long getCoordenadaY();

	void setCoordenadaY(Long coordenadaY);

	Long getProjeto();

	void setProjeto(Long projeto);

	Date getDataCadastro();

	void setDataCadastro(Date dataCadastro);

	String getPonto();

	void setPonto(String ponto);

	Long getFuso();

	void setFuso(Long fuso);




	
}