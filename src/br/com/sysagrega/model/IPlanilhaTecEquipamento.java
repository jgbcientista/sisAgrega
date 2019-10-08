package br.com.sysagrega.model;

import java.io.Serializable;

import br.com.sysagrega.model.imp.Proposta;

public interface IPlanilhaTecEquipamento extends Serializable{

	Long getId();

	void setId(Long id);

	IProposta getProposta();

	void setProposta(Proposta proposta);

	String getDescricao();

	void setDescricao(String descricao);

	Integer getEquipeAdm();

	void setEquipeAdm(Integer equipeAdm);

	Integer getEquipeCampo();

	void setEquipeCampo(Integer equipeCampo);

	Boolean getItem();

	void setItem(Boolean item);

	

}