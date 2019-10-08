package br.com.sysagrega.model;

import java.io.Serializable;

public interface IEndereco extends Serializable {

	String getCep();

	void setCep(String cep);

	String getBairro();

	void setBairro(String bairro);

	String getRua();

	void setRua(String rua);

	String getInfoComplementar();

	void setInfoComplementar(String infoComplementar);

	Long getId();

	void setId(Long id);

	void setCidade(ICidade cidade);

	ICidade getCidade();

	Long getNumeroRua();

	void setNumeroRua(Long numeroRua);

	IEstado getEstado();

	void setEstado(IEstado estado);
}