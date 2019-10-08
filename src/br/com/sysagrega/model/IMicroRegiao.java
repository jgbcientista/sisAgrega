package br.com.sysagrega.model;

import java.io.Serializable;

public interface IMicroRegiao extends Serializable{

	void setEstado(IEstado estado);

	IEstado getEstado();

	void setMacroRegiao(IMacroRegiao macroRegiao);

	IMacroRegiao getMacroRegiao();

	void setId(Long id);

	Long getId();

	String getNome();

	void setNome(String nome);

	

}