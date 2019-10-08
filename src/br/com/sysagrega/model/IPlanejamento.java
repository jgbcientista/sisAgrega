package br.com.sysagrega.model;

import java.io.Serializable;
import java.util.Date;

public interface IPlanejamento extends Serializable{

	boolean isNovo();

	boolean isExistente();

	Long getId();

	void setId(Long id);

	IProfissional getProfissionalVerificacao();

	void setProfissionalVerificacao(IProfissional profissionalVerificacao);

	IProfissional getProfissionalMapas();

	void setProfissionalMapas(IProfissional profissionalMapas);

	IProfissional getProfissionalEPI();

	void setProfissionalEPI(IProfissional profissionalEPI);

	IProfissional getProfissionalEVCTGAL();

	void setProfissionalEVCTGAL(IProfissional profissionalEVCTGAL);
	

	Date getDtEntrega();

	void setDtEntrega(Date dtEntrega);

	String getObsPlanejamento();

	void setObsPlanejamento(String obsPlanejamento);

	IProjeto getProjeto();

	void setProjeto(IProjeto projeto);

	Date getDtInicioPlanejamento();

	void setDtInicioPlanejamento(Date dtInicioPlanejamento);

	Date getDtfimPlanejamento();

	void setDtfimPlanejamento(Date dtfimPlanejamento);

	Date getDataInsercao();

	void setDataInsercao(Date dataInsercao);

	
	
}