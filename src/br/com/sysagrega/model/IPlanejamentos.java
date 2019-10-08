package br.com.sysagrega.model;

import java.io.Serializable;
import java.util.Date;

import br.com.sysagrega.model.imp.Usuario;

public interface IPlanejamentos extends Serializable{

	boolean isNovo();

	boolean isExistente();

	Long getId();

	void setId(Long id);

	void setDtAlteracaoPlanej(Date dtAlteracaoPlanej);

	Date getDtAlteracaoPlanej();

	void setNome(String nome);

	String getNome();

	void setNrRevisao(Integer nrRevisao);

	Integer getNrRevisao();

	Date getDtCriacaoPlanej();

	void setDtCriacaoPlanej(Date dtCriacaoPlanej);

	Integer getNrPlan();

	void setNrPlan(Integer nrPlan);

	void setUsuarioAtualizacao(Usuario usuarioAtualizacao);

	Usuario getUsuarioAtualizacao();

	String getTitulo();

	void setTitulo(String titulo);
	
	
}