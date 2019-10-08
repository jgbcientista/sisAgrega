package br.com.sysagrega.model;

import java.io.Serializable;
import java.util.Set;

import br.com.sysagrega.model.imp.Parcela;

public interface IItensControleContrato extends Serializable {

	/**
	 * @return the id
	 */
	Long getId();

	/**
	 * @param id the id to set
	 */
	void setId(Long id);

	/**
	 * @return the nrEstudo
	 */
	String getNrEstudo();

	/**
	 * @param nrEstudo the nrEstudo to set
	 */
	void setNrEstudo(String nrEstudo);

	/**
	 * @return the nomeProjeto
	 */
	String getNomeProjeto();

	/**
	 * @param nomeProjeto the nomeProjeto to set
	 */
	void setNomeProjeto(String nomeProjeto);

	/**
	 * @return the municipio
	 */
	String getMunicipio();

	/**
	 * @param municipio the municipio to set
	 */
	void setMunicipio(String municipio);

	/**
	 * @return the epi
	 */
	String getEpi();

	/**
	 * @param epi the epi to set
	 */
	void setEpi(String epi);

	/**
	 * @return the os
	 */
	String getOs();

	/**
	 * @param os the os to set
	 */
	void setOs(String os);

	/**
	 * @return the parcelas
	 */
	Set<Parcela> getParcelas();

	/**
	 * @param parcelas the parcelas to set
	 */
	void setParcelas(Set<Parcela> parcelas);

	void setObservacoes(String observacoes);

	String getObservacoes();

	void setProjeto(String projeto);

	String getProjeto();

}