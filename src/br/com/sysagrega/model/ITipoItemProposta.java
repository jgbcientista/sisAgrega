package br.com.sysagrega.model;

import java.io.Serializable;

public interface ITipoItemProposta extends Serializable{

	Long getId();

	void setId(Long id);

	String getDescricao();

	void setDescricao(String descricao);
	
	String getTitulo();

	void setTitulo(String descricao);

	void setProposta(IProposta proposta);

	IProposta getProposta();

	Long getTipoProposta();

	void setTipoProposta(Long tipoProposta);

	boolean isAtivo();

	void setAtivo(boolean ativo);

}