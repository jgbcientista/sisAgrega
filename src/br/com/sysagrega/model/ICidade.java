package br.com.sysagrega.model;

import java.io.Serializable;

public interface ICidade extends Serializable {

	Long getId();

	void setId(Long id);

	String getNome();

	void setNome(String nome);

	IEstado getEstado();

	void setEstado(IEstado estado);

	IRegiao getReigao();

	void setReigao(IRegiao reigao);

}