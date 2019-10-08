package br.com.sysagrega.model;

import java.io.Serializable;

public interface IMacroRegiao extends Serializable{

	Long getId();

	void setId(Long id);

	IEstado getEstado();

	void setEstado(IEstado estado);

	String getNome();

	void setNome(String nome);

	ICidade getCidade();

	void setCidade(ICidade cidade);

	

}