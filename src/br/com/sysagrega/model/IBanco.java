package br.com.sysagrega.model;

import java.io.Serializable;

public interface IBanco extends Serializable {

	/**
	 * @return the id
	 */
	Long getId();

	/**
	 * @param id the id to set
	 */
	void setId(Long id);

	/**
	 * @return the codigo
	 */
	Long getCodigo();

	/**
	 * @param codigo the codigo to set
	 */
	void setCodigo(Long codigo);

	/**
	 * @return the nome
	 */
	String getNome();

	/**
	 * @param nome the nome to set
	 */
	void setNome(String nome);

}