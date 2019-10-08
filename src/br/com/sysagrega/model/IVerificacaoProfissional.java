package br.com.sysagrega.model;

import java.io.Serializable;
import java.util.Date;

public interface IVerificacaoProfissional extends Serializable{

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

	String getObservacaoPlanejamento();

	void setObservacaoPlanejamento(String observacaoPlanejamento);

	IProjeto getProjeto();

	void setProjeto(IProjeto projeto);

	void setDataEntrega(Date dataEntrega);

	Date getDataEntrega();

	void setDtFimPlanejamento(Date dtFimPlanejamento);

	Date getDtFimPlanejamento();

	void setDtInicioPlanejamento(Date dtInicioPlanejamento);

	Date getDtInicioPlanejamento();

	Date getDtInicioCampo();

	void setDtInicioCampo(Date dtInicioCampo);

	Date getDtFimCampo();

	void setDtFimCampo(Date dtFimCampo);

	
	
	
}