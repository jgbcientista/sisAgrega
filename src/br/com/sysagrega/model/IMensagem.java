package br.com.sysagrega.model;

import java.io.Serializable;
import java.util.Date;

import br.com.sysagrega.model.imp.Profissional;
import br.com.sysagrega.model.imp.Projeto;

public interface IMensagem extends Serializable{

	/**
	 * @return the id
	 */
	Long getId();

	/**
	 * @param id the id to set
	 */
	void setId(Long id);

	String getTitulo();

	void setTitulo(String titulo);

	String getDescricao();

	void setDescricao(String descricao);

	void setLida(boolean lida);

	boolean isLida();

	void setDataInclusao(Date dataInclusao);

	Date getDataInclusao();

	void setTipo(Long tipo);

	Long getTipo();

	Projeto getProjeto();

	void setProjeto(Projeto projeto);

	Profissional getProfissional();

	void setProfissional(Profissional profissional);

	Date getDataAlteracao();

	void setDataAlteracao(Date dataAlteracao);

	Long getContador();

	void setContador(Long contador);

}