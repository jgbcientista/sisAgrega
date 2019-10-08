package br.com.sysagrega.model;

import java.io.Serializable;

public interface IAnexo extends Serializable {

	String getDescricao();
	
	void setDescricao(String descricao);
	
	Long getTipo();
	
	void setTipo(Long tipo);

	Long getIdEntidade();

	void setIdEntidade(Long idEntidade);

	Long getId();

	void setId(Long id);


}
