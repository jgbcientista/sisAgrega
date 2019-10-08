package br.com.sysagrega.model;

import java.io.Serializable;

import br.com.sysagrega.model.imp.Proposta;

public interface ICustoPlanilhaTecnica extends Serializable{

	String getDescricao();

	void setDescricao(String descricao);

	Long getId();

	void setId(Long id);

	IProposta getProposta();

	void setProposta(Proposta proposta);

	void setUnidade(Integer unidade);

	Integer getUnidade();

	Long getQuantitativo();

	void setQuantitativo(Long quantitativo);


	 

}