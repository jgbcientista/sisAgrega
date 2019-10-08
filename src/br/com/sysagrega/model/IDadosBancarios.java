package br.com.sysagrega.model;

import java.io.Serializable;

public interface IDadosBancarios extends Serializable{

	String getNrAgencia();

	void setNrAgencia(String nrAgencia);

	String getNrConta();

	void setNrConta(String nrConta);

	String getTipoConta();

	void setTipoConta(String tipoConta);

	void setIdBanco(IBanco idBanco);

	IBanco getIdBanco();

	void setId(Long id);

	Long getId();

}