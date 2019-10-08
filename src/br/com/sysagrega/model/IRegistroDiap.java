package br.com.sysagrega.model;

import java.io.Serializable;
import java.util.Date;


public interface IRegistroDiap extends Serializable{

	Long getId();

	void setId(Long id);

	Long getNumeroProcesso();

	void setNumeroProcesso(Long numeroProcesso);

	String getDataEntrada();

	void setDataEntrada(String dataEntrada);

	Boolean getGerouDAE();

	void setGerouDAE(Boolean gerouDAE);

	String getMotivo();

	void setMotivo(String motivo);

	Double getValorDAE();

	void setValorDAE(Double valorDAE);

	Date getDataPagamento();

	void setDataPagamento(Date dataPagamento);

	Date getDataPagtoCoelba();

	void setDataPagtoCoelba(Date dataPagtoCoelba);

	IProjeto getProjeto();

	void setProjeto(IProjeto projeto);

	

}