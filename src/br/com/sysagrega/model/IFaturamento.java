package br.com.sysagrega.model;

import java.io.Serializable;
import java.util.Date;

import br.com.sysagrega.model.imp.Profissional;
import br.com.sysagrega.model.imp.Situacao;


public interface IFaturamento extends Serializable{

	Long getId();

	void setId(Long id);

	Long getNumeroNF();

	void setNumeroNF(Long numeroNF);

	void setDataNF(Date dataNF);

	Date getDataNF();

	void setProjeto(IProjeto projeto);

	IProjeto getProjeto();

	Profissional getResponsavel();

	void setResponsavel(Profissional responsavel);

	void setParcela(Long parcela);

	Long getParcela();

	Double getValorParcela();

	void setValorParcela(Double valorParcela);

	Situacao getSituacao();

	void setSituacao(Situacao situacao);

	void setContrato(IContrato contrato);

	IContrato getContrato();

	
	
}